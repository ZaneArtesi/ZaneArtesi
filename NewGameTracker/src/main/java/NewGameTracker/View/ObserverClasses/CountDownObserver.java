package NewGameTracker.View.ObserverClasses;

import NewGameTracker.Model.Observer;
import NewGameTracker.View.AppWindow;
import javafx.scene.control.Label;

public class CountDownObserver implements Observer {
    private final AppWindow view;
    private final Label countDownLabel;

    public CountDownObserver(AppWindow view) {
        this.view = view;
        this.view.getModel().addObserver(this);
        this.countDownLabel = new Label("35 : 00");
    }

    @Override
    public void update() {
        int secondsLeft = this.view.getModel().getSecondsLeft();
        int minutesLeft = secondsLeft / 60;
        int secondsLeftOver = secondsLeft % 60;
        String timeLeft = minutesLeft + " : " + String.format("%02d", secondsLeftOver);
        this.countDownLabel.setText(timeLeft);
    }

    public Label getCountDownLabel() {
        return this.countDownLabel;
    }
}
