package BLL.character.player;

import BLL.ACQ.IInventory;
import BLL.ACQ.IPlayer;
import BLL.character.Character;
import BLL.character.Inventory;
import BLL.character.player.buff.Buff;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character implements IPlayer {
	private Inventory inventory;
	private List<Buff> buffs;
	private Point2D coordinates;
	private double fuel;
	private int totalFuelConsumption;
	private final int MAX_FUEL;
	private int morphId;

	public Player() {
		super(null, null);
		this.inventory = new Backpack(6);
		this.buffs = new ArrayList<>();
		this.coordinates = new Point2D(0, 0);
		this.fuel = 100;
		this.MAX_FUEL = (int) fuel;
		this.morphId = -1;
	}

	@Override
	public boolean isFuelEmpty() {
		return fuel == 0;
	}

	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public IInventory getIInventory() {
		return (IInventory) inventory;
	}

	public List<Buff> getBuffs() {
		return buffs;
	}

	public void addBuff(Buff buff) {
		buffs.add(buff);
		buff.onApply(this);
	}

	public void removeBuff(int index) {
		Buff buff = buffs.remove(index);
		buff.onEnd(this);
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

	@Override
	public int getMorphId() {
		return morphId;
	}

	public void setMorphId(int morphId) {
		this.morphId = morphId;
	}
}
