package BLL.ACQ;

import BLL.character.Inventory;

import java.util.Map;

public interface IPlayer {
	Map<String, ? extends IPlanet> getPlanets();
	boolean isFuelEmpty();
	double getFuel();
	int getMaxFuel();
	Inventory getInventory();
}
