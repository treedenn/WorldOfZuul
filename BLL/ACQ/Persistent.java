package BLL.ACQ;

import BLL.UsableHandler;
import BLL.character.player.Quiz;
import BLL.item.Item;
import BLL.scoring.Score;
import BLL.world.Planet;

import java.util.List;
import java.util.Map;

public interface Persistent {
	void load();
	void setUsableHandler(UsableHandler handler);
	Item getItemById(int index);
	Map<String, Planet> getPlanets();
	List<Quiz> getQuizes();
	List<Score> getHighscore();
	void saveHighscore();
	String getMessage(String key);
}
