package BLL.entity;

import BLL.ACQ.IPlanet;
import BLL.world.Planet;

/**
 * Entity is a 'living' creature, who can be on a planet.
 */
public abstract class Entity {
	private Planet currentPlanet;

	protected Entity() {
		this(null);
	}

	public Entity(Planet currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	/**
	 * Gets the planet that the entity is currently on.
	 * @return the current planet
	 */
	public Planet getCurrentPlanet() {
		return currentPlanet;
	}

	/**
	 * Sets the entity's current planet to another planet.
	 * @param currentPlanet the new planet
	 */
	public void setCurrentPlanet(Planet currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	/**
	 * Checks if the entity is on the planet being inserted.
	 * @param planet to check for
	 * @return true, if it is the same planet
	 */
	public boolean isOnPlanet(IPlanet planet) {
		return getCurrentPlanet() == planet;
	}
}
