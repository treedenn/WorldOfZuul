package DAL.item;

public enum Color {
	CYAN(0, 188, 212), PINK(233, 30, 99), ORANGE(255, 152, 0), PURPLE(156, 39, 176);

	private int red;
	private int green;
	private int blue;

	Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public int[] getRGB() {
		return new int[] {red, green, blue};
	}
}