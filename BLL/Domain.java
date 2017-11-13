package BLL;

import BLL.character.player.Player;
import BLL.scoring.Score;

import java.util.List;

public interface Domain {
	Player getPlayer();
	boolean hasBeatenHighscore();
	void addPlayerToHighscore(String playerName);
	List<Score> getHighscore();
}
