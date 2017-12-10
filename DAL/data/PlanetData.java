package DAL.data;

import BLL.ACQ.data.IPlanetData;
import BLL.item.ItemStack;
import DAL.GameStateHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanetData implements IPlanetData {
	private List<String> name;
	private List<Double> x;
	private List<Double> y;
	private List<List<Integer>> npcIds;
	private List<ItemStack[]> itemStacks;

	public PlanetData() {
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

	public Map<Integer, Object> getPlanetMap() {
		Map<Integer, Object> planetMap = new HashMap<>();

		for(int i = 0; i < name.size(); i++) {
			planetMap.put(i, turnPlanetToMap(i));
		}

		return planetMap;
	}

	private Map<String, Object> turnPlanetToMap(int index) {
		Map<String, Object> map = new HashMap<>();

		map.put("name", name.get(index));
		map.put("x-coordinate", x.get(index));
		map.put("y-coordinate", y.get(index));
		map.put("npcs", npcIds.get(index));
		map.put("inventory", GameStateHandler.turnInventoryToMap(itemStacks.get(index)));

		return map;
	}
}
