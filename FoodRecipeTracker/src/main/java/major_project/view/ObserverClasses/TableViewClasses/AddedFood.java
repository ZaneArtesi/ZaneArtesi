package major_project.view.ObserverClasses.TableViewClasses;

import javafx.beans.property.SimpleStringProperty;

public class AddedFood {

    private final SimpleStringProperty name;
    private final SimpleStringProperty runningTotal;

    public AddedFood(String name , String runningTotal) {
        this.name = new SimpleStringProperty(name);
        this.runningTotal = new SimpleStringProperty(runningTotal);
    }


    public String getName() {
        return name.get();
    }

    public String getRunningTotal() {
        return runningTotal.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setRunningTotal(String runningTotal) {
        this.runningTotal.set(runningTotal);
    }


    @Override
    public String toString() {
        return "AddedFood{" +
                "name='" + name + '\'' +
                ", runningTotal='" + runningTotal + '\'' +
                '}';
    }
}
