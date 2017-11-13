package BLL.character.player;

import BLL.character.Character;

public class Player extends Character {
	private Backpack backpack;
	private double fuel;
	private int totalFuelConsumption;

	private final int MAX_FUEL;

	public Player() {
		super(null, null);
		this.backpack = new Backpack(6);
		this.fuel = 100;
		this.MAX_FUEL = (int) fuel;
	}

	public boolean isFuelEmpty() {
		return fuel == 0;
	}

	public Backpack getBackpack() {
		return backpack;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = Math.min(MAX_FUEL, Math.max(fuel, 0));
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

	public int getMaxFuel() {
		return MAX_FUEL;
	}

}
