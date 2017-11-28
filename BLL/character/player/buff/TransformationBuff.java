package BLL.character.player.buff;

import BLL.character.player.Player;

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

	@Override
	public void onApply(Player player) {
		player.setMorphId(morphId);
	}

	@Override
	public void onGameTick(Player player) {

	}

	@Override
	public void onEnd(Player player) {
		player.setMorphId(-1);
	}

	@Override
	public boolean isExpired() {
		return System.currentTimeMillis() >= endDuration;
	}
}
