package BLL.character.player;

import BLL.ACQ.IPlayer;
import BLL.character.Character;
import BLL.character.Inventory;
import javafx.geometry.Point2D;

public class Player extends Character implements IPlayer {
	private Inventory inventory;
	private Point2D coordinates;
	private double fuel;
	private int totalFuelConsumption;

	private final int MAX_FUEL;

	public Player() {
		super(null, null);
		this.inventory = new Backpack(6);
		this.coordinates = new Point2D(0, 0);
		this.fuel = 100;
		this.MAX_FUEL = (int) fuel;
	}

	@Override
	public boolean isFuelEmpty() {
		return fuel == 0;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public double getFuel() {
		return fuel;
	}

	@Override
	public int getMaxFuel() {
		return MAX_FUEL;
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
}
