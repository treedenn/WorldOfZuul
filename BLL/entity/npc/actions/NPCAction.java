package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.PersistenceLayer;
import BLL.Game;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;

public class NPCAction implements INPCAction {
	private final String DEFAULT_MESSAGE;
	protected String message;

	public NPCAction(String message) {
		DEFAULT_MESSAGE = message;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void onStartEvent(NPC npc, Game game) {
		message = DEFAULT_MESSAGE;
	}

	@Override
	public void onEndEvent(NPC npc, Game game) {

	}
}
