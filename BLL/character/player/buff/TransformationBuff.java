package BLL.character.player.buff;

import BLL.character.player.Player;

/**
 * It describes the Transformation Buff.
 */
public class TransformationBuff implements Buff {
	private int morphId;
	private int duration;
	private long startDuration;
	private long endDuration;

	public TransformationBuff(int morphId, int duration) {
		this.morphId = morphId;
		this.duration = duration;
		this.startDuration = System.currentTimeMillis();
		this.endDuration = startDuration + duration * 1000;
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
	public void onGameTick(Player player) {

	}

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
		return System.currentTimeMillis() >= endDuration;
	}
}
