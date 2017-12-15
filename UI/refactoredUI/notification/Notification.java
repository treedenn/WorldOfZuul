package UI.refactoredUI.notification;

import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.INotification;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Notification extends Component implements INotification {


    @FXML
    private Label notificationMessage;

    public Notification(){super("notification_view.fxml");}

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
