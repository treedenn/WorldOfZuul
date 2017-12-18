package UI.components.dashboard;

import UI.components.avatar.Avatar;
import UI.components.bar.Bar;
import UI.components.bar.BarStyle;
import UI.components.icomponents.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This concrete GUI handles the dashboard view.
 */
public class Dashboard extends Component implements IDashboard{

    /** Reference to nested component of type {@link IAvatar}. */
    private IAvatar avatar;
    /** Reference to nested component of type {@link IBar}. */
    private IBar fuelBar;
    /** Reference to nested component of type {@link IBar}. */
    private IBar backpackBar;
    /** List of observers. */
    private List<IEventListener<?>> onBackpackSubscribers = new ArrayList<>();
    /** List of observers. */
    private List<IEventListener<?>> onHelpSubscribers = new ArrayList<>();

    @FXML
    private GridPane grid;

    @FXML
    private HBox bars;

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public Dashboard(){
        super("dashboard_view.fxml");
        avatar = new Avatar();
        fuelBar = new Bar();
        backpackBar = new Bar();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComponentLoader.loadComponent(grid, avatar.getView(), 0,0, false);
        ComponentLoader.loadComponent(bars, fuelBar.getView());
        fuelBar.setLegend("Fuel:");
        fuelBar.setStyle(BarStyle.RED);
        ComponentLoader.loadComponent(bars, backpackBar.getView());
        backpackBar.setLegend("Backpack:");
        backpackBar.setStyle(BarStyle.GREEN);
        backpackBar.getView().toBack();
        fuelBar.getView().toBack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackpack(IEventListener<?> listener) {
        onBackpackSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onHelp(IEventListener<?> listener) {
        onHelpSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IAvatar getAvatar(){
        return avatar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFuelValue(double value, String textualValue) {
        fuelBar.setValue(value, textualValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBackpackValue(double value, String textualValue) {
        backpackBar.setValue(value, textualValue);
    }

    /**
     * Method to notify every subscriber of the onHelp()-event.
     * @param event the type of user event.
     */
    @FXML
    void help(ActionEvent event) {
        onHelpSubscribers.forEach(listener -> listener.onAction(null));
    }

    /**
     * Method to notify every subscriber of the onBackpack()-event.
     * @param event the type of user event.
     */
    @FXML
    void openBackpack(ActionEvent event) { onBackpackSubscribers.forEach(listener -> listener.onAction(null));}


}
