package major_project;

import major_project.model.AppEngine;
import major_project.model.HTTPrequests.InputAPI.GetHelper;
import major_project.model.HTTPrequests.InputAPI.GetRequests;
import major_project.model.Observers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import static org.mockito.Mockito.when;

public class SetterAndGetterTest {

    AppEngine model;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
    }

    @Test
    public void testGetSearchResult() {
        HashMap<String, String> searchResult = new HashMap<>();
        AppEngine modelMock = Mockito.mock(AppEngine.class);
        when(modelMock.getSearchResult()).thenReturn(searchResult);
        assertEquals(searchResult, modelMock.getSearchResult());
    }

    @Test
    public void testGetNutrientResult() {
        HashMap<String, String> nutrientResult = new HashMap<>();
        AppEngine modelMock = Mockito.mock(AppEngine.class);
        when(modelMock.getNutrientResult()).thenReturn(nutrientResult);
        assertEquals(nutrientResult, modelMock.getNutrientResult());
    }

    @Test
    public void testGetTotalAddedFood() {
        ArrayList<String> totalAddedFood = new ArrayList<>();
        AppEngine modelMock = Mockito.mock(AppEngine.class);
        when(modelMock.getTotalAddedFood()).thenReturn(totalAddedFood);
        assertEquals(totalAddedFood, modelMock.getTotalAddedFood());
    }

    @Test
    public void testGetSetMaxes() {
        HashMap<String, Double> setMaxes = new HashMap<>();
        AppEngine modelMock = Mockito.mock(AppEngine.class);
        when(modelMock.getSetMaxes()).thenReturn(setMaxes);
        assertEquals(setMaxes, modelMock.getSetMaxes());
    }

    @Test
    public void testGetObserverList() {
        ArrayList<Observers> observerList = new ArrayList<>();
        AppEngine modelMock = Mockito.mock(AppEngine.class);
        when(modelMock.getObserversList()).thenReturn(observerList);
        assertEquals(observerList, modelMock.getObserversList());
    }

    @Test
    public void testSetDummy() {
        model.setInputDummy(true);
        assertEquals(true, model.getInputDummy());

        model.setInputDummy(false);
        assertEquals(false, model.getInputDummy());
    }

    @Test
    public void testGetPrevPaste() {
        String prevPaste = "paste";
        AppEngine modelMock = Mockito.mock(AppEngine.class);
        when(modelMock.getPreviousPaste()).thenReturn(prevPaste);
        assertEquals(prevPaste, modelMock.getPreviousPaste());
    }

}
