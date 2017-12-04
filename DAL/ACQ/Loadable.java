package DAL.ACQ;

import java.io.IOException;

/**
 * Describes when an object has the ability to load.
 */
public interface Loadable {
	/**
	 * Contains how the object should load.
	 * @throws IOException when loading fails
	 */
	void load() throws IOException;
}
