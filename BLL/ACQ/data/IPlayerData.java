package BLL.ACQ.data;

import BLL.entity.player.buff.Buff;
import BLL.item.ItemStack;

import java.util.Set;

/**
 * Interface makes a contract by telling what is the required data to save and load within the player.
 * When the business layer sets, the persistent layer gets them.
 * When the persistent layer sets, the business layer gets them.
 */
public interface IPlayerData {
	/**
	 * Gets the current planet of the player.
	 * @return name of the planet
	 */
	String getCurrentPlanet();

	/**
	 * Sets the name of the current planet, the player was on.
	 * @param currentPlanet player's planet
	 */
	void setCurrentPlanet(String currentPlanet);

	/**
	 * Gets the x coordinate of the player.
	 * @return x coordinate
	 */
	double getX();

	/**
	 * Sets the x coordinate of the player.
	 * @param value player's x-coordinate
	 */
	void setX(double value);

	/**
	 * Gets the y coordinate of the player.
	 * @return y coordinate
	 */
	double getY();

	/**
	 * Sets the x coordinate of the player.
	 * @param value player's y-coordinate
	 */
	void setY(double value);

	/**
	 * Gets the inventory of the player in ItemStacks.
	 * @return the content of inventory
	 */
	ItemStack[] getInventory();

	/**
	 * Sets the inventory of the player in ItemStacks.
	 * @param itemStacks the content to set
	 */
	void setInventory(ItemStack[] itemStacks);

	/**
	 * Gets the buffs of the player in a set.
	 * @return a set of buffs
	 */
	Set<Buff> getBuffs();

	/**
	 * Sets the buffs of the player.
	 * @param buffs player's buffs
	 */
	void setBuffs(Set<Buff> buffs);

	/**
	 * Gets the fuel of the player.
	 * @return fuel
	 */
	double getFuel();

	/**
	 * Sets the fuel of the player.
	 * @param fuel player's fuel
	 */
	void setFuel(double fuel);

	/**
	 * Gets the total fuel consumption of the player.
	 * @return total fuel consumption
	 */
	int getFuelConsumption();

	/**
	 * Sets the fuel consumption of the player.
	 * @param fuelConsumption the total fuel consumption
	 */
	void setFuelConsumption(int fuelConsumption);
}
