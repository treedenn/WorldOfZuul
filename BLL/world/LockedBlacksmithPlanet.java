package BLL.world;

import BLL.Game;
import BLL.entity.player.Player;

/**
 * A planet that is lockable.
 */
public class LockedBlacksmithPlanet extends Planet implements Lockable {
	public LockedBlacksmithPlanet(String name, String description, double x, double y) {
		super(name, description, x, y);
	}

	// TODO: I do not know if this is allowed.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUnlocked() {
		Player player = (Player) Game.getInstance().getPlayer();
		return player.getMorphId() == 1;
	}
}