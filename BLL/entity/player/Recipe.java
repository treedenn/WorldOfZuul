package BLL.entity.player;

import BLL.ACQ.IItemStack;
import BLL.item.Item;
import BLL.item.ItemStack;

/**
 * Recipe is the object holding all the requirements for the player to obtain to repair the portal gun.
 */
public class Recipe {
	private ItemStack[] requirements;

	/**
	 * Constructs a new Recipe with requirements.
	 * @param requirements requirements for
	 */
	public Recipe(ItemStack[] requirements) {
		this.requirements = requirements;
	}

	/**
	 * Gets an array of the requirements as {@link Item}.
	 * @return array of requirements
	 */
	public ItemStack[] getRequirements() {
		return requirements;
	}

	/**
	 * Searches the recipe for the given items.
	 * A boolean will be set based on existing.
	 * @param itemStacks the itemstacks to search for
	 * @return a boolean array of whether the items is in the recipe
	 */
	public boolean[] haveItems(IItemStack[] itemStacks) {
		boolean[] containItems = new boolean[requirements.length];

		for(int i = 0; i < requirements.length; i++) {
			for(IItemStack itemStack : itemStacks) {
				if(requirements[i].equals(itemStack)) {
					containItems[i] = true; break;
				}
			}
		}

		return containItems;
	}
}