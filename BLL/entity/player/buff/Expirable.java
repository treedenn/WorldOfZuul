package BLL.entity.player.buff;

/**
 * If an object is expirable by time, this interface will be used.
 * An example where this is used, is the transformation buff.
 */
public interface Expirable {
	/**
	 * The base of the function should return a duration in milliseconds.
	 * @return duration in milliseconds
	 */
	long getDuration();
}
