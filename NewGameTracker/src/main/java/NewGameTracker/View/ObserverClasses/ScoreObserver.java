package NewGameTracker.View.ObserverClasses;

import NewGameTracker.Model.Observer;
import NewGameTracker.View.AppWindow;
import javafx.scene.control.Label;

public class ScoreObserver implements Observer {

    private final AppWindow view;
    private Label label;
    private final boolean type;

    public ScoreObserver(AppWindow view, boolean type) {

        this.view = view;
        this.type = type;
        this.label = new Label("0");
        this.view.getModel().addObserver(this);
    }


    @Override
    public void update() {
        if (type) {
            this.label.setText(String.valueOf(this.view.getModel().getUsydScore()));
        } else {
            this.label.setText(String.valueOf(this.view.getModel().getOpponentScore()));
        }
    }

    public Label getLabel() {
        return this.label;
    }
}
