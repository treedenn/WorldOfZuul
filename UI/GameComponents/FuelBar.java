package UI.GameComponents;

import BLL.Game;
import BLL.character.player.Backpack;
import BLL.character.player.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class FuelBar extends Meter{

    public FuelBar(ProgressBar bar, Label label){
        super(bar, label);
    }

    @Override
    public void update() {
        Player player = Game.getInstance().getPlayer();
        getBar().progressProperty().setValue(player.getFuel() / player.getMaxFuel());
        getLabel().setText(String.format("[%.0f / %d]", player.getFuel(), player.getMaxFuel()));

    }
}
