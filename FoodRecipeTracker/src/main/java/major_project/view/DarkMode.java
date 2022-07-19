package major_project.view;

import java.util.ArrayList;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * I choose to put this into the view section as I believe it relates to the Views state i.e. dark or light
 * Also moving this to the model would mean the model importing from Javafx which would ruing model view seperation
 *
 * Responsible for turing objects dark / light
 */
public class DarkMode {

    private final ArrayList<Button> buttonsList;
    private final ArrayList<ComboBox<String>> comboboxList;
    private final ArrayList<Label> labelList;
    private final ArrayList<TableView<?>> tableViewList;
    private final ArrayList<TextField> textFieldList;
    private ScrollPane leftSideButtons;
    private HBox topSearch;
    private ScrollPane rightSideButtons;
    private HBox bottomButtons;
    private VBox middleButtons;

    public DarkMode() {
        this.buttonsList = new ArrayList<>();
        this.comboboxList = new ArrayList<>();
        this.labelList = new ArrayList<>();
        this.tableViewList = new ArrayList<>();
        this.textFieldList = new ArrayList<>();
    }

    public void addButtons(Button b) {
        this.buttonsList.add(b);
    }

    public void addComboBox(ComboBox<String> c) {
        this.comboboxList.add(c);
    }

    public void addLabel(Label l) {
        this.labelList.add(l);
    }

    public void addTableView(TableView<?> tv) { this.tableViewList.add(tv);}

    public void addTextField(TextField tf) { this.textFieldList.add(tf);}

    /**
     * depending on state - will change lighting
     * @param isDark boolean if already dark
     * @return returns the new state
     */
    public boolean changeLighting(boolean isDark) {

        if (isDark) {
            turnLight();
        } else {
            turnDark();
        }
        return !isDark;
    }

    /**
     * Will make all elements dark
     */
    public void turnDark() {
        System.out.println("GOING DARK");
        topSearch.setBackground(Background.fill(Color.BLACK));
        leftSideButtons.setBackground(Background.fill(Color.BLACK));
        rightSideButtons.setBackground(Background.fill(Color.BLACK));
        bottomButtons.setBackground(Background.fill(Color.BLACK));
        middleButtons.setBackground(Background.fill(Color.BLACK));

        for (Button b : this.buttonsList) {
                b.setStyle("-fx-background-color: grey");
                b.setTextFill(Color.WHITE);
        }

        String css = this.getClass().getResource("/CSS/DarkStyleComboBox.css").toExternalForm();
        for (ComboBox<String> c : this.comboboxList) {
            c.setStyle("-fx-background-color: grey");
            c.getStylesheets().add(css);
        }

        for (Label l : this.labelList) {
//            l.setStyle("-fx-background-color: grey");
            l.setTextFill(Color.WHITE);
        }

        css = this.getClass().getResource("/CSS/DarkStyle.css").toExternalForm();

        for (TableView<?> tv : tableViewList) {
            String s = "-fx-background-color: grey;";
            tv.setStyle(s);
            tv.getStylesheets().add(css);
        }

        for (TextField tf : textFieldList) {
            tf.setStyle(null);
            tf.setStyle("-fx-background-color: grey; -fx-text-fill: white");
        }

    }

    /**
     * Will make all elements light
     */
    public void turnLight() {
        System.out.println("GOING LIGHT");
        topSearch.setBackground(null);
        leftSideButtons.setBackground(null);
        rightSideButtons.setBackground(null);
        bottomButtons.setBackground(null);
        middleButtons.setBackground(null);

        for (Button b : this.buttonsList) {
            b.setStyle(null);
            b.setTextFill(Color.BLACK);
        }

        for (ComboBox<String> c : this.comboboxList) {
            c.setStyle(null);
            c.getStylesheets().clear();
        }

        for (Label l : this.labelList) {
            l.setStyle(null);
            l.setTextFill(Color.BLACK);
        }

        for (TableView<?> tv : tableViewList) {
            tv.setStyle(null);
            tv.getStylesheets().clear();
        }

        for (TextField tf : textFieldList) {
            tf.setStyle("-fx-text-fill: black");

        }

    }


    /**
     * Static method to turn specific buttons dark
     * @param b button
     */
    public static void turnButtonDark(Button b) {
        b.setStyle("-fx-background-color: grey");
        b.setTextFill(Color.WHITE);
    }

    /**
     * Static method to turn labels dark
     * @param l label
     */
    public static void turnLabelDark(Label l) {
        l.setStyle("-fx-background-color: grey");
        l.setTextFill(Color.WHITE);
    }

    public void setLeftSideButtons(ScrollPane leftSideButtons) {
        this.leftSideButtons = leftSideButtons;
    }

    public void setTopSearch(HBox topSearch) {
        this.topSearch = topSearch;
    }

    public void setRightSideButtons(ScrollPane rightSideButtons) {
        this.rightSideButtons = rightSideButtons;
    }

    public void setBottomButtons(HBox bottomButtons) {
        this.bottomButtons = bottomButtons;
    }

    public void setMiddleButtons(VBox middleButtons) {
        this.middleButtons = middleButtons;
    }

}