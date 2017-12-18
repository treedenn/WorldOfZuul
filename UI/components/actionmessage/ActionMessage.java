package UI.components.actionmessage;

import UI.components.icomponents.Component;
import UI.components.icomponents.IActionMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This concrete GUI component controls the label in the top of the screen.
 */
public class ActionMessage extends Component implements IActionMessage {


    @FXML private Label gestureText;

    @FXML private Label keyHint;

    @FXML private Label actionText;

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public ActionMessage(){super("actionmessage_view.fxml");}

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
    public void setup(String getureText, String keyHint, String actionText) {
        this.gestureText.setText(getureText);
        this.keyHint.setText(keyHint);
        this.actionText.setText(actionText);
    }
}
