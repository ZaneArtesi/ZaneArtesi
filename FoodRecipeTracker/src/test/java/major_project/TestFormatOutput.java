package major_project;

import com.google.gson.Gson;
import major_project.model.AppEngine;
import major_project.model.HTTPrequests.DummyData.NutrientDummy;
import major_project.model.HTTPrequests.DummyData.SearchDummy;
import major_project.model.HTTPrequests.InputAPI.POJOs.RootNutrients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TestFormatOutput {

    AppEngine model;
    Gson gson;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
        gson = new Gson();
    }


    @Test
    public void testBasicFormat() {
        String dummyData = NutrientDummy.getData();
        RootNutrients post = gson.fromJson(dummyData, RootNutrients.class);

        model.formatOuput(post.toString());
        assertNotNull(model.getParsedNutrientResult());
    }

    @Test
    public void testFormatMatchesExpected() {

        String dummyData = NutrientDummy.getData();
        RootNutrients post = gson.fromJson(dummyData, RootNutrients.class);

        model.formatOuput(post.toString());
        assertNotNull(model.getParsedNutrientResult().get("Total lipid (fat)(g)"));
        assertNotNull(model.getParsedNutrientResult().get("Carbohydrate, by difference(g)"));
        assertNotNull(model.getParsedNutrientResult().get("Potassium, K(mg)"));
        assertNotNull(model.getParsedNutrientResult().get("Sugars, total(g)"));
        assertNotNull(model.getParsedNutrientResult().get("Zinc, Zn(mg)"));
        assertNotNull(model.getParsedNutrientResult().get("Water(g)"));
        assertNotNull(model.getParsedNutrientResult().get("Niacin(mg)"));
        assertNotNull(model.getParsedNutrientResult().get("Thiamin(mg)"));
    }

    @Test
    public void getParsedNutrientResult() {
        HashMap<String, Double> map = new HashMap<>();
        model.setParsedNutrientResult(map);
        assertEquals(map, model.getParsedNutrientResult());
    }
}


