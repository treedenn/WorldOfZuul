package BLL.ACQ;

import BLL.UsableHandler;
import BLL.character.player.Quiz;
import BLL.item.Item;
import BLL.scoring.Score;
import BLL.world.Planet;

import java.util.List;
import java.util.Map;

/**
 * Describes what the persitent layer has to have.
 */
public interface Persistent {
	/**
	 * Business layer will invoke load when it is initialising.
	 * Load database etc.
	 */
	void load();

	/**
	 * When initialising, business layer will invoke this and send the {@link UsableHandler}.
	 * @param handler the usable handler
	 */
	void setUsableHandler(UsableHandler handler);

	/**
	 * Gets an item based on an index.
	 * @param index of the item
	 * @return the item at given index
	 */
	Item getItemById(int index);

	/**
	 * Gets all the planets.
	 * @return the planets
	 */
	Map<String, Planet> getPlanets();

	/**
	 * Gets all the quizes.
	 * @return the quizes
	 */
	List<Quiz> getQuizes();

	/**
	 * Get the highscore.
	 * @return List of scores
	 */
	List<Score> getHighscore();

	/**
	 * Saves the highscore.
	 */
	void saveHighscore();

	/**
	 * Gets a message from the localization based on key.
	 * @param key to obtain the message
	 * @return the message of the key
	 */
	String getMessage(String key);
}
