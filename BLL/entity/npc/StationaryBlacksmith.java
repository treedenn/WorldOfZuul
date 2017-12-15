package BLL.entity.npc;

import BLL.Game;
import BLL.GameUtility;
import BLL.entity.Entity;
import BLL.entity.npc.actions.NPCAction;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.entity.npc.actions.NPCDialogAction;
import BLL.entity.npc.actions.NPCTerminateAction;
import BLL.entity.player.Player;
import BLL.world.Planet;

/**
 * Contains all the functionality of the Stationary Blacksmith NPC.
 * To seperate the movable Blacksmith - a stationary Blacksmith was created.
 * First time you meet the Blacksmith this is the one you will meet and
 * it will initiate the movable Blacksmith.
 */
public class StationaryBlacksmith extends Entity implements NPC {
	private NPCAction[] actions;

	/**
	 * Constructs a Stationary Blacksmith.
	 */
	public StationaryBlacksmith() {
		super();
		initActions();
	}

	@Override
	public int getId() {
		return 1;
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

	private void initActions() {
		actions = new NPCAction[] {
				new NPCAction("Hello Fellow, I'm the blacksmith, name's Gearhead!" +
						"\nDear adventurer, I believe our meeting is Fate!" +
						"\nI ran out of fuel and is now stranded on this planet ..." +
						"\nPlease spare me some fuel, and I will forever be in your debt!"),
				new NPCDialogAction("Would you like to help Gearhead?") {
					@Override
					public void onEndEvent(NPC npc, Game game) {
						super.onEndEvent(npc, game);

						Player player = (Player) game.getPlayer();

						if(answerYes) {
							player.decreaseFuel(20);

							((Entity) npc).getCurrentPlanet().getNPCs().remove(npc);
							((Entity) npc).setCurrentPlanet(null);

							Blacksmith blacksmith = game.getNpcHandler().getBlacksmith();

							Planet planet = GameUtility.getRandomPlanetNotXehna(player.getPlanets().values().toArray(new Planet[player.getPlanets().size()]));

							blacksmith.setCurrentPlanet(planet);
							blacksmith.addTrace(planet.getName());
							planet.getNPCs().add(blacksmith);

							blacksmith.generateRecipeRequirements(game.getModel());
							game.addCluesToPlanets();
						} else {
							setActionId(3);
						}
					}
				},
				new NPCTerminateAction("... Thank you Rick! I will be leaving!" +
						"\nIf you need to find me again, I will be leaving traces on the planets - I hope this helps!" +
						"\nBy the way rick, should you need more fuel for your ship, i noticed a gas station  located on New Earth."),
				new NPCAction("... Thrr, another time perhaps?")
		};
	}
}
