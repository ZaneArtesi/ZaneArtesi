package NewGameTracker.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class GameTrackerEngine {

    private final HashMap<Integer, Player> playerMap;
    private final ArrayList<Observer> observerArrayList;
    private final int gameLengthSeconds = 2100;
    private int remainingSeconds = gameLengthSeconds;
    private boolean running = false;
    Timeline countDown = new Timeline();

    private int usydScore;
    private int opponentScore;
    private int completedSet;
    private int unCompletedSet;
    private int penaltiesFor;
    private int penaltiesAgainst;

    private final ArrayList<String> gameLogList;

    public GameTrackerEngine() {
        this.playerMap = new HashMap<>();
        this.observerArrayList = new ArrayList<>();
        this.usydScore = 0;
        this.opponentScore = 0;
        this.completedSet = 0;
        this.unCompletedSet = 0;
        this.penaltiesFor = 0;
        this.penaltiesAgainst = 0;
        this.gameLogList = new ArrayList<>();
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
    EventHandler countdownEventHandler = e -> {
        remainingSeconds--;
        if (remainingSeconds <= 0) {
            pauseTimer();
            running = false;
            remainingSeconds = 2100;
        }
        updateObservers();
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

    public void handleStats(String number, String type) {
        if (number.strip().equals("")) {
            return;
        }

        Player player = this.playerMap.get(Integer.parseInt(number));
        if (player == null) {
            return;
        }

        switch (type) {
            case "Errors" -> player.setErrors(player.getErrors() + 1);
            case "Hit Ups" -> player.setHitUps(player.getHitUps() + 1);
            case "Tackles" -> player.setTackles(player.getTackles() + 1);
            case "Try" -> player.setTrys(player.getTrys() + 1);
            case "Try Assist" -> player.setTryAssists(player.getTryAssists() + 1);
        }
    }


    public void addToLog(String s) {
        String toLog = formatSecondsLeft() + " | " + s;
        this.gameLogList.add(toLog);
    }

    private String formatSecondsLeft() {
        int minutesInGame = (this.gameLengthSeconds - this.getSecondsLeft()) / 60;
        int secondInGame = (this.gameLengthSeconds - this.getSecondsLeft()) % 60;
        return  String.format("%02d", minutesInGame) + " : " + String.format("%02d", secondInGame);
    }

    public void endGame(String filename) {
        int ret = WriteToFile.writeToFile(filename, this);
        if (ret == 1) {
            Platform.exit();
        }
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
        addToLog("Completed Set");
        updateObservers();
    }

    public void incrementUncompletedSet() {
        this.unCompletedSet++;
        addToLog("Un-Completed Set");
        updateObservers();
    }

    public void incrementPenaltiesFor() {
        this.penaltiesFor++;
        addToLog("Penalty For");
        updateObservers();
    }

    public void incrementPenaltiesAgainst() {
        this.penaltiesAgainst++;
        addToLog("Penalty Against");
        updateObservers();
    }


    public void setUsydScore(int score) {
        this.usydScore = score;
        String toLog = "Score Updated USYD: " + score + " Other: " + getOpponentScore();
        addToLog(toLog);
        updateObservers();
    }

    public void setOpponentScore(int score) {
        this.opponentScore = score;
        String toLog = "Score Updated USYD:" + getUsydScore() + " Other: " + score;
        addToLog(toLog);
        updateObservers();
    }

    public ArrayList<String> getGameLogList() {
        return this.gameLogList;
    }

    public int getCompletedSet() {
        return  this.completedSet;
    }

    public int getUnCompletedSet() {
        return this.unCompletedSet;
    }

    public int getUsydScore() {
        return this.usydScore;
    }

    public int getOpponentScore() {
        return this.opponentScore;
    }

    public int getPenaltiesFor() {
        return this.penaltiesFor;
    }

    public int getPenaltiesAgainst() {
        return this.penaltiesAgainst;
    }

    public HashMap<Integer, Player> getPlayerMap() {
        return this.playerMap;
    }
}
