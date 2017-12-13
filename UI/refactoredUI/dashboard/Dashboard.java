package UI.refactoredUI.dashboard;

import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IDashboard;
import UI.refactoredUI.components.IEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Dashboard extends Component implements IDashboard{

    private List<IEventListener<?>> onBackpackBarClickSubscribers = new ArrayList<>();
    private List<IEventListener<?>> onHelpSubscribers = new ArrayList<>();

    public Dashboard(){super("dashboard_view.fxml");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackpackBarClick(IEventListener<?> listener) {
        onBackpackBarClickSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onHelp(IEventListener<?> listener) {
        onHelpSubscribers.add(listener);
    }


    @FXML
    void help(ActionEvent event) {
        onHelpSubscribers.forEach(listener -> listener.onAction(null));
    }

}
