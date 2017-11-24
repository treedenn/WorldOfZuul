package BLL.ACQ;

import BLL.character.Inventory;

public interface IInventory extends Inventory {
	double getMaxCapacity();
	double getCurrentCapacity();
	IItemStack[] getIContent();
}
