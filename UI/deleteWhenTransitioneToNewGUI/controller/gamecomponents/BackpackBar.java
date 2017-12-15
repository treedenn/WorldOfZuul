package UI.deleteWhenTransitioneToNewGUI.controller.gamecomponents;

import BLL.ACQ.Domain;
import BLL.ACQ.IInventory;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class BackpackBar extends Meter {

    public BackpackBar(Domain domain, ProgressBar bar, Label title, Label label) {
        super(domain, bar, title, label);
    }

    @Override
    public void update() {
        IInventory bp = getDomain().getPlayer().getIInventory();
        getBar().progressProperty().setValue(bp.getCurrentCapacity() / bp.getMaxCapacity());
        getLabel().setText(String.format(" [%.1f / %.1f] Kg", bp.getCurrentCapacity(), bp.getMaxCapacity()));
    }
}







