package BLL.ACQ;

import BLL.item.Item;
import BLL.item.ItemStack;

import java.util.List;
import java.util.Map;

public interface Domain {
	void injectPersistent(Persistent persistent);
	void addPlayerToHighscore(String playerName);
	IPlayer getPlayer();

	Map<String, IPlanet> getPlayerPlanets();
	MovePlayerState movePlayer(String planetName);
	List<IScore> getHighscore();
	boolean hasBeatenHighscore();
	boolean useItem(IItemStack iis);
	boolean pickupItem(IItemStack iis);
	boolean dropItem(IItemStack iis);
	SearchPlanetState searchPlanet();
}