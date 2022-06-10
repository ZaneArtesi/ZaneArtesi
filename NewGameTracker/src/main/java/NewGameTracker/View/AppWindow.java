package NewGameTracker.View;

import NewGameTracker.Model.GameTrackerEngine;
import NewGameTracker.View.SceneBuilders.BuildHomeScene;
import NewGameTracker.View.SceneBuilders.BuildPlayerRosterScene;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppWindow {

    private Stage primaryStage;
    private final GameTrackerEngine model;
    private Scene scene;
    private BorderPane pane;

    public AppWindow(GameTrackerEngine model) {
        this.primaryStage = primaryStage;
        this.model = model;
        this.pane = new BorderPane();
        this.scene = new Scene(pane, 700, 500);


        BuildPlayerRosterScene b = new BuildPlayerRosterScene(this);
        b.build();
        pane.setVisible(true);

    }


    public void homeScene() {
        this.pane = new BorderPane();
        this.scene = new Scene(pane, 700, 500);

        BuildHomeScene builder = new BuildHomeScene(this);
        builder.build();

        getPrimaryStage().setScene(this.scene);
        primaryStage.setTitle("Game Tracker");
        pane.setVisible(true);
    }


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public BorderPane getPane() {
        return this.pane;
    }

    public Scene getScene() {
        return this.scene;
    }

    public GameTrackerEngine getModel() {
        return this.model;
    }

}
