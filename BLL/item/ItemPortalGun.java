package BLL.item;

/**
 * This defines the functionality of the Portal Gun used within the game.
 */
public class ItemPortalGun extends Item {
	private boolean broken;

	public ItemPortalGun(String name, String description, double weight, boolean isPickupable, boolean isDropable) {
		super(name, description, weight, isPickupable, isDropable);
		this.broken = true;
	}

	/**
	 * Returns the condition of the Portal Gun.
	 * @return true, if broken
	 */
	public boolean isBroken() {
		return broken;
	}

	/**
	 * Repairs the Portal Gun.
	 */
	public void repair() {
		broken = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Portal Gun" + (broken ? " [BROKEN]" : "");
	}
}