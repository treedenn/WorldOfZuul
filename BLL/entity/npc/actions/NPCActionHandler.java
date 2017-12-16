package BLL.entity.npc.actions;

import BLL.Game;
import BLL.entity.npc.NPC;

/**
 * Handles the NPC Actions. Allowing to start and end an interaction.
 */
public class NPCActionHandler {
	/**
	 * It triggers the start event of the action inside a specific NPC.
	 * At every action, the GUI must invoke this function first before doing anything.
	 * @param game this game instance
	 * @param npc is the specific NPC for interaction
	 * @param actionId is the specific action of the NPC
	 */
	public void startInteraction(Game game, NPC npc, int actionId) {
		if(npc != null) {
			NPCAction[] actions = npc.getActions();

			if(actions[actionId] instanceof NPCJumpAction) {
				((NPCJumpAction) actions[actionId]).resetActionId();
			}

			actions[actionId].onStartEvent(npc, game);
			System.out.println("START EVENT");
		}
	}

	/**
	 * It triggers the end event of the action inside specific NPC.
	 * At every action, the GUI must invoke this function last.
	 * @param game this game instance
	 * @param npc is the specific NPC for interaction
	 * @param actionId is the specific action of the NPC
	 */
	public void endInteraction(Game game, NPC npc, int actionId) {
		if(npc != null) {
			NPCAction[] actions = npc.getActions();
			actions[actionId].onEndEvent(npc, game);
			System.out.println("END EVENT");
		}
	}
}
