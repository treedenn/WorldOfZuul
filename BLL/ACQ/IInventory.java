package BLL.ACQ;

import BLL.entity.Inventory;

/**
 * Limits the functionality of {@link Inventory}.
 * Casted from Inventory and sent to the GUI.
 */
public interface IInventory {
	/**
	 * Gets the max capacity an inventory can have.
	 * @return max capacity
	 */
	double getMaxCapacity();

	/**
	 * Returns the current capacity the inventory has.
	 * @return current capacity
	 */
	double getCurrentCapacity();

	/**
	 * Gets all the content inside inventory.
	 * @return all the content inside inventory
	 */
	IItemStack[] getIContent();
}