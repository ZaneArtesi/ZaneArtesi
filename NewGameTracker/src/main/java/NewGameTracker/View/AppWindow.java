package NewGameTracker.View;

import NewGameTracker.Model.GameTrackerEngine;
import NewGameTracker.Model.Player;
import NewGameTracker.View.SceneBuilders.BuildHomeScene;
import NewGameTracker.View.SceneBuilders.BuildPlayerRosterScene;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.HashMap;

public class AppWindow {

    private Stage primaryStage;
    private final GameTrackerEngine model;
    private Scene scene;
    private BorderPane pane;

    public AppWindow(GameTrackerEngine model) {
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

    public void submitStats(String number, String type) {
        this.model.handleStats(number, type);
    }

    public void graphGameStats() {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Game Stats");
        xAxis.setLabel("Stat type");
        xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList("Completed Sets", "Un-Completed Sets",
                "Penalties For", "Penalties Against")));
        yAxis.setLabel("Amount");
        yAxis.setTickUnit(1);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("SURLFC");
        series.getData().add(new XYChart.Data<>("Completed Sets", this.model.getCompletedSet()));
        series.getData().add(new XYChart.Data<>("Un-Completed Sets", this.model.getUnCompletedSet()));
        series.getData().add(new XYChart.Data<>("Penalties For", this.model.getPenaltiesFor()));
        series.getData().add(new XYChart.Data<>("Penalties Against", this.model.getPenaltiesAgainst()));

        bc.getData().add(series);
        Scene s = new Scene(bc, 700, 400);
        Stage nw = new Stage();
        nw.setTitle("Game Stats Graph");
        nw.setResizable(false);
        nw.setScene(s);

        nw.show();
    }

    public void graphPlayerStats(String type) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Player Number");
        bc.setTitle("Player Specific Stats");
        yAxis.setLabel("Amount");
        yAxis.setTickUnit(1);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        HashMap<Integer, Player> playerHashMap = this.getModel().getPlayerMap();

        for (int i : playerHashMap.keySet()) {


            switch (type) {
                case "Errors" -> {
                    series.getData().add(new XYChart.Data<>(String.valueOf(playerHashMap.get(i).getNumber()), playerHashMap.get(i).getErrors()));
                    bc.setTitle("Player Errors");
                }
                case "Hit Ups" -> {
                    series.getData().add(new XYChart.Data<>(String.valueOf(playerHashMap.get(i).getNumber()), playerHashMap.get(i).getHitUps()));
                    bc.setTitle("Player Hit Ups");
                }
                case "Tackles" -> {
                    series.getData().add(new XYChart.Data<>(String.valueOf(playerHashMap.get(i).getNumber()), playerHashMap.get(i).getTackles()));
                    bc.setTitle("Player Tackles");
                }
                case "Try" -> {
                    series.getData().add(new XYChart.Data<>(String.valueOf(playerHashMap.get(i).getNumber()), playerHashMap.get(i).getTrys()));
                    bc.setTitle("Player Trys");
                }
                case "Try Assist" -> {
                    series.getData().add(new XYChart.Data<>(String.valueOf(playerHashMap.get(i).getNumber()), playerHashMap.get(i).getTryAssists()));
                    bc.setTitle("Player Try Assists");
                }
            }
        }

        bc.getData().add(series);
        Scene s = new Scene(bc, 700, 400);
        Stage nw = new Stage();
        nw.setTitle("Game Stats Graph");
        nw.setResizable(false);
        nw.setScene(s);

        nw.show();

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
