package BLL.entity;

import BLL.ACQ.IPlanet;
import BLL.world.Planet;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * An entity can be on a planet.
 */
public abstract class Entity {
	private Planet currentPlanet;
	private File image;

	/**
	 * Constructs a new Entity with default value.
	 * Set methods exist.
	 */
	protected Entity() {
		this(null);
	}

	/**
	 * Constructs an Entity, where it is possible to set the current planet
	 * @param currentPlanet
	 */
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

	/**
	 * Sets the Entity's image to a File reference
	 * @param image	the file of the Entity's image
	 */
	public void setImage(File image) throws FileNotFoundException {
		// TODO: Check if the file is actually an image
		this.image = image;
		/*
		if(image.isFile()) {
			this.image = image;
		} else {
			throw new FileNotFoundException();
		}
		*/
	}
}
