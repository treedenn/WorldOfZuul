package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;

public class NPCAction implements INPCAction {
	String message;

	public NPCAction(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void onStartEvent(Player player, NPC npc, Persistent persistent) {

	}

	@Override
	public void onEndEvent(Player player, NPC npc, Persistent persistent) {

	}
}
