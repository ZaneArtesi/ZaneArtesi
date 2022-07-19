package major_project.view.HttpMediator;

import major_project.model.AppEngine;
import major_project.view.AppWindow;

import java.util.ArrayList;

public class Online implements HttpHelper {
    private final AppEngine model;
    private final AppWindow view;

    public Online(AppEngine model, AppWindow view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void submitSearchParse(String input) {
        ArrayList<String> params = new ArrayList<>();
        params.add(input);
        model.HTTPrequest( "parse", params, false, false);
    }

    @Override
    public void submitNutrients() {
        String measureURI = this.view.getPrevResult().split("=========")[0].
                split(this.view.getMeasureOptionsBox().getValue())[1].split("&&&")[1];
        this.view.submitNutrientsSearch(this.view.getPrevQuantity(), measureURI, this.view.getPrevFoodId());
    }

    @Override
    public void submitTopSearch() {
        if (this.view.getSr().getSearchResultsCombo().getValue() != null) {
            this.view.selectItem(this.view.getSr().getSearchResultsCombo().getValue());
            this.view.getSr().getSearchResultsCombo().getItems().clear();
            // please enter valid selection
        }
    }

}
