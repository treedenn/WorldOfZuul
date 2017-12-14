package BLL.item;

import BLL.GameUtility;

/**
 * An extension of {@link Item}.
 * This defines the functionality of Components within the game.
 */
public class ItemComponent extends Item {
	private ComponentType componentType;
	private Color color;
	private State state;

	/**
	 * Instantiates a new component in the game.
	 * Only invoked by the persistent layer.
	 * @param id id of the component
	 * @param name name of the component
	 * @param description description of the component
	 * @param weight weight of the component
	 * @param isPickupable is it pickupable
	 * @param isDropable is it dropable
	 * @param componentType what type it is
	 * @param color what colour is it
	 * @param state what state is it
	 */
	public ItemComponent(int id, String name, String description, double weight, boolean isPickupable, boolean isDropable, ComponentType componentType, Color color, State state) {
		super(id, name, description, ItemType.COMPONENT, weight, isPickupable, isDropable);
		this.componentType = componentType;
		this.color = color;
		this.state = state;
	}

	/**
	 * Gets the type of the component, declared by the enum {@link ComponentType}.
	 * @return
	 */
	public ComponentType getComponentType() {
		return componentType;
	}

	/**
	 * Gets the color of the component.
	 * {@link Color} is homemade.
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Gets the state of the component, declared by the enum {@link State}.
	 * State is abstract, however, it can be compared to a condition.
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * Returns a string based on the state of the object.
	 * @return {@link #getName()} of [{@link #getComponentType()}]: {@link #getDescription()}
	 */
	@Override
	public String toString() {
		return String.format("%s [%s]: %s", getName(), getComponentType(), GameUtility.replacePlaceHolders(getDescription(),"{{state}}", getState().name().toLowerCase(), "{{color}}", getColor().name().toLowerCase()));
	}
}