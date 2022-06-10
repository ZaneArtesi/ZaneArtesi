package NewGameTracker;

import NewGameTracker.Model.GameTrackerEngine;
import NewGameTracker.View.AppWindow;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    private final GameTrackerEngine model = new GameTrackerEngine();
    private final AppWindow view = new AppWindow(model);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Enter Playing Roster");
        primaryStage.setScene(view.getScene());
        this.view.setPrimaryStage(primaryStage);
        primaryStage.show();
    }
}
