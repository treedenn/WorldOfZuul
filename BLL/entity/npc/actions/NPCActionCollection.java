package BLL.entity.npc.actions;

/**
 * A collection of NPCActions.
 * The interface is to set the actions of an NPC by using an object.
 */
public interface NPCActionCollection {
	/**
	 * Gets the actions of the NPC as {@link NPCAction}.
	 * @return An array of INPCAction
	 */
	NPCAction[] getActions();
}
