package BLL.ACQ;

import java.util.Map;

public interface IPlayer {
	IPlanet getCurrentPlanet();
	Map<String, ? extends IPlanet> getPlanets();
	boolean isFuelEmpty();
	double getFuel();
	int getMaxFuel();
	IInventory getIInventory();
}
