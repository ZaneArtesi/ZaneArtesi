package major_project;

import major_project.model.AppEngine;
import major_project.model.DB.DatabaseOffline;
import major_project.model.HTTPrequests.InputAPI.GetHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;



public class CacheTest {
    public AppEngine model;
    public GetHelper getHelperOffline;
    public GetHelper getHelperOnline;
//    private AppEngine mockModel;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
        model.setOutputDummy(true);
    }



    @Test
    public void testCacheHit() {

        DatabaseOffline mockDB = Mockito.mock(DatabaseOffline.class);
        Mockito.when(mockDB.searchFood(anyString(), anyString(), anyString())).thenReturn("Apple");

        ArrayList<String> params = new ArrayList<>();
        params.add("appID");
        params.add("appKey");
        params.add("IngredientInput");

        model.setDbHelper(mockDB);

        assertFalse(model.getCacheHit());
        model.HTTPrequest("getNutrients", params, true, true);

        model.HTTPrequest("getNutrients", params, true, false);

        assertTrue(model.getCacheHit());

    }

    @Test
    public void testRejectCacheHit() {
        ArrayList<String> params = new ArrayList<>();
        params.add("appID");
        params.add("appKey");
        params.add("IngredientInput");

        assertFalse(model.getRejectCacheHit());
        model.HTTPrequest("getNutrients", params, true, true);

        model.HTTPrequest("getNutrients", params, true, true);

        assertTrue(model.getRejectCacheHit());

    }

}
