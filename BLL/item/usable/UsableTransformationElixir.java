package BLL.item.usable;

import BLL.ACQ.Usable;
import BLL.Game;
import BLL.character.player.Player;
import BLL.character.player.buff.TransformationBuff;
import BLL.item.Item;

/**
 * This describes the functionality of the Transformation Elixir given by {@link BLL.character.npc.ProfessorPutricide}.
 */
public class UsableTransformationElixir implements Usable {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean use(Item item, Player player, Game game) {
		// TODO: add transformation buff to player.
		player.addBuff(new TransformationBuff(0, 60));
		System.out.println("Transformation Elixir has been used!");

		return true;
	}
}
