package major_project;

import com.google.gson.Gson;
import major_project.model.AppEngine;
import major_project.model.HTTPrequests.DummyData.ErrorDummy;
import major_project.model.HTTPrequests.DummyData.NutrientDummy;
import major_project.model.HTTPrequests.InputAPI.GetNutrientsHelper;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootError;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootNutrients;
import major_project.model.HTTPrequests.InputAPI.POSTrequestsOffline;
import major_project.model.HTTPrequests.InputAPI.PostRequestFormat;
import major_project.model.HTTPrequests.InputAPI.POSTrequests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.HashMap;

public class HTTPNutrientTest {

    public AppEngine model;
    public GetNutrientsHelper getNutrientsHelperOnline;
    public GetNutrientsHelper getNutrientsHelperOffline;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
        model.setOutputDummy(true);
        this.getNutrientsHelperOnline = new POSTrequests();
        this.getNutrientsHelperOffline = new POSTrequestsOffline();
    }

    // Direct tests
    @Test
    public void testInvalidArgs() {
        ArrayList<String> params = new ArrayList<>();
        params.add("only one paramater.. yikes");
        HashMap<String, String> result = this.getNutrientsHelperOnline.getNutrients(params);

        assertEquals("400", result.get("statusCode"));
        assertEquals("Woops something went wrong, make sure you inputed a search value",
                result.get("response"));
    }

    @Test
    public void testStatusCode() {
        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");
        HashMap<String, String> result = this.getNutrientsHelperOffline.getNutrients(params);

        assertEquals("200", result.get("statusCode"));
    }

    // Tests through model
    @Test
    public void testObserverCalled() {
        TestObserver basic = new TestObserver(this.model);
        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");

        model.registerObserver(basic);
        assertEquals(0, basic.getCounter());
        model.HTTPrequest( "getNutrients", params, false, true);
        assertEquals(1, basic.getCounter());

    }

    @Test
    public void wrongInput() {
        ArrayList<String> param = new ArrayList<>();
        assertEquals(0, model.HTTPrequest( "parse", param, false, false) );
    }

    // Test serialisation and deserialisation with mocks

    @Test
    public void testErrorCode() {
        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");


        Gson gson = new Gson();

        HashMap<String, String> errorReturnMap = new HashMap<>();
        RootError e = gson.fromJson(ErrorDummy.getData(), RootError.class);
        errorReturnMap.put("statusCode", "404");
        errorReturnMap.put("response", e.getMessage());


        PostRequestFormat postRequestFormat = Mockito.mock(PostRequestFormat.class);
        try (MockedStatic<PostRequestFormat> mock = mockStatic(PostRequestFormat.class)) {

            mock.when(() -> PostRequestFormat.formatResponse(anyInt(), anyString()))
                    .thenReturn(errorReturnMap);

            HashMap<String, String> result = this.getNutrientsHelperOffline.getNutrients(params);

            assertEquals("There was an error", result.get("response"));
            assertEquals("404", result.get("statusCode"));
        }
    }

    @Test
    public void testRegularCode() {
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

            HashMap<String, String> result = this.getNutrientsHelperOffline.getNutrients(params);

            assertEquals(e.toString(), result.get("response"));
            assertEquals("200", result.get("statusCode"));
        }
    }
}
