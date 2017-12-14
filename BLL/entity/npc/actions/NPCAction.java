package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.Game;
import BLL.entity.npc.NPC;

/**
 * The base of all NPC actions.
 * It simply contains a message to be displayed.
 */
public class NPCAction implements INPCAction {
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
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * {@inheritDoc}
	 * The base always sets the message to default,
	 * since derives classes can overwrite message.
	 */
	@Override
	public void onStartEvent(NPC npc, Game game) {
		message = DEFAULT_MESSAGE;
	}

	/**
	 * {@inheritDoc}
	 * Base does nothing.
	 */
	@Override
	public void onEndEvent(NPC npc, Game game) {

	}
}
