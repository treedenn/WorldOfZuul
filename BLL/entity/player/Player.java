package BLL.entity.player;

import BLL.ACQ.IInventory;
import BLL.ACQ.IPlayer;
import BLL.ACQ.data.IPlayerData;
import BLL.entity.Inventory;
import BLL.entity.MovableEntity;
import BLL.entity.player.buff.Buff;
import BLL.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * Identifies a Player in the game.
 * Implements the {@link IPlayer} interface,
 * which is used to limit the functionality to the GUI.
 */
public class Player extends MovableEntity implements IPlayer {
	private Inventory inventory;
	private Set<Buff> buffs;
	private double coordX;
	private double coordY;
	private double fuel;
	private int totalFuelConsumption;
	private final int MAX_FUEL;
	private int morphId;

	public Player() {
		super(null, null);
		this.inventory = new Backpack(6);
		this.buffs = new HashSet<>();
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
		return inventory;
	}

	/**
	 * Returns a list of buffs/objects implementing the {@link Buff} interface.
	 * @return List of {@link Buff}
	 */
	public Set<Buff> getBuffs() {
		return buffs;
	}

	/**
	 * Sets the buffs.
	 * @param buffs a set of any buffs
	 */
	public void setBuffs(Set<Buff> buffs) {
		for(Buff buff : buffs) {
			addBuff(buff);
		}
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
	 * @param buff the buff to remove
	 */
	public void removeBuff(Buff buff) {
		buff.onEnd(this);
		buffs.remove(buff);
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

	public void loadPlayer(IPlayerData data) {
		if(data.getCurrentPlanet() != null) {
			if(getPlanets().containsKey(data.getCurrentPlanet())) {
				setCurrentPlanet(getPlanets().get(data.getCurrentPlanet()));
			}
		}

		setCoordX(data.getX());
		setCoordY(data.getY());
		setBuffs(data.getBuffs());

		for(ItemStack itemStack : data.getInventory()) {
			getInventory().add(itemStack);
		}

		setFuel(data.getFuel());
		totalFuelConsumption = data.getFuelConsumption();
	}

	@Override
	public void move() {}

	@Override
	public boolean canMove() { return false; }

	@Override
	public void setMove(boolean canMove) {}
}
