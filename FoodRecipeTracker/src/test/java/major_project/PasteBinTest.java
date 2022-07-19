package major_project;

import com.google.gson.Gson;
import major_project.model.AppEngine;
import major_project.model.HTTPrequests.OutputAPI.POJOs.PasteBinPOSTOffline;
import major_project.model.HTTPrequests.OutputAPI.PasteBinSend;
import major_project.model.HTTPrequests.OutputAPI.PasteBinPOST;
import major_project.model.HTTPrequests.OutputAPI.PostHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PasteBinTest {
    public AppEngine model;
    public PostHelper postHelperOnline;
    public PostHelper postHelperOffline;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
        this.postHelperOffline = new PasteBinPOSTOffline();
        this.postHelperOnline = new PasteBinPOST();
    }

    // Direct Test
    @Test
    public void testPasteMock() {

        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");

        PasteBinSend sendRequest = Mockito.mock(PasteBinSend.class);
        try (MockedStatic<PasteBinSend> mock = mockStatic(PasteBinSend.class)) {

            mock.when(() -> PasteBinSend.sendPost(anyString(), anyString()))
                    .thenReturn("GENERATED LINK");

            String result = postHelperOnline.post("String", "String");
            assertEquals("GENERATED LINK", result);

        }
    }


    @Test
    public void testPasteMockError() {

        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");

        PasteBinSend sendRequest = Mockito.mock(PasteBinSend.class);
        try (MockedStatic<PasteBinSend> mock = mockStatic(PasteBinSend.class)) {

            mock.when(() -> PasteBinSend.sendPost(anyString(), anyString()))
                    .thenReturn("Errors are passed as strings so this is almost redundant");

            String result = postHelperOnline.post("String", "String");
            assertEquals("Errors are passed as strings so this is almost redundant", result);

        }
    }

    @Test
    public void testNoData() {

        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");

        assertEquals(null, model.outputAPI("long"));
    }

    @Test
    public void testPasteMockModel() {

        model.setOutputDummy(true);
        model.setInputDummy(true);
        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");

        HashMap<String, Double> runningTotal = new HashMap<String, Double>();
        runningTotal.put("Protein(g)", 10.1);
        runningTotal.put("Water(g)", 5.0);

        ArrayList<String> totalAddedFood = new ArrayList<>();
        totalAddedFood.add("Chicken>>Whole>>1");
        totalAddedFood.add("Water>>Glass>>1");


        model.setTotalAddedFood(totalAddedFood);
        model.setRunningTotal(runningTotal);

        assertEquals("LINK HERE - Terminal Shows tobe Pasted", model.outputAPI("short"));

    }

    @Test
    public void testPasteMockModelLong() {




        model.setOutputDummy(true);
        model.setInputDummy(true);
        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");

        HashMap<String, Double> runningTotal = new HashMap<String, Double>();
        runningTotal.put("Protein(g)", 10.1);
        runningTotal.put("Water(g)", 5.0);

        ArrayList<String> totalAddedFood = new ArrayList<>();
        totalAddedFood.add("Chicken>>Whole>>1");
        totalAddedFood.add("Water>>Glass>>1");


        model.setTotalAddedFood(totalAddedFood);
        model.setRunningTotal(runningTotal);

        assertEquals("LINK HERE - Terminal Shows tobe Pasted", model.outputAPI("long"));

    }

}
