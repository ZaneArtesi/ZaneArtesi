package major_project.view.ObserverClasses;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import major_project.model.AppEngine;
import major_project.model.Observers;

import javax.swing.text.ComponentView;
import java.util.HashMap;

/**
 * Search result class that implements the Observers class - will update the combobox to be populated with
 * the different types of food results from the initial search.
 */
public class SearchResult implements Observers {
    private final AppEngine model;
    private final ComboBox<String> searchResultsCombo;
    private final Label label;

    public SearchResult(AppEngine model) {
        this.model = model;
        searchResultsCombo = new ComboBox<>();
        model.registerObserver(this);
        this.label = new Label("Select a food from your search result");
        this.searchResultsCombo.setPrefWidth(150);
        this.searchResultsCombo.setVisibleRowCount(5);
//        this.searchResultsCombo.setMaxWidth(300);
    }

    public ComboBox<String> getSearchResultsCombo() {
        return this.searchResultsCombo;
    }

    public Label getLabel() {
        return this.label;
    }

    /**
     * Updates the combobox in the middle section of the home screen with result
     */
    @Override
    public void update() {
        searchResultsCombo.getSelectionModel().clearSelection();
        searchResultsCombo.getItems().clear();

        HashMap<String, String> result = model.getSearchResult();

        String status = result.get("statusCode");
        String response = result.get("response");
        if (status.equals("200")) {
            String[] responseSplit = response.split("===");
            for (int i = 0; i < responseSplit.length; i++) {
                searchResultsCombo.getItems().add(responseSplit[i].split("&&&")[0]);
            }
        }
        this.searchResultsCombo.setPrefWidth(300);
    }
}
