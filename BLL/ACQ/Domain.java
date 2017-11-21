package BLL.ACQ;

import java.util.List;
import java.util.Map;

public interface Domain {
	void injectPersistent(Persistent persistent);
	IPlayer getPlayer();
	Map<String, IPlanet> getPlayerPlanets();
	boolean hasBeatenHighscore();
	void addPlayerToHighscore(String playerName);
	List<IScore> getHighscore();
}
