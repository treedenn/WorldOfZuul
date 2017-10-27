package DAL;

import BLL.character.Blacksmith;
import BLL.character.player.Player;
import BLL.character.player.Quiz;
import BLL.character.player.QuizManager;
import BLL.item.Item;
import BLL.item.ItemStack;
import DAL.scoring.PointSystem;
import BLL.world.Planet;
import DAL.yaml.YamlParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Model {
	private PointSystem pointSystem;
	private List<Quiz> quizes;

	public Model() {
		pointSystem = new PointSystem();
		createPlanets();
		initalizeQuizes();
	}

	public PointSystem getPointSystem() {
		return pointSystem;
	}

	public List<Quiz> getQuizes() {
		return quizes;
	}



	/* function to create rooms */
	public Map<String, Planet> createPlanets() {
		Planet cleron, scurn, hebrilles, xehna, gallifrey,
				skaro, orion, deineax, uskillion, ayrus, amrit, earth;

		/* initializing planets */

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

		orion.addItemStack(new ItemStack(Item.getItemById(0), 2));
		orion.addItemStack(new ItemStack(Item.getItemById(14), 2));
		orion.addItemStack(new ItemStack(Item.getItemById(26), 2));
		orion.addItemStack(new ItemStack(Item.getItemById(40), 2));

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

		return planetMap;
	}

	@SuppressWarnings("unchecked")
	private void initalizeQuizes() {
		YamlParser parser = new YamlParser(new File("./src/DAL/resource/quizdatabase.yaml"));

		Map<Integer, Map<String, Object>> database;

		try {
			database = parser.getYaml().load(new FileReader(parser.getFile()));

			quizes = new ArrayList<>(database.size());

			String question;
			List<String> opList;
			String[] options;
			Integer answer;

			for (Map<String, Object> map : database.values()) {
				question = (String) map.get("question");

				opList = (List<String>) map.get("options");
				options = opList.toArray(new String[opList.size()]);

				answer = (Integer) map.get("answer");

				quizes.add(new Quiz(question, options, answer));
			}
		} catch (FileNotFoundException e) {
			quizes = null;
			e.printStackTrace();
		}
	}
}