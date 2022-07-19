package major_project;

import major_project.model.AppEngine;
import major_project.model.HTTPrequests.DummyData.SearchDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SetMaxesTest {
    AppEngine model;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
    }

    @Test
    public void testAddToSetMaxess() {
        model.setSetMaxes("New", "10.1");
        assertTrue(model.getSetMaxes().keySet().contains("New"));
    }

    @Test
    public void testInt() {
        model.setSetMaxes("New", "10");
        assertTrue(model.getSetMaxes().keySet().contains("New"));
    }

    @Test
    public void testString() {

        assertEquals(-1, model.setSetMaxes("New", "a"));
    }
}
