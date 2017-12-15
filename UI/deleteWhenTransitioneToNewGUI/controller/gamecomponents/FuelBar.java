package UI.deleteWhenTransitioneToNewGUI.controller.gamecomponents;

import BLL.ACQ.Domain;
import BLL.ACQ.IPlayer;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class FuelBar extends Meter{

    public FuelBar(Domain domain, ProgressBar bar, Label title, Label label){
        super(domain, bar, title, label);
    }

    @Override
    public void update() {
        IPlayer player = getDomain().getPlayer();
        getBar().progressProperty().setValue(player.getFuel() / player.getMaxFuel());
        getLabel().setText(String.format(" %.2f %% ", player.getFuel()/player.getMaxFuel()*100));
    }
}
