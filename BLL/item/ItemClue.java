package BLL.item;

import BLL.GameUtility;

public class ItemClue extends Item {
	private ComponentType componentType;
	private Color color;
	private State state;

	public ItemClue(int id, String name, String description, double weight, boolean isPickupable, boolean isDropable, ComponentType componentType, Color color, State state) {
		super(id, name, description, ItemType.COMPONENT, weight, isPickupable, isDropable);
		this.componentType = componentType;
		this.color = color;
		this.state = state;
	}

	/**
	 * Gets the type of the component, declared by the enum {@link ComponentType}.
	 * @return the component type of the clue
	 */
	public ComponentType getComponentType() {
		return componentType;
	}

	/**
	 * Sets the type of the clue/component.
	 * @param componentType described by an enum
	 */
	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	/**
	 * Gets the color of the component.
	 * {@link Color} is homemade.
	 * @return the color of the clue
	 */
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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
	 * Sets the state of the clue.
	 * @param state limited by enum
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Returns a string based on the state of the object.
	 * @return {@link #getName()} of [{@link #getComponentType()}]: {@link #getDescription()}
	 */
	@Override
	public String toString() {
		return String.format("%s of [%s]: %s", getName(), getComponentType(), GameUtility.replacePlaceHolders(getDescription(),"{{state}}", getState().name().toLowerCase(), "{{color}}", getColor().name().toLowerCase()));
	}
}
