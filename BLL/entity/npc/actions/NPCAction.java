package BLL.entity.npc.actions;

import BLL.Game;
import BLL.entity.npc.NPC;

/**
 * The base of all NPC actions.
 * It simply contains a message to be displayed.
 */
public class NPCAction {
	private final String DEFAULT_MESSAGE;
	protected String message;

	/**
	 * Constructs a new NPC action.
	 * @param message to be displayed
	 */
	public NPCAction(String message) {
		DEFAULT_MESSAGE = message;
		this.message = message;
	}

	/**
	 * Gets the message of the action.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Whenever an action starts, this method has to be invoked.
	 * The base always sets the message to default,
	 * since derives classes can overwrite message.
	 */
	public void onStartEvent(NPC npc, Game game) {
		message = DEFAULT_MESSAGE;
	}

	/**
	 * Whenever an actio ends, this method has to be invoked.
	 * Base does nothing.
	 */
	public void onEndEvent(NPC npc, Game game) { }
}
