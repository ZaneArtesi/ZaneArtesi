package major_project;

import major_project.model.AppEngine;
import major_project.model.HTTPrequests.DummyData.SearchDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UpdateRunningTotalTest {

    AppEngine model;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
    }

    @Test
    public void testGeneral() {
        String dummyData = SearchDummy.getData();
        model.updateRunningTotals(dummyData);
        assertNotNull(model.getRunningTotal());
    }

    @Test
    public void testWaterContentMatchesDummy() {
        String dummyData = SearchDummy.getData();

        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");
        model.HTTPrequest("getNutrients", params, false, true);
        model.confimAddFood("Onion", "Gram", "1", "");
        model.updateRunningTotals(dummyData);

        // data taken from dummy
        assertEquals(111.388, model.getRunningTotal().get("Water(g)"));
    }

    @Test
    public void testMatchedDummyLarge() {
        String dummyData = SearchDummy.getData();

        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");
        model.HTTPrequest("getNutrients", params, false, true);
        model.confimAddFood("Onion", "Gram", "1", "chop finely");
        model.updateRunningTotals(dummyData);

        // data taken from dummy
        assertEquals(111.388, model.getRunningTotal().get("Water(g)"));
        assertEquals(0.145, model.getRunningTotal().get("Niacin(mg)"));
        assertEquals(50.0, model.getRunningTotal().get("Energy(kcal)"));
        assertEquals(1.375, model.getRunningTotal().get("Protein(g)"));
        assertEquals(182.5, model.getRunningTotal().get("Potassium, K(mg)"));
        assertEquals(5.3, model.getRunningTotal().get("Sugars, total(g)"));
    }

    @Test
    public void testGet() {
        HashMap<String, Double> runningTotal = new HashMap<>();
        model.setRunningTotal(runningTotal);
        assertEquals(runningTotal, model.getRunningTotal());

    }

    @Test
    public void testValuesAdd() {
        String dummyData = SearchDummy.getData();

        ArrayList<String> params = new ArrayList<>();
        params.add("quantity");
        params.add("measureURI");
        params.add("foodID");
        params.add("appID");
        params.add("appKey");
        model.HTTPrequest( "getNutrients", params, false, true);
        model.confimAddFood("Onion", "Gram", "1", "");
        model.updateRunningTotals(dummyData);

        model.HTTPrequest( "getNutrients", params, false, true);
        model.confimAddFood("Onion", "Gram", "1", "dice the onions");
        model.updateRunningTotals(dummyData);

        // data taken from dummy
        assertEquals(222.776, model.getRunningTotal().get("Water(g)"));
        assertEquals(0.29, model.getRunningTotal().get("Niacin(mg)"));
        assertEquals(100.0, model.getRunningTotal().get("Energy(kcal)"));
        assertEquals(2.75, model.getRunningTotal().get("Protein(g)"));
        assertEquals(365.0, model.getRunningTotal().get("Potassium, K(mg)"));
        assertEquals(10.6, model.getRunningTotal().get("Sugars, total(g)"));
    }
}
