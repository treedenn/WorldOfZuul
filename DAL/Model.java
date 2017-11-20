package DAL;

import BLL.ACQ.Persistent;
import BLL.character.player.Quiz;
import BLL.item.*;
import BLL.world.Planet;
import BLL.scoring.Score;
import DAL.ACQ.Loadable;
import DAL.ACQ.Savable;

import java.io.*;
import java.util.*;

public class Model implements Persistent {
	private static Model INSTANCE;

	private DatabaseHandler dbHandler;
	private QuizHandler qzHandler;
	private HighscoreHandler hsHandler;
	private PlanetHandler plHandler;

	private Model() {
		dbHandler = new DatabaseHandler();
		qzHandler = new QuizHandler();
		hsHandler = new HighscoreHandler();
		plHandler = new PlanetHandler(this);

		load(dbHandler);
		load(qzHandler);
		load(hsHandler);
		load(plHandler);
	}

	@Override
	public List<Quiz> getQuizes() {
		return qzHandler.getQuizes();
	}

	@Override
	public List<Score> getHighscore() {
		return hsHandler.getHighscore();
	}

	@Override
	public void saveHighscore() {
		save(hsHandler);
	}

	@Override
	public Item getItemById(int index) {
		return new Item(dbHandler.getItemById(index));
	}

	/* function to create rooms */
	@Override
	public Map<String, Planet> getPlanets() {
		return plHandler.getPlanets();
	}

	private void load(Loadable object) {
		try {
			object.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private void save(Savable object) {
		try {
			object.save();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static Model getInstance() {
		if(INSTANCE == null) INSTANCE = new Model();
		return INSTANCE;
	}
}