package NewGameTracker.View.SceneBuilders;

import NewGameTracker.View.AppWindow;
import NewGameTracker.View.ObserverClasses.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BuildHomeScene implements SceneBuild{

    private final AppWindow view;


    public BuildHomeScene(AppWindow view) {
        this.view = view;

    }

    @Override
    public void build() {
        buildTop();
        buildLeft();
        buildMiddle();
        buildRight();
        buildBottom();
    }



    private void buildTop() {
        Label usydLabel = new Label("SURLFC: ");
        ScoreObserver usydScoreObserver = new ScoreObserver(this.view, true);
        Label opponentLabel = new Label("Opposition: ");
        ScoreObserver opponentScoreObserver = new ScoreObserver(this.view, false);

        Button clockButton = new Button("Toggle Time");
        CountDownObserver countDown = new CountDownObserver(this.view);


        VBox timeAndScore = new VBox(countDown.getCountDownLabel(), clockButton);
        timeAndScore.setAlignment(Pos.CENTER);

        HBox hbox = new HBox(usydLabel, usydScoreObserver.getLabel(), timeAndScore,
                            opponentLabel, opponentScoreObserver.getLabel());
        hbox.setSpacing(30);
        hbox.setPadding(new Insets(5,5,5,5));
        hbox.setAlignment(Pos.CENTER);

        clockButton.setOnAction(event -> this.view.getModel().toggleTime());

        view.getPane().setTop(hbox);
    }

    private void buildLeft() {
        Label leftHeaderLabel = new Label("Team Stats");

        Label completedSetLabel = new Label(" Completed Sets: ");
        SetsObserver usydCompletedObserver = new SetsObserver(this.view, true);
        Button completedSetButton = new Button("Add");
        HBox completedSetHbox = new HBox(completedSetButton, completedSetLabel, usydCompletedObserver.getLabel());

        Label uncompletedSetLabel = new Label(" Un-Completed Sets: ");
        SetsObserver usydUnCompletedObserver = new SetsObserver(this.view, false);
        Button uncompletedSetButton = new Button("Add");
        HBox uncompletedSetHbox = new HBox(uncompletedSetButton, uncompletedSetLabel, usydUnCompletedObserver.getLabel());

        Label usydScoreUpdateLabel = new Label(" Set SURLFC Score:");
        TextField usydScoreField = new TextField();
        usydScoreField.setPromptText("Set Score");
        usydScoreField.setMaxWidth(75);
        Button addUsydScoreButton = new Button("Update");
        HBox usydScoreUpdateHbox = new HBox(addUsydScoreButton, usydScoreField);

        Label opponentScoreUpdateLabel = new Label(" Set Opponent Score:");
        TextField opponentScoreField = new TextField();
        opponentScoreField.setPromptText("Set Score");
        opponentScoreField.setMaxWidth(75);
        Button addOpponentScoreButton = new Button("Update");
        HBox opponentScoreUpdateHbox = new HBox(addOpponentScoreButton, opponentScoreField);

        Label penaltiesFor = new Label(" Penalties For: ");
        PenaltiesObserver penaltiesForObserver = new PenaltiesObserver(this.view, true);
        Button penaltiesForButton = new Button("Add");
        HBox penForBox = new HBox(penaltiesForButton, penaltiesFor, penaltiesForObserver.getLabel());

        Label penaltiesAgainst = new Label(" Penalties Against: ");
        PenaltiesObserver penaltiesAgainstObserver = new PenaltiesObserver(this.view, false);
        Button penaltiesAgainstButton = new Button("Add");
        HBox penAgainstBox = new HBox(penaltiesAgainstButton, penaltiesAgainst, penaltiesAgainstObserver.getLabel());


        completedSetButton.setOnAction((event -> this.view.getModel().incrementCompletedSet()));

        uncompletedSetButton.setOnAction((event2 -> this.view.getModel().incrementUncompletedSet()));

        penaltiesForButton.setOnAction(event3 -> this.view.getModel().incrementPenaltiesFor());

        penaltiesAgainstButton.setOnAction(event4 -> this.view.getModel().incrementPenaltiesAgainst());

        addUsydScoreButton.setOnAction((event3 -> {
            try {
                this.view.getModel().setUsydScore(Integer.parseInt(usydScoreField.getText()));
                usydScoreField.clear();
                usydScoreField.setStyle(null);
            } catch (NumberFormatException f) {
                usydScoreField.setStyle("-fx-text-box-border: red;");
            }
        }));

        addOpponentScoreButton.setOnAction(event4 -> {
            try {
                this.view.getModel().setOpponentScore(Integer.parseInt(opponentScoreField.getText()));
                opponentScoreField.clear();
                opponentScoreField.setStyle(null);
            } catch (NumberFormatException f) {
                opponentScoreField.setStyle("-fx-text-box-border: red;");
            }});




        VBox vbox = new VBox(leftHeaderLabel,
                completedSetHbox, uncompletedSetHbox, penForBox, penAgainstBox,
                usydScoreUpdateLabel,usydScoreUpdateHbox, opponentScoreUpdateLabel,opponentScoreUpdateHbox);

        leftHeaderLabel.setPadding(new Insets(0,0,20,0));
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPadding(new Insets(5,0,5,5));

        view.getPane().setLeft(vbox);
    }

    public void buildMiddle() {

        Label middleHeading = new Label("Player Stats");

        TextField playerNumberTextField = new TextField();
        playerNumberTextField.setMaxWidth(75);
        playerNumberTextField.setPromptText("Player num");

        Button submitButton = new Button("Submit");

        ComboBox<String> typeToSubmitCombo = new ComboBox<>();
        typeToSubmitCombo.getItems().add("Errors");
        typeToSubmitCombo.getItems().add("Hit Ups");
        typeToSubmitCombo.getItems().add("Tackles");
        typeToSubmitCombo.getItems().add("Try");
        typeToSubmitCombo.getItems().add("Try Assist");
        typeToSubmitCombo.getSelectionModel().selectFirst();

        HBox hbox = new HBox(playerNumberTextField, typeToSubmitCombo, submitButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        VBox vbox = new VBox(middleHeading, hbox);

        vbox.setAlignment(Pos.CENTER);
        this.view.getPane().setCenter(vbox);

        submitButton.setOnAction(event -> {
            if (playerNumberTextField.getText().strip().equals("")) {
                playerNumberTextField.setStyle("-fx-text-box-border: red;");
            } else {
                playerNumberTextField.setStyle(null);
                this.view.submitStats(playerNumberTextField.getText(), typeToSubmitCombo.getValue());
            }
            playerNumberTextField.clear();
        });

    }

    public void buildRight() {
        GameLogObserver logObserver = new GameLogObserver(this.view);
        VBox vbox = new VBox(logObserver.getListView());
        this.view.getPane().setRight(vbox);


    }

    public void buildBottom() {
        Button endGameButton = new Button("End Game");
        Button generatePlayerGraph = new Button("Graph Player Stats");
        Button generateGameGraph = new Button("Graph Game Stats");
        ComboBox<String> typeToSubmitCombo = new ComboBox<>();
        typeToSubmitCombo.getItems().add("Errors");
        typeToSubmitCombo.getItems().add("Hit Ups");
        typeToSubmitCombo.getItems().add("Tackles");
        typeToSubmitCombo.getItems().add("Try");
        typeToSubmitCombo.getItems().add("Try Assist");
        typeToSubmitCombo.getSelectionModel().selectFirst();

        TextField fileName = new TextField();
        fileName.setPromptText("File Name");

        HBox hbox = new HBox(typeToSubmitCombo,generatePlayerGraph, generateGameGraph,endGameButton, fileName);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5,5,20,5));
        hbox.setSpacing(15);
        this.view.getPane().setBottom(hbox);

        generateGameGraph.setOnAction(event -> this.view.graphGameStats());
        generatePlayerGraph.setOnAction(event2 -> this.view.graphPlayerStats(typeToSubmitCombo.getValue()));

        endGameButton.setOnAction(event -> {
            if (fileName.getText().strip().equals("")) {
                fileName.setStyle("-fx-text-box-border: red;");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("End Game Note");
                alert.setContentText("Please enter a name for the file to be saved as.");
                alert.showAndWait();
            } else {
                this.view.getModel().endGame(fileName.getText());
            }
        });
    }

}
