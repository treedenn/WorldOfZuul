package DAL;

import BLL.entity.Inventory;
import BLL.item.ItemStack;
import BLL.world.LockedBlacksmithPlanet;
import BLL.world.Planet;
import javafx.geometry.Point2D;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Handles the planets. It loads the planets.
 */
class PlanetHandler implements Loadable {
	private Map<String, Planet> planets;
	private Model model;

	PlanetHandler(Model model) {
		this.planets = null;
		this.model = model;

	}

	/**
	 * Gets the planets as a map.
	 * Key is planet name and value is the planet object.
	 * @return the map of planets
	 */
	Map<String, Planet> getPlanets() {
		return planets;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load() throws IOException {
		Planet cleron, scurn, hebrilles, xehna, gallifrey,
				skaro, orion, deineax, uskillion, ayrus, amrit, earth;

		/* initializing planets */

		cleron = new Planet("Cleron OR7", "A blue and orange gas planet. A beautiful view!", "./src/DAL/resource/images/planetViews/CleronOr7.jpg", "./src/DAL/resource/images/planet2DMaps/cleron.jpg", 0, 0);
		scurn = new Planet("Scurn 01K", "Uninhabitable planet, however it's possible to be on the planet with a suit.", "./src/DAL/resource/images/planetViews/Scurn 01k.jpg","./src/DAL/resource/images/planet2DMaps/scurn.jpg", 0, 0);
		hebrilles = new Planet("Hebrilles", "Beneath the atmosphere, a beautiful crystallized sea can be seen.", "./src/DAL/resource/images/planetViews/Hebrilles.png","./src/DAL/resource/images/planet2DMaps/hebrilles.jpg",0, 0);
		xehna = new LockedBlacksmithPlanet("Xehna", "Home planet and populated by female amazons. The name comes from the Warrior Princess, Xena.", "./src/DAL/resource/images/planetViews/Xehna.jpg","./src/DAL/resource/images/planet2DMaps/xehna.jpg",0, 0);
		gallifrey = new Planet("Gallifrey", "Home planet of the almost extinct species, Time Lords, except the last one, Doctor Who.", "./src/DAL/resource/images/planetViews/Gallifrey.jpg", "./src/DAL/resource/images/planet2DMaps/gallifrey.jpg",0, 0);
		skaro = new Planet("Skaro", "A terrifying planet, conquered by the destroyers named Daleks.", "./src/DAL/resource/images/planetViews/scaro.jpg","./src/DAL/resource/images/planet2DMaps/skaro.jpg",0, 0);
		orion = new Planet("Orion", "The planet is originally from the constellation called Orion's Belt. It travelled into our Solar System.","./src/DAL/resource/images/planetViews/Orion.jpg", "./src/DAL/resource/images/planet2DMaps/orion.jpg", 0, 0);
		deineax = new Planet("Deineax", "No one knows exactly the origin behind the name of this planet, yet myths say it comes from 'Dennis'.", "./src/DAL/resource/images/planetViews/Deinax.jpg", "./src/DAL/resource/images/planet2DMaps/deineax.jpg",0, 0);
		uskillion = new Planet("Uskillon", "An extremely hot planet, yet it contains different types of liquids.", "./src/DAL/resource/images/planetViews/Uskillon.jpg", "./src/DAL/resource/images/planet2DMaps/uskillon.jpg",0, 0);
		ayrus = new Planet("J8 Ayrus Z420", "No one knows what this planet contains. Secrets...", "./src/DAL/resource/images/planetViews/J8ayrus.jpg", "./src/DAL/resource/images/planet2DMaps/ayrus.jpg",0, 0);
		amrit = new Planet("Amrif Arret", "A habitable planet, covered with Mother Nature. Backwards, it is Terra Firma hence the content.", "./src/DAL/resource/images/planetViews/amrifarret.jpg", "./src/DAL/resource/images/planet2DMaps/amrifarret.jpg",0, 0);
		earth = new Planet("New Earth", "Humans tried to repopulate another planet and named it New Earth. You destroyed the original Earth, before it happened.", "./src/DAL/resource/images/planetViews/new earth.jpg", "./src/DAL/resource/images/planet2DMaps/earth.jpg",0, 0);

		/* adding items to planets */

		Inventory cleronInv = (Inventory) cleron.getInventory();
		Inventory scurnInv = (Inventory) scurn.getInventory();
		Inventory hebrillesInv = (Inventory) hebrilles.getInventory();
		Inventory xehnaInv = (Inventory) xehna.getInventory();
		Inventory gallifreyInv = (Inventory) gallifrey.getInventory();
		Inventory skaroInv = (Inventory) skaro.getInventory();
		Inventory orionInv = (Inventory) orion.getInventory();
		Inventory deineaxInv = (Inventory) deineax.getInventory();
		Inventory uskillionInv = (Inventory) uskillion.getInventory();
		Inventory ayrusInv = (Inventory) ayrus.getInventory();
		Inventory amritInv = (Inventory) amrit.getInventory();
		Inventory earthInv = (Inventory) earth.getInventory();

		cleronInv.add(new ItemStack(model.getItemById(0)));
		cleronInv.add(new ItemStack(model.getItemById(14)));
		cleronInv.add(new ItemStack(model.getItemById(26)));
		cleronInv.add(new ItemStack(model.getItemById(40)));
		cleronInv.add(new ItemStack(model.getItemById(39)));

		scurnInv.add(new ItemStack(model.getItemById(1)));
		scurnInv.add(new ItemStack(model.getItemById(15)));
		scurnInv.add(new ItemStack(model.getItemById(27)));
		scurnInv.add(new ItemStack(model.getItemById(41)));
		scurnInv.add(new ItemStack(model.getItemById(54)));

		hebrillesInv.add(new ItemStack(model.getItemById(2)));
		hebrillesInv.add(new ItemStack(model.getItemById(16)));
		hebrillesInv.add(new ItemStack(model.getItemById(28)));
		hebrillesInv.add(new ItemStack(model.getItemById(42)));
		hebrillesInv.add(new ItemStack(model.getItemById(55)));

		xehnaInv.add(new ItemStack(model.getItemById(3)));
		xehnaInv.add(new ItemStack(model.getItemById(17)));
		xehnaInv.add(new ItemStack(model.getItemById(29)));
		xehnaInv.add(new ItemStack(model.getItemById(43)));
		xehnaInv.add(new ItemStack(model.getItemById(53)));

		gallifreyInv.add(new ItemStack(model.getItemById(4)));
		gallifreyInv.add(new ItemStack(model.getItemById(18)));
		gallifreyInv.add(new ItemStack(model.getItemById(30)));
		gallifreyInv.add(new ItemStack(model.getItemById(44)));
		gallifreyInv.add(new ItemStack(model.getItemById(60)));

		skaroInv.add(new ItemStack(model.getItemById(5)));
		skaroInv.add(new ItemStack(model.getItemById(19)));
		skaroInv.add(new ItemStack(model.getItemById(31)));
		skaroInv.add(new ItemStack(model.getItemById(45)));

		orionInv.add(new ItemStack(model.getItemById(6)));
		orionInv.add(new ItemStack(model.getItemById(20)));
		orionInv.add(new ItemStack(model.getItemById(32)));
		orionInv.add(new ItemStack(model.getItemById(46)));

		deineaxInv.add(new ItemStack(model.getItemById(7)));
		deineaxInv.add(new ItemStack(model.getItemById(21)));
		deineaxInv.add(new ItemStack(model.getItemById(33)));
		deineaxInv.add(new ItemStack(model.getItemById(47)));

		uskillionInv.add(new ItemStack(model.getItemById(8)));
		uskillionInv.add(new ItemStack(model.getItemById(22)));
		uskillionInv.add(new ItemStack(model.getItemById(34)));
		uskillionInv.add(new ItemStack(model.getItemById(48)));

		ayrusInv.add(new ItemStack(model.getItemById(9)));
		ayrusInv.add(new ItemStack(model.getItemById(23)));
		ayrusInv.add(new ItemStack(model.getItemById(35)));
		ayrusInv.add(new ItemStack(model.getItemById(49)));
		ayrusInv.add(new ItemStack(model.getItemById(13)));

		amritInv.add(new ItemStack(model.getItemById(10)));
		amritInv.add(new ItemStack(model.getItemById(24)));
		amritInv.add(new ItemStack(model.getItemById(36)));
		amritInv.add(new ItemStack(model.getItemById(50)));
		amritInv.add(new ItemStack(model.getItemById(12)));

		earthInv.add(new ItemStack(model.getItemById(11)));
		earthInv.add(new ItemStack(model.getItemById(25)));
		earthInv.add(new ItemStack(model.getItemById(37)));
		earthInv.add(new ItemStack(model.getItemById(51)));
		earthInv.add(new ItemStack(model.getItemById(52)));
		earthInv.add(new ItemStack(model.getItemById(38)));

		/* shuffle the planets */

		Planet[] planets = new Planet[] {
				cleron, scurn, hebrilles, xehna, gallifrey, skaro, orion,
				deineax, uskillion, ayrus, amrit, earth
		};

		// TODO: Comment this statement to disable planet shuffle.
		Collections.shuffle(Arrays.asList(planets));

		/* the shuffled planets with random positions */

		Point2D[] points = new Point2D[] {
				new Point2D(4578, 7000), new Point2D(6240, 7313), new Point2D(7100, 5310),
				new Point2D(6580, 3029), new Point2D(6940, 1288), new Point2D(5116, 1556),
				new Point2D(3354, 1502), new Point2D(1619, 785), new Point2D(1221, 2628),
				new Point2D(1119, 4970), new Point2D(2041, 6821), new Point2D(2995, 5600),
		};

		this.planets = new LinkedHashMap<>();

		for(int i = 0; i < planets.length; i++) {
			planets[i].setCoordinates(points[i].getX(), points[i].getY());
			this.planets.put(planets[i].getName().replaceAll(" ", "").toLowerCase(), planets[i]);
		}
	}
}