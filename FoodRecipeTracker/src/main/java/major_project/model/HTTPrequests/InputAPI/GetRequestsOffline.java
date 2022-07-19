package major_project.model.HTTPrequests.InputAPI;

import major_project.model.HTTPrequests.DummyData.SearchDummy;

import java.util.ArrayList;
import java.util.HashMap;

public class GetRequestsOffline implements GetHelper{


    @Override
    public HashMap<String, String> parser(ArrayList<String> params, boolean popOut) {
        String searchResult = SearchDummy.getData();
        int statusCode = 200;

        return GetRequestFormat.formatResponse(statusCode, searchResult, popOut);

    }
}
