package BLL.entity.player.buff;

import BLL.entity.player.Player;

/**
 * The Transformation Buff is a buff that allows to change
 * the morph id of the player for an x duration of time.
 */
public class TransformationBuff implements Buff, Expirable {
	private int morphId;
	private long startDuration;
	private long endDuration;

	/**
	 * Empty transformation buff.
	 * Morph id and duration can be set by their responsive functions.
	 */
	public TransformationBuff() {}

	/**
	 * Constructs a new transformation buff with a morph id and duration.
	 * @param morphId id of morph
	 * @param duration in milliseconds
	 */
	public TransformationBuff(int morphId, long duration) {
		this.morphId = morphId;
		this.startDuration = System.currentTimeMillis();
		this.endDuration = startDuration + duration;
	}

	public int getMorphId() {
		return morphId;
	}

	/**
	 * Sets the morph id.
	 * @param morphId
	 */
	public void setMorphId(int morphId) {
		this.morphId = morphId;
	}

	/**
	 * Sets the duration.
	 * The value has to be in milliseconds.
	 * @param duration
	 */
	public void setDuration(long duration) {
		this.startDuration = System.currentTimeMillis();
		this.endDuration = startDuration + duration * 1000;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getId() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onApply(Player player) {
		player.setMorphId(morphId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGameTick(Player player) { }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEnd(Player player) {
		player.setMorphId(-1);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Expires by duration.
	 */
	@Override
	public boolean isExpired() {
		return getDuration() <= 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getDuration() {
		return endDuration - System.currentTimeMillis();
	}

	/**
	 * To make it unique in a set list.
	 * @return the id
	 */
	@Override
	public int hashCode() {
		return getId();
	}
}
