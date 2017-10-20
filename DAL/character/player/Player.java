package DAL.character.player;

import DAL.character.Character;

public class Player extends Character {
	private Backpack backpack;
	private double fuel;

	public Player() {
		super(null, null);
		this.backpack = new Backpack(3);
	}

	public Backpack getBackpack() {
		return backpack;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = Math.max(fuel, 0);
		Math.min(0, 0);
	}

	public void increaseFuel(double amount) {
		setFuel(getFuel() + amount);
	}

	public void decreaseFuel(double amount) {
		setFuel(getFuel() - amount);
	}
}
