package BLL.character.player;

import BLL.character.Character;

public class Player extends Character {
	private Backpack backpack;
	private boolean fuelEmpty;
	private double fuel;
	private int totalFuelConsumption;

	public Player() {
		super(null, null);
		this.backpack = new Backpack(6);
		this.fuel = 100;
		fuelEmpty = false;
	}

	public void setFuelEmpty(boolean fuelEmpty) {
		this.fuelEmpty = fuelEmpty;
	}

	public boolean isFuelEmpty() {
		return fuelEmpty;
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
