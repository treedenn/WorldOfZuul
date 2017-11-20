package BLL.item;

import BLL.ACQ.Usable;
import BLL.Game;
import BLL.character.player.Player;

public class Item implements Cloneable {
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

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPickupable() {
		return pickupable;
	}

	public boolean isDropable() {
		return dropable;
	}

	public double getWeight() {
		return weight;
	}

	public void setUsable(Usable usable) {
		this.usable = usable;
	}

	public boolean use(Player player, Game game) {
		return usable != null && usable.use(this, player, game);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if(object instanceof Item) {
			Item item = (Item) object;

			return this.getName().equals(item.getName()) && this.getDescription().equals(item.getDescription());
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("%s: %s", getName(), getDescription());
	}

}