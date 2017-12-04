package DAL;

import BLL.item.ItemStack;
import BLL.world.Planet;
import DAL.ACQ.Loadable;

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

		cleron = new Planet("Cleron OR7", "A blue and orange gas planet. A beautiful view!", 429, 5370);
		scurn = new Planet("Scurn 01K", "Uninhabitable planet, however it's possible to be on the planet with a suit.", 2388, 5504);
		hebrilles = new Planet("Hebrilles", "Beneath the atmosphere, a beautiful crystallized sea can be seen.", 4050, 5410);
		xehna = new Planet("Xehna", "Home planet and populated by female amazons. The name comes from the Warrior Princess, Xena.", 5648, 5343);
		gallifrey = new Planet("Gallifrey", "Home planet of the almost extinct species, Time Lords, except the last one, Doctor Who.", 5629, 3267);
		skaro = new Planet("Skaro", "A terrifying planet, conquered by the destroyers named Daleks.", 4087, 3508);
		orion = new Planet("Orion", "The planet is originally from the constellation called Orion's Belt. It travelled into our Solar System.", 2213, 3428);
		deineax = new Planet("Deineax", "No one knows exactly the origin behind the name of this planet, yet myths say it comes from 'Dennis'.", 347, 3348);
		uskillion = new Planet("Uskillon", "An extremely hot planet, yet it contains different types of liquids.", 399, 1419);
		ayrus = new Planet("J8 Ayrus Z420", "No one knows what this planet contains. Secrets...", 2130, 1258);
		amrit = new Planet("Amrif Arret", "A habitable planet, covered with Mother Nature. Backwards, it is Terra Firma hence the content.", 4064, 1352);
		earth = new Planet("New Earth", "Humans tried to repopulate another planet and named it New Earth. You destroyed the original Earth, before it happened.", 5736, 1232);

		/* adding items to planets */

		cleron.addItemStack(new ItemStack(model.getItemById(0)));
		cleron.addItemStack(new ItemStack(model.getItemById(14)));
		cleron.addItemStack(new ItemStack(model.getItemById(26)));
		cleron.addItemStack(new ItemStack(model.getItemById(40)));
		cleron.addItemStack(new ItemStack(model.getItemById(39)));

		scurn.addItemStack(new ItemStack(model.getItemById(1)));
		scurn.addItemStack(new ItemStack(model.getItemById(15)));
		scurn.addItemStack(new ItemStack(model.getItemById(27)));
		scurn.addItemStack(new ItemStack(model.getItemById(41)));
		scurn.addItemStack(new ItemStack(model.getItemById(54)));

		hebrilles.addItemStack(new ItemStack(model.getItemById(2)));
		hebrilles.addItemStack(new ItemStack(model.getItemById(16)));
		hebrilles.addItemStack(new ItemStack(model.getItemById(28)));
		hebrilles.addItemStack(new ItemStack(model.getItemById(42)));
		hebrilles.addItemStack(new ItemStack(model.getItemById(55)));

		xehna.addItemStack(new ItemStack(model.getItemById(3)));
		xehna.addItemStack(new ItemStack(model.getItemById(17)));
		xehna.addItemStack(new ItemStack(model.getItemById(29)));
		xehna.addItemStack(new ItemStack(model.getItemById(43)));
		xehna.addItemStack(new ItemStack(model.getItemById(53)));

		gallifrey.addItemStack(new ItemStack(model.getItemById(4)));
		gallifrey.addItemStack(new ItemStack(model.getItemById(18)));
		gallifrey.addItemStack(new ItemStack(model.getItemById(30)));
		gallifrey.addItemStack(new ItemStack(model.getItemById(44)));

		skaro.addItemStack(new ItemStack(model.getItemById(5)));
		skaro.addItemStack(new ItemStack(model.getItemById(19)));
		skaro.addItemStack(new ItemStack(model.getItemById(31)));
		skaro.addItemStack(new ItemStack(model.getItemById(45)));

		orion.addItemStack(new ItemStack(model.getItemById(6)));
		orion.addItemStack(new ItemStack(model.getItemById(20)));
		orion.addItemStack(new ItemStack(model.getItemById(32)));
		orion.addItemStack(new ItemStack(model.getItemById(46)));

		deineax.addItemStack(new ItemStack(model.getItemById(7)));
		deineax.addItemStack(new ItemStack(model.getItemById(21)));
		deineax.addItemStack(new ItemStack(model.getItemById(33)));
		deineax.addItemStack(new ItemStack(model.getItemById(47)));

		uskillion.addItemStack(new ItemStack(model.getItemById(8)));
		uskillion.addItemStack(new ItemStack(model.getItemById(22)));
		uskillion.addItemStack(new ItemStack(model.getItemById(34)));
		uskillion.addItemStack(new ItemStack(model.getItemById(48)));

		ayrus.addItemStack(new ItemStack(model.getItemById(9)));
		ayrus.addItemStack(new ItemStack(model.getItemById(23)));
		ayrus.addItemStack(new ItemStack(model.getItemById(35)));
		ayrus.addItemStack(new ItemStack(model.getItemById(49)));
		ayrus.addItemStack(new ItemStack(model.getItemById(13)));

		amrit.addItemStack(new ItemStack(model.getItemById(10)));
		amrit.addItemStack(new ItemStack(model.getItemById(24)));
		amrit.addItemStack(new ItemStack(model.getItemById(36)));
		amrit.addItemStack(new ItemStack(model.getItemById(50)));
		amrit.addItemStack(new ItemStack(model.getItemById(12)));

		earth.addItemStack(new ItemStack(model.getItemById(11)));
		earth.addItemStack(new ItemStack(model.getItemById(25)));
		earth.addItemStack(new ItemStack(model.getItemById(37)));
		earth.addItemStack(new ItemStack(model.getItemById(51)));
		earth.addItemStack(new ItemStack(model.getItemById(52)));
		earth.addItemStack(new ItemStack(model.getItemById(38)));

		/* shuffle the planets and put it inside a map */

		Planet[] planets = new Planet[]{
				cleron, scurn, hebrilles, xehna, gallifrey, skaro, orion,
				deineax, uskillion, ayrus, amrit, earth
		};

		Collections.shuffle(Arrays.asList(planets));

		this.planets = new LinkedHashMap<>();

		for (Planet planet : planets) {
			this.planets.put(planet.getName().replaceAll(" ", "").toLowerCase(), planet);
		}
	}
}