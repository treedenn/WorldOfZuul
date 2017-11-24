package BLL.character.player;

import BLL.ACQ.IItemStack;
import BLL.item.Item;

public class Recipe {
	private Item[] requirements;

	public Recipe(Item[] requirements) {
		this.requirements = requirements;
	}

	public Item[] getRequirements() {
		return requirements;
	}

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

	public boolean hasItem(Item item) {
		for(Item requirement : requirements) {
			if(requirement.getName().equals(item.getName())) {
				return true;
			}
		}

		return false;
	}
}