package BLL.item;

public class PortalGun {
	private boolean broken;

	public PortalGun() {
		this.broken = true;
	}

	public boolean isBroken() {
		return broken;
	}

	public void repair() {
		broken = false;
	}

	public boolean shoot() {
		return true;
	}

	@Override
	public String toString() {
		return "Portal Gun" + (broken ? " [BROKEN]" : "");
	}
}