package BLL.character.player;

import BLL.item.Item;
import BLL.item.ItemStack;
import DAL.Model;

public class Recipe {
	private Item[] requirements;

	public Recipe(Item[] requirements) {
		this.requirements = requirements;
	}

	public Item[] getRequirements() {
		return requirements;
	}

	public boolean[] haveItems(ItemStack[] itemStacks) {
		boolean[] containItems = new boolean[4];

		for(int i = 0; i < requirements.length; i++) {
			for(ItemStack itemStack : itemStacks) {
				if(requirements[i].getName().equals(itemStack.getItem().getName())) {
					containItems[i] = true; break;
				}
			}
		}

		return containItems;
	}

	public boolean hasItem(Item item) {
		for(Item requirement : requirements) {
			if(requirement.getName().equals(item.getName())) {
				return true;
			}
		}

		return false;
	}
}