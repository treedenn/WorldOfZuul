package BLL.ACQ;

import BLL.ACQ.data.IPlanetData;
import BLL.ACQ.data.IPlayerData;
import BLL.ACQ.data.IWorldData;
import BLL.UsableHandler;
import BLL.entity.player.Quiz;
import BLL.item.Item;
import BLL.scoring.Score;
import BLL.world.Planet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Describes what the persitent layer has to have,
 * before business layer can function properly.
 */
public interface PersistenceLayer {
	/**
	 * Gets the IWorldData object to save/load data.
	 * @return IWorldData object
	 */
	IWorldData getWorldData();

	/**
	 * Gets the IPlayerData object to save/load data.
	 * @return IPlayerData object
	 */
	IPlayerData getPlayerData();

	/**
	 * Gets the IPlanetData object to save/load data.
	 * @return IPlanetData object
	 */
	IPlanetData getPlanetData();

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

	/**
	 * Tells the persistent layer to save the game.
	 * @throws IOException throws, if file does not exist
	 */
	void saveGame() throws IOException;

	/**
	 * Tells the persistent layer to load the game.
	 * @throws IOException throws, if file does not exist
	 */
	void loadGame() throws IOException;

	/**
	 * Checks whether the saving/loading file exists.
	 * @return true, if it exists
	 */
	boolean hasLoadingFile();

	/**
	 * Deletes the saving/loading file.
	 */
	boolean deleteLoadingFile();
}
