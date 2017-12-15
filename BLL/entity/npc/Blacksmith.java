package BLL.entity.npc;

import BLL.ACQ.BlacksmithTraceState;
import BLL.ACQ.PersistenceLayer;
import BLL.Game;
import BLL.entity.Inventory;
import BLL.entity.MovableEntity;
import BLL.entity.npc.actions.NPCAction;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.entity.npc.actions.NPCDialogAction;
import BLL.entity.npc.actions.NPCTerminateAction;
import BLL.entity.player.Player;
import BLL.entity.player.Recipe;
import BLL.item.ItemComponent;
import BLL.item.ItemPortalGun;
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

	private NPCAction[] actions;

	/**
	 * Constructs a new Blacksmith.
	 */
	public Blacksmith() {
		super();
		recipe = null;
		visitedPlanets = new String[4]; // used for traces
		initActions();
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
	public NPCAction[] getActions() {
		return actions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActions(NPCActionCollection collection) {
		this.actions = collection.getActions();
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

	private void initActions() {
		actions = new NPCAction[] {
				new NPCDialogAction("MNy dear Rick!" +
						"\nIt's already time to return the favor?" +
						"\nI've heard that you somehow broke your portal gun?"),
				new NPCDialogAction("Would you like to see my recipe for the Portal Gun?") {
					@Override
					public void onEndEvent(NPC npc, Game game) {
						super.onEndEvent(npc, game);

						if(!answerYes) {
							setActionId(4);
						}
					}
				},
				new NPCAction("My tinker tools show what you already have, not what you need..." +
						"I have heard rumours that clues are around the universe!" +
						"\nThe recipe is:") {
					@Override
					public void onStartEvent(NPC npc, Game game) {
						super.onStartEvent(npc, game);

						if(npc instanceof Blacksmith) {
							Blacksmith bs = (Blacksmith) npc;

							Player player = (Player) game.getPlayer();

							Inventory inventory = player.getInventory();
							Recipe recipe = bs.getRecipe();

							boolean[] haveItems = recipe.haveItems(inventory.getContent());

							ItemStack[] items = recipe.getRequirements();

							if(allTrue(haveItems)) {
								for(ItemStack itemStack : inventory.getContent()) {
									if(itemStack.getItem() instanceof ItemPortalGun) {
										ItemPortalGun pg = (ItemPortalGun) itemStack.getItem();
										pg.repair();
										break;
									}
								}

								for(ItemStack item : items) {
									inventory.remove(item);
								}

								message = "Oh, since you had the materials to repair your Portal Gun, I did it.";
							} else {
								StringBuilder sb = new StringBuilder();

								String text;
								ItemComponent itemComponent;
								for(ItemStack item : items) {
									itemComponent = (ItemComponent) item.getItem();

									if(player.getInventory().contains(item)) {
										text = String.format("[\u2713] %s [%s]", itemComponent.getName(), itemComponent.getComponentType().name());
									} else {
										text = String.format("[\u2715] %s [%s]", "XXXXXXXXXX", itemComponent.getComponentType().name());
									}

									sb.append(System.lineSeparator());
									sb.append(text);
								}

								message += sb.toString();
							}
						}
					}
				},
				new NPCTerminateAction("... I hope I will see you again!")
		};
	}

	/**
	 * Checks if all the booleans inside an array are true.
	 * @param booleans any boolean array
	 * @return true, if all booleans are true
	 */
	private boolean allTrue(boolean[] booleans) {
		for(boolean b : booleans) {
			if(!b) { return false; }
		}

		return true;
	}
}
