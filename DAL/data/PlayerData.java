package DAL.data;

import BLL.ACQ.data.IPlayerData;
import BLL.entity.player.buff.Buff;
import BLL.item.ItemStack;
import DAL.BuffUtility;
import DAL.GameStateHandler;
import DAL.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerData implements IPlayerData {
	private Model model;

	private String currentPlanet;
	private double x;
	private double y;
	private ItemStack[] itemStacks;
	private Set<Buff> buffs;
	private double fuel;
	private int fuelConsumption;

	public PlayerData(Model model) {
		this.model = model;
	}

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

	public Map<String, Object> save() {
		Map<String, Object> playerMap = new HashMap<>();



		playerMap.put("current-planet", currentPlanet);
		playerMap.put("x-coordinate", x);
		playerMap.put("y-coordinate", y);
		playerMap.put("inventory", model.getGameStateHandler().turnInventoryToMap(itemStacks));
		playerMap.put("buffs", turnBuffsToMap(buffs));
		playerMap.put("fuel", fuel);
		playerMap.put("fuel-consumption", fuelConsumption);

		return playerMap;
	}

	public void load(Map<String, Object> map) {
		this.currentPlanet = (String) map.get("current-planet");
		this.x = (double) map.get("x-coordinate");
		this.y = (double) map.get("y-coordinate");
		this.itemStacks = model.getGameStateHandler().turnMapToInventory((Map<Integer, Object>) map.get("inventory"));
		this.buffs = turnMapToBuffs((Map<Integer, Object>) map.get("buffs"));
		this.fuel = (double) map.get("fuel");
		this.fuelConsumption = (int) map.get("fuel-consumption");
	}

	private Map<Integer, Object> turnBuffsToMap(Set<Buff> buffs) {
		Map<Integer, Object> map = new HashMap<>();

		for(Buff buff : buffs) {
			map.put(buff.getId(), BuffUtility.getBuffInformation(buff));
		}

		return map;
	}

	private Set<Buff> turnMapToBuffs(Map<Integer, Object> map) {
		Set<Buff> buffs = new HashSet<>();

		for(Map.Entry<Integer, Object> entry : map.entrySet()) {
			buffs.add(BuffUtility.getBuffById(entry.getKey(), (Map<String, Object>) entry.getValue()));
		}

		return buffs;
	}
}
