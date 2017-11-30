package BLL.item.usable;

import BLL.ACQ.Usable;
import BLL.Game;
import BLL.character.player.Player;
import BLL.item.Item;

/**
 * An {@link Usable} can be assigned to an item to give it functionality when {@link Item#use(Player, Game)} is invoked.
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