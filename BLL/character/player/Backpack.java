package BLL.character.player;

import BLL.ACQ.IInventory;
import BLL.ACQ.IItemStack;
import BLL.character.Inventory;
import BLL.item.Item;
import BLL.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Backpack implements IInventory {
	private double maxWeightCapacity;
	private double currentWeightCapacity;
	private List<ItemStack> items;

	Backpack(double maxCapacity) {
		this.maxWeightCapacity = maxCapacity;
		this.currentWeightCapacity = 0;
		items = new ArrayList<>();
	}

	@Override
	public double getMaxCapacity() {
		return maxWeightCapacity;
	}

	@Override
	public double getCurrentCapacity() {
		return currentWeightCapacity;
	}

	private void increaseCurrentCapacity(double amount) {
		this.currentWeightCapacity += amount;
	}

	private void decreaseCurrentCapacity(double amount) {
		this.currentWeightCapacity -= amount;
	}

	private boolean isBelowMaxCapacity(ItemStack item) {
		return item.getTotalWeight() + currentWeightCapacity <= maxWeightCapacity;
	}

	private int findItem(Item item) {
		return findItem(item, -1);
	}

	private int findItem(Item item, int quantity) {
		return findItem(new ItemStack(item, quantity));
	}

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

	@Override
	public boolean remove(Item item) {
		int index = findItem(item);

		if(index != -1) {
			remove(index);
			return true;
		}

		return false;
	}

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

	@Override
	public void remove(int index) throws ArrayIndexOutOfBoundsException {
		decreaseCurrentCapacity(items.remove(index).getTotalWeight());
	}

	@Override
	public boolean contains(Item item) {
		return findItem(item) != -1;
	}

	@Override
	public boolean contains(Item item, int quantity) {
		return findItem(item, quantity) != -1;
	}

	@Override
	public boolean contains(ItemStack itemStack) {
		return findItem(itemStack) != -1;
	}

	@Override
	public ItemStack getItemStack(int index) {
		return items.get(index);
	}

	@Override
	public ItemStack setItemStack(int index, ItemStack item) throws ArrayIndexOutOfBoundsException {
		return items.set(index, item);
	}

	@Override
	public ItemStack[] getContent() {
		return items.toArray(new ItemStack[items.size()]);
	}

	@Override
	public IItemStack[] getIContent() {
		IItemStack[] iis = new IItemStack[getContent().length];
		System.arraycopy(getContent(), 0, iis, 0, iis.length);

		return iis;
	}
}
