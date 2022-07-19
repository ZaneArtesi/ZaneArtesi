package major_project.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import major_project.model.AppEngine;
import major_project.view.HttpMediator.HttpHelper;
import major_project.view.HttpMediator.Offline;
import major_project.view.HttpMediator.Online;
import major_project.view.ObserverClasses.PopOutDarkMode;
import major_project.view.ObserverClasses.SearchResult;
import major_project.view.ObserverClasses.TableViewClasses.TableViewExample;

import java.util.ArrayList;
import java.util.HashMap;


public class AppWindow {
    private final AppEngine model;
    private final Scene scene;
    private final HttpHelper httpHelper;
    private final MusicHandler musicHandler;

    private ScrollPane leftSideButtons;
    private HBox topSearch;
    private ScrollPane rightSideButtons;
    private HBox bottomButtons;
    private VBox middleButtons;
    private HBox popOutTop;
    private VBox popOutMid;
    private ScrollPane popOutBottom;
    private VBox popOutRight;
    private ComboBox<String> measureOptionsBox;
    private TextField valueFieldQuantity;
    private TextField notesField;
    private SearchResult sr;
    private String lastSearchedFood;
    private MediaPlayer mediaPlayer;
    private boolean musicPlaying;
    private final DarkMode darkMode;
    private boolean isDark = false;
    private PopOutDarkMode popOutDarkMode;
    private TableViewExample popOutTableView;

    private String prevResult;
    private String prevFoodId;
    private String prevQuantity;
    private String prevNotes;

    public AppWindow(AppEngine model) {
        this.model = model;
        BorderPane pane = new BorderPane();
        this.scene = new Scene(pane, 1000, 700);

        if (model.getInputDummy()) {
            httpHelper = new Offline(model, this);
        } else {
            httpHelper = new Online(model, this);
        }

        darkMode = new DarkMode();
        BuilderHome builderHome = new BuilderHome(this);

        // call builds
        builderHome.buildTopSearch();
        builderHome.buildLeftSide();
        builderHome.buildRightSide();
        builderHome.buildBottom();
        builderHome.buildMiddle();

        pane.setTop(topSearch);
        pane.setLeft(leftSideButtons);
        pane.setRight(rightSideButtons);
        pane.setBottom(bottomButtons);
        pane.setCenter(middleButtons);
        musicPlaying = true;

        // set in dark mode
        darkMode.setBottomButtons(bottomButtons);
        darkMode.setTopSearch(topSearch);
        darkMode.setMiddleButtons(middleButtons);
        darkMode.setLeftSideButtons(leftSideButtons);
        darkMode.setRightSideButtons(rightSideButtons);

        pane.setVisible(true);

        // play music
        this.musicHandler = new MusicHandler(this);
        this.musicHandler.playMusic();
    }

    protected void toggleMusic() {
        this.musicHandler.toggleMusic();
    }

    protected void submitSearch(String input) {
        getHttpHelper().submitSearchParse(input);
    }


    /**
     * SelectItem uses selected food item as input and then generates a the popout screen based on this info
     * It also retrives the foodID that relates to the chosen food
     * @param selectedItem i.e. what was choosen in the ComboBox from the list of food options
     */
    public void selectItem(String selectedItem) {
        // search results stored as string in the order label&&&foodId&&&measures===label2&&&foodId2......
        // grouped foods are separated by a &&& and a new food starts after a ===
        lastSearchedFood = selectedItem;
        String foodId = model.getSearchResult().get("response").split(selectedItem)[1].
                split("&&&")[1].
                split("===")[0];
        generatePopOut(selectedItem, foodId);
    }

    /**
     * generatePopOut will create and display the popOut window where users can choose to select measurement type
     * and amount of a specific food, then be shown the updated nutritional information
     * @param title the selected food name
     * @param foodId the corresponding foodID for the food title
     */
    private void generatePopOut(String title, String foodId) {
        ArrayList<String> params = new ArrayList<>();
        params.add(foodId);
        model.HTTPrequest( "parse", params, true, false);
        String result = model.getSearchResult().get("response");

        BorderPane newPane = new BorderPane();
        Scene s = new Scene(newPane, 600, 600);
        Stage nw = new Stage();
        nw.setTitle(title);
        nw.setResizable(false);
        nw.setScene(s);

        this.popOutDarkMode = new PopOutDarkMode();

        popOutTop(result);
        popOutMid(result, foodId);
        popOutBottom();
        setPopOutRight(nw);

        newPane.setTop(popOutTop);
        newPane.setCenter(popOutMid);
        newPane.setBottom(popOutBottom);
        newPane.setRight(popOutRight);

        popOutDarkMode.setPopOutTop(this.popOutTop);
        popOutDarkMode.setPopOutMid(this.popOutMid);
        popOutDarkMode.setPopOutBottom(this.popOutBottom);
        popOutDarkMode.setPopOutRight(this.popOutRight);

        if (this.isDark) {
            this.popOutDarkMode.turnPopOutDark();
        }

        nw.showAndWait();
    }

    /**
     * Builds the top portion of the Pop Out screen - this displays base nutritional information
     * @param result The result from the FoodAPI search from the specific food chosen
     */
    private void popOutTop(String result) {
        String[] split = result.split("=========")[1].split("&&&");

        Label baseNutrients = new Label();

        this.popOutDarkMode.addLabel(baseNutrients);

        String text = "BASE NUTRITION INFO:  ";
        text += "ENERC_KCAL: " + split[0] + " | ";
        text += "PROCNT: " + split[1] + " | ";
        text += "FAT: " + split[2] + " | ";
        text += "CHOCDF: " + split[3] + " | ";
        text += "FIBTG: " + split[4] + " | ";

        baseNutrients.setText(text);

        this.popOutTop = new HBox(baseNutrients);
        popOutTop.setAlignment(Pos.TOP_CENTER);
        popOutTop.setPadding(new Insets(5, 5, 5, 5));
    }

    /**
     * Builds the middle of the pop out, this is where the user can select the type of merasurement and amount
     * @param result the search result from the FoodAPI
     * @param foodId the foodID that relates to the chosen food result
     */
    private void popOutMid(String result, String foodId) {
        String[] split = result.split("=========")[0].split("===");
        this.measureOptionsBox = new ComboBox<>();
        this.valueFieldQuantity = new TextField();
        this.notesField = new TextField();
        this.valueFieldQuantity.setPromptText("Enter Quantity");
        this.notesField.setPromptText("Add Notes");
        valueFieldQuantity.setMaxWidth(100);
        notesField.setMaxWidth(200);
        Button submitButton = new Button("Submit");

        this.popOutDarkMode.addComboBox(this.measureOptionsBox);
        this.popOutDarkMode.addTextField(this.valueFieldQuantity);
        this.popOutDarkMode.addButton(submitButton);
        this.popOutDarkMode.addTextField(this.notesField);

        for (String s : split) {
            measureOptionsBox.getItems().add(s.split("&&&")[0]);
        }
        measureOptionsBox.getSelectionModel().selectFirst();


        submitButton.setOnAction((event -> {
            String quantity = valueFieldQuantity.getText();
            try {
                Double.parseDouble(quantity);
            } catch (NumberFormatException nfe) {
                return;
                // alert box please enter valid number
            }

            this.prevResult = result;
            this.prevFoodId = foodId;
            this.prevQuantity = quantity;
            this.prevNotes = notesField.getText();
            getHttpHelper().submitNutrients();
        }));

        this.popOutMid = new VBox(measureOptionsBox, valueFieldQuantity, notesField,submitButton);
        popOutMid.setSpacing(5);
        popOutMid.setAlignment(Pos.CENTER);
        popOutMid.setPadding(new Insets(5, 5, 5, 5));
    }

    /**
     * Triggers when users uses the submit button - will reach out to the FoodAPI to get the nutritional information
     * @param quantity the amount of the specified food
     * @param measureURI the measureURI that relates to the chosen food measurement
     * @param foodId the foodID that relates to the chosen food
     */
    public void submitNutrientsSearch(String quantity, String measureURI, String foodId) {
        this.popOutTableView.getTableView().getItems().clear();
        ArrayList<String> input = new ArrayList<>();
        input.add(quantity);
        input.add(measureURI);
        input.add(foodId);

        int res = model.HTTPrequest( "getNutrients", input, false, false);
        if (res == 1) {
            cacheHit();
        }

    }

    /**
     * Generates the Cache Hit window if a cache hit has been detected
     * This displays two buttons - to either use or reject the cache
     * If user wishes to use cache then it will be generated internally
     * If the user wishes to reject the cache an API call will be made as usual
     */
    public void cacheHit() {
        Pane cacheHitPane = new StackPane();
        Scene cacheScene = new Scene(cacheHitPane, 400, 200);
        Stage newWindow = new Stage();
        newWindow.setTitle("Cache Hit");
        newWindow.setScene(cacheScene);

        Button useCacheButton = new Button("Use Cache");
        Button rejectCacheButton = new Button("Reject Cache");

        if (isDark) {
            DarkMode.turnButtonDark(useCacheButton);
            DarkMode.turnButtonDark(rejectCacheButton);
            cacheHitPane.setBackground(Background.fill(Color.BLACK));
        }

        cacheHitPane.getChildren().add(useCacheButton);
        cacheHitPane.getChildren().add(rejectCacheButton);
        rejectCacheButton.setTranslateY(useCacheButton.getTranslateY() + 50);

        useCacheButton.setOnAction((event) -> {
            model.useCache();
            newWindow.close();
        });

        rejectCacheButton.setOnAction((event2) -> {
            model.rejectCache();
            newWindow.close();
        });

        newWindow.show();
    }

    /**
     * Generates the bottom for the pop out screen
     * This is a table with the nutritional info that is updated once a search is submited
     */
    private void popOutBottom() {
        this.popOutTableView = new TableViewExample(this.model);
        popOutTableView.setIsPopout();

        this.popOutBottom = new ScrollPane(popOutTableView.getTableView());
        popOutBottom.setMaxHeight(400);
        popOutBottom.setFitToHeight(true);
        popOutBottom.setFitToWidth(true);

        popOutDarkMode.addTableView(popOutTableView.getTableView());
    }

    /**
     * Generates the right side buttons
     * Add item will add the item into the system
     * Cancel aborts
     * @param s the stage of the pop out so that we can close once done
     */
    private void setPopOutRight(Stage s) {
        Button submitButton = new Button("Add Item");
        Button cancelButton = new Button("Cancel");

        this.popOutDarkMode.addButton(submitButton);
        this.popOutDarkMode.addButton(cancelButton);

        submitButton.setOnAction((event) -> {
            String label = this.lastSearchedFood;
            String measure = this.measureOptionsBox.getValue();
            String quantity = this.valueFieldQuantity.getText();
            String notes = this.prevNotes;

            try {
                Integer.parseInt(quantity);
            } catch (Exception e) {
                return;
            }
            // This is trivial as it would be more effort to break this into HttpHelper than to leave this
            // i.e. requireing more global variables and setters + getters
            if (model.getInputDummy()) {
                measure = "Whole";
                quantity = "1";
            }

            model.confimAddFood(label, measure, quantity, notes);
            s.close();
            sr.getSearchResultsCombo().getItems().clear();
            this.sr.getSearchResultsCombo().setPrefWidth(150);

        });

        cancelButton.setOnAction((event -> {
            this.sr.getSearchResultsCombo().setPrefWidth(150);
            s.close();
        }));

        this.popOutRight = new VBox(submitButton, cancelButton);
        popOutRight.setAlignment(Pos.CENTER_RIGHT);
        popOutRight.setSpacing(5);
        popOutRight.setPadding(new Insets(5, 5, 5, 5));
    }

    /**
     * Responsible for generating the stacked bar chart
     */
    protected void createGraph() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
        xAxis.setLabel("Nutrient");
        yAxis.setLabel("% In comparison to Max");
        barChart.setTitle("Max Stacked Bar Chart");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Current Running Total");
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Max Amount");

        if (this.isDark) {
            barChart.setBackground(Background.fill(Color.BLACK));
            xAxis.setTickLabelFill(Color.WHITE);
            yAxis.setTickLabelFill(Color.WHITE);
            xAxis.setBackground(Background.fill(Color.GREY));
            yAxis.setBackground(Background.fill(Color.GREY));
            barChart.setBackground(Background.fill(Color.GREY));
        }

        HashMap<String, ArrayList<Integer>> result = model.generateGraph();

        for (String s : result.keySet()) {
            if (result.get(s).size() == 1) {
                series1.getData().add(new XYChart.Data<>(s, result.get(s).get(0)));
                continue;
            }
            series1.getData().add(new XYChart.Data<>(s, result.get(s).get(0)));
            series2.getData().add(new XYChart.Data<>(s, result.get(s).get(1)));
        }
        barChart.getData().add(series1);
        barChart.getData().add(series2);

        Scene s = new Scene(barChart, 600, 600);
        Stage nw = new Stage();
        nw.setTitle("Graph");
        nw.setResizable(false);
        nw.setScene(s);
        nw.show();
    }

    /**
     * getRecipe will open a new window and display a list of items, measures and notes
     */
    public void showRecipe() {
        ScrollPane newPane = new ScrollPane();
        Scene s = new Scene(newPane, 600, 300);
        Stage nw = new Stage();
        nw.initModality(Modality.APPLICATION_MODAL);
        nw.setTitle("Full Recipe");
        nw.setResizable(false);
        nw.setScene(s);
        newPane.setContent(createRecipeList());
        newPane.setFitToWidth(true);
        newPane.setFitToHeight(true);
        newPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        nw.show();
    }

    /**
     * @return ListView<String>, with all the recipe ingreients + notes added on each line
     */
    private ListView<String> createRecipeList() {
        ListView<String> list = new ListView<>();
        if (model.getTotalAddedFood().size() == 0) {
            list.getItems().add("There are currently no items added in this recipe.");
        } else {
            for (String s : model.getTotalAddedFood()) {
                String[] splitString = s.split(">>");
                String toAdd = splitString[0] + ", " + splitString[1] + ", " + splitString[2] + ": " + splitString[3] + ";";
                list.getItems().add(toAdd);
            }
        }
        if (isDark) {
            list.setStyle("-fx-control-inner-background: black");
        }

        return list;
    }

    public Scene getScene() {
        return this.scene;
    }

    public AppEngine getModel() {
        return model;
    }

    public HBox getTopSearch() {
        return this.topSearch;
    }

    public ScrollPane getLeftSideButtons() {
        return this.leftSideButtons;
    }

    public void setRightSideButtons(ScrollPane rightSideButtons) {
        this.rightSideButtons = rightSideButtons;
    }

    public ScrollPane getRightSideButtons() {
        return this.rightSideButtons;
    }

    public void setLeftSideButtons(ScrollPane leftSideButtons) {
        this.leftSideButtons = leftSideButtons;
    }

    public HBox getBottomButtons() {
        return this.bottomButtons;
    }

    public void setBottomButtons(HBox bottomButtons) {
        this.bottomButtons = bottomButtons;
    }

    public VBox getMiddleButtons() {
        return middleButtons;
    }

    public void setMiddleButtons(VBox middleButtons) {
        this.middleButtons = middleButtons;
    }

    public HBox getPopOutTop() {
        return popOutTop;
    }

    public VBox getPopOutMid() {
        return popOutMid;
    }

    public ScrollPane getPopOutBottom() {
        return popOutBottom;
    }

    public VBox getPopOutRight() {
        return popOutRight;
    }

    public ComboBox<String> getMeasureOptionsBox() {
        return measureOptionsBox;
    }

    public TextField getValueFieldQuantity() {
        return valueFieldQuantity;
    }

    public SearchResult getSr() {
        return sr;
    }
    public void setSr(SearchResult sr) {
        this.sr = sr;
    }

    public String getLastSearchedFood() {
        return lastSearchedFood;
    }

    public void setTopSearch(HBox topSearch) {
        this.topSearch = topSearch;
    }

    public DarkMode getDarkMode() {
        return this.darkMode;
    }

    public void setIsDark(boolean b) {
        this.isDark = b;
    }

    public boolean getisDark() {
        return this.isDark;
    }

    public HttpHelper getHttpHelper() {
        return httpHelper;
    }

    public String getPrevResult() {
        return this.prevResult;
    }

    public String getPrevFoodId() {
        return prevFoodId;
    }

    public String getPrevQuantity() {
        return prevQuantity;
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mp) {
        this.mediaPlayer = mp;
    }

    public boolean getMusicPlaying() {
        return this.musicPlaying;
    }

    public void setMusicPlaying(boolean b) {
        this.musicPlaying = b;
    }
}
