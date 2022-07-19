package major_project.view.ObserverClasses.TableViewClasses;

import javafx.beans.property.SimpleStringProperty;

public class FoodList {

    private final SimpleStringProperty name;
    private final SimpleStringProperty measurement;
    private final  SimpleStringProperty quantity;

    public FoodList(String name, String measurement, String quantity) {
        this.name = new SimpleStringProperty(name);
        this.measurement = new SimpleStringProperty(measurement);
        this.quantity = new SimpleStringProperty(quantity);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMeasurement() {
        return measurement.get();
    }

    public SimpleStringProperty measurementProperty() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement.set(measurement);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }
}
