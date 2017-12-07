package DAL;

import BLL.ACQ.PersistenceLayer;
import BLL.Game;
import BLL.UsableHandler;
import BLL.entity.player.Quiz;
import BLL.item.*;
import BLL.world.Planet;
import BLL.scoring.Score;
import DAL.ACQ.Loadable;
import DAL.ACQ.Savable;

import java.io.*;
import java.util.*;

/**
 * The facade of the Data Access Layer (DLL).
 * **SINGLETON**
 */
public class Model implements PersistenceLayer {
	private static Model INSTANCE;

	private DatabaseHandler dbHandler;
	private QuizHandler qzHandler;
	private HighscoreHandler hsHandler;
	private PlanetHandler plHandler;
	private MessageHandler msgHandler;
	private GameStateHandler gsHandler;

	private Model() {
		dbHandler = new DatabaseHandler(new File("./src/DAL/resource/itemdatabase.yaml"));
		qzHandler = new QuizHandler(new File("./src/DAL/resource/quizdatabase.yaml"));
		hsHandler = new HighscoreHandler(new File("./src/DAL/resource/highscore.yaml"));
		msgHandler = new MessageHandler(new File("./src/DAL/resource/localization.yaml"));
		plHandler = new PlanetHandler(this);
		gsHandler = new GameStateHandler(new File("./game-save.yaml"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load() {
		load(dbHandler);
		load(qzHandler);
		load(hsHandler);
		load(plHandler);
		load(msgHandler);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUsableHandler(UsableHandler handler) {
		dbHandler.setUsableHandler(handler);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Quiz> getQuizes() {
		return qzHandler.getQuizes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Score> getHighscore() {
		return hsHandler.getHighscore();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveHighscore() {
		save(hsHandler);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item getItemById(int index) {
		try {
			return (Item) dbHandler.getItemById(index).clone();
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage(String key) {
		return msgHandler.getMessage(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Planet> getPlanets() {
		return plHandler.getPlanets();
	}

	/**
	 * Calls the load function from the {@link Loadable} interface.
	 * @param object to load
	 */
	private void load(Loadable object) {
		try {
			object.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calls the save function from the {@link Savable} interface.
	 * @param object to save
	 */
	private void save(Savable object) {
		try {
			object.save();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void saveGame(Game gameInstance) throws IOException, NullPointerException {
		gsHandler.setGameInstance(gameInstance);
		save(gsHandler);
	}

	public void loadGame(Game gameInstance) throws IOException, NullPointerException {
		gsHandler.setGameInstance(gameInstance);
		load(gsHandler);
	}

	/**
	 * Gets the instance of the model.
	 * @return the model
	 */
	public static Model getInstance() {
		if(INSTANCE == null) INSTANCE = new Model();
		return INSTANCE;
	}
}