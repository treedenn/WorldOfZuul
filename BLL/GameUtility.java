package BLL;

import BLL.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameUtility {
	/**
	 * Removes clues from a content/inventory.
	 * @param itemStacks the itemstack to search for clues
	 * @return content without clue
	 */
	public static ItemStack[] removeCluesFromContent(ItemStack[] itemStacks) {
		List<ItemStack> list = new ArrayList<>(itemStacks.length);

		Collections.addAll(list, itemStacks);

		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getItem().getId() == 59) {
				list.remove(i);
			}
		}

		return list.toArray(new ItemStack[list.size()]);
	}
}
