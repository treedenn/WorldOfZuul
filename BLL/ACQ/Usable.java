package BLL.ACQ;

import BLL.Game;
import BLL.character.player.Player;
import BLL.item.Item;

public interface Usable {
	/**
	 * Whenever an item is used, this is the function that will be invoked.
	 * @param item is the owner of the usable being invoked
	 * @param player the player using the item
	 * @param game a layer to receive variables within
	 * @return a boolean based on the successful of use
	 */
	boolean use(Item item, Player player, Game game);
}