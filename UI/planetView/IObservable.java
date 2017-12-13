package UI.planetView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface IObservable {
    void onAction(Runnable action);
}
