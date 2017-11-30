package BLL.ACQ;

import BLL.character.Inventory;

public interface IInventory extends Inventory {
	/**
	 * {@inheritDoc}
	 */
	double getMaxCapacity();

	/**
	 * {@inheritDoc}
	 */
	double getCurrentCapacity();

	/**
	 * {@inheritDoc}
	 */
	IItemStack[] getIContent();
}
