package major_project.model.HTTPrequests.InputAPI;

import com.google.gson.Gson;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootError;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootNutrients;

import java.util.HashMap;

public class PostRequestFormat {

    /**
     * Formats the response from the PostRequest i.e. the get nutrients food api search
     * @param statusCode the response status code
     * @param searchResult the response search result
     * @return HashMap<String, String> containing <statusCode, CODE> and <response, RESPONSE>
     */
    public static HashMap<String, String>  formatResponse(int statusCode, String searchResult) {
        HashMap<String, String> toReturn = new HashMap<>();
        Gson gson = new Gson();

        if (statusCode == 200) {
            RootNutrients post = gson.fromJson(searchResult, RootNutrients.class);
            toReturn.put("statusCode", String.valueOf(statusCode));
            toReturn.put("response", post.toString());

        } else {
            RootError e = gson.fromJson(searchResult, RootError.class);
            toReturn.put("statusCode", String.valueOf(statusCode));
            toReturn.put("response", e.getMessage());
        }
        return toReturn;
    }
}
