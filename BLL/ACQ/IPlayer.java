package BLL.ACQ;

import java.util.Map;

/**
 * Limits the functionality of {@link BLL.character.player.Player}.
 * Casted from Player and sent to the GUI.
 */
public interface IPlayer {
	/**
	 * Gets the current planet the player is on.
	 * @return the current planet
	 */
	IPlanet getCurrentPlanet();

	/**
	 * Gets the all the planets available as {@link IPlanet} for the player.
	 * @return a map. Key is planet name and value is the IPlanet.
	 */
	Map<String, ? extends IPlanet> getPlanets();

	/**
	 * Returns a boolean on whether the fuel is empty or not.
	 * @return true, if fuel is empty
	 */
	boolean isFuelEmpty();

	/**
	 * Gets the amount of fuel the player has.
	 * @return the fuel level
	 */
	double getFuel();

	/**
	 * Gets the total amount of fuel the player can have at a given time.
	 * @return the maximum fuel level
	 */
	int getMaxFuel();

	/**
	 * Gets the inventory of the player as {@link IInventory}.
	 * @return inventory cased as IInventory.
	 */
	IInventory getIInventory();

	/**
	 * Gets the morph id of the player.
	 * Default is -1.
	 * @return the morph id
	 */
	int getMorphId();
}
