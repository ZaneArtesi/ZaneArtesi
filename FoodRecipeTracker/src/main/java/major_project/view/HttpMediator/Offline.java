package major_project.view.HttpMediator;

import major_project.model.AppEngine;
import major_project.view.AppWindow;

import java.util.ArrayList;

public class Offline implements HttpHelper{

    private final AppEngine model;
    private final AppWindow view;

    public Offline(AppEngine model, AppWindow view) {
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
        view.submitNutrientsSearch("1", "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
                        "food_bmrvi4ob4binw9a5m7l07amlfcoy");
    }

    @Override
    public void submitTopSearch() {
        this.view.selectItem("Onion");
    }

}
