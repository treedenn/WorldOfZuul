package BLL.entity;

import BLL.world.Planet;

import java.util.Map;

/**
 * MovableEntity is a 'living' creature, who has the ability to move from planet to planet.
 */
public abstract class MovableEntity extends Entity implements Movable {
	private Map<String, Planet> planets;
	private boolean canMove;

	public MovableEntity() {
		this(null, null);
		canMove = true;
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
	 * Gets a map containing all the planets available for the entity.
	 */
	public Map<String, Planet> getPlanets() {
		return planets;
	}

	/**
	 * Sets the planets for the entity.
	 */
	public void setPlanets(Map<String, Planet> planets) {
		this.planets = planets;
	}

	@Override
	public boolean canMove() {
		return canMove;
	}

	@Override
	public void setMove(boolean canMove) {
		this.canMove = canMove;
	}
}
