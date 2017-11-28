package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.character.npc.NPC;
import BLL.character.player.Player;

public class NPCAction implements INPCAction {
	String message;

	public NPCAction(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return null;
	}

	@Override
	public void onStartEvent(Player player, NPC npc, Persistent persistent) {

	}

	@Override
	public void onEndEvent(Player player, NPC npc, Persistent persistent) {

	}
}
