package major_project.model.HTTPrequests.InputAPI;

import com.google.gson.Gson;
import major_project.model.HTTPrequests.DummyData.SearchDummy;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootError;
import major_project.model.HTTPrequests.InputAPI.POJOs.*;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class GetRequests implements GetHelper{

    /**
     * @param params - a string array list that has params in order app_id, app_key, input
     * @return string response from server
     */
    @Override
    public HashMap<String, String> parser(ArrayList<String> params, boolean popOut) {

        HashMap<String, String> toReturn = new HashMap<>();

        if (params.size() != 3) {
            toReturn.put("statusCode", "400");
            toReturn.put("response", "Woops something went wrong, make sure you inputed a search value");
            return toReturn;
        }

        try {
            String appID = params.get(0);
            String appKey = params.get(1);
            String input = params.get(2);

            String newURI = "https://api.edamam.com/api/food-database/v2/parser?app_id=" + appID + "&app_key=" + appKey
                    + "&ingr=" + input + "&nutrition-type=cooking";


            String searchResult;
            int statusCode;

            HttpRequest request = HttpRequest.newBuilder(new URI(newURI)).GET().build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            statusCode = response.statusCode();
            searchResult = (String) response.body();

            toReturn = GetRequestFormat.formatResponse(statusCode, searchResult, popOut);

        } catch (Exception ignored) {
            System.out.println(ignored);
            toReturn.put("statusCode", "400");
            toReturn.put("Response", "Something went wrong");
        }

        return toReturn;
    }

}
