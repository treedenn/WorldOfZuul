package BLL.item;

import BLL.ACQ.IItem;
import BLL.ACQ.IItemStack;

/**
 * An ItemStack is a combination of an {@link Item} and a quantity.
 * You can have an item to be standalone, however,
 * you need an item stack to assign it with a quantity.
 */
public final class ItemStack implements IItemStack {
	private Item item;
	private int quantity;

	public ItemStack(Item item){
		this(item,1);
	}

	public ItemStack(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	/**
	 * Gets the item from the ItemStack.
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IItem getIItem() {
		return getItem();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the item to the value entered.
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = Math.max(quantity, 0);
	}

	/**
	 * Increases the quantity relative to the current amount.
	 * Derives from the {@link #setQuantity(int)}.
	 * @param amount
	 */
	public void increaseQuantity(int amount) {
		setQuantity(this.quantity + amount);
	}

	/**
	 * Decreases the quantity relative to the current amount.
	 * Derives from the {@link #setQuantity(int)}.
	 * @param amount
	 */
	public void decreaseQuantity(int amount) {
		setQuantity(this.quantity - amount);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getTotalWeight() {
		return item.getWeight() * quantity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%dx %s", quantity, item.toString());
	}
}
