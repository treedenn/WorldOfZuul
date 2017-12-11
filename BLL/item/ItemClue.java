package BLL.item;

import BLL.Game;

public class ItemClue extends Item {
	private ComponentType componentType;
	private Color color;
	private State state;

	public ItemClue(int id, String name, String description, double weight, ComponentType componentType, Color color, State state) {
		super(id, name, description, ItemType.COMPONENT, weight, true, true);
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

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	/**
	 * Gets the color of the component.
	 * {@link Color} is homemade.
	 * @return
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

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return String.format("%s of [%s]: %s", getName(), getComponentType(), Game.replacePlaceHolders(getDescription(),"{{state}}", getState().name().toLowerCase(), "{{color}}", getColor().name().toLowerCase()));
	}
}
