package BLL.ACQ;

import BLL.Game;
import BLL.character.player.Player;
import BLL.item.Item;

public interface Usable {
	boolean use(Item item, Player player, Game game);
}