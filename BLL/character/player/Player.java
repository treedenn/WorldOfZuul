package DAL.character.player;

import DAL.character.Character;
import DAL.character.Inventory;

public class Player extends Character {
	private Backpack backpack;
	private double fuel;
	private int totalFuelConsumption;

	public Player() {
		super(null, null);
		this.backpack = new Backpack(10);
		this.fuel = 100;
	}

	public Backpack getBackpack() {
		return backpack;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = Math.max(fuel, 0);
	}

	public int getTotalFuelConsumption() {
		return totalFuelConsumption;
	}

	public void increaseFuel(double amount) {
		setFuel(getFuel() + amount);
	}

	public void decreaseFuel(double amount) {
		setFuel(getFuel() - amount);
		totalFuelConsumption += amount;
	}

}
