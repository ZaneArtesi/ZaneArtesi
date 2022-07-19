package major_project;

import major_project.model.AppEngine;
import major_project.model.Observers;

public class TestObserver implements Observers {

    private final AppEngine model;
    private int counter;

    public TestObserver(AppEngine model) {
        this.model = model;
        this.counter = 0;
    }


    @Override
    public void update() {
        this.counter += 1;
    }

    public int getCounter() {
        return this.counter;
    }
}
