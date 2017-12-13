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

public class Blacksmith extends MovableEntity implements NPC {
	private Recipe recipe;
	private String[] visitedPlanets;

	private NPCActionCollection collection;

	public Blacksmith() {
		recipe = null;
		visitedPlanets = new String[4]; // used for traces
	}

	public Blacksmith(Planet currentPlanet, Map<String, Planet> planets) {
		super(currentPlanet, planets);
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public String getName() {
		return "Blacksmith";
	}

	@Override
	public boolean isGood() {
		return true;
	}

	@Override
	public INPCAction[] getActions() {
		return collection.getActions();
	}

	@Override
	public void setActions(NPCActionCollection collection) {
		this.collection = collection;
	}

	public String[] getBlacksmithMsg() {
        return new String[] {
            "[Blacksmith]: My dear Rick!",
            "It's already time to return the favor?",
            "I've heard that you somehow broke your portal gun?",
            "Here's a list of items needed for you to repair it!",
            "--------------",
        };
    }

    public String[] getLockedMsg() {
        return new String[] {
            "[BlackSmith]: Hello Fellow, I'm the blacksmith, name's Gearhead!",
            "Dear adventurer, I believe our meeting is Fate!",
            "I ran out of fuel and is now stranded on this planet ...",
            "Please spare me some fuel, I will be in your debt and do anything in return!",
            "[Would you like to help Gearhead, enter Y/N]",
        };
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

	public String[] getVisitedPlanets() {
		return visitedPlanets;
	}

	public void setVisitedPlanets(String[] traces) {
		this.visitedPlanets = traces;
	}

	public void addTrace(String name) {
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

				pushTraces();
				addTrace(getCurrentPlanet().getName().toLowerCase().replace(" ", ""));

				break;
			}
		}
	}

	/**
	 * Pushes/overwrites the traces by 1 in the positive direction.
	 * 0 -> 1 -> 2 -> 3
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
				if(visitedPlanets[i].equals(planetName.toLowerCase().replace(" ", ""))) {
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
	 * @param model where the item database is
	 */
	public void generateRecipeRequirements(PersistenceLayer model) {
		// TODO: Call function when spoken to Blacksmith for the firs time.

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

//		requirements[0] = Model.getItemById(0);
//		requirements[1] = Model.getItemById(liquids);
//		requirements[2] = Model.getItemById(liquids + canisters);
//		requirements[3] = Model.getItemById(liquids + canisters + gears);

	}
}
