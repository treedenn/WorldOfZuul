package UI.refactoredUI.actionmessage;

import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IActionMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ActionMessage extends Component implements IActionMessage {

    @FXML
    private Label gestureText;

    @FXML
    private Label keyHint;

    @FXML
    private Label actionText;


    public ActionMessage(){super("actionmessage_view.fxml");}

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
