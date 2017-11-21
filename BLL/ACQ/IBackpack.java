package BLL.ACQ;

import BLL.character.Inventory;

public interface IBackpack extends Inventory {
	double getMaxCapacity();
	double getCurrentCapacity();
}
