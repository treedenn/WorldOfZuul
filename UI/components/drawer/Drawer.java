package UI.components.drawer;

import UI.components.components.Component;
import UI.components.components.IDrawer;
import UI.components.components.IEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Drawer extends Component implements IDrawer {

    private List<IEventListener> onCloseSubscribers = new ArrayList<>();
    private List<IEventListener> onSaveAndQuitSubscribers = new ArrayList<>();
    private List<IEventListener> onMaximizeMinimizeSubscribers = new ArrayList<>();

    @FXML
    private Button toggleFullscreenButton;


    public Drawer(){super("drawer_view.fxml");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClose(IEventListener listener) {
        onCloseSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSaveAndQuit(IEventListener listener) {
        onSaveAndQuitSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onMaximizeMinimize(IEventListener listener) {
        onMaximizeMinimizeSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWindowState(String state) {
        toggleFullscreenButton.setText(state);
    }

    @FXML
    void close(ActionEvent event) {
        onCloseSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void saveAndQuit(ActionEvent event) {
        onSaveAndQuitSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void toggleFullscreen(ActionEvent event) {
        onMaximizeMinimizeSubscribers.forEach(listener -> listener.onAction(null));
    }

}
