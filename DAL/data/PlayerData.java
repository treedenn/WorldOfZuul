package DAL.data;

import BLL.ACQ.data.IPlayerData;
import BLL.entity.Inventory;
import BLL.entity.player.buff.Buff;
import BLL.item.ItemStack;
import DAL.GameStateHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerData implements IPlayerData {
	private String currentPlanet;
	private double x;
	private double y;
	private ItemStack[] itemStacks;
	private Map<Integer, Long> buffs;
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
	public Map<Integer, Long> getBuffs() {
		return buffs;
	}

	@Override
	public void setBuffs(Map<Integer, Long> buffs) {
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

		playerMap.put("current-planet", currentPlanet);
		playerMap.put("x-coordinate", x);
		playerMap.put("y-coordinate", y);
		playerMap.put("inventory", GameStateHandler.turnInventoryToMap(itemStacks));
		playerMap.put("buffs", buffs);
		playerMap.put("fuel", fuel);
		playerMap.put("fuel-consumption", fuelConsumption);

		return playerMap;
	}
}
