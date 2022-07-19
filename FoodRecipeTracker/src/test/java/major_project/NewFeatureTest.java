package major_project;

import major_project.model.AppEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class NewFeatureTest {

    AppEngine model;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
    }

    @Test
    public void testAddFoodBasicNote() {
        String expected = "Chicken>>Whole>>1>>this is a note";
        HashMap<String, String> mockHash =  new HashMap<String, String>();
        mockHash.put("response", "Dummy response");

        model.setNutrientResult(mockHash);
        model.confimAddFood("Chicken", "Whole", "1", "this is a note");
        assertEquals(expected, model.getTotalAddedFood().get(0));
    }

    @Test
    public void testAddFoodEmptyNote() {
        String expected = "Chicken>>Whole>>1>>no notes given";
        HashMap<String, String> mockHash =  new HashMap<String, String>();
        mockHash.put("response", "Dummy response");

        model.setNutrientResult(mockHash);
        model.confimAddFood("Chicken", "Whole", "1", "");
        assertEquals(expected, model.getTotalAddedFood().get(0));
    }

    @Test
    public void testAddFoodEmptyNoteSpaced() {
        String expected = "Chicken>>Whole>>1>>no notes given";
        HashMap<String, String> mockHash =  new HashMap<String, String>();
        mockHash.put("response", "Dummy response");

        model.setNutrientResult(mockHash);
        model.confimAddFood("Chicken", "Whole", "1", "       ");
        assertEquals(expected, model.getTotalAddedFood().get(0));
    }
}
