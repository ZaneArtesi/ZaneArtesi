package major_project;

import major_project.model.AppEngine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObserverTests {

    public AppEngine model;
    TestObserver testObserver;
    TestObserver testObserver2;
    TestObserver testObserver3;

    @BeforeEach
    public void init() {
        model = new AppEngine();
        this.testObserver = new TestObserver(model);
        this.testObserver2 = new TestObserver(model);
        this.testObserver3 = new TestObserver(model);
    }


    @Test
    public void testRegisterObserver() {
        assertFalse(model.getObserversList().contains(testObserver));
        model.registerObserver(this.testObserver);
        assertTrue(model.getObserversList().contains(this.testObserver));
    }

    @Test
    public void testUpdateObservers() {
        assertEquals(this.testObserver.getCounter(), 0);
        model.registerObserver(this.testObserver);
        model.updateObservers();
        assertEquals(this.testObserver.getCounter(), 1);
    }

    @Test
    public void testUpdateObserversBig() {
        assertEquals(this.testObserver.getCounter(), 0);
        assertEquals(this.testObserver2.getCounter(), 0);
        assertEquals(this.testObserver3.getCounter(), 0);

        model.registerObserver(this.testObserver);
        model.registerObserver(this.testObserver2);

        model.updateObservers();

        assertEquals(this.testObserver.getCounter(), 1);
        assertEquals(this.testObserver2.getCounter(), 1);

        model.registerObserver(this.testObserver3);
        assertEquals(this.testObserver3.getCounter(), 0);

        model.updateObservers();

        assertEquals(this.testObserver.getCounter(), 2);
        assertEquals(this.testObserver2.getCounter(), 2);
        assertEquals(this.testObserver3.getCounter(), 1);
    }
}
