package BLL.entity;

import BLL.world.Planet;

import java.util.Map;

/**
 * MovableEntity is a 'living' creature, who has the ability to move from planet to planet.
 */
public abstract class MovableEntity extends Entity implements Movable {
	Map<String, Planet> planets;

	public MovableEntity() {
		this(null, null);
	}

	public MovableEntity(Map<String, Planet> planets) {
		super(null);
		this.planets = planets;
	}

	public MovableEntity(Planet currentPlanet, Map<String, Planet> planets) {
		super(currentPlanet);
		this.planets = planets;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Planet> getPlanets() {
		return planets;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPlanets(Map<String, Planet> planets) {
		this.planets = planets;
	}
}
