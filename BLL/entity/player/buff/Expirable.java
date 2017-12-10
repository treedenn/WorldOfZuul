package BLL.entity.player.buff;

public interface Expirable {
	/**
	 * The base of the function should return a duration in milliseconds.
	 * @return duration in milliseconds
	 */
	long getDuration();
}
