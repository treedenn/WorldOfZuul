package BLL.ACQ;

import BLL.character.npc.NPC;

import java.util.List;
import java.util.Map;

public interface Domain {
	void injectPersistent(Persistent persistent);
	void addPlayerToHighscore(String playerName);
	IPlayer getPlayer();
	Map<String, IPlanet> getPlayerPlanets();
	MovePlayerState movePlayerToPlanet(String planetName);
	List<IScore> getHighscore();
	boolean hasBeatenHighscore();
	void updateBuffs();
	void startInteract(NPC npc, int actionId);
	void endInteract(NPC npc, int actionId);
	boolean useItem(IItemStack iis);
	boolean pickupItem(IItemStack iis);
	boolean dropItem(IItemStack iis);
	SearchPlanetState searchPlanet();
	void decreaseFuelOnMove();
	IQuiz getQuiz();
	boolean isAnswerCorrect(int index);
}