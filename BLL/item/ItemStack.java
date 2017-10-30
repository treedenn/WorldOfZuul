package BLL.item;

public final class ItemStack {
	private Item item;
	private int quantity;

	public ItemStack(Item item){
		this(item,1);
	}

	public ItemStack(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = Math.max(quantity, 0);
	}

	public void increaseQuantity(int amount) {
		setQuantity(this.quantity + amount);
	}

	public void decreaseQuantity(int amount) {
		setQuantity(this.quantity - amount);
	}

	public double getTotalWeight() {
		return item.getWeight() * quantity;
	}

	@Override
	public String toString() {
		return String.format("%dx %s", quantity, item.toString());
	}
}
