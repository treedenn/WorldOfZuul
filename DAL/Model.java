package DAL;

import BLL.character.player.Quiz;
import BLL.item.*;
import DAL.scoring.PointSystem;
import BLL.world.Planet;
import DAL.scoring.Score;
import DAL.yaml.YamlParser;

import java.io.*;
import java.util.*;

public class Model {
	private static List<Item> itemDatabase;

	private PointSystem pointSystem;
	private List<Quiz> quizes;
	private List<Score> highscore;

	static {
		initalizeDatabase();
	}

	public Model() {
		pointSystem = new PointSystem();
		highscore = new ArrayList<>();
		createPlanets();
		initalizeQuiz();
	}

	public static Item getItemById(int index) {
		return new Item(itemDatabase.get(index));
	}

	public PointSystem getPointSystem() {
		return pointSystem;
	}

	public static Item getItemByIndex(int index) {
		return itemDatabase.get(index);
	}

	public List<Quiz> getQuizes() {
		return quizes;
	}

	public List<Score> getHighscore() {
		return highscore;
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

		cleron.addItemStack(new ItemStack(Model.getItemById(0)));
		cleron.addItemStack(new ItemStack(Model.getItemById(14)));
		cleron.addItemStack(new ItemStack(Model.getItemById(26)));
		cleron.addItemStack(new ItemStack(Model.getItemById(40)));
		cleron.addItemStack(new ItemStack(Model.getItemById(39)));

		scurn.addItemStack(new ItemStack(Model.getItemById(1)));
		scurn.addItemStack(new ItemStack(Model.getItemById(15)));
		scurn.addItemStack(new ItemStack(Model.getItemById(27)));
		scurn.addItemStack(new ItemStack(Model.getItemById(41)));
		scurn.addItemStack(new ItemStack(Model.getItemById(54)));

		hebrilles.addItemStack(new ItemStack(Model.getItemById(2)));
		hebrilles.addItemStack(new ItemStack(Model.getItemById(16)));
		hebrilles.addItemStack(new ItemStack(Model.getItemById(28)));
		hebrilles.addItemStack(new ItemStack(Model.getItemById(42)));
		hebrilles.addItemStack(new ItemStack(Model.getItemById(55)));

		xehna.addItemStack(new ItemStack(Model.getItemById(3)));
		xehna.addItemStack(new ItemStack(Model.getItemById(17)));
		xehna.addItemStack(new ItemStack(Model.getItemById(29)));
		xehna.addItemStack(new ItemStack(Model.getItemById(43)));
		xehna.addItemStack(new ItemStack(Model.getItemById(53)));

		gallifrey.addItemStack(new ItemStack(Model.getItemById(4)));
		gallifrey.addItemStack(new ItemStack(Model.getItemById(18)));
		gallifrey.addItemStack(new ItemStack(Model.getItemById(30)));
		gallifrey.addItemStack(new ItemStack(Model.getItemById(44)));

		skaro.addItemStack(new ItemStack(Model.getItemById(5)));
		skaro.addItemStack(new ItemStack(Model.getItemById(19)));
		skaro.addItemStack(new ItemStack(Model.getItemById(31)));
		skaro.addItemStack(new ItemStack(Model.getItemById(45)));

		orion.addItemStack(new ItemStack(Model.getItemById(6)));
		orion.addItemStack(new ItemStack(Model.getItemById(20)));
		orion.addItemStack(new ItemStack(Model.getItemById(32)));
		orion.addItemStack(new ItemStack(Model.getItemById(46)));

		deineax.addItemStack(new ItemStack(Model.getItemById(7)));
		deineax.addItemStack(new ItemStack(Model.getItemById(21)));
		deineax.addItemStack(new ItemStack(Model.getItemById(33)));
		deineax.addItemStack(new ItemStack(Model.getItemById(47)));

		uskillion.addItemStack(new ItemStack(Model.getItemById(8)));
		uskillion.addItemStack(new ItemStack(Model.getItemById(22)));
		uskillion.addItemStack(new ItemStack(Model.getItemById(34)));
		uskillion.addItemStack(new ItemStack(Model.getItemById(48)));

		ayrus.addItemStack(new ItemStack(Model.getItemById(9)));
		ayrus.addItemStack(new ItemStack(Model.getItemById(23)));
		ayrus.addItemStack(new ItemStack(Model.getItemById(35)));
		ayrus.addItemStack(new ItemStack(Model.getItemById(49)));
		ayrus.addItemStack(new ItemStack(Model.getItemById(13)));

		amrit.addItemStack(new ItemStack(Model.getItemById(10)));
		amrit.addItemStack(new ItemStack(Model.getItemById(24)));
		amrit.addItemStack(new ItemStack(Model.getItemById(36)));
		amrit.addItemStack(new ItemStack(Model.getItemById(50)));
		amrit.addItemStack(new ItemStack(Model.getItemById(12)));

		earth.addItemStack(new ItemStack(Model.getItemById(11)));
		earth.addItemStack(new ItemStack(Model.getItemById(25)));
		earth.addItemStack(new ItemStack(Model.getItemById(37)));
		earth.addItemStack(new ItemStack(Model.getItemById(51)));
		earth.addItemStack(new ItemStack(Model.getItemById(52)));
		earth.addItemStack(new ItemStack(Model.getItemById(38)));

		/* shuffle the planets and put it inside a HashMap */

		Planet[] planets = new Planet[]{
				cleron, scurn, hebrilles, xehna, gallifrey, skaro, orion,
				deineax, uskillion, ayrus, amrit, earth
		};

		Collections.shuffle(Arrays.asList(planets));

		LinkedHashMap<String, Planet> planetMap = new LinkedHashMap<>();

		for (Planet planet : planets) {
			planetMap.put(planet.getName().replaceAll(" ", "").toLowerCase(), planet);
		}

		return planetMap;
	}

	public void loadHighscore() {
		YamlParser parser = new YamlParser(new File("./src/DAL/resource/highscore.yaml"));

		try {
			Map<Integer, Map<String, Object>> map = parser.getYaml().load(new FileReader(parser.getFile()));

			String name;
			int score;

			for (Map<String, Object> o : map.values()) {
				name = (String) o.get("name");
				score = (int) o.get("score");

				highscore.add(new Score(name, score));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void saveHighscore(List<Score> scores) {
		File file = new File("./src/DAL/resource/highscore.yaml");
		YamlParser yaml = new YamlParser(file);

		Map<Integer, HashMap<String, Object>> map = new HashMap<>();

		for (int i = 0; i < scores.size(); i++) {
			HashMap<String, Object> data = new HashMap<>();

			data.put("name", scores.get(i).getName());
			data.put("score", scores.get(i).getScore());

			map.put(i, data);
		}

		try {
			yaml.getYaml().dump(map, new FileWriter(new File("./src/DAL/resource/highscore.yaml")));
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void initalizeDatabase() {
		YamlParser parser = new YamlParser(new File("./src/DAL/resource/itemdatabase.yaml"));

		try {
			Map<Integer, Map<String, Object>> map = parser.getYaml().load(new FileReader(parser.getFile()));

			itemDatabase = new ArrayList<>(map.size());

			String name;
			String description;
			ItemType type;
			Color color;
			State state;
			boolean pickupable;
			boolean dropable;
			double weight;

			for (Map<String, Object> o : map.values()) {
				name = (String) o.get("name");
				color = Color.valueOf((String) o.get("color"));
				state = State.valueOf((String) o.get("state"));
				description = (String) o.get("description");
				type = ItemType.valueOf((String) o.get("itemType"));
				pickupable = (boolean) o.get("pickupable");
				dropable = (boolean) o.get("dropable");
				weight = (double) o.get("weight");

				itemDatabase.add(new Item(name, description, type, color, state, weight, pickupable, dropable));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void initalizeQuiz() {
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