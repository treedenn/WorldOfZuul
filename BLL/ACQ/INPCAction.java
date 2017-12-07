package BLL.ACQ;

import BLL.entity.npc.NPC;
import BLL.entity.player.Player;

/**
 * Limits and describes the functionality of {@link BLL.entity.npc.actions.NPCAction}.
 * Casted from NPCAction and sent to the GUI.
 */
public interface INPCAction {
	/**
	 * Gets the message of the action.
	 * @return the message
	 */
	String getMessage();

	/**
	 * StartEvent of the action.
	 * It changes the action dynamically based on what is inside the statement.
	 * Must be invoked as the beginning of each action.
	 * @param player the player invoking the event
	 * @param npc the npc the action is on
	 * @param persistenceLayer the persistenceLayer layer
	 */
	void onStartEvent(Player player, NPC npc, PersistenceLayer persistenceLayer);

	/**
	 * EndEvent of the action.
	 * At the end of an action, the action might wants to do something.
	 * Must be invoked as the end of each action.
	 * @param player the player invoking the event
	 * @param npc the npc the action is on
	 * @param persistenceLayer the persistenceLayer layer
	 */
	void onEndEvent(Player player, NPC npc, PersistenceLayer persistenceLayer);
}
