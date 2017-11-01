package BLL.item;

import DAL.yaml.ItemParser;

import java.io.IOException;
import java.util.*;

public class Item {
	private String name;
	private String description;
	private ItemType itemType;
	private Color color;
	private State state;
	private boolean pickupable;
	private boolean dropable;
	private double weight;

	public Item(Item item) {
		this(item.getName(), item.getDescription(), item.getItemType(), item.getColor(), item.getState(), item.getWeight(), item.isPickupable(), item.isDropable());
	}

	public Item(String name, String description, ItemType itemType, Color color, State state, double weight, boolean isPickupable, boolean isDropable) {
		this.name = name;
		this.description = description;
		this.itemType = itemType;
		this.color = color;
		this.state = state;
		this.pickupable = isPickupable;
		this.dropable = isDropable;
		this.weight = weight;
	}

	public Item(String name, String description, ItemType itemType, Color color, State state, double weight) {
		this(name, description, itemType, color, state, weight, true, true);
	}

	public Item(String name, String description, ItemType itemType, Color color, State state) {
		this(name, description, itemType, color, state,1, true, true);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public Color getColor() {
		return color;
	}

	public State getState() {
		return state;
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

	@Override
	public boolean equals(Object object) {
		if(object instanceof Item) {
			Item item = (Item) object;

			return this.getName().equals(item.getName()) && this.getDescription().equals(item.getDescription()) && this.getItemType().equals(item.getItemType());
		}

		return false;
	}

	@Override
	public String toString() {
		return String.format("%s [%s]: %s", getName(), getItemType().name(), getDescription());
	}
}