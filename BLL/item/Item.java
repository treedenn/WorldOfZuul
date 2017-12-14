package BLL.item;

import BLL.ACQ.IItem;
import BLL.item.usable.Usable;
import BLL.Game;
import BLL.entity.player.Player;

/**
 * Any item inside the game is either an Item or derives from Item.
 * It contains the basic variables and functions.
 */
public class Item implements IItem, Cloneable {
	private int id;
	private String name;
	private String description;
	private ItemType type;
	private boolean pickupable;
	private boolean dropable;
	private double weight;
	private Usable usable;

	/**
	 * Constructs an item without any values.
	 */
	public Item() {}

	/**
	 * Constructs a new Item with values.
	 * @param id id of item
	 * @param name name of item
	 * @param description description of item
	 * @param type type of item
	 * @param weight the weight
	 * @param isPickupable is it pickupable
	 * @param isDropable is it dropable
	 */
	public Item(int id, String name, String description, ItemType type, double weight, boolean isPickupable, boolean isDropable) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.pickupable = isPickupable;
		this.dropable = isDropable;
		this.weight = weight;
		this.usable = null;
	}

	/**
	 * Constructs a new Item with values.
	 * @param id id of item
	 * @param name name of item
	 * @param description description of item
	 * @param weight the weight
	 * @param isPickupable is it pickupable
	 * @param isDropable is it dropable
	 */
	public Item(Integer id, String name, String description, double weight, boolean isPickupable, boolean isDropable) {
		this(id, name, description, ItemType.DEFAULT, weight, isPickupable, isDropable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getId() {
		return id;
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
		return hasUsable() && usable.use(this, player, game);
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

			return this.id == item.id;
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