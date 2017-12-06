package BLL.item.usable;

import BLL.Game;
import BLL.entity.player.Player;
import BLL.item.Item;

/**
 * This describes the functionality of the Portal Gun.
 */
public class UsablePortalGun implements Usable {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean use(Item item, Player player, Game game) {
		game.setGameWon(true);
		game.setFinished(true);

		return true;
	}
}