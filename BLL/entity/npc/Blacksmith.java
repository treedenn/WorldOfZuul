package BLL.entity.npc;

import BLL.ACQ.BlacksmithTraceState;
import BLL.ACQ.INPCAction;
import BLL.ACQ.PersistenceLayer;
import BLL.entity.MovableEntity;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.entity.player.Recipe;
import BLL.item.ItemStack;
import BLL.world.Planet;

import java.util.Iterator;
import java.util.Map;

/**
 * Contains all the functionality of the Blacksmith NPC.
 * The Blacksmith is the NPC player has to find to begin his journey.
 * He is the one that allows the player to win, since he can repair his portal gun.
 */
public class Blacksmith extends MovableEntity implements NPC {
	private Recipe recipe;
	private String[] visitedPlanets;

	private NPCActionCollection collection;

	/**
	 * Constructs a new Blacksmith.
	 */
	public Blacksmith() {
		recipe = null;
		visitedPlanets = new String[4]; // used for traces
	}

	/**
	 * Constructs a new Blacksmith.
	 * @param currentPlanet startPlanet
	 * @param planets planets to move on
	 */
	public Blacksmith(Planet currentPlanet, Map<String, Planet> planets) {
		super(currentPlanet, planets);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getId() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "Blacksmith";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGood() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public INPCAction[] getActions() {
		return collection.getActions();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActions(NPCActionCollection collection) {
		this.collection = collection;
	}

	/**
	 * Gets the recipe that the Blacksmith has.
	 * @return the recipe
	 */
	public Recipe getRecipe() {
		return recipe;
	}

	/**
	 * Sets the recipe of the Blacksmith.
	 * @param recipe
	 */
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	/**
	 * Gets the name of the planets, the Blacksmith has visited.
	 * The order of the planets goes from current to recently to last.
	 * @return a string of planet names
	 */
	public String[] getVisitedPlanets() {
		return visitedPlanets;
	}

	/**
	 * Sets the traces of the Blacksmith manually.
	 * @param traces visited planets
	 */
	public void setVisitedPlanets(String[] traces) {
		this.visitedPlanets = traces;
	}

	/**
	 * Pushes the traces one step further, then adds the new trace.
	 * @param name planet name
	 */
	public void addTrace(String name) {
		pushTraces();
		visitedPlanets[0] = name.toLowerCase();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move() {
		Map<String, Planet> map = getPlanets();

		Iterator<Planet> iterator = map.values().iterator();

		while(iterator.hasNext()) {
			if(iterator.next() == getCurrentPlanet()) {
				getCurrentPlanet().getNPCs().remove(this);

				Planet planet;
				if(iterator.hasNext()) {
					planet = iterator.next();
				} else {
					planet = map.values().iterator().next();
				}

				setCurrentPlanet(planet);
				getCurrentPlanet().getNPCs().add(this);

				addTrace(getCurrentPlanet().getName().toLowerCase());

				break;
			}
		}
	}

	/**
	 * Pushes/overwrites the traces by 1 in the positive direction.
	 * 0 -> 1 -> 2 -> 3
	 * Trace 3 will disappear.
	 */
	private void pushTraces() {
		for(int i = visitedPlanets.length - 1; i > 0; i--) {
			visitedPlanets[i] = visitedPlanets[i - 1];
		}
	}

	/**
	 * Gets the visit stage of the Blacksmith.
	 * If the player visits a planet the Blacksmith has just been on,
	 * it will return {@link BlacksmithTraceState#ONE_STEP}.
	 * @param planetName the planet name to search for
	 * @return the visit state of the blacksmith
	 */
	public BlacksmithTraceState getVisitState(String planetName) {
		int traceIndex = -1;

		for(int i = 0; i < visitedPlanets.length; i++) {
			if(visitedPlanets[i] != null) {
				if(visitedPlanets[i].equals(planetName.toLowerCase())) {
					traceIndex = i;
					break;
				}
			}
		}

		switch(traceIndex) {
			case 0: return BlacksmithTraceState.ON_PLANET;
			case 1: return BlacksmithTraceState.ONE_STEP;
			case 2: return BlacksmithTraceState.TWO_STEP;
			case 3: return BlacksmithTraceState.THREE_STEP;
			default: return BlacksmithTraceState.NO_TRACE;
		}
	}

	/**
	 * Generates the recipe list based on the item indexes in the database.
	 * @param model the persistence layer
	 */
	public void generateRecipeRequirements(PersistenceLayer model) {
		final int liquids = 14; // 0 -> 14
		final int canisters = 12; // 14 -> 26
		final int gears = 14; // 26 -> 40
		final int cpus = 16; // 40 -> 56

		ItemStack[] requirements = new ItemStack[4];

		requirements[0] = new ItemStack(model.getItemById((int) (Math.random() * liquids)));
		requirements[1] = new ItemStack(model.getItemById((int) (liquids + Math.random() * canisters)));
		requirements[2] = new ItemStack(model.getItemById((int) (liquids + canisters + Math.random() * gears)));
		requirements[3] = new ItemStack(model.getItemById((int) (liquids + canisters + gears + Math.random() * cpus)));

		recipe = new Recipe(requirements);
	}
}
