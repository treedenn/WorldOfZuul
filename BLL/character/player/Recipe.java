package BLL.character.player;

import BLL.item.Item;
import BLL.item.ItemStack;
import DAL.Model;

public class Recipe {
	private Item[] requirements;

	public Recipe() {
		requirements = new Item[4];
		generateRecipeRequirements();
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

	private void generateRecipeRequirements() {
		final int liquids = 14; // 0 -> 14
		final int canisters = 12; // 14 -> 26
		final int gears = 14; // 26 -> 40
		final int cpus = 16; // 40 -> 56
		/*
		requirements[0] = Item.getItemById((int) (Math.random() * liquids));
		requirements[1] = Item.getItemById((int) (liquids + Math.random() * canisters));
		requirements[2] = Item.getItemById((int) (liquids + canisters + Math.random() * gears));
		requirements[3] = Item.getItemById((int) (liquids + canisters + gears + Math.random() * cpus));
		*/
		requirements[0] = Model.getItemById(0);
		requirements[1] = Model.getItemById(liquids);
		requirements[2] = Model.getItemById(liquids + canisters);
		requirements[3] = Model.getItemById(liquids + canisters + gears);
	}
}