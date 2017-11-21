package UI.GameComponents;

import BLL.ACQ.IBackpack;
import BLL.Game;
import BLL.character.player.Backpack;
import BLL.character.player.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class BackpackBar extends Meter{

    public BackpackBar(ProgressBar bar, Label label){
        super(bar, label);
    }

    // TODO: Lasse, you cannot go directly to the game instance (line 20)

    @Override
    public void update() {
        IBackpack bp = (IBackpack) Game.getInstance().getPlayer().getInventory();
        getBar().progressProperty().setValue(bp.getCurrentCapacity() / bp.getMaxCapacity());
        getLabel().setText(String.format("[%.1f / %.1f]", bp.getCurrentCapacity(), bp.getMaxCapacity()));
    }
}







