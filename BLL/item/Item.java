package BLL.item;

import BLL.ACQ.IItem;
import BLL.ACQ.Usable;
import BLL.Game;
import BLL.character.player.Player;

public class Item implements IItem, Cloneable {
	private String name;
	private String description;
	private ItemType type;
	private boolean pickupable;
	private boolean dropable;
	private double weight;
	private Usable usable;

	public Item() {

	}

	public Item(String name, String description, ItemType type, double weight, boolean isPickupable, boolean isDropable) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.pickupable = isPickupable;
		this.dropable = isDropable;
		this.weight = weight;
		this.usable = null;
	}

	public Item(String name, String description, double weight, boolean isPickupable, boolean isDropable) {
		this(name, description, ItemType.DEFAULT, weight, isPickupable, isDropable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of item.
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPickupable() {
		return pickupable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDropable() {
		return dropable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getWeight() {
		return weight;
	}

	/**
	 * Returns a boolean based on the item has a usable effect when {@link #use(Player, Game)} is invoked.
	 * @return true, if usable is set
	 */
	public boolean hasUsable() {
		return this.usable != null;
	}

	/**
	 * Sets the usable variable to the value given as argument.
	 * @param usable the usable to give the item
	 */
	public void setUsable(Usable usable) {
		this.usable = usable;
	}

	/**
	 * If item has a usable it will trigger the use item and return a boolean based on successful or not.
	 * @param player the player to trigger the effect on
	 * @param game the game object
	 * @return true, if usable could trigger
	 */
	public boolean use(Player player, Game game) {
		return usable != null && usable.use(this, player, game);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Use to clone current object.
	 * @return the clone of object
	 * @throws CloneNotSupportedException If cloning is not supported
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * {@inheritDoc}
	 * Compares this object with another of same type.
	 * @param object the object to compare with
	 * @return true, if identical
	 */
	@Override
	public boolean equals(Object object) {
		if(object instanceof Item) {
			Item item = (Item) object;

			return this.getName().equals(item.getName()) && this.getDescription().equals(item.getDescription());
		}
		return false;
	}

	/**
	 * Converts the object to the String, that gives information about the object.
	 * @return information in current state
	 */
	@Override
	public String toString() {
		return String.format("%s: %s", getName(), getDescription());
	}
}