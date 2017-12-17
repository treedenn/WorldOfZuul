package BLL;

import BLL.item.ItemStack;
import BLL.world.Planet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A utility class for the game.
 * It contains small functions, which can be useful in the business layer.
 */
public final class GameUtility {
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

	/**
	 * Gets a random planet based on the array of planets inside the array.
	 * @param planets an array of planets
	 * @return a random planet within the array
	 */
	public static Planet getRandomPlanet(Planet[] planets) {
		int rand = (int) (Math.random() * planets.length);
		return planets[rand];
	}

	/**
	 * Extended version of {@link #getRandomPlanet(Planet[])}.
	 * It excludes Xehna as an option.
	 * @param planets an array of planets
	 * @return a random planet within the array
	 */
	public static Planet getRandomPlanetNotXehna(Planet[] planets) {
		Planet planet;
		do {
			planet = GameUtility.getRandomPlanet(planets);
		} while(planet.getName().equalsIgnoreCase("xehna"));

		return planet;
	}

	/**
	 * Looks inside a string for placeholders and converts them.
	 * EXAMPLE:
	 * replacePlaceHolder("Group {X} is {STATE}", "{X}", "24", "{STATE}", "AWESOME!");
	 * -> Group 24 is AWESOME!
	 * @param text with the placeholders
	 * @param strings every first string is the placeholder and the second is the string to replace at the given placeholder
	 * @return a new string, where the placeholders has been replaced with their corresponding value.
	 */
	public static String replacePlaceHolders(String text, String ... strings) {
		if(strings.length % 2 == 1) { return null; }

		StringBuilder sb = new StringBuilder(text);

		int startIndex;
		char[] signature, value;
		for(int i = 0; i < strings.length / 2; i++) {
			signature = strings[i * 2].toCharArray();
			value = strings[i * 2 + 1].toCharArray();

			startIndex = sb.indexOf(String.valueOf(signature));

			if(startIndex == -1) { continue; }

			sb.delete(startIndex, startIndex + signature.length);
			sb.insert(startIndex, value);
		}

		return sb.toString();
	}
}
