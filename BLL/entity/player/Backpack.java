package BLL.entity.player;

import BLL.ACQ.IItemStack;
import BLL.entity.Inventory;
import BLL.item.Item;
import BLL.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Backpack is an inventory, based on the inventory.
 * It is focused on weight instead of the amount of items inside.
 */
public class Backpack implements Inventory {
	private double maxWeightCapacity;
	private double currentWeightCapacity;
	private List<ItemStack> items;

	Backpack(double maxCapacity) {
		this.maxWeightCapacity = maxCapacity;
		this.currentWeightCapacity = 0;
		items = new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMaxCapacity() {
		return maxWeightCapacity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getCurrentCapacity() {
		return currentWeightCapacity;
	}

	/**
	 * Increases the current capacity with argument.
	 * @param amount to increase
	 */
	private void increaseCurrentCapacity(double amount) {
		this.currentWeightCapacity += amount;
	}

	/**
	 * Decreases the current capacity with argument.
	 * @param amount to deecrease
	 */
	private void decreaseCurrentCapacity(double amount) {
		this.currentWeightCapacity -= amount;
	}

	/**
	 * Checks if the given ItemStack and its content combined is below the max capacity.
	 * @param item to check for
	 * @return a boolean based, if it is capable
	 */
	private boolean isBelowMaxCapacity(ItemStack item) {
		return item.getTotalWeight() + currentWeightCapacity <= maxWeightCapacity;
	}

	/**
	 * Searches for an item inside inventory and returns it index.
	 * Method derives from {@link #findItem(ItemStack)}.
	 * @param item to search for
	 * @return the index if found
	 */
	private int findItem(Item item) {
		return findItem(item, -1);
	}

	/**
	 * Searches for an item inside inventory and returns it index.
	 * Method derives from {@link #findItem(ItemStack)}.
	 * @param item to search for
	 * @param quantity the at least amount the item has to have
	 * @return the index if found
	 */
	private int findItem(Item item, int quantity) {
		return findItem(new ItemStack(item, quantity));
	}

	/**
	 * Searches for an item inside inventory and returns it index.
	 * @param itemStack to search for
	 * @return the index if found
	 */
	private int findItem(ItemStack itemStack) {
		ItemStack is;

		for(int i = 0; i < items.size(); i++) {
			is = items.get(i);

			if(is.getItem().equals(itemStack.getItem())) {
				if(is.getQuantity() == -1 || is.getQuantity() >= itemStack.getQuantity()) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(ItemStack itemStack) {
		if(isBelowMaxCapacity(itemStack)) {
			int indexExists = findItem(itemStack);

			if(indexExists != -1) {
				ItemStack existingStack = items.get(indexExists);

				decreaseCurrentCapacity(existingStack.getTotalWeight());
				existingStack.increaseQuantity(itemStack.getQuantity());
				increaseCurrentCapacity(existingStack.getTotalWeight());
			} else {
				items.add(itemStack);
				increaseCurrentCapacity(itemStack.getTotalWeight());
			}

			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(Item item) {
		int index = findItem(item);

		if(index != -1) {
			remove(index);
			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(ItemStack itemStack) {
		int index = findItem(itemStack.getItem());

		if(index != -1) {
			ItemStack existingStack = items.get(index);

			if(existingStack.getQuantity() - itemStack.getQuantity() == 0) {
				remove(index);
			} else{
				existingStack.decreaseQuantity(itemStack.getQuantity());
				decreaseCurrentCapacity(itemStack.getTotalWeight());
			}

			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(int index) throws ArrayIndexOutOfBoundsException {
		decreaseCurrentCapacity(items.remove(index).getTotalWeight());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Item item) {
		return findItem(item) != -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Item item, int quantity) {
		return findItem(item, quantity) != -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(ItemStack itemStack) {
		return findItem(itemStack) != -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack getItemStack(int index) {
		return items.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack setItemStack(int index, ItemStack item) throws ArrayIndexOutOfBoundsException {
		return items.set(index, item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack[] getContent() {
		return items.toArray(new ItemStack[items.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IItemStack[] getIContent() {
		IItemStack[] iis = new IItemStack[getContent().length];
		System.arraycopy(getContent(), 0, iis, 0, iis.length);

		return iis;
	}
}
