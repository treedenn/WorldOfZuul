package UI.GameComponents;

import BLL.ACQ.IPlayer;
import BLL.Game;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class FuelBar extends Meter{

    public FuelBar(ProgressBar bar, Label label){
        super(bar, label);
    }

    @Override
    public void update() {
        IPlayer player = Game.getInstance().getPlayer();
        getBar().progressProperty().setValue(player.getFuel() / player.getMaxFuel());
        getLabel().setText(String.format(" [%.2f / %d] %%", player.getFuel(), player.getMaxFuel()));

    }
}
