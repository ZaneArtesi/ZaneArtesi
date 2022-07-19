package major_project.model.HTTPrequests.InputAPI;

import major_project.model.HTTPrequests.DummyData.NutrientDummy;

import java.util.ArrayList;
import java.util.HashMap;

public class POSTrequestsOffline implements GetNutrientsHelper {
    @Override
    public HashMap<String, String> getNutrients(ArrayList<String> params) {
        String searchResult = NutrientDummy.getData();
        int statusCode = 200;
        return PostRequestFormat.formatResponse(statusCode, searchResult);
    }
}
