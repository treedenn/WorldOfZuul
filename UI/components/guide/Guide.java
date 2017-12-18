package UI.components.guide;

import UI.components.icomponents.Component;
import UI.components.icomponents.IEventListener;
import UI.components.icomponents.IGuide;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This concrete GUI handles the highscore view.
 */
public class Guide extends Component implements IGuide{

    /** List of observers. */
    private List<IEventListener> onCloseSubscribers = new ArrayList<>();

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public Guide(){super("guide_view.fxml");}

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
     * Method to notify all subscribers to the onClose()-method.
     * @param event
     */
    @FXML
    void closeHelp(ActionEvent event) {
        onCloseSubscribers.forEach(listener -> listener.onAction(null));
    }

}
