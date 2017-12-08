package BLL.ACQ;

import BLL.entity.Inventory;

/**
 * Limits the functionality of {@link Inventory}.
 * Casted from Inventory and sent to the GUI.
 */
public interface IInventory {
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