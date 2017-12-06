package BLL.entity.player;

import BLL.ACQ.IItemStack;
import BLL.item.Item;

/**
 * Recipe is the object holding all the requirements for the player to obtain.
 */
public class Recipe {
	private Item[] requirements;

	public Recipe(Item[] requirements) {
		this.requirements = requirements;
	}

	/**
	 * Gets an array of the requirements as {@link Item}.
	 * @return array of requirements
	 */
	public Item[] getRequirements() {
		return requirements;
	}

	/**
	 * Searches the recipe for the given items.
	 * A boolean will be set based on existing.
	 * @param itemStacks the itemstacks to search for
	 * @return a boolean array of whether the items is in the recipe
	 */
	public boolean[] haveItems(IItemStack[] itemStacks) {
		boolean[] containItems = new boolean[4];

		for(int i = 0; i < requirements.length; i++) {
			for(IItemStack itemStack : itemStacks) {
				if(requirements[i].getName().equals(itemStack.getIItem().getName())) {
					containItems[i] = true; break;
				}
			}
		}

		return containItems;
	}

	/**
	 * Checks if the item exists in the recipe
	 * @param item the item to search for
	 * @return true, if exists
	 */
	public boolean hasItem(Item item) {
		for(Item requirement : requirements) {
			if(requirement.getName().equals(item.getName())) {
				return true;
			}
		}

		return false;
	}
}