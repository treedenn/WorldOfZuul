package BLL.item.usable;

import BLL.ACQ.Usable;
import BLL.Game;
import BLL.character.player.Player;
import BLL.character.player.buff.TransformationBuff;
import BLL.item.Item;

public class UsableTransformationElixir implements Usable {
	@Override
	public boolean use(Item item, Player player, Game game) {
		// TODO: add transformation buff to player.
		player.addBuff(new TransformationBuff(0, 60));
		System.out.println("Transformation Elixir has been used!");

		return true;
	}
}
