package BLL.ACQ;

import BLL.character.npc.NPC;
import BLL.character.player.Player;

public interface INPCAction {
	String getMessage();
	void onStartEvent(Player player, NPC npc, Persistent persistent);
	void onEndEvent(Player player, NPC npc, Persistent persistent);
}
