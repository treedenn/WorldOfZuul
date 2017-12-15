package DAL;

import java.io.IOException;

/**
 * Describes when an object has the ability to save.
 */
public interface Savable {
	/**
	 * How the object should save.
	 * @throws IOException when saving fails
	 */
	void save() throws IOException;
}
