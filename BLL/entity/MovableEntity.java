package BLL.entity;

import BLL.world.Planet;

import java.util.Map;

/**
 * MovableEntity  has the ability to move from planet to planet.
 */
public abstract class MovableEntity extends Entity implements Movable {
	private Map<String, Planet> planets;
	private boolean canMove;

	/**
	 * Constructs a new MovableEntity with default values.
	 */
	public MovableEntity() {
		this(null, null);
		canMove = true;
	}

	/**
	 * Constructs a new MovableEntity, where the available planets can be set.
	 * @param planets the where the entity can move onto
	 */
	public MovableEntity(Map<String, Planet> planets) {
		super(null);
		this.planets = planets;
	}

	/**
	 * Constructs a new MovableEntity, where everything can be set directly.
	 * @param currentPlanet
	 * @param planets
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canMove() {
		return canMove;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMove(boolean canMove) {
		this.canMove = canMove;
	}
}
