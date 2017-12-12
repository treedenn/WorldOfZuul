package UI.gamecomponents;

import BLL.ACQ.IPlayer;
import BLL.Game;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class FuelBar extends Meter{

    public FuelBar(ProgressBar bar, Label title, Label label){
        super(bar, title, label);
    }

    @Override
    public void update() {
        IPlayer player = Game.getInstance().getPlayer();
        getBar().progressProperty().setValue(player.getFuel() / player.getMaxFuel());
        getLabel().setText(String.format(" %.2f %% ", player.getFuel()/player.getMaxFuel()*100));
    }
}
