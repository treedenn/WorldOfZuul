package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.entity.Entity;
import BLL.entity.npc.actions.NPCActionCollection;

/**
 * Contains all the functionality of the Stationary Blacksmith NPC.
 * To seperate the movable Blacksmith - a stationary Blacksmith was created.
 * First time you meet the Blacksmith this is the one you will meet and
 * it will initiate the movable Blacksmith.
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
