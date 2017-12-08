package BLL.item;

import BLL.Game;

/**
 * This defines the functionality of Components within the game.
 */
public class ItemComponent extends Item {
	private ComponentType componentType;
	private Color color;
	private State state;

	public ItemComponent(int id, String name, String description, double weight, ComponentType componentType, Color color, State state) {
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

	@Override
	public String toString() {
		return getComponentType().name() + ": " + getName() + " " + Game.replacePlaceHolders(getDescription(),"{state}", getState().name(), "{color}", getColor().name());
	}
}