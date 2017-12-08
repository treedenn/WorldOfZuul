package BLL.item.usable;

import BLL.Game;
import BLL.entity.player.Player;
import BLL.item.Item;
import BLL.item.ItemPortalGun;

/**
 * This describes the functionality of the Portal Gun.
 */
public class UsablePortalGun implements Usable {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean use(Item item, Player player, Game game) {
		if(item instanceof ItemPortalGun) {
			ItemPortalGun pg = (ItemPortalGun) item;

			if(!pg.isBroken()) {
				game.setGameWon(true);
				game.setFinished(true);

				return true;
			}
		}

		return false;
	}
}