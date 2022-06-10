package NewGameTracker.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.util.Duration;

import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.HashMap;

public class GameTrackerEngine {

    private HashMap<Integer, Player> playerMap;
    private ArrayList<Observer> observerArrayList;
    private final int gameLengthSeconds = 5;
    private int remainingSeconds = gameLengthSeconds;
    private boolean running = false;
    Timeline countDown = new Timeline();

    private int usydScore;
    private int opponentScore;
    private int completedSet;
    private int unCompletedSet;

    public GameTrackerEngine() {
        this.playerMap = new HashMap<Integer, Player>();
        this.observerArrayList = new ArrayList<>();
        this.usydScore = 0;
        this.opponentScore = 0;
        this.completedSet = 0;
        this.unCompletedSet = 0;
        CountdownTimerModel();
    }

    public void generatePlayers(HashMap<Integer, String> playerList) {
        for (int i : playerList.keySet()) {
            System.out.println("Name = " + playerList.get(i) + " Number = " + i);
            Player newPlayer = new Player(i, playerList.get(i));

            playerMap.put(i, newPlayer);
        }
    }

    /**
     * Timer events from https://github.com/ArsalaBangash/JavaFX-CountdownTimer/tree/master/src
     */
    EventHandler countdownEventHandler = new EventHandler() {
        @Override
        public void handle(Event e) {
            remainingSeconds--;
            if (remainingSeconds <= 0) {
                pauseTimer();
                running = false;
                remainingSeconds = 2100;
            }
            updateObservers();
        }
    };

    public void CountdownTimerModel() {
        countDown.setCycleCount(Timeline.INDEFINITE);
        KeyFrame perSecondKeyFrame = new KeyFrame(Duration.seconds(1), this.countdownEventHandler);
        countDown.getKeyFrames().add(perSecondKeyFrame);
    }

    public void toggleTime() {
        if (this.running) {
            pauseTimer();
        } else {
            startTimer();
        }
        updateObservers();
        running = !running;
    }

    private void startTimer() {
        System.out.println("Starting Timer");
        countDown.play();
    }

    private void pauseTimer() {
        System.out.println("Pausing Timer");
        countDown.pause();
    }

    private void resetTimer() {
        System.out.println("Resetting Timer");
        countDown.stop();
        remainingSeconds = gameLengthSeconds;
        updateObservers();
    }

    public int getSecondsLeft() {
        return remainingSeconds;
    }

    public void addObserver(Observer o) {
        this.observerArrayList.add(o);
    }

    public void updateObservers() {
        for (Observer o : this.observerArrayList) {
            o.update();
        }
    }

    public void incrementCompletedSet() {
        this.completedSet ++;
        updateObservers();
    }

    public void incrementUncompletedSet() {
        this.unCompletedSet++;
        updateObservers();
    }

    public int getCompletedSet() {
        return  this.completedSet;
    }

    public int getUnCompletedSet() {
        return this.unCompletedSet;
    }

    public void setUsydScore(int score) {
        this.usydScore = score;
        updateObservers();
    }

    public void setOpponentScore(int score) {
        this.opponentScore = score;
        updateObservers();
    }

    public int getUsydScore() {
        return this.usydScore;
    }

    public int getOpponentScore() {
        return this.opponentScore;
    }

}
