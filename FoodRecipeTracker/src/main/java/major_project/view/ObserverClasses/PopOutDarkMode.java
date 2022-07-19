package major_project.view.ObserverClasses;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * Responsible for setting the pop out to be dark if required
 */
public class PopOutDarkMode {

    private ArrayList<Button> buttonsList;
    private ArrayList<ComboBox> comboboxList;
    private ArrayList<Label> labelList;
    private ArrayList<TextField> textFieldList;
    private ArrayList<TableView> tableviewList;

    private  HBox popOutTop;
    private  VBox popOutMid;
    private  ScrollPane popOutBottom;
    private  VBox popOutRight;

    public PopOutDarkMode() {
        this.buttonsList = new ArrayList<>();
        this.comboboxList = new ArrayList<>();
        this.labelList = new ArrayList<>();
        this.textFieldList = new ArrayList<>();
        this.tableviewList = new ArrayList<>();

    }

    /**
     * Turns all elements dark
     */
    public void turnPopOutDark() {
        System.out.println("GOING DARK");
        popOutBottom.setBackground(Background.fill(Color.BLACK));
        popOutMid.setBackground(Background.fill(Color.BLACK));
        popOutRight.setBackground(Background.fill(Color.BLACK));
        popOutTop.setBackground(Background.fill(Color.BLACK));

        for (Button b : this.buttonsList) {
            b.setStyle("-fx-background-color: grey");
            b.setTextFill(Color.WHITE);
        }

        String css = this.getClass().getResource("/CSS/DarkStyleComboBox.css").toExternalForm();
        for (ComboBox c : this.comboboxList) {
            c.setStyle("-fx-background-color: grey");
            c.getStylesheets().add(css);
        }

        css = this.getClass().getResource("/CSS/DarkStyle.css").toExternalForm();
        for (TableView tv : tableviewList) {
            String s = "-fx-background-color: grey;";
            tv.setStyle(s);
            tv.getStylesheets().add(css);
        }
        for (Label l : this.labelList) {
            l.setStyle("-fx-background-color: grey");
            l.setTextFill(Color.WHITE);
        }



        for (TextField tf : textFieldList) {
            tf.setStyle(null);
            tf.setStyle("-fx-background-color: grey; -fx-text-fill: white");

        }
    }


    public void setPopOutTop(HBox popOutTop) {
        this.popOutTop = popOutTop;
    }

    public void setPopOutMid(VBox popOutMid) {
        this.popOutMid = popOutMid;
    }

    public void setPopOutBottom(ScrollPane popOutBottom) {
        this.popOutBottom = popOutBottom;
    }

    public void setPopOutRight(VBox popOutRight) {
        this.popOutRight = popOutRight;
    }

    public void addButton(Button b) {
        this.buttonsList.add(b);
    }

    public void addComboBox(ComboBox c) {
        this.comboboxList.add(c);
    }

    public void addLabel(Label l) {
        this.labelList.add(l);
    }

    public void addTextField(TextField t) {
        this.textFieldList.add(t);
    }

    public void addTableView(TableView t) { this.tableviewList.add(t);}
}