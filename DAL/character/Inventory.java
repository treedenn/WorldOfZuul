package DAL.character;

import DAL.item.Item;
import DAL.item.ItemStack;

public interface Inventory {
	boolean add(ItemStack itemStack);
	boolean remove(Item item);
	boolean remove(ItemStack itemStack);
	void remove(int index) throws ArrayIndexOutOfBoundsException;
	boolean contains(Item item);
	boolean contains(Item item, int quantity);
	boolean contains(ItemStack itemStack);
	ItemStack getItemStack(int index);
	ItemStack setItemStack(int index, ItemStack item) throws ArrayIndexOutOfBoundsException;
	ItemStack[] getContent();
}