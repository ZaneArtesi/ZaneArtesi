package NewGameTracker.View.SceneBuilders;

import NewGameTracker.View.AppWindow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;

import static javafx.scene.paint.Color.RED;


public class BuildPlayerRosterScene implements SceneBuild{

    private final AppWindow view;

    public BuildPlayerRosterScene(AppWindow view) {
        this.view = view;
    }

    @Override
    public void build() {
        BorderPane middlePane = new BorderPane();
        ScrollPane scroller = new ScrollPane();

        VBox leftSide = new VBox();
        VBox rightSide = new VBox();
        HBox topSide = new HBox();
        HBox bottomSide = new HBox();

        Label topLabel = new Label("Enter Player Number and Player Name");
        topSide.getChildren().add(topLabel);

        Button submitButton = new Button("Submit");
        bottomSide.getChildren().add(submitButton);

        for (int i = 0; i < 20; i++) {
            TextField playerNum = new TextField();
            playerNum.setPromptText("Player #");
            leftSide.getChildren().add(playerNum);

            TextField playerName = new TextField();
            playerName.setPromptText("Player Name");
            rightSide.getChildren().add(playerName);

            if (i%2 == 0) {
                playerNum.setStyle("-fx-text-box-border: black ;");
                playerName.setStyle("-fx-text-box-border: black ;");
            }
        }

        topSide.setAlignment(Pos.CENTER);
        leftSide.setAlignment(Pos.CENTER);
        rightSide.setAlignment(Pos.CENTER);
        bottomSide.setAlignment(Pos.CENTER);

        leftSide.setPadding(new Insets(5,5,5,5));
        leftSide.setSpacing(10);
        rightSide.setPadding(new Insets(5,5,5,5));
        rightSide.setSpacing(10);
        middlePane.setPadding(new Insets(5,100,5,100));
        topSide.setPadding(new Insets(5,5,5,5));
        bottomSide.setPadding(new Insets(5,5,5,5));

        middlePane.setLeft(leftSide);
        middlePane.setRight(rightSide);

        scroller.setContent(middlePane);
        scroller.setFitToWidth(true);
        scroller.setFitToHeight(true);

        view.getPane().setTop(topSide);
        view.getPane().setCenter(scroller);
        view.getPane().setBottom(bottomSide);

        submitButton.setOnAction(event -> submitTeam(leftSide, rightSide));
    }

    public void submitTeam(VBox leftSide, VBox rightSide) {
        HashMap<Integer, String> playerList = new HashMap<Integer, String>();
        boolean inputError = false;

        TextField playerNumber;
        TextField playerName;
        for (int i = 0; i <leftSide.getChildren().size(); i ++) {
            playerNumber = (TextField) leftSide.getChildren().get(i);
            playerName = (TextField) rightSide.getChildren().get(i);

            try {
                playerList.put(Integer.parseInt(playerNumber.getText()), playerName.getText());
            } catch (NumberFormatException f) {
                ((TextField) leftSide.getChildren().get(i)).setStyle("-fx-background-color: red;");
//                inputError = true;
            }
        }

        if (!inputError) {
            view.getModel().generatePlayers(playerList);
            view.homeScene();
        }
    }

}
