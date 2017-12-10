package BLL.ACQ;

import BLL.Game;
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
	 * @param npc the npc the action is on
	 * @param game the logic layer
	 */
	void onStartEvent(NPC npc, Game game);

	/**
	 * EndEvent of the action.
	 * At the end of an action, the action might wants to do something.
	 * Must be invoked as the end of each action.
	 * @param npc the npc the action is on
	 * @param game the logic layer
	 */
	void onEndEvent(NPC npc, Game game);
}
