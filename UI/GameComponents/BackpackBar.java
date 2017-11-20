package UI.GameComponents;

import BLL.Game;
import BLL.character.player.Backpack;
import BLL.character.player.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class BackpackBar extends Meter{

    public BackpackBar(ProgressBar bar, Label label){
        super(bar, label);
    }

    @Override
    public void update() {
        Backpack bp = Game.getInstance().getPlayer().getBackpack();
        getBar().progressProperty().setValue(bp.getCurrentCapacity() / bp.getMaxCapacity());
        getLabel().setText(String.format("[%.1f / %.1f]", bp.getCurrentCapacity(), bp.getMaxCapacity()));
    }
}







