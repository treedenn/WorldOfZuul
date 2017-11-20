package BLL.item;

public class ItemPortalGun extends Item {
	private boolean broken;

	public ItemPortalGun(String name, String description, double weight, boolean isPickupable, boolean isDropable) {
		super(name, description, weight, isPickupable, isDropable);
		this.broken = true;
	}

	public boolean isBroken() {
		return broken;
	}

	public void repair() {
		broken = false;
	}

	@Override
	public String toString() {
		return "Portal Gun" + (broken ? " [BROKEN]" : "");
	}
}