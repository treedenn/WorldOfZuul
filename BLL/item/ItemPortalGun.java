package BLL.item;

/**
 * This defines the functionality of the Portal Gun used within the game.
 */
public class ItemPortalGun extends Item {
	private boolean broken;

	/**
	 * Constructs a new Portal Gun, where it is broken.
	 * @param id id of item
	 * @param name name of item
	 * @param description description of item
	 * @param weight the weight
	 * @param isPickupable is it pickupable
	 * @param isDropable is it dropable
	 */
	public ItemPortalGun(int id, String name, String description, double weight, boolean isPickupable, boolean isDropable) {
		super(id, name, description, weight, isPickupable, isDropable);
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