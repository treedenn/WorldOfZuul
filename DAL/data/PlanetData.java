package DAL.data;

import BLL.ACQ.data.IPlanetData;
import BLL.item.ItemStack;
import DAL.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An object that implements the {@link IPlanetData} from the business layer.
 * It contains the details on how to load and save data for the planets.
 */
public class PlanetData implements IPlanetData {
	private Model model;

	private List<String> name;
	private List<Double> x;
	private List<Double> y;
	private List<List<Integer>> npcIds;
	private List<ItemStack[]> itemStacks;

	/**
	 * Constructs a new PlanetData object.
	 * @param model the persistent layer
	 */
	public PlanetData(Model model) {
		this.model = model;

		name = new ArrayList<>();
		x = new ArrayList<>();
		y = new ArrayList<>();
		npcIds = new ArrayList<>();
		itemStacks = new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return itemStacks.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addData(String name, double x, double y, List<Integer> NPCs, ItemStack[] itemStacks) {
		this.name.add(name);
		this.x.add(x);
		this.y.add(y);
		this.npcIds.add(NPCs);
		this.itemStacks.add(itemStacks);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName(int index) {
		return name.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setName(int index, String name) {
		this.name.set(index, name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getX(int index) {
		return x.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setX(int index, double value) {
		x.set(index, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getY(int index) {
		return y.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setY(int index, double value) {
		y.set(index, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNPCs(int index, List<Integer> npcs) {
		npcIds.set(index, npcs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Integer> getNPCIds(int index) {
		return npcIds.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack[] getInventory(int index) {
		return itemStacks.get(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInventory(int index, ItemStack[] itemStacks) {
		this.itemStacks.set(index, itemStacks);
	}

	/**
	 * A method to save the variables in the object in a Yaml format.
	 * @return a map with the details
	 */
	public Map<Integer, Object> save() {
		Map<Integer, Object> planetMap = new HashMap<>();

		for(int i = 0; i < name.size(); i++) {
			planetMap.put(i, turnPlanetToMap(i));
		}

		return planetMap;
	}

	/**
	 * A method to turn the map from {@link #save()} to usable data for the business layer.
	 * @param map any map
	 */
	public void load(Map<Integer, Object> map) {
		for(Map.Entry<Integer, Object> entry : map.entrySet()) {
			turnMapAndAddPlanet((Map<String, Object>) entry.getValue());
		}
	}

	/**
	 * Turns a planet into a map.
	 * Reverse method: {@link #turnMapAndAddPlanet(Map)}.
	 * @param index index of the planet
	 * @return a generated map
	 */
	private Map<String, Object> turnPlanetToMap(int index) {
		Map<String, Object> map = new HashMap<>();

		map.put("name", name.get(index));
		map.put("x-coordinate", x.get(index));
		map.put("y-coordinate", y.get(index));
		map.put("npcs", npcIds.get(index));
		map.put("inventory", model.getGameStateHandler().turnInventoryToMap(itemStacks.get(index)));

		return map;
	}

	/**
	 * Turns a generated map into data and adds it.
	 * @param map generated map
	 */
	private void turnMapAndAddPlanet(Map<String, Object> map) {
		this.name.add((String) map.get("name"));
		this.x.add((Double) map.get("x-coordinate"));
		this.y.add((Double) map.get("y-coordinate"));
		this.npcIds.add((List<Integer>) map.get("npcs"));
		this.itemStacks.add(model.getGameStateHandler().turnMapToInventory((Map<Integer, Object>) map.get("inventory")));
	}
}
