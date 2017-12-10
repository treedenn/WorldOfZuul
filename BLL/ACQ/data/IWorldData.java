package BLL.ACQ.data;

import BLL.item.ItemStack;

public interface IWorldData {
	long getTimeElapsed();
	void setTimeElapsed(long time);

	boolean isPortalGunBroken();
	void setPortalGunBroken(boolean isBroken);

	ItemStack[] getRequirements();
	void setRequirements(ItemStack[] requirements);
}
