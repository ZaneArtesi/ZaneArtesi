package major_project.view.ObserverClasses.TableViewClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import major_project.model.AppEngine;
import major_project.model.Observers;

import java.util.HashMap;

public class TableViewExample implements Observers {

    private final AppEngine model;
    private final TableView<AddedFood> tableView;
    private final TableColumn<AddedFood, String> nameColumn;
    private final TableColumn<AddedFood, String> runningTotalColumn;

    private final ObservableList<AddedFood> data;
    private boolean isPopOut = false;


    public TableViewExample(AppEngine model) {
        this.model = model;

        this.tableView = new TableView<>();
        this.nameColumn = new TableColumn<>("Name & (Measurement)");
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name & (Measurement)"));
        this.runningTotalColumn = new TableColumn<>("Running Total");
        this.runningTotalColumn.setCellValueFactory(new PropertyValueFactory<>("Running Total"));

        TableColumn<AddedFood, String> titleColumn = new TableColumn<>("Total Nutrients");
        titleColumn.getColumns().add(nameColumn);
        titleColumn.getColumns().add(runningTotalColumn);

        this.data = FXCollections.observableArrayList();
        tableView.getColumns().add(titleColumn);

        setupTable();
        tableView.setEditable(true);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.setMaxHeight(500);
        tableView.setPrefWidth(300);
        tableView.setMaxWidth(300);
        tableView.setMaxHeight(500);

        model.registerObserver(this);
    }

    private void setupTable() {
        this.nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        this.runningTotalColumn.setCellValueFactory(
                new PropertyValueFactory<>("runningTotal")
        );
    }

    private int convertToObserverList() {
        HashMap<String, Double> result = model.getRunningTotal();

        if (isPopOut) {
            result = model.getParsedNutrientResult();
        }

        this.data.clear();

        if (result == null) {
            return -1;
        }

        for (String s : result.keySet()) {
            AddedFood food = new AddedFood(s, result.get(s).toString());
            this.data.add(food);
        }
        return 1;
    }

    @Override
    public void update() {

        if (isPopOut) {
            this.data.clear();
        }

        if ((convertToObserverList() == -1 || (this.tableView.getItems() == null))) {
            return;
        }

        this.tableView.setItems(this.data);
    }

    public TableView<AddedFood> getTableView() {
        return this.tableView;
    }

    public void setIsPopout() {
        this.isPopOut = true;
        tableView.setMaxHeight(600);
        tableView.setPrefWidth(600);
        tableView.setMaxWidth(600);
        tableView.setMaxHeight(600);
    }


    // need to add to dark mode
}
