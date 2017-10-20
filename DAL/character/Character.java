package DAL.character;

import DAL.world.Planet;

import java.util.Map;

public abstract class Character {
	private Planet currentPlanet;
	private Map<String, Planet> planets;

	public Character() {
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

	public boolean samePlanet(String planetName) {
		return currentPlanet.getName().equals(planetName);
	}

	public boolean goPlanet(String planetName) {
		if(planets.containsKey(planetName)) {
			currentPlanet = planets.get(planetName);
			return true;
		}

		return false;
	}

	public String getPlanetNames() {
		StringBuilder builder = new StringBuilder();

		for(String planetName : planets.keySet()) {
			builder.append(planetName.replaceAll(" ", "") + " ");
		}

		return builder.toString();
	}

	public void move() {}
}
