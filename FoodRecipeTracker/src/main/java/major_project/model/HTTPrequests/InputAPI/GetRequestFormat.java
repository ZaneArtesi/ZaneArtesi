package major_project.model.HTTPrequests.InputAPI;

import com.google.gson.Gson;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootError;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootParse;

import java.util.HashMap;

public class GetRequestFormat {

    /**
     * Formats the response from the GetRequest
     * @param statusCode the response status code
     * @param searchResult the search result
     * @param popOut if this was generated for the pop out window or not
     * @return HashMap<String, String> containing <statusCode, CODE> and <response, RESPONSE>
     */
    public static HashMap<String, String> formatResponse(int statusCode, String searchResult, boolean popOut) {
        HashMap<String, String> toReturn = new HashMap<>();
        Gson gson = new Gson();
        if (statusCode == 200) {
            RootParse post = gson.fromJson(searchResult, RootParse.class);
            toReturn.put("statusCode", String.valueOf(statusCode));

            if (popOut) {
                toReturn.put("response", post.measureToString());
            } else {
                toReturn.put("response", post.toString());
            }

        } else {
            RootError e = gson.fromJson(searchResult, RootError.class);
            toReturn.put("statusCode", String.valueOf(searchResult));
            toReturn.put("response", e.getMessage());
        }
        return toReturn;
    }

}
