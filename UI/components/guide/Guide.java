package UI.components.guide;

import UI.components.components.Component;
import UI.components.components.IEventListener;
import UI.components.components.IGuide;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Guide extends Component implements IGuide{

    private List<IEventListener> onCloseSubscribers = new ArrayList<>();

    public Guide(){super("guide_view.fxml");}

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

    @FXML
    void closeHelp(ActionEvent event) {
        onCloseSubscribers.forEach(listener -> listener.onAction(null));
    }

}
