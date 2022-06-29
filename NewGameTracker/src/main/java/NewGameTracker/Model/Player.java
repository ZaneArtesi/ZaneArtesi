package NewGameTracker.Model;

public class Player {

    private final String name;
    private final int number;
    private int errors;
    private int hitUps;
    private int tackles;
    private int trys;
    private int tryAssists;

    public Player(int number, String name) {
        this.number = number;
        this.name = name;
        this.errors = 0;
        this.hitUps = 0;
        this.tackles = 0;
        this.trys = 0;
        this.tryAssists = 0;
    }


    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public int getHitUps() {
        return hitUps;
    }

    public void setHitUps(int hitUps) {
        this.hitUps = hitUps;
    }

    public int getTackles() {
        return tackles;
    }

    public void setTackles(int tackles) {
        this.tackles = tackles;
    }

    public int getTrys() {
        return trys;
    }

    public void setTrys(int trys) {
        this.trys = trys;
    }

    public int getTryAssists() {
        return tryAssists;
    }

    public void setTryAssists(int tryAssists) {
        this.tryAssists = tryAssists;
    }
}
