package BLL.item.usable;

import BLL.Game;
import BLL.entity.player.Player;
import BLL.entity.player.buff.TransformationBuff;
import BLL.item.Item;

/**
 * This describes the functionality of the Transformation Elixir given by {@link BLL.entity.npc.ProfessorPutricide}.
 */
public class UsableTransformationElixir implements Usable {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean use(Item item, Player player, Game game) {
		// TODO: add transformation buff to player.
		player.addBuff(new TransformationBuff(0, 60000));

		return true;
	}
}
