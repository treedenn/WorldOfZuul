package UI.components.notification;

import UI.components.icomponents.Component;
import UI.components.icomponents.INotification;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This concrete GUI handles the notification view.
 */
public class Notification extends Component implements INotification {


    @FXML private Label notificationMessage;

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public Notification(){super("notification_view.fxml");}

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
    public void setMessage(String message) {
        notificationMessage.setText(message);
    }
}
