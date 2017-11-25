package UI.GameComponents;

import BLL.ACQ.IInventory;
import BLL.Game;
import BLL.character.Inventory;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class BackpackBar extends Meter{

    public BackpackBar(ProgressBar bar, Label label){
        super(bar, label);
    }

    // TODO: Lasse, you cannot go directly to the game instance (line 20)

    // TODO: Dennis, this is not my work but still needs to be fixed (line 20)

    @Override
    public void update() {
        IInventory bp = Game.getInstance().getPlayer().getIInventory();
        getBar().progressProperty().setValue(bp.getCurrentCapacity() / bp.getMaxCapacity());
        getLabel().setText(String.format(" [%.1f / %.1f] Kg", bp.getCurrentCapacity(), bp.getMaxCapacity()));
    }
}







