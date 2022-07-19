package major_project;

import com.google.gson.Gson;
import major_project.model.AppEngine;
import major_project.model.HTTPrequests.DummyData.NutrientDummy;
import major_project.model.HTTPrequests.DummyData.SearchDummy;
import major_project.model.HTTPrequests.InputAPI.GetRequestFormat;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootNutrients;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootParse;
import major_project.model.HTTPrequests.InputAPI.PostRequestFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.HashMap;

public class HTTPrequestFunctionTest {
    AppEngine model;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
    }

    // Simple tests with mocks to test if it calls and sets functions as intended

    @Test
    public void testSearchResultUpdated() {

        ArrayList<String> params = new ArrayList<>();
        params.add("appID");
        params.add("appKey");
        params.add("IngredientInput");


        Gson gson = new Gson();

        HashMap<String, String> regularReturnMap = new HashMap<>();
        RootParse e = gson.fromJson(SearchDummy.getData(), RootParse.class);
        regularReturnMap.put("statusCode", "200");
        regularReturnMap.put("response", e.toString());


        GetRequestFormat getRequestsMock = Mockito.mock(GetRequestFormat.class);
        try (MockedStatic<GetRequestFormat> mock = mockStatic(GetRequestFormat.class)) {

            mock.when(() -> GetRequestFormat.formatResponse(anyInt(), anyString(), anyBoolean()))
                    .thenReturn(regularReturnMap);

            model.HTTPrequest( "parse", params, false, false);
            HashMap<String, String> result = model.getSearchResult();

            assertEquals(regularReturnMap.get("response"), result.get("response"));

        }
    }


    @Test
    public void testGetNutrientsUpdated() {
        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");


        Gson gson = new Gson();

        HashMap<String, String> regularReturnMap = new HashMap<>();
        RootNutrients e = gson.fromJson(NutrientDummy.getData(), RootNutrients.class);
        regularReturnMap.put("statusCode", "200");
        regularReturnMap.put("response", e.toString());


        PostRequestFormat postRequestFormat = Mockito.mock(PostRequestFormat.class);
        try (MockedStatic<PostRequestFormat> mock = mockStatic(PostRequestFormat.class)) {

            mock.when(() -> PostRequestFormat.formatResponse(anyInt(), anyString()))
                    .thenReturn(regularReturnMap);


            model.HTTPrequest("getNutrients", params,false, true);
            HashMap<String, String> result = model.getNutrientResult();

            assertEquals(e.toString(), result.get("response"));
            assertEquals("200", result.get("statusCode"));
        }
    }
}
