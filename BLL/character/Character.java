package BLL.character;

import BLL.ACQ.IPlanet;
import BLL.world.Planet;

import java.util.Map;

/**
 * Any object that derives from Character is a movable entity.
 * The definition of movable is it can travel from planet to planet.
 */
public abstract class Character {
	private Planet currentPlanet;
	private Map<String, Planet> planets;

	protected Character() {
		this(null, null);
	}

	public Character(Planet currentPlanet, Map<String, Planet> planets) {
		this.currentPlanet = currentPlanet;
		this.planets = planets;
	}

	/**
	 * Gets the planet that the character is currently on.
	 * @return the current planet
	 */
	public Planet getCurrentPlanet() {
		return currentPlanet;
	}

	/**
	 * Sets the character's current planet to another planet.
	 * @param currentPlanet the new planet
	 */
	public void setCurrentPlanet(Planet currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	/**
	 * Gets a map of all the planet currently available.
	 * @return the map of all the planets
	 */
	public Map<String, Planet> getPlanets() {
		return planets;
	}

	/**
	 * Sets the planet map to a new map.
	 * @param planets the new map
	 */
	public void setPlanets(Map<String, Planet> planets) {
		this.planets = planets;
	}

	/**
	 * Checks if the character is on the planet being inserted.
	 * @param planet to check for
	 * @return true, if it is the same planet
	 */
	public boolean samePlanet(IPlanet planet) {
		return getCurrentPlanet() == planet;
	}

	/**
	 * Sets the current planet to the planet of the entered name.
	 * @param planetName name of the planet
	 */
	public void go(String planetName) {
		currentPlanet = planets.get(planetName);
	}
}
