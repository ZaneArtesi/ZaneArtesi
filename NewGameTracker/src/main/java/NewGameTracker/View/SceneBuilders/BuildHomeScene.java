package NewGameTracker.View.SceneBuilders;

import NewGameTracker.View.AppWindow;
import NewGameTracker.View.ObserverClasses.CountDownObserver;
import NewGameTracker.View.ObserverClasses.ScoreObserver;
import NewGameTracker.View.ObserverClasses.SetsObserver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class BuildHomeScene implements SceneBuild{

    private final AppWindow view;
    private HBox top;
    private VBox left;

    public BuildHomeScene(AppWindow view) {
        this.view = view;

    }

    @Override
    public void build() {
        buildTop();
        buildLeft();
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

        this.top = new HBox(usydLabel, usydScoreObserver.getLabel(), timeAndScore,
                            opponentLabel, opponentScoreObserver.getLabel());
        this.top.setSpacing(30);
        this.top.setPadding(new Insets(5,5,5,5));
        this.top.setAlignment(Pos.CENTER);

        clockButton.setOnAction(event -> {
            this.view.getModel().toggleTime();
        });

        view.getPane().setTop(top);
    }

    private void buildLeft() {
        Label leftHeaderLabel = new Label("Team Stats");

        Label completedSetLabel = new Label("Completed Sets: ");
        SetsObserver usydCompletedObserver = new SetsObserver(this.view, true);
        Button completedSetButton = new Button("Add Completed Set");
        HBox completedSetHbox = new HBox(completedSetLabel, usydCompletedObserver.getLabel());

        Label uncompletedSetLabel = new Label("Un-Completed Sets: ");
        SetsObserver usydUnCompletedObserver = new SetsObserver(this.view, false);
        Button uncompletedSetButton = new Button("Add Un-Completed Set");
        HBox uncompletedSetHbox = new HBox(uncompletedSetLabel, usydUnCompletedObserver.getLabel());

        Label usydScoreUpdateLabel = new Label("Set SURLFC Score:");
        TextField usydScoreField = new TextField();
        usydScoreField.setPromptText("Set Score");
        Button addUsydScoreButton = new Button("Add");
        HBox usydScoreUpdateHbox = new HBox(usydScoreField, addUsydScoreButton);

        Label opponentScoreUpdateLabel = new Label("Set Opponent Score:");
        TextField opponentScoreField = new TextField();
        opponentScoreField.setPromptText("Set Score");
        Button addOpponentScoreButton = new Button("Add");
        HBox opponentScoreUpdateHbox = new HBox(opponentScoreField, addOpponentScoreButton);


        completedSetButton.setOnAction((event -> {
            this.view.getModel().incrementCompletedSet();
        }));

        uncompletedSetButton.setOnAction((event2 -> {
            this.view.getModel().incrementUncompletedSet();
        }));

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




        this.left = new VBox(leftHeaderLabel,
                completedSetHbox, completedSetButton,
                uncompletedSetHbox, uncompletedSetButton,
                usydScoreUpdateLabel,usydScoreUpdateHbox, opponentScoreUpdateLabel,opponentScoreUpdateHbox);

        leftHeaderLabel.setPadding(new Insets(0,0,20,0));
        this.left.setSpacing(15);
        this.left.setAlignment(Pos.CENTER_LEFT);
        this.left.setPadding(new Insets(5,5,5,5));

        view.getPane().setLeft(this.left);
    }

}
