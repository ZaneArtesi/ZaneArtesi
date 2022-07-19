package major_project.view.ObserverClasses.TableViewClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import major_project.model.AppEngine;
import major_project.model.HTTPrequests.InputAPI.POJOs.Food;
import major_project.model.Observers;

import java.util.ArrayList;

public class FoodListTable implements Observers {
    private final AppEngine model;

    private final TableView<FoodList> tableView;
    private final TableColumn<FoodList, String> nameColumn;
    private final TableColumn<FoodList, String> measureColumn;
    private final TableColumn<FoodList, String> quantityColumn;
    private final Label listLabel;

    private ObservableList<FoodList> data;


    public FoodListTable(AppEngine model) {
        this.model = model;

        this.tableView = new TableView<>();
        this.nameColumn = new TableColumn<>("Name");
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.measureColumn = new TableColumn<>("Measurement");
        this.measureColumn.setCellValueFactory(new PropertyValueFactory<>("Measurement"));
        this.quantityColumn = new TableColumn<>("Quantity");
        this.quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        this.listLabel = new Label("Added Items");

        TableColumn<FoodList, String> titleColumn = new TableColumn<>("Added Food");

        this.data = FXCollections.observableArrayList();
        titleColumn.getColumns().add(nameColumn);
        titleColumn.getColumns().add(measureColumn);
        titleColumn.getColumns().add(quantityColumn);

        tableView.getColumns().add(titleColumn);

        setUpTable();
        tableView.setEditable(true);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.setMaxHeight(500);
        tableView.setPrefWidth(300);
        tableView.setMaxWidth(300);
        tableView.setMaxHeight(500);


        model.registerObserver(this);
    }


    private void setUpTable() {
        this.nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        this.measureColumn.setCellValueFactory(
                new PropertyValueFactory<>("measurement")
        );

        this.quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
    }

    private int convertToObserverList() {
        ArrayList<String> result = model.getTotalAddedFood();
        this.data.clear();

        if (result == null) {
            return -1;
        }

        for (String s : result) {
            String[] split = s.split(">>");
            FoodList fl = new FoodList(split[0], split[1], split[2]);
            this.data.add(fl);
        }
        return 1;
    }


    public Label getLabel() {
        return this.listLabel;
    }

    public TableView getTableView() {
        return this.tableView;
    }

    @Override
    public void update() {
        if (convertToObserverList() == -1) {
            return;
        }

        this.tableView.setItems(this.data);

    }
}
