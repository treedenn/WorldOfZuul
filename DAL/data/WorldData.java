package DAL.data;

import BLL.ACQ.data.IWorldData;
import BLL.item.ItemStack;
import DAL.GameStateHandler;

import java.util.HashMap;
import java.util.Map;

public class WorldData implements IWorldData {
	private long timeElapsed;
	private boolean portalGunBroken;
	private ItemStack[] requirements;

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
	public ItemStack[] getRequirements() {
		return requirements;
	}

	@Override
	public void setRequirements(ItemStack[] requirements) {
		this.requirements = requirements;
	}

	public Map<String, Object> getWorldMap() {
		Map<String, Object> worldMap = new HashMap<>();

		worldMap.put("time-elapsed", timeElapsed);
		worldMap.put("portalgun-broken", portalGunBroken);
		if(requirements != null) {
			worldMap.put("requirements", GameStateHandler.turnInventoryToMap(requirements));
		}

		return worldMap;
	}
}
