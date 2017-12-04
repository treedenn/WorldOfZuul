package BLL.ACQ;

/**
 * Limits the functionality of {@link BLL.item.ItemStack}.
 * Casted from ItemStack and sent to the GUI.
 */
public interface IItemStack {
	/**
	 * Gets the item as an IItem
	 * @return item as IItem
	 */
	IItem getIItem();

	/**
	 * Gets the quantity.
	 * @return
	 */
	int getQuantity();

	/**
	 * Gets the total weight.
	 * Quantity times the weight of the item.
	 * @return
	 */
	double getTotalWeight();
}
