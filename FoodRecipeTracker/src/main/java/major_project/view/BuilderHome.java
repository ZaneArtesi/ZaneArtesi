package major_project.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import major_project.view.ObserverClasses.SearchResult;
import major_project.view.ObserverClasses.TableViewClasses.FoodListTable;
import major_project.view.ObserverClasses.TableViewClasses.TableViewExample;

import java.util.Objects;


/**
 * This class is responsible for building the different sections on the home screen.
 * (Top, middle, left, right, bottom)
 *
 * AppWindow will call this class which will set up all the buttons in that section,
 * this keeps the AppWindow class from being hundreds of lines long and gives this class a specific role
 */
public class BuilderHome {

    private final AppWindow view;

    public BuilderHome(AppWindow view) {
        this.view = view;
    }

    /**
     * Generates the layout and objects to be in the top bar
     * Top bar contains toggle music, lighting, submit search and clear cache
     */
    public void buildTopSearch() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setPrefWidth(250);
        Button submitButton = new Button("Submit");
        Button toggleMusic = new Button("Toggle Music");
        Button clearCache = new Button("Clear Cache");
        Button darkModeButton = new Button("Change Lighting");

        view.setTopSearch(new HBox(toggleMusic, clearCache, searchField, submitButton, darkModeButton));
        searchField.setTranslateX(clearCache.getTranslateX() + 120);
        submitButton.setTranslateX(searchField.getTranslateX() + 2);
        darkModeButton.setTranslateX(submitButton.getTranslateX() + 140);

        view.getTopSearch().setAlignment(Pos.CENTER_LEFT);
        view.getTopSearch().setSpacing(20);
        view.getTopSearch().setPadding(new Insets(25, 5, 50, 25));

        view.getDarkMode().addButtons(submitButton);
        view.getDarkMode().addButtons(toggleMusic);
        view.getDarkMode().addTextField(searchField);
        view.getDarkMode().addButtons(darkModeButton);
        view.getDarkMode().addButtons(clearCache);

        submitButton.setOnAction((event) -> this.view.submitSearch(searchField.getText()));
        toggleMusic.setOnAction((event2) -> this.view.toggleMusic());
        clearCache.setOnAction((event3) -> this.view.getModel().clearCache());
        darkModeButton.setOnAction((event4) -> this.view.setIsDark(view.getDarkMode().changeLighting(view.getisDark())));
    }

    /**
     * Builds left side table which contains total running nutritional information
     */
    public void buildLeftSide() {
        TableViewExample tv = new TableViewExample(view.getModel());

        view.setLeftSideButtons(new ScrollPane(tv.getTableView()));
        view.getLeftSideButtons().setPadding(new Insets(15,5,5,10));
        view.getLeftSideButtons().setFitToHeight(true);
        view.getLeftSideButtons().setFitToWidth(true);
        view.getLeftSideButtons().setFitToHeight(true);
        view.getDarkMode().addTableView(tv.getTableView());
    }

    /**
     * Builds Right side table with total added foods
     */
    public void buildRightSide() {
        FoodListTable foodTable = new FoodListTable(view.getModel());
        view.getDarkMode().addTableView(foodTable.getTableView());
        view.setRightSideButtons(new ScrollPane(foodTable.getTableView()));

        view.getRightSideButtons().setPadding(new Insets(5, 5, 5, 5));
        view.getRightSideButtons().setFitToWidth(true);
        view.getRightSideButtons().setFitToHeight(true);
    }

    /**
     * Builds middle that contains the selection / submission for specific food and PasteBin calls / output
     */
    public void buildMiddle() {
        view.setSr(new SearchResult(view.getModel()));

        Button submitButton = new Button("Submit");
        Label outputAPILabel = new Label("Interact with PasteBIN below:");
        Button outputAPIshort = new Button("Output - short");
        Button outputAPIlong = new Button("Output - long");
        TextField pasteOutput = new TextField();
        pasteOutput.setPromptText("Paste Output ");


        view.getDarkMode().addButtons(submitButton);
        view.getDarkMode().addButtons(outputAPIlong);
        view.getDarkMode().addButtons(outputAPIshort);
        view.getDarkMode().addComboBox(view.getSr().getSearchResultsCombo());
        view.getDarkMode().addLabel(view.getSr().getLabel());
        view.getDarkMode().addTextField(pasteOutput);
        view.getDarkMode().addLabel(outputAPILabel);

        outputAPILabel.setTranslateY(submitButton.getTranslateY() + 100);
        outputAPIlong.setTranslateY(outputAPILabel.getTranslateY() + 10);
        outputAPIshort.setTranslateY(outputAPIlong.getTranslateY() + 10);
        pasteOutput.setTranslateY(outputAPIshort.getTranslateY() + 10);
        pasteOutput.setMaxWidth(300);

        view.setMiddleButtons( new VBox(view.getSr().getLabel(), view.getSr().getSearchResultsCombo(),
                submitButton, outputAPILabel, outputAPIlong, outputAPIshort, pasteOutput));


        view.getMiddleButtons().setAlignment(Pos.TOP_CENTER);
        view.getMiddleButtons().setSpacing(5);
        view.getMiddleButtons().setPadding(new Insets(80, 5, 5, 5));

        submitButton.setOnAction((event -> {

            view.getHttpHelper().submitTopSearch();

        }));

        outputAPIshort.setOnAction((event2) -> {
            String result = view.getModel().outputAPI("short");
            pasteOutput.setText(Objects.requireNonNullElse(result, "Make sure you have added food first!"));
        });

        outputAPIlong.setOnAction((event3) -> {
            String result = view.getModel().outputAPI("long");
            pasteOutput.setText(Objects.requireNonNullElse(result, "Make sure you have added food first!"));
        });
    }

    /**
     * Creates items in the bottom section - this contains submitting max amounts for specific nutrients and
     * generating the graph
     */
    public void buildBottom() {
        Label l = new Label("Set Max Amount");
        ComboBox<String> nutrientsList = new ComboBox<>();
        TextField maxAmount = new TextField();
        maxAmount.setPromptText("Enter Amount");
        Button submitButton = new Button( "Submit the Max");
        Button generateGraph = new Button("Generate Graph");
        Button showRecipe = new Button("Shop Recipe");

        view.getDarkMode().addButtons(submitButton);
        view.getDarkMode().addButtons(generateGraph);
        view.getDarkMode().addLabel(l);
        view.getDarkMode().addComboBox(nutrientsList);
        view.getDarkMode().addTextField(maxAmount);
        view.getDarkMode().addButtons(showRecipe);

        nutrientsList.getItems().add("Calcium, Ca(mg)");
        nutrientsList.getItems().add("Carbohydrate, by difference(g)");
        nutrientsList.getItems().add("Cholesterol(mg)");
        nutrientsList.getItems().add("Energy(kcal)");
        nutrientsList.getItems().add("Fatty acids, total monounsaturated(g)");
        nutrientsList.getItems().add("Fatty acids, total polyunsaturated(g)");
        nutrientsList.getItems().add("Fatty acids, total saturated(g)");
        nutrientsList.getItems().add("Total lipid (fat)(g)");
        nutrientsList.getItems().add("trans fatty acids (g)");
        nutrientsList.getItems().add("Iron, Fe(mg)");
        nutrientsList.getItems().add("Fiber, total dietary(g)");
        nutrientsList.getItems().add("Folic acid(μg)");
        nutrientsList.getItems().add("Folate, food(μg)");
        nutrientsList.getItems().add("Folate, DFE(μg)");
        nutrientsList.getItems().add("Potassium, K(mg)");
        nutrientsList.getItems().add("Magnesium, Mg(mg)");
        nutrientsList.getItems().add("Sodium, Na(mg)");
        nutrientsList.getItems().add("Niacin(mg)");
        nutrientsList.getItems().add("Phosphorus, P(mg)");
        nutrientsList.getItems().add("Protein(g)");
        nutrientsList.getItems().add("Riboflavin(mg)");
        nutrientsList.getItems().add("Sugars, total(g)");
        nutrientsList.getItems().add("Thiamin(mg)");
        nutrientsList.getItems().add("Vitamin E (alpha-tocopherol)(mg)");
        nutrientsList.getItems().add("Vitamin A, RAE(μg)");
        nutrientsList.getItems().add("Vitamin B-12(μg)");
        nutrientsList.getItems().add("Vitamin B-6(mg)");
        nutrientsList.getItems().add("Vitamin C, total ascorbic acid(mg)");
        nutrientsList.getItems().add("Vitamin D (D2 + D3)(μg)");
        nutrientsList.getItems().add("Vitamin K (phylloquinone)(μg)");
        nutrientsList.getItems().add("Water(g)");
        nutrientsList.getItems().add("Zinc, Zn(mg)");

        nutrientsList.getSelectionModel().selectFirst();
        nutrientsList.setVisibleRowCount(3);
        nutrientsList.setMaxWidth(160);
        maxAmount.setMaxWidth(160);

        submitButton.setOnAction((event) -> view.getModel().setSetMaxes(nutrientsList.getValue(), maxAmount.getText()));
        generateGraph.setOnAction((event) -> view.createGraph());
        showRecipe.setOnAction(event2 -> {view.showRecipe();});

        view.setBottomButtons(new HBox(l, nutrientsList, maxAmount,submitButton, generateGraph, showRecipe));

        view.getBottomButtons().setAlignment(Pos.BOTTOM_CENTER);
        view.getBottomButtons().setSpacing(10);
        view.getBottomButtons().setPadding(new Insets(50, 5, 100, 5));
    }
}
