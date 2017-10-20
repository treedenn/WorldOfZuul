package DAL.character.player;

import DAL.item.Item;

public class Recipe {
	private Item[] requirements;

	public Recipe() {
		requirements = new Item[4];
		generateRecipeRequirements();
	}

	public Item[] getRequirements() {
		return requirements;
	}

	private void generateRecipeRequirements() {
		final int liquids = 14; // 0 -> 14
		final int canisters = 14; // 14 -> 28
		final int gears = 12; // 28 -> 40
		final int cpus = 16; // 40 -> 56

		requirements[0] = Item.getItemById((int) (Math.random() * liquids));
		requirements[1] = Item.getItemById((int) (liquids + Math.random() * canisters));
		requirements[2] = Item.getItemById((int) (liquids + canisters + Math.random() * gears));
		requirements[3] = Item.getItemById((int) (liquids + canisters + gears + Math.random() * cpus));
	}
}