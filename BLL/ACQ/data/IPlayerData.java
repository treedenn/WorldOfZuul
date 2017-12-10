package BLL.ACQ.data;

import BLL.entity.Inventory;
import BLL.entity.player.buff.Buff;
import BLL.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IPlayerData {
	String getCurrentPlanet();
	void setCurrentPlanet(String currentPlanet);

	double getX();
	void setX(double value);

	double getY();
	void setY(double value);

	ItemStack[] getInventory();
	void setInventory(ItemStack[] itemStacks);

	Set<Buff> getBuffs();
	void setBuffs(Set<Buff> buffs);

	double getFuel();
	void setFuel(double fuel);

	int getFuelConsumption();
	void setFuelConsumption(int fuelConsumption);
}
