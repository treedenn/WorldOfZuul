package BLL;

import BLL.ACQ.Usable;
import BLL.item.usable.UsablePortalGun;
import BLL.item.usable.UsableTransformationElixir;

/**
 * Handles all the {@link Usable} objects, by returning a usable depending on the index giving.
 * It is only used in the data layer, when creating new items.
 * If they have a usable id, the id will be sent here and will return the corresponding usable effect.
 */
public class UsableHandler {
	/**
	 * Gets the usable at given index.
	 * @param index index of usable
	 * @return usable at given index
	 */
	public Usable getUsable(int index) {
		switch(index) {
			case 0: return new UsablePortalGun();
			case 1: return new UsableTransformationElixir();
			default: return null;
		}
	}
}
