package major_project;

import com.google.gson.Gson;
import major_project.model.AppEngine;
import major_project.model.HTTPrequests.DummyData.ErrorDummy;
import major_project.model.HTTPrequests.DummyData.SearchDummy;
import major_project.model.HTTPrequests.InputAPI.GetHelper;
import major_project.model.HTTPrequests.InputAPI.GetRequests;
import major_project.model.HTTPrequests.InputAPI.GetRequestFormat;
import major_project.model.HTTPrequests.InputAPI.GetRequestsOffline;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootError;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootParse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.HashMap;
import org.mockito.MockedStatic;

// here we utilise the dummy feature to test the method functions
class HTTPParseTest {

    public AppEngine model;
    public GetHelper getHelperOffline;
    public GetHelper getHelperOnline;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
        this.getHelperOffline = new GetRequestsOffline();
        this.getHelperOnline = new GetRequests();
    }


    // Test function directly
    @Test
    public void testInvalidArgs() {

        ArrayList<String> params = new ArrayList<>();
        boolean popOut = false;
        boolean isDummy = true;
        params.add("only one paramater.. yikes");

        HashMap<String, String> toReturn = getHelperOnline.parser(params, popOut);
        assertEquals("400", toReturn.get("statusCode"));
        assertEquals("Woops something went wrong, make sure you inputed a search value", (toReturn.get("response")));
    }


    @Test
    public void testStatusCode() {
        ArrayList<String> params = new ArrayList<>();
        params.add("appID");
        params.add("appKey");
        params.add("IngredientInput");
        HashMap<String, String> toReturn = getHelperOffline.parser(params, false);
        assertEquals("200", toReturn.get("statusCode"));
    }


    @Test
    public void testPopOutReturnNotEqualNormal() {
        ArrayList<String> params = new ArrayList<>();

        params.add("appID");
        params.add("appKey");
        params.add("IngredientInput");
        HashMap<String, String> popOutReturn = getHelperOffline.parser(params, true);

        ArrayList<String> params2 = new ArrayList<>();
        params.add("appID2");
        params.add("appKey2");
        params.add("IngredientInput2");
        HashMap<String, String> regularReturn = getHelperOffline.parser(params2, false);

        assertNotEquals(regularReturn.get("response"), popOutReturn.get("response"));
    }

    @Test
    public void testparseBasic() {
        ArrayList<String> param = new ArrayList<>();
        param.add("onion");
        model.HTTPrequest("parse", param, false, false);
        assertNotNull(model.getSearchResult());
    }

    @Test
    public void wrongInput() {
        ArrayList<String> param = new ArrayList<>();
        param.add("onion");
        assertEquals(0, model.HTTPrequest( "parse", param, false, false) );
    }

    @Test
    public void testObserverCalled() {
        TestObserver basic = new TestObserver(this.model);
        ArrayList<String> params = new ArrayList<>();
        params.add("appID");
        params.add("appKey");
        params.add("IngredientInput");

        model.registerObserver(basic);
        assertEquals(0, basic.getCounter());
        model.HTTPrequest( "parse", params, false, false);
        assertEquals(1, basic.getCounter());

    }


    // Test serialisation and deserialisation with mocks

    @Test
    public void testErrorCode() {
        ArrayList<String> params = new ArrayList<>();
        params.add("appID");
        params.add("appKey");
        params.add("IngredientInput");


        Gson gson = new Gson();

        HashMap<String, String> errorReturnMap = new HashMap<>();
        RootError e = gson.fromJson(ErrorDummy.getData(), RootError.class);
        errorReturnMap.put("statusCode", "404");
        errorReturnMap.put("response", e.getMessage());


        GetRequestFormat getRequestsMock = Mockito.mock(GetRequestFormat.class);
        try (MockedStatic<GetRequestFormat> mock = mockStatic(GetRequestFormat.class)) {

            mock.when(() -> GetRequestFormat.formatResponse(anyInt(), anyString(), anyBoolean()))
                    .thenReturn(errorReturnMap);

            HashMap<String, String> result = getHelperOffline.parser(params, false);

            assertEquals("There was an error", result.get("response"));
            assertEquals("404", result.get("statusCode"));
        }
    }

    @Test
    public void testRegularCode() {
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

            HashMap<String, String> result = getHelperOffline.parser(params, false);

            assertEquals(e.toString(), result.get("response"));
            assertEquals("200", result.get("statusCode"));
        }
    }

}
