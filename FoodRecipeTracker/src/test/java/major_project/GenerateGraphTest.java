package major_project;

import major_project.model.AppEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;



public class GenerateGraphTest {

    // set the maxes
    // change the local var to a global so we can get and set
    // test
    AppEngine model;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
    }

    @Test
    public void testGenerateNumbers() {
        HashMap<String, Double> setMaxes = new HashMap<>();
        setMaxes.put("Water(g)", 100.0);

        HashMap<String, Double> runningTotal = new HashMap<>();
        runningTotal.put("Water(g)", 50.0);

//        AppEngine modelMock = Mockito.mock(AppEngine.class);
        model.setAllMaxes(setMaxes);
        model.setRunningTotal(runningTotal);
//        when(modelMock.getSetMaxes()).thenReturn(setMaxes);
//        when(modelMock.getRunningTotal()).thenReturn(runningTotal);

        model.generateGraph();
        HashMap<String, ArrayList<Integer>> result = model.getGraphResultMap();

        assertEquals(50, result.get("Water(g)").get(0));
    }


    @Test
    public void testMoreThanOneGraph() {
        HashMap<String, Double> setMaxes = new HashMap<>();
        setMaxes.put("Water(g)", 100.0);
        setMaxes.put("Protein(g)", 250.0);
        setMaxes.put("Zinc(g)", 500.5);
        setMaxes.put("Carbs(g)", 0.500);

        HashMap<String, Double> runningTotal = new HashMap<>();
        runningTotal.put("Water(g)", 50.0);
        runningTotal.put("Protein(g)", 200.0);
        runningTotal.put("Zinc(g)", 200.25);
        runningTotal.put("Carbs(g)", 0.02);

        model.setAllMaxes(setMaxes);
        model.setRunningTotal(runningTotal);
        model.generateGraph();
        HashMap<String, ArrayList<Integer>> result = model.getGraphResultMap();

        // The percentage
        assertEquals(50, result.get("Water(g)").get(0));

        assertEquals(80, result.get("Protein(g)").get(0));

        assertEquals(40, result.get("Zinc(g)").get(0));

        assertEquals(4, result.get("Carbs(g)").get(0));
    }

    @Test
    public void testTotalHigherThanMax() {
        // when this happens we only show the percentage - i.e. if max is 100 and running total is 200,
        // we are 200%
        HashMap<String, Double> setMaxes = new HashMap<>();
        setMaxes.put("Water(g)", 50.0);

        HashMap<String, Double> runningTotal = new HashMap<>();
        runningTotal.put("Water(g)", 100.0);

        model.setAllMaxes(setMaxes);
        model.setRunningTotal(runningTotal);


        model.generateGraph();
        HashMap<String, ArrayList<Integer>> result = model.getGraphResultMap();

        assertEquals(200, result.get("Water(g)").get(0));

    }
}
