package BLL.ACQ;

import BLL.character.Inventory;

/**
 * Limits the functionality of {@link Inventory}.
 * Casted from Inventory and sent to the GUI.
 */
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