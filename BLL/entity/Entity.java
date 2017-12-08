package BLL.entity;

import BLL.ACQ.IPlanet;
import BLL.world.Planet;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Entity is a 'living' creature, who can be on a planet.
 */
public abstract class Entity {
	private Planet currentPlanet;
	private File image;

	protected Entity() {
		this(null);
	}

	public Entity(Planet currentPlanet) { this.currentPlanet = currentPlanet; }

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

	/**
	 * Gets a object of type File containing the Entity's image
	 * @return	the file of the Entity's image.
	 */
	public File getImage() { return image;}


	// TODO: Check if the file actually is a file! Throw exception
	/**
	 * Sets the Entity's image to a File reference
	 * @param image	the file of the Entity's image
	 */
	public void setImage(File image){ this.image = image; }
}
