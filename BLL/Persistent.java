package BLL;

import BLL.character.player.Quiz;
import BLL.item.Item;
import BLL.world.Planet;
import BLL.scoring.Score;

import java.util.List;
import java.util.Map;

public interface Persistent {
	Map<String, Planet> getPlanets();
	List<Quiz> getQuizes();
	List<Score> getHighscore();
	void saveHighscore();
	Item getItemById(int index);
}
