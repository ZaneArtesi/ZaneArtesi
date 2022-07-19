package major_project;

import major_project.model.AppEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;

public class CompileTotalFoodTest {
    AppEngine model;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        model.setInputDummy(true);
    }

    @Test
    public void testCompileFood() {
        String apple = "Apple>>KG>>2";
        String pear = "Pear>>KG>>1";

        ArrayList<String> totalAdded = new ArrayList<>();
        totalAdded.add(apple);
        totalAdded.add(pear);

        model.setTotalAddedFood(totalAdded);

        String result = model.compileTotalAddedFood();

        String expected = "Apple>>KG>>2 & Pear>>KG>>1 &";
        assertEquals(expected.strip(), result.strip());

    }
}
