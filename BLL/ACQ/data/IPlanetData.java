package BLL.ACQ.data;

import BLL.item.ItemStack;

import java.util.List;

/**
 * Interface makes a contract by telling what is the required data to save and load within the planets.
 * When the business layer sets, the persistent layer gets them.
 * When the persistent layer sets, the business layer gets them.
 */
public interface IPlanetData {
	/**
	 * The amount of planets inside the data.
	 * @return the size
	 */
	int size();

	/**
	 * Adds the data of the planet, that must be saved.
	 * @param name the planet's name
	 * @param x the planet's x coordinate
	 * @param y the planet's y coordinate
	 * @param NPCs NPCs of the planet
	 * @param itemStacks the inventory/itemstacks on the planet
	 */
	void addData(String name, double x, double y, List<Integer> NPCs, ItemStack[] itemStacks);

	/**
	 * Gets the name at index.
	 * @param index position
	 * @return name
	 */
	String getName(int index);

	/**
	 * Sets the name on index.
	 * @param index position
	 * @param name planet's name
	 */
	void setName(int index, String name);

	/**
	 * Gets the x coordinate at index.
	 * @param index position
	 * @return x coordinate of planet
	 */
	double getX(int index);

	/**
	 * Sets the x coordinate on index.
	 * @param index position
	 * @param x coordinate of planet
	 */
	void setX(int index, double x);

	/**
	 * Gets the y coordinate at index.
	 * @param index position
	 * @return y coordinate of planet
	 */
	double getY(int index);

	/**
	 * Sets the y coordinate on index.
	 * @param index position
	 * @param y coordinate of planet
	 */
	void setY(int index, double y);

	/**
	 * Gets a list of NPC ids at index.
	 * @param index position
	 * @return npc ids at position
	 */
	List<Integer> getNPCIds(int index);

	/**
	 * Sets the list of NPC ids on index.
	 * @param index position
	 * @param npcs ids of npcs
	 */
	void setNPCs(int index, List<Integer> npcs);

	/**
	 * Gets the content at index.
	 * @param index position
	 * @return content of planet at index
	 */
	ItemStack[] getInventory(int index);

	/**
	 * Sets the content on index.
	 * @param index position
	 * @param itemStacks content of planet
	 */
	void setInventory(int index, ItemStack[] itemStacks);
}
