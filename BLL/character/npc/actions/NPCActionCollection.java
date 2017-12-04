package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;

/**
 * A collection of NPCActions.
 * The interface is to set the actions of an NPC by using an object.
 */
public interface NPCActionCollection {
	/**
	 * Gets the actions of the NPC as {@link INPCAction}.
	 * @return An array of INPCAction
	 */
	INPCAction[] getActions();
}
