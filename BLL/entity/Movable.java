package BLL.entity;

import BLL.world.Planet;

import java.util.Map;

public interface Movable {
	/**
	 * Gets a map of all the planet currently existing.
	 * @return the map of all the planets
	 */
	Map<String, Planet> getPlanets();

	/**
	 * Sets the planet map to a new map.
	 * @param planets the new map
	 */
	void setPlanets(Map<String, Planet> planets);

	/**
	 * A method that allows the entity the move.
	 * The only exception is the player.
	 */
	void move();
}
