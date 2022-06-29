package NewGameTracker.View.ObserverClasses;

import NewGameTracker.Model.Observer;
import NewGameTracker.View.AppWindow;
import javafx.scene.control.ListView;

public class GameLogObserver implements Observer {

    private final AppWindow view;
    private final ListView<String> listView;
    private int lastLogLength;

    public GameLogObserver(AppWindow view) {
        this.view = view;
        this.listView = new ListView<>();
        this.view.getModel().addObserver(this);
        this.lastLogLength = 0;
    }


    public ListView<String> getListView() {
        return listView;
    }

    @Override
    public void update() {
        if (view.getModel().getGameLogList().size() != lastLogLength) {
            lastLogLength = view.getModel().getGameLogList().size();
            listView.getItems().clear();

            for (String s : view.getModel().getGameLogList()) {
                listView.getItems().add(s);
            }
        }
    }
}
