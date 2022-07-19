package major_project.model.HTTPrequests.InputAPI;

import com.google.gson.Gson;
import major_project.model.HTTPrequests.InputAPI.POJOs.*;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;



public class POSTrequests implements GetNutrientsHelper{
    /**
     * @param params - a string array list that has params in order quantity, measureURI, foodId, app_id, app_key,
     * @return string response from server
     */

    public HashMap<String, String> getNutrients(ArrayList<String> params) {
        HashMap<String, String> toReturn = new HashMap<>();

        if (params.size() < 5) {
            toReturn.put("statusCode", "400");
            toReturn.put("response", "Woops something went wrong, make sure you inputed a search value");
            return toReturn;
        }

        try {
            String quantity = params.get(0);
            String measureURI = params.get(1);
            String foodId = params.get(2);
            String appID = params.get(3);
            String appKey = params.get(4);

            String newURI = "https://api.edamam.com/api/food-database/v2/nutrients?app_id=" + appID + "&app_key=" + appKey;

            Gson gson = new Gson();
            String searchResult;
            int statusCode;

            Ingredient ingredient = new Ingredient(Integer.parseInt(quantity), measureURI, foodId);
            ArrayList<Ingredient> ingredientList = new ArrayList<>();
            ingredientList.add(ingredient);
            RootGetNutrients rgn = new RootGetNutrients(ingredientList);
            String postJSON = gson.toJson(rgn);

            HttpRequest request = HttpRequest.newBuilder(new URI(newURI)).
                    header("Content-Type", "application/json").
                    POST(HttpRequest.BodyPublishers.ofString(postJSON)).build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

            statusCode = response.statusCode();
            searchResult = (String) response.body();

            toReturn = PostRequestFormat.formatResponse(statusCode, searchResult);

        } catch (Exception ignored) {
            System.out.println(ignored);
            toReturn.put("statusCode", "400");
            toReturn.put("Response", "Something went wrong");
        }

        return toReturn;
    }




}
