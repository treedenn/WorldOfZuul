package BLL.ACQ;

import java.util.Map;

public interface IPlayer {
	Map<String, ? extends IPlanet> getPlanets();
	boolean isFuelEmpty();
	double getFuel();
	int getMaxFuel();
	IInventory getIInventory();
}
