package BLL.character.player.buff;

import BLL.character.player.Player;

public interface Buff {
	void onApply(Player player);
	void onGameTick(Player player);
	void onEnd(Player player);
	boolean isExpired();
}
