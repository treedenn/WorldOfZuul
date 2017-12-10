package DAL.data;

import BLL.ACQ.data.IPlayerData;
import BLL.entity.player.buff.Buff;
import BLL.item.ItemStack;
import DAL.BuffUtility;
import DAL.GameStateHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlayerData implements IPlayerData {
	private String currentPlanet;
	private double x;
	private double y;
	private ItemStack[] itemStacks;
	private Set<Buff> buffs;
	private double fuel;
	private int fuelConsumption;

	@Override
	public String getCurrentPlanet() {
		return currentPlanet;
	}

	@Override
	public void setCurrentPlanet(String currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double value) {
		this.x = value;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double value) {
		this.y = value;
	}

	@Override
	public ItemStack[] getInventory() {
		return itemStacks;
	}

	@Override
	public void setInventory(ItemStack[] itemStacks) {
		this.itemStacks = itemStacks;
	}

	@Override
	public Set<Buff> getBuffs() {
		return buffs;
	}

	@Override
	public void setBuffs(Set<Buff> buffs) {
		this.buffs = buffs;
	}

	@Override
	public double getFuel() {
		return fuel;
	}

	@Override
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	@Override
	public int getFuelConsumption() {
		return fuelConsumption;
	}

	@Override
	public void setFuelConsumption(int fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public Map<String, Object> getPlayerMap() {
		Map<String, Object> playerMap = new HashMap<>();

		Map<Integer, Object> buffMap = new HashMap<>();
		for(Buff buff : buffs) {
			buffMap.put(buff.getId(), BuffUtility.getBuffInformation(buff));
		}

		playerMap.put("current-planet", currentPlanet);
		playerMap.put("x-coordinate", x);
		playerMap.put("y-coordinate", y);
		playerMap.put("inventory", GameStateHandler.turnInventoryToMap(itemStacks));
		playerMap.put("buffs", buffMap);
		playerMap.put("fuel", fuel);
		playerMap.put("fuel-consumption", fuelConsumption);

		return playerMap;
	}
}
