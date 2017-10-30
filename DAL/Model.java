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
		scurn = new Planet("Scurn 01K", "Uninhabitable planet, however it's possible to be on the planet with a suit.");
		hebrilles = new Planet("Hebrilles", "Beneath the atmosphere, a beautiful crystallized sea can be seen.");
		xehna = new Planet("Xehna", "Home planet and populated by female amazons. The name comes from the Warrior Princess, Xena.");
		gallifrey = new Planet("Gallifrey", "Home planet of the almost extinct species, Time Lords, except the last one, Doctor Who.");
		skaro = new Planet("Skaro", "A terrifying planet, conquered by the destroyers named Daleks.");
		orion = new Planet("Orion", "The planet is originally from the constellation called Orion's Belt. It travelled into our Solar System.");
		deineax = new Planet("Deineax", "No one knows exactly the origin behind the name of this planet, yet myths say it comes from 'Dennis'.");
		uskillion = new Planet("Uskillon", "An extremely hot planet, yet it contains different types of liquids.");
		ayrus = new Planet("J8 Ayrus Z420", "No one knows what this planet contains. Secrets...");
		amrit = new Planet("Amrif Arret", "A habitable planet, covered with Mother Nature. Backwards, it is Terra Firma hence the content.");
		earth = new Planet("New Earth", "Humans tried to repopulate another planet and named it New Earth. You destroyed the original Earth, before it happened.");

		/* adding items to planets */

		cleron.addItemStack(new ItemStack(Item.getItemById(0)));
		cleron.addItemStack(new ItemStack(Item.getItemById(14)));
		cleron.addItemStack(new ItemStack(Item.getItemById(26)));
		cleron.addItemStack(new ItemStack(Item.getItemById(40)));
		cleron.addItemStack(new ItemStack(Item.getItemById(39)));

		scurn.addItemStack(new ItemStack(Item.getItemById(1)));
		scurn.addItemStack(new ItemStack(Item.getItemById(15)));
		scurn.addItemStack(new ItemStack(Item.getItemById(27)));
		scurn.addItemStack(new ItemStack(Item.getItemById(41)));
		scurn.addItemStack(new ItemStack(Item.getItemById(54)));

		hebrilles.addItemStack(new ItemStack(Item.getItemById(2)));
		hebrilles.addItemStack(new ItemStack(Item.getItemById(16)));
		hebrilles.addItemStack(new ItemStack(Item.getItemById(28)));
		hebrilles.addItemStack(new ItemStack(Item.getItemById(42)));
		hebrilles.addItemStack(new ItemStack(Item.getItemById(55)));

		xehna.addItemStack(new ItemStack(Item.getItemById(3)));
		xehna.addItemStack(new ItemStack(Item.getItemById(17)));
		xehna.addItemStack(new ItemStack(Item.getItemById(29)));
		xehna.addItemStack(new ItemStack(Item.getItemById(43)));
		xehna.addItemStack(new ItemStack(Item.getItemById(53)));

		gallifrey.addItemStack(new ItemStack(Item.getItemById(4)));
		gallifrey.addItemStack(new ItemStack(Item.getItemById(18)));
		gallifrey.addItemStack(new ItemStack(Item.getItemById(30)));
		gallifrey.addItemStack(new ItemStack(Item.getItemById(44)));

		skaro.addItemStack(new ItemStack(Item.getItemById(5)));
		skaro.addItemStack(new ItemStack(Item.getItemById(19)));
		skaro.addItemStack(new ItemStack(Item.getItemById(31)));
		skaro.addItemStack(new ItemStack(Item.getItemById(45)));

		orion.addItemStack(new ItemStack(Item.getItemById(6)));
		orion.addItemStack(new ItemStack(Item.getItemById(20)));
		orion.addItemStack(new ItemStack(Item.getItemById(32)));
		orion.addItemStack(new ItemStack(Item.getItemById(46)));

		deineax.addItemStack(new ItemStack(Item.getItemById(7)));
		deineax.addItemStack(new ItemStack(Item.getItemById(21)));
		deineax.addItemStack(new ItemStack(Item.getItemById(33)));
		deineax.addItemStack(new ItemStack(Item.getItemById(47)));

		uskillion.addItemStack(new ItemStack(Item.getItemById(8)));
		uskillion.addItemStack(new ItemStack(Item.getItemById(22)));
		uskillion.addItemStack(new ItemStack(Item.getItemById(34)));
		uskillion.addItemStack(new ItemStack(Item.getItemById(48)));

		ayrus.addItemStack(new ItemStack(Item.getItemById(9)));
		ayrus.addItemStack(new ItemStack(Item.getItemById(23)));
		ayrus.addItemStack(new ItemStack(Item.getItemById(35)));
		ayrus.addItemStack(new ItemStack(Item.getItemById(49)));
		ayrus.addItemStack(new ItemStack(Item.getItemById(13)));

		amrit.addItemStack(new ItemStack(Item.getItemById(10)));
		amrit.addItemStack(new ItemStack(Item.getItemById(24)));
		amrit.addItemStack(new ItemStack(Item.getItemById(36)));
		amrit.addItemStack(new ItemStack(Item.getItemById(50)));
		amrit.addItemStack(new ItemStack(Item.getItemById(12)));

		earth.addItemStack(new ItemStack(Item.getItemById(11)));
		earth.addItemStack(new ItemStack(Item.getItemById(25)));
		earth.addItemStack(new ItemStack(Item.getItemById(37)));
		earth.addItemStack(new ItemStack(Item.getItemById(51)));
		earth.addItemStack(new ItemStack(Item.getItemById(52)));
		earth.addItemStack(new ItemStack(Item.getItemById(38)));



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