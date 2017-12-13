package DAL.data;

import BLL.ACQ.data.IWorldData;
import BLL.item.ItemStack;
import DAL.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldData implements IWorldData {
	private Model model;

	private long timeElapsed;
	private boolean portalGunBroken;
	private String[] traces;
	private ItemStack[] requirements;

	public WorldData(Model model) {
		this.model = model;

		this.requirements = null;
	}

	@Override
	public long getTimeElapsed() {
		return timeElapsed;
	}

	@Override
	public void setTimeElapsed(long time) {
		timeElapsed = time;
	}

	@Override
	public boolean isPortalGunBroken() {
		return portalGunBroken;
	}

	@Override
	public void setPortalGunBroken(boolean isBroken) {
		portalGunBroken = isBroken;
	}

	@Override
	public String[] getBlacksmithTraces() {
		return traces;
	}

	@Override
	public void setBlacksmthTraces(String[] traces) {
		this.traces = traces;
	}

	@Override
	public ItemStack[] getRequirements() {
		return requirements;
	}

	@Override
	public void setRequirements(ItemStack[] requirements) {
		this.requirements = requirements;
	}

	public Map<String, Object> save() {
		Map<String, Object> worldMap = new HashMap<>();

		if(requirements != null) {
			worldMap.put("requirements", model.getGameStateHandler().turnInventoryToMap(requirements));
			worldMap.put("bs-traces", traces);
		}

		worldMap.put("time-elapsed", timeElapsed);
		worldMap.put("portalgun-broken", portalGunBroken);

		return worldMap;
	}

	public void load(Map<String, Object> map) {
		if(map.containsKey("requirements")) {
			this.requirements = model.getGameStateHandler().turnMapToInventory((Map<Integer, Object>) map.get("requirements"));
			List<String> stringList = (List<String>) map.get("bs-traces");
			this.traces = stringList.toArray(new String[stringList.size()]);
		}

		this.timeElapsed = (int) map.get("time-elapsed");
		this.portalGunBroken = (boolean) map.get("portalgun-broken");
	}
}
