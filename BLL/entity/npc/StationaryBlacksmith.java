package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.entity.Entity;
import BLL.entity.npc.NPC;
import BLL.entity.npc.actions.NPCActionCollection;

/**
 *
 */
public class StationaryBlacksmith extends Entity implements NPC {
	private NPCActionCollection collection;

	@Override
	public int getId() {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "Blacksmith";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGood() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public INPCAction[] getActions() {
		return collection.getActions();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActions(NPCActionCollection collection) {
		this.collection = collection;
	}
}
