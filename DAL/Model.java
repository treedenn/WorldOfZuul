package DAL;

import DAL.character.Blacksmith;
import DAL.character.player.Player;
import DAL.character.player.QuizManager;
import DAL.item.Item;
import DAL.item.ItemStack;
import DAL.world.Planet;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Model {
	private boolean finished;
	private Player player;
	private Blacksmith blacksmith;
	private QuizManager manager;

	public Model() {
		finished = false;
		player = new Player();
		blacksmith = new Blacksmith();
		manager = new QuizManager();

		createPlanets();
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean value) {
		finished = value;
	}

	public Player getPlayer() {
		return player;
	}

	public Blacksmith getBlacksmith() {
		return blacksmith;
	}

	public QuizManager getManager() {
		return manager;
	}

	/* function to create rooms */
	private void createPlanets() {
		Planet centerUniverse,
				cleron, scurn, hebrilles, xehna, gallifrey,
				skaro, orion, deineax, uskillion, ayrus, amrit, earth;

		/* initializing planets */

		centerUniverse = new Planet("Center of the Universe", "This is not exactly the center, since a black hole exists in the center of every Universe.");
		cleron = new Planet("Cleron OR7", "A blue and orange gas planet. A beautiful view!");
		scurn = new Planet("Scurn 01K", "Un-habitable planet, however, it is possible to be on the planet with a suit.");
		hebrilles = new Planet("Hebrilles", "Beneath the atmosphere, a beautiful crystallized sea can be seen.");
		xehna = new Planet("Xehna", "Home-planet and populated by female amazons. The name comes from the Warrior Princess, Xena.");
		gallifrey = new Planet("Gallifrey", "Home-planet of the almost extincted specie, Time Lords, except the last one, Doctor Who.");
		skaro = new Planet("Skaro", "A terrifying planet, conquered by the destroyers named Daleks.");
		orion = new Planet("Orion", "The planet is originally from the constellation called Orion's Belt. It travelled into our Solar System.");
		deineax = new Planet("Deineax", "No one knows exactly the name origin of this planet, yet myths say it comes from 'Dennis'.");
		uskillion = new Planet("Uskillon", "An extremely hot planet, yet, it contains different types of liquids.");
		ayrus = new Planet("J8 Ayrus Z420", "No one knows what this planet contains. Secrets...");
		amrit = new Planet("Amrif Arret", "A habitable planet, covered with Mother Nature. Backwards, it is Terra Firma hence the content.");
		earth = new Planet("New Earth", "Humans tried to repopulate another planet and named it New Earth. You destroyed the original Earth, before it happen.");

		/* adding items to planets */

		centerUniverse.addItemStack(new ItemStack(Item.getItemById(0), 2));
		centerUniverse.addItemStack(new ItemStack(Item.getItemById(14), 2));
		centerUniverse.addItemStack(new ItemStack(Item.getItemById(26), 2));
		centerUniverse.addItemStack(new ItemStack(Item.getItemById(40), 2));

		/* shuffle the planets and put it inside a HashMap */

		Planet[] planets = new Planet[] {
				cleron, scurn, hebrilles, xehna, gallifrey, skaro, orion,
				deineax, uskillion, ayrus, amrit, earth
		};

		Collections.shuffle(Arrays.asList(planets));

		LinkedHashMap<String, Planet> planetMap = new LinkedHashMap<>();
		
		for(Planet planet : planets) {
			planetMap.put(planet.getName().replaceAll(" ", "").toLowerCase(), planet);
		}

		/* assign planets to character objects */

		player.setCurrentPlanet(centerUniverse);
		player.setPlanets(planetMap);

		blacksmith.setCurrentPlanet(planets[(int) (Math.random() * planets.length)]);
		blacksmith.setPlanets(planetMap);
	}
}
