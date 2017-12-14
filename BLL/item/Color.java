package BLL.item;

/**
 * Color contains all the colours available for the components.
 */
public enum Color {
	CYAN(0, 188, 212), PINK(233, 30, 99), ORANGE(255, 152, 0), PURPLE(156, 39, 176), GREEN(0, 255,0), WHITE(255,255,255);

	private int red;
	private int green;
	private int blue;

	/**
	 * Constructs a Color.
	 * @param red amount of red
	 * @param green amount of green
	 * @param blue amount of blue
	 */
	Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * Gets the amount of red in a color (MIN: 0, MAX: 255)
	 * @return amount of red
	 */
	public int getRed() {
		return red;
	}

	/**
	 * Gets the amount of green in a color (MIN: 0, MAX: 255)
	 * @return amount of green
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * Gets the amount of blue in a color (MIN: 0, MAX: 255)
	 * @return amount of blue
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * Gets the colours as an array in RED, GREEN, BLUE order.
	 * @return an array of the colours
	 */
	public int[] getRGB() {
		return new int[] {red, green, blue};
	}
}