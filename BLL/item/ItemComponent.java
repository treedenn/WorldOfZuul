package BLL.item;

public class ItemComponent extends Item {
	private ComponentType componentType;
	private Color color;
	private State state;

	public ItemComponent(String name, String description, double weight, ComponentType componentType, Color color, State state) {
		super(name, description, ItemType.COMPONENT, weight, true, true);
		this.componentType = componentType;
		this.color = color;
		this.state = state;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public Color getColor() {
		return color;
	}

	public State getState() {
		return state;
	}
}