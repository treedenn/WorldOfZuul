package BLL.entity.player;

import BLL.ACQ.IInventory;
import BLL.ACQ.IPlayer;
import BLL.entity.Inventory;
import BLL.entity.MovableEntity;
import BLL.entity.player.buff.Buff;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Identifies a Player in the game.
 * Implements the {@link IPlayer} interface,
 * which is used to limit the functionality to the GUI.
 */
public class Player extends MovableEntity implements IPlayer {
	private Inventory inventory;
	private List<Buff> buffs;
	private double coordX;
	private double coordY;
	private double fuel;
	private int totalFuelConsumption;
	private final int MAX_FUEL;
	private int morphId;

	public Player() {
		super(null, null);
		this.inventory = new Backpack(6);
		this.buffs = new ArrayList<>();
		this.coordX = 0;
		this.coordY = 0;
		this.fuel = 100;
		this.MAX_FUEL = (int) fuel;
		this.morphId = -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFuelEmpty() {
		return fuel == 0;
	}

	/**
	 * Returns the interface Inventory of the player.
	 * @return Inventory of the player.
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IInventory getIInventory() {
		return (IInventory) inventory;
	}

	/**
	 * Returns a list of buffs/objects implementing the {@link Buff} interface.
	 * @return List of {@link Buff}
	 */
	public List<Buff> getBuffs() {
		return buffs;
	}

	/**
	 * Use to add a buff to the buff list with an object implementing the {@link Buff} interface.
	 * @param buff
	 */
	public void addBuff(Buff buff) {
		buffs.add(buff);
		buff.onApply(this);
	}

	/**
	 * Use to remove a buff from buff list with an index, specifying which buff.
	 * @param index index in the list of buffs.
	 */
	public void removeBuff(int index) {
		Buff buff = buffs.remove(index);
		buff.onEnd(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getCoordX() {
		return coordX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getCoordY() {
		return coordY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getFuel() {
		return fuel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxFuel() {
		return MAX_FUEL;
	}

	/**
	 * Sets the fuel as the value.
	 * However, it cannot go below or above the minimum (0) or the maximum ({@link #getMaxFuel()}).
	 * @param fuel the value of fuel to set
	 */
	public void setFuel(double fuel) {
		this.fuel = Math.min(MAX_FUEL, Math.max(fuel, 0));
	}

	/**
	 * Gets the amount of fuel the player has consume since the game start.
	 * @return an integer of the total fuel consumption
	 */
	public int getTotalFuelConsumption() {
		return totalFuelConsumption;
	}

	/**
	 * Increase fuel by relative of the current fuel.
	 * This cannot go below or above maximum fuel.
	 * See {@link #setFuel(double)}.
	 * @param amount the amount of relatively increasing
	 */
	public void increaseFuel(double amount) {
		setFuel(getFuel() + amount);
	}

	/**
	 * Decrease fuel by relative of the current fuel.
	 * This cannot go below or above maximum fuel.
	 * See {@link #setFuel(double)}.
	 * @param amount the amount of relatively decreasing
	 */
	public void decreaseFuel(double amount) {
		setFuel(getFuel() - amount);
		totalFuelConsumption += amount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMorphId() {
		return morphId;
	}

	/**
	 * Sets the player's morph id.
	 * @param morphId the value for morph id.
	 */
	public void setMorphId(int morphId) {
		this.morphId = morphId;
	}

	@Override
	public void move() {}
}
