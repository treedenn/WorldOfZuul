package DAL;

import BLL.character.player.Quiz;
import BLL.item.*;
import DAL.scoring.PointSystem;
import BLL.world.Planet;
import DAL.yaml.ItemParser;
import DAL.yaml.YamlParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Model {
	private static List<Item> itemDatabase;

	private PointSystem pointSystem;
	private List<Quiz> quizes;

	static {
		initalizeDatabase();
	}

	public Model() {
		pointSystem = new PointSystem();
		createPlanets();
		initalizeQuizes();
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

		cleron.addItemStack(new ItemStack(getItemByIndex(0)));
		cleron.addItemStack(new ItemStack(getItemByIndex(14)));
		cleron.addItemStack(new ItemStack(getItemByIndex(26)));
		cleron.addItemStack(new ItemStack(getItemByIndex(40)));
		cleron.addItemStack(new ItemStack(getItemByIndex(39)));

		scurn.addItemStack(new ItemStack(getItemByIndex(1)));
		scurn.addItemStack(new ItemStack(getItemByIndex(15)));
		scurn.addItemStack(new ItemStack(getItemByIndex(27)));
		scurn.addItemStack(new ItemStack(getItemByIndex(41)));
		scurn.addItemStack(new ItemStack(getItemByIndex(54)));

		hebrilles.addItemStack(new ItemStack(getItemByIndex(2)));
		hebrilles.addItemStack(new ItemStack(getItemByIndex(16)));
		hebrilles.addItemStack(new ItemStack(getItemByIndex(28)));
		hebrilles.addItemStack(new ItemStack(getItemByIndex(42)));
		hebrilles.addItemStack(new ItemStack(getItemByIndex(55)));

		xehna.addItemStack(new ItemStack(getItemByIndex(3)));
		xehna.addItemStack(new ItemStack(getItemByIndex(17)));
		xehna.addItemStack(new ItemStack(getItemByIndex(29)));
		xehna.addItemStack(new ItemStack(getItemByIndex(43)));
		xehna.addItemStack(new ItemStack(getItemByIndex(53)));

		gallifrey.addItemStack(new ItemStack(getItemByIndex(4)));
		gallifrey.addItemStack(new ItemStack(getItemByIndex(18)));
		gallifrey.addItemStack(new ItemStack(getItemByIndex(30)));
		gallifrey.addItemStack(new ItemStack(getItemByIndex(44)));

		skaro.addItemStack(new ItemStack(getItemByIndex(5)));
		skaro.addItemStack(new ItemStack(getItemByIndex(19)));
		skaro.addItemStack(new ItemStack(getItemByIndex(31)));
		skaro.addItemStack(new ItemStack(getItemByIndex(45)));

		orion.addItemStack(new ItemStack(getItemByIndex(6)));
		orion.addItemStack(new ItemStack(getItemByIndex(20)));
		orion.addItemStack(new ItemStack(getItemByIndex(32)));
		orion.addItemStack(new ItemStack(getItemByIndex(46)));

		deineax.addItemStack(new ItemStack(getItemByIndex(7)));
		deineax.addItemStack(new ItemStack(getItemByIndex(21)));
		deineax.addItemStack(new ItemStack(getItemByIndex(33)));
		deineax.addItemStack(new ItemStack(getItemByIndex(47)));

		uskillion.addItemStack(new ItemStack(getItemByIndex(8)));
		uskillion.addItemStack(new ItemStack(getItemByIndex(22)));
		uskillion.addItemStack(new ItemStack(getItemByIndex(34)));
		uskillion.addItemStack(new ItemStack(getItemByIndex(48)));

		ayrus.addItemStack(new ItemStack(getItemByIndex(9)));
		ayrus.addItemStack(new ItemStack(getItemByIndex(23)));
		ayrus.addItemStack(new ItemStack(getItemByIndex(35)));
		ayrus.addItemStack(new ItemStack(getItemByIndex(49)));
		ayrus.addItemStack(new ItemStack(getItemByIndex(13)));

		amrit.addItemStack(new ItemStack(getItemByIndex(10)));
		amrit.addItemStack(new ItemStack(getItemByIndex(24)));
		amrit.addItemStack(new ItemStack(getItemByIndex(36)));
		amrit.addItemStack(new ItemStack(getItemByIndex(50)));
		amrit.addItemStack(new ItemStack(getItemByIndex(12)));

		earth.addItemStack(new ItemStack(getItemByIndex(11)));
		earth.addItemStack(new ItemStack(getItemByIndex(25)));
		earth.addItemStack(new ItemStack(getItemByIndex(37)));
		earth.addItemStack(new ItemStack(getItemByIndex(51)));
		earth.addItemStack(new ItemStack(getItemByIndex(52)));
		earth.addItemStack(new ItemStack(getItemByIndex(38)));

		/* shuffle the planets and places them inside a HashMap */

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

	private static void initalizeDatabase() {
		ItemParser parser = ItemParser.getInstance();

		try {
			Map<Integer, Map<String, Object>> map = parser.getDatabase();

			itemDatabase = new ArrayList<>(map.size());

			String name;
			String description;
			ItemType type;
			Color color;
			State state;
			boolean pickupable;
			boolean dropable;
			double weight;

			for(Map<String, Object> o : map.values()) {
				name = (String) o.get("name");
				color = Color.valueOf((String) o.get("color"));
				state = State.valueOf((String) o.get("state"));
				description = (String) o.get("description");
				description = description.replace("{{color}}", color.name().toLowerCase());
				description = description.replace("{{state}}", state.name().toLowerCase());
				type = ItemType.valueOf((String) o.get("itemType"));
				pickupable = (boolean) o.get("pickupable");
				dropable = (boolean) o.get("dropable");
				weight = (double) o.get("weight");

				itemDatabase.add(new Item(name, description, type, color, state, weight, pickupable, dropable));
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public enum id {
		LQ_KERISA, LQ_MOLT, LQ_SEDARUS, LQ_GOULIIQ, LQ_CRYPTIC, LQ_AMANZI, LQ_ZUUR, LQ_EAUX, LQ_HUNAJAR, LQ_MIELEPE, LQ_FUSILIA_FERROX, LQ_LATEX, KALIDECA, LQ_AMBRUSIA, // LIQUIDS
		CN_REKAR, CN_OLTAM, CN_EDAUSS, CN_QIIGOL, CN_RYPICAS, CN_ZINAMA, CN_RUUKAZ, CN_XARUJA, CN_UNJAROH, CN_LEMIPE, CN_FEROXIS, CN_TEXIO, CN_DOIM, CN_ALDAKK, // CANISTERS
		GR_KAREDU, GR_LOQOO, GR_DAUPYRA, GR_GONAZI, GR_PIKARQ, GR_ZAJANJ, GR_JUKOHRA, GR_MIPLIM, GR_REXIRN, GR_TEDOX, GR_AKELOS, GR_DOIH, // GEARS
		CPU_TEK_XX, CPU_TEK_XXVI, CPU_MOLT_IV, CPU_MOLT_VD, CPU_CX_TITANIUM_4, CPU_CX_TITANIUM_8, CPU_FIX_FEROCITY_1, CPU_FIX_FEROCITY_3, // CPUS
		CPU_I11_X2017, CPU_I13_II5290, CPU_I13_IV8525, CPU_I15_7750, CPU_CSP_6M2T, CPU_CSP_10MT, CPU_CSP_MXV, CPU_CSP_M2X1V
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