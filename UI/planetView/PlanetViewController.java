package UI.planetView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class PlanetViewController {

    List<Runnable> onActionbservers = new ArrayList<>();
    List<Runnable> onListClickedObservers = new ArrayList<>();


    public void onButtonPressed(Runnable r) {
        onActionbservers.add(r);
    }

    public void onListClicked(Runnable r) {
        onActionbservers.add(r);
    }
    void click() {
        for (Runnable observer : onActionbservers) {
            observer.run();
        }
    }

    void listClicked() {

        for (Runnable observer : onListClickedObservers) {
            observer.run();
        }
    }

}
