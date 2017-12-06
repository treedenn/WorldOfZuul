package BLL.entity;

import BLL.ACQ.INPCAction;
import BLL.entity.npc.NPC;
import BLL.entity.npc.actions.NPCActionCollection;

/**
 *
 */
public class StationaryBlacksmith extends Entity implements NPC {
	private NPCActionCollection collection;

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
