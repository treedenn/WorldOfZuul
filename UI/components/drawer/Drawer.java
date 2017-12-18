package UI.components.drawer;

import UI.components.icomponents.Component;
import UI.components.icomponents.IDrawer;
import UI.components.icomponents.IEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This concrete GUI handles the drawer view.
 */
public class Drawer extends Component implements IDrawer {

    /** List of observers. */
    private List<IEventListener> onCloseSubscribers = new ArrayList<>();
    /** List of observers. */
    private List<IEventListener> onSaveAndQuitSubscribers = new ArrayList<>();
    /** List of observers. */
    private List<IEventListener> onMaximizeMinimizeSubscribers = new ArrayList<>();

    @FXML
    private Button toggleFullscreenButton;

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public Drawer(){super("drawer_view.fxml");}

    /**
     * {@inheritDoc}
     */
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

    /**
     * Method to notify observers of the onClose()-event.
     * @param event the type of user event.
     */
    @FXML
    void close(ActionEvent event) {
        onCloseSubscribers.forEach(listener -> listener.onAction(null));
    }

    /**
     * Method to notify every subscriber of the onSaveAndQuit()-event.
     * @param event the type of user event.
     */
    @FXML
    void saveAndQuit(ActionEvent event) {
        onSaveAndQuitSubscribers.forEach(listener -> listener.onAction(null));
    }

    /**
     * Method to notify every subscriber of the onMaximizeMinimize()-event.
     * @param event the type of user event.
     */
    @FXML
    void toggleFullscreen(ActionEvent event) {
        onMaximizeMinimizeSubscribers.forEach(listener -> listener.onAction(null));
    }

}
