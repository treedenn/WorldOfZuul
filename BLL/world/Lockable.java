package BLL.world;

/**
 * When an object has to be lockable, this can interface can be implemented.
 * It contains a function, to describe when the object is unlocked.
 */
public interface Lockable {
	/**
	 * Checking when the object is unlocked.
	 * @return true, if it is unlocked
	 */
	boolean isUnlocked();
}
