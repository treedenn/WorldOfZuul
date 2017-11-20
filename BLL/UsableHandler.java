package BLL;

import BLL.ACQ.Usable;
import BLL.item.usable.UsablePortalGun;

public class UsableHandler {
	public Usable getUsable(int index) {
		return obtainUsable(index);
	}

	private Usable obtainUsable(int index) {
		switch(index) {
			case 0: return new UsablePortalGun();
			default: return null;
		}
	}
}
