package DAL.data;

import BLL.ACQ.data.IPlayerData;
import BLL.entity.player.buff.Buff;
import BLL.item.ItemStack;
import DAL.BuffUtility;
import DAL.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An object that implements the {@link IPlayerData} from the business layer.
 * It contains the details on how to load and save data for the player.
 */
public class PlayerData implements IPlayerData {
	private Model model;

	private String currentPlanet;
	private double x;
	private double y;
	private ItemStack[] itemStacks;
	private Set<Buff> buffs;
	private double fuel;
	private int fuelConsumption;

	/**
	 * Constructs a new PlayerData object with a model.
	 * @param model any model
	 */
	public PlayerData(Model model) {
		this.model = model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCurrentPlanet() {
		return currentPlanet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCurrentPlanet(String currentPlanet) {
		this.currentPlanet = currentPlanet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getX() {
		return x;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setX(double value) {
		this.x = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getY() {
		return y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setY(double value) {
		this.y = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack[] getInventory() {
		return itemStacks;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInventory(ItemStack[] itemStacks) {
		this.itemStacks = itemStacks;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Buff> getBuffs() {
		return buffs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBuffs(Set<Buff> buffs) {
		this.buffs = buffs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getFuel() {
		return fuel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getFuelConsumption() {
		return fuelConsumption;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFuelConsumption(int fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	/**
	 * Saves the data from the object into a map.
	 * Reverse method: {@link #load(Map)}.
	 */
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

	/**
	 * Loads the data from a map created by {@link #save()} into the object.
	 * @param map generated map
	 */
	public void load(Map<String, Object> map) {
		this.currentPlanet = (String) map.get("current-planet");
		this.x = (double) map.get("x-coordinate");
		this.y = (double) map.get("y-coordinate");
		this.itemStacks = model.getGameStateHandler().turnMapToInventory((Map<Integer, Object>) map.get("inventory"));
		this.buffs = turnMapToBuffs((Map<Integer, Object>) map.get("buffs"));
		this.fuel = (double) map.get("fuel");
		this.fuelConsumption = (int) map.get("fuel-consumption");
	}

	/**
	 * A method to turn buffs into a map for the {@link #save()} function.
	 * Reverse method: {@link #turnMapToBuffs(Map)}.
	 * @param buffs the buffs the player has
	 * @return a map with buff details
	 */
	private Map<Integer, Object> turnBuffsToMap(Set<Buff> buffs) {
		Map<Integer, Object> map = new HashMap<>();

		for(Buff buff : buffs) {
			map.put(buff.getId(), BuffUtility.getBuffInformation(buff));
		}

		return map;
	}

	/**
	 * Turn a generated map to buffs the player had.
	 * @param map generated map
	 * @return set of buffs
	 */
	private Set<Buff> turnMapToBuffs(Map<Integer, Object> map) {
		Set<Buff> buffs = new HashSet<>();

		for(Map.Entry<Integer, Object> entry : map.entrySet()) {
			buffs.add(BuffUtility.getBuffById(entry.getKey(), (Map<String, Object>) entry.getValue()));
		}

		return buffs;
	}
}
