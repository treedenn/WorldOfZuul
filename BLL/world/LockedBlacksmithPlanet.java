package BLL.world;

import BLL.Game;
import BLL.entity.player.Player;

/**
 * A normal planet, however, it implements the {@link Lockable} interface.
 */
public class LockedBlacksmithPlanet extends Planet implements Lockable {
	/**
	 * Constructs a new planet.
	 * @param name name of planet
	 * @param description description of planet
	 * @param imagePath image when entering the planet
	 * @param map2DPath the sphere image as 2D
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public LockedBlacksmithPlanet(String name, String description, String imagePath, String map2DPath, double x, double y) {
		super(name, description, imagePath, map2DPath, x, y);
	}

	/**
	 * {@inheritDoc}
	 *
	 * When the player's morph id is zero, the planet will unlock.
	 */
	@Override
	public boolean isUnlocked() {
		Player player = (Player) Game.getInstance().getPlayer();
		return player.getMorphId() == 0;
	}
}