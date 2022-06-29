package NewGameTracker.View.ObserverClasses;

import NewGameTracker.Model.Observer;
import NewGameTracker.View.AppWindow;
import javafx.scene.control.Label;

public class PenaltiesObserver implements Observer {

    private final AppWindow view;
    private final boolean type;
    private final Label label;

    public PenaltiesObserver(AppWindow view, boolean type) {
        this.view = view;
        this.type = type;
        this.label =  new Label("0");
        this.view.getModel().addObserver(this);
    }

    public Label getLabel() {
        return this.label;
    }

    @Override
    public void update() {
        if (type) {
            this.label.setText(String.valueOf(view.getModel().getPenaltiesFor()));
        } else {
            this.label.setText(String.valueOf(view.getModel().getPenaltiesAgainst()));
        }
    }
}
