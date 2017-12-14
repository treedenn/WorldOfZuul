package DAL.data;

import BLL.ACQ.data.IWorldData;
import BLL.item.ItemStack;
import DAL.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An object that implements the {@link IWorldData} from the business layer.
 * It contains the details on how to load and save data for the world.
 */
public class WorldData implements IWorldData {
	private Model model;

	private int worldSeed;
	private int timeElapsed;
	private boolean portalGunBroken;
	private String[] traces;
	private ItemStack[] requirements;

	/**
	 * Constructs a new World Data object.
	 * @param model any model
	 */
	public WorldData(Model model) {
		this.model = model;
		this.requirements = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWorldSeed() {
		return worldSeed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setWorldSeed(int worldSeed) {
		this.worldSeed = worldSeed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTimeElapsed() {
		return timeElapsed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTimeElapsed(int time) {
		timeElapsed = time;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPortalGunBroken() {
		return portalGunBroken;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPortalGunBroken(boolean isBroken) {
		portalGunBroken = isBroken;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getBlacksmithTraces() {
		return traces;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBlacksmthTraces(String[] traces) {
		this.traces = traces;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack[] getRequirements() {
		return requirements;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRequirements(ItemStack[] requirements) {
		this.requirements = requirements;
	}

	/**
	 * Saves the data from the object into a map.
	 * Reverse method: {@link #load(Map)}.
	 */
	public Map<String, Object> save() {
		Map<String, Object> worldMap = new HashMap<>();

		if(requirements != null) {
			worldMap.put("requirements", model.getGameStateHandler().turnInventoryToMap(requirements));
			worldMap.put("bs-traces", traces);
		}

		worldMap.put("world-seed", worldSeed);
		worldMap.put("time-elapsed", timeElapsed);
		worldMap.put("portalgun-broken", portalGunBroken);

		return worldMap;
	}

	/**
	 * Loads the data from a map created by {@link #save()} into the object.
	 * @param map generated map
	 */
	public void load(Map<String, Object> map) {
		if(map.containsKey("requirements")) {
			this.requirements = model.getGameStateHandler().turnMapToInventory((Map<Integer, Object>) map.get("requirements"));
			List<String> stringList = (List<String>) map.get("bs-traces");
			this.traces = stringList.toArray(new String[stringList.size()]);
		}

		this.worldSeed = (int) map.get("world-seed");
		this.timeElapsed = (int) map.get("time-elapsed");
		this.portalGunBroken = (boolean) map.get("portalgun-broken");
	}
}
