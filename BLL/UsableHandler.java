package BLL;

import BLL.ACQ.Usable;
import BLL.item.usable.UsablePortalGun;
import BLL.item.usable.UsableTransformationElixir;

public class UsableHandler {
	public Usable getUsable(int index) {
		return obtainUsable(index);
	}

	private Usable obtainUsable(int index) {
		switch(index) {
			case 0: return new UsablePortalGun();
			case 1: return new UsableTransformationElixir();
			default: return null;
		}
	}
}
