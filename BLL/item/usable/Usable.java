package BLL.item.usable;

import BLL.Game;
import BLL.entity.player.Player;
import BLL.item.Item;

/**
 * A {@link Usable} can be assigned to an item to give it functionality when {@link Item#use(Player, Game)} is invoked.
 */
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