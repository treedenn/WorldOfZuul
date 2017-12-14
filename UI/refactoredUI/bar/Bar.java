package UI.refactoredUI.bar;

import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IBar;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class Bar extends Component implements IBar {

    @FXML
    private Label legend;

    @FXML
    private Label textualValue;

    @FXML
    private ProgressBar meter;


    public Bar(){super("bar_view.fxml");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(double value, String textualValue) {
        meter.progressProperty().setValue(value);
        this.textualValue.setText(textualValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLegend(String text) {
        legend.setText(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setStyle(BarStyle barStyle) {
        switch (barStyle){
            case RED:
                meter.getStyleClass().add("meter--red");
                return true;
            case GREEN:
                meter.getStyleClass().add("meter--green");
                return true;
            default:
                return false;
        }
    }
}
