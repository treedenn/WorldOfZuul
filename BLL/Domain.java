package BLL;

import BLL.scoring.Score;

import java.util.List;

public interface Domain {
	boolean hasBeatenHighscore();
	void addPlayerToHighscore(String playerName);
	List<Score> getHighscore();
}
