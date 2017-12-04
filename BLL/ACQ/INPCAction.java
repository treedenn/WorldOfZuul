package BLL.ACQ;

import BLL.character.npc.NPC;
import BLL.character.player.Player;

/**
 * Limits and describes the functionality of {@link BLL.character.npc.actions.NPCAction}.
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
	 * @param persistent the persistent layer
	 */
	void onStartEvent(Player player, NPC npc, Persistent persistent);

	/**
	 * EndEvent of the action.
	 * At the end of an action, the action might wants to do something.
	 * Must be invoked as the end of each action.
	 * @param player the player invoking the event
	 * @param npc the npc the action is on
	 * @param persistent the persistent layer
	 */
	void onEndEvent(Player player, NPC npc, Persistent persistent);
}
