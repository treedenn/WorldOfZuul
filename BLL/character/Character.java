package BLL.character;

import BLL.ACQ.IPlanet;
import BLL.world.Planet;

import java.util.Map;

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

	public Planet getCurrentPlanet() {
		return currentPlanet;
	}

	public void setCurrentPlanet(Planet currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	public Map<String, Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(Map<String, Planet> planets) {
		this.planets = planets;
	}

	public boolean samePlanet(IPlanet planet) {
		return getCurrentPlanet() == planet;
	}

	public void go(String planetName) {
		currentPlanet = planets.get(planetName);
	}

	public String getPlanetNames() {
		StringBuilder builder = new StringBuilder();

		for(Planet planet : planets.values()) {
			builder.append(planet.getName().replaceAll(" ", "") + " ");
		}

		return builder.toString();
	}

	public void move() {}
}
