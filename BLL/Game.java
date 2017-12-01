package BLL;

import BLL.ACQ.*;
import BLL.character.Inventory;
import BLL.character.npc.NPC;
import BLL.character.player.Player;
import BLL.character.player.buff.Buff;
import BLL.item.Item;
import BLL.item.ItemStack;
import BLL.scoring.Score;
import BLL.scoring.ScoreHandler;
import BLL.world.Planet;

import java.util.*;

public class Game implements Domain {
	private static Game INSTANCE;

	private Persistent model;
	private UsableHandler usableHandler;

	private boolean finished;
	private boolean gameWon;
    private boolean trapped;
	private Player player;
	private NPCHandler npcHandler;
	private ScoreHandler scoreHandler;
	private MessageContainer messageContainer;

	private Game() {
		usableHandler = new UsableHandler();

		finished = false;
		gameWon = false;
		player = new Player();
		npcHandler = new NPCHandler();
		scoreHandler = new ScoreHandler();
		messageContainer = new MessageContainer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void injectPersistent(Persistent persistent) {
		this.model = persistent;
		this.model.setUsableHandler(usableHandler);
		this.model.load();
		init();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPlayer getPlayer() {
		return player;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, IPlanet> getPlayerPlanets() {
		return new HashMap<>(player.getPlanets());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessageContainer getMessageContainer() {
		return messageContainer;
	}

	/**
	 * Sets the finished boolean to a new value.
	 * It is used to determine whether the game finish.
	 * Default is false.
	 * @param finished a boolean to override current value.
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * Sets the game won boolean to a new value.
	 * It is used to determine whether the player has won the game or not.
	 * Default is false.
	 * @param gameWon a boolean to override current value.
	 */
	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	/**
	 * An initialization of the business layer.
	 * Is invoked by {@link #injectPersistent(Persistent)} function.
	 */
	private void init() {
		Map<String, Planet> planetMap = model.getPlanets();
		Planet[] planets = planetMap.values().toArray(new Planet[planetMap.size()]);

		Planet centerUniverse = new Planet("Center of the Universe", "This is not exactly the center, since a black hole exists in the center of every Universe.", 0, 0);
		player.setCurrentPlanet(centerUniverse);
		player.setPlanets(planetMap);
		npcHandler.getBlacksmith().setCurrentPlanet(planets[(int) (Math.random() * planets.length)]);
		npcHandler.getBlacksmith().setPlanets(planetMap);
		npcHandler.getUnoX().setQuizes(model.getQuizes());
		//addCluesToPlanets();
        trapped = true;

        player.getCurrentPlanet().getNPCs().add(npcHandler.getProfessorPutricide());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAnswerCorrect(int index) {
		return npcHandler.getUnoX().isAnswerCorrect(index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IQuiz getQuiz() {
		npcHandler.getUnoX().pickRandomQuiz();
		return npcHandler.getUnoX().getCurrentQuiz();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchPlanetState searchPlanet() {
		if(player.getCurrentPlanet().getTempSearched()) {
			messageContainer.setMessage(model.getMessage("planet-search-already"));
			return SearchPlanetState.ALREADY_SEARCHED;
		} else {
			player.getCurrentPlanet().setPermanentSearch(true);
			player.getCurrentPlanet().setTemporarySearch(true);

			if(player.samePlanet(npcHandler.getBlacksmith().getCurrentPlanet())) {
				messageContainer.setMessage(model.getMessage("planet-search-blacksmith"));
				return SearchPlanetState.BLACKSMITH;
			}
		}

		messageContainer.setMessage(model.getMessage("planet-search-nothing"));
		return SearchPlanetState.NOTHING;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateBuffs() {
		List<Buff> buffs = player.getBuffs();

		Buff buff;
		for(int i = 0; i < buffs.size(); i++) {
			buff = buffs.get(i);

			buff.onGameTick(player);

			if(buff.isExpired()) {
				player.removeBuff(i);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startInteract(NPC npc, int actionId) {
		if(npc != null) {
			INPCAction[] actions = npc.getActions();
			actions[actionId].onStartEvent(player, npc, model);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endInteract(NPC npc, int actionId) {
		if(npc != null) {
			INPCAction[] actions = npc.getActions();
			actions[actionId].onEndEvent(player, npc, model);
		}


//
//					if(command.hasArguments()) {
//			view.println("Interact does not need any arguments.");
//		} else {
//			if(!player.getCurrentPlanet().getTempSearched()) {
//				view.println("You have not searched the planet!");
//			} else {
//				if(!blacksmith.samePlanet(player.getCurrentPlanet())) {
//					view.println("The blacksmith is not here.");
//				} else {
//                    int trappedInt = trapped ? 1 : 2;
//                    switch(trappedInt) {
//                        case 1:
//                            view.println(blacksmith.getLockedMsg());
//                            if(blacksmith.hasAccepted(view.getParser().getQuizOfferAnswer())) {
//                                player.decreaseFuel(10);
//                                view.println("You chose to help Gearhead! Fuel has decreased by 10!");
//                                trapped = false;
//                            }
//                            view.getParser().resetReader();
//                            break;
//                        case 2:
//                            view.println(blacksmith.getBlacksmithMsg());
//                            Recipe recipe = blacksmith.getRecipe();
//                            Item[] items = recipe.getRequirements();
//	                        ItemStack[] content = player.getInventory().getContent();
//                            boolean[] containItems = recipe.haveItems(content);
//
//                            for(int i = 0; i < items.length; i++) {
//                                view.println((containItems[i] ? "[\u2713] " : "[\u2715] ") + "XXXXXXXX ");
//                            }
//
//                            if(allTrue(containItems)) {
//                                // TODO: remove items from the players backpack
//	                            // TODO: change portal gun to be repaired
//                                view.println("");
//                                view.println("Portalgun has been repaired!");
//                            }
//                            break;
//                    }*/
//                    view.println(lockedBlacksmith.getBlacksmithMsg());
//					Recipe recipe = lockedBlacksmith.getRecipe();
//					Item[] items = recipe.getRequirements();
//					boolean[] containItems = recipe.haveItems(player.getInventory().getContent());
//
//					for(int i = 0; i < items.length; i++) {
//						view.println((containItems[i] ? "[\u2713] " : "[\u2715] ") + "XXXXXXXX " + items[i].getComponentType().name());
//					}
//
//					if(allTrue(containItems)) {
//						// TODO: remove items from the players backpack
//						view.println("");
//						view.println("Portalgun has been repaired!");
//						player.getInventory().getItemPortalGun().repair();
////					}
//				}
//			}
//		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean useItem(IItemStack iis) {
		if(iis.getIItem() instanceof Item) {
			Item i = (Item) iis.getIItem();

			if(i.hasUsable()) {
				i.use(player, this);
				return true;
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean pickupItem(IItemStack iis) {
		if(player.getCurrentPlanet().getPermSearched()) {
			if(iis.getIItem().isPickupable()) {
				Inventory bp = player.getInventory();
				ItemStack is = (ItemStack) iis;

				if(bp.add(is)) {
					player.getCurrentPlanet().removeItemStack(is);
					messageContainer.setMessage(model.getMessage("item-pickup-successful"));
					return true;
				}
			}
		}

		messageContainer.setMessage(model.getMessage("item-pickup-denied"));
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean dropItem(IItemStack iis) {
		if(player.getCurrentPlanet().getPermSearched()) {
			if(iis.getIItem().isDropable()) {
				Inventory bp = player.getInventory();
				ItemStack is = (ItemStack) iis;

				if(bp.remove(is)) {
					player.getCurrentPlanet().addItemStack(is);
					messageContainer.setMessage(model.getMessage("item-drop-successful"));
					return true;
				}
			}
		}

		messageContainer.setMessage(model.getMessage("item-drop-denied"));
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MovePlayerState movePlayerToPlanet(String planetName) {
		planetName = planetName.toLowerCase();

		Map<String, Planet> planets = player.getPlanets();

		if(!planets.containsKey(planetName)) {
			return MovePlayerState.NOT_VALID;
		} else if(player.samePlanet(planets.get(planetName))) {
			return MovePlayerState.SAME_PLANET;
		} else {
			player.go(planetName);
			player.decreaseFuel(10);
			player.getCurrentPlanet().setTemporarySearch(false);

			return MovePlayerState.SUCCESS;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void decreaseFuelOnMove(int ticksPerSecond) {
		player.decreaseFuel(0.3 / ticksPerSecond);
	}

	/**
	 * Add the clues to the planets at random.
	 */
	private void addCluesToPlanets(){
		Item[] items = npcHandler.getBlacksmith().getRecipe().getRequirements();
		Item[] clues = new Item[8];

		for (int i = 0; i < clues.length; i++) {
			clues[i] = model.getItemById(56);
		}

		Item item;
		Item clue;
		String s;
		String newDescription;

		for (int i = 0; i < items.length; i++) {
			item = items[i];
			for (int j = i * 2; j < i * 2 + 2; j++) {
				clue = clues[j];
//				clue.setColor(item.getColor());
//				clue.setState(item.getState());
//				clue.setComponentType(item.getComponentType());
				s = j % 2 == 0 ? "{{color}}" : "{{state}}";
				newDescription = clue.getDescription().replace("{{clue}}", s);
				clue.setDescription(newDescription);
			}
		}

		List<Planet> planetsList = new ArrayList<>(player.getPlanets().values());
		Collections.shuffle(planetsList);
		for (int i = 0; i < clues.length; i++) {
			planetsList.get(i).addItemStack(new ItemStack(clues[i]));
		}
	}

	// TODO: Perhaps change boolean to int to get the direct location

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean hasBeatenHighscore() {
		int points = scoreHandler.calculatePoints(player.getTotalFuelConsumption());
		List<Score> scores = model.getHighscore();

		for(Score score : scores) {
			if(points > score.getScore()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPlayerToHighscore(String playerName) {
		int points = scoreHandler.calculatePoints(player.getTotalFuelConsumption());
		List<Score> scores = model.getHighscore();
		scores.add(new Score(playerName, points));
		Collections.sort(scores);
		scores.remove(scores.size() - 1);
		model.saveHighscore();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IScore> getHighscore() {
		return new ArrayList<>(model.getHighscore());
	}

	/* function to print a welcome message */
	private String[] welcomeMessage() {
		return new String[] {
			"",
			"Welcome to the ridicoulous Rick & Morty spinoff!",
			"Rick & Morty spinoff is a new and incredibly addictive adventure game!",
			"[Type '" + /*CommandWord.HELP + */ "' if you need help]",
            "[Type '" + /*CommandWord.INFO + */ "' if you need more information]",
			"",
		};
	}
        
    private String[] descriptionMessage() {
        return new String[] {
                            "GAME DESCRIPTION",
			"--------------",
			"You are Rick, the brilliant scientist. But you have mistakenly destroyed Earth in your current dimension.",
			"Normally, you would use your Portal Gun to teleport yourself to a new dimension... But it's broken!",
			"Your mission is now to fix your Portal Gun and travel safely to a new dimension. Good luck!",
			"--------------",
                            "",
        };
    }
        
    private String[] hintMessage() {
        return new String[] {
            "OBJECTIVE:",
            "--------------",
            "> You need to find the blacksmith named Gearhead!",
            "> Gearhead will show you his recipe for the Portal Gun!",
            "> It is now your job to find all the items needed!",
            "> Return to Gearhead and repair your Portal Gun!",
            "--------------",
        };
    }

	private String[] argumentMessage(String usage) {
		return new String[]{
				"You have entered too many arguments!",
				"Usage: " + usage
		};
	}

	private boolean allTrue(boolean[] booleans) {
		for(boolean b : booleans) {
			if(!b) { return false; }
		}

		return true;
	}

	private void gameIsFinished() {
		StringBuilder sb = new StringBuilder();

		if(gameWon){
			double millisecondsElapsed = scoreHandler.calculateTimeElapsed();
			int seconds = (int) ((millisecondsElapsed / 1000) % 60);
			int minutes = (int) ((millisecondsElapsed / 1000) / 60);

			int points = scoreHandler.calculatePoints(player.getTotalFuelConsumption());
			int stars = scoreHandler.getStars(points);

			char[] earnedStars = new char[stars];
			Arrays.fill(earnedStars, '\u26e4');

			sb.append("---------------------- GAME WON! -----------------------\n");
			sb.append("Here are some stats for you to brag about...\n");
			sb.append("You played for ").append(minutes).append(":").append(seconds).append(" minutes\n");
			sb.append("Your total fuel consumption was ").append(player.getTotalFuelConsumption()).append(" liters\n");
			sb.append("Out of 5 stars ").append("you earned: ").append(earnedStars).append("\n");
			sb.append("--------------------------------------------------------");

		} else{
			sb.append("---------------------- GAME OVER! ----------------------\n");
			if(player.isFuelEmpty()){
				sb.append("You ran out of fuel!\n");
			}
			/*sb.append("If you want to play again - type '" + CommandWord.RESTART + "'\n");
			sb.append("If you want to quit - type '" + CommandWord.QUIT + "'\n");*/
			sb.append("--------------------------------------------------------");
		}
	}

	/**
	 * Gets the instance of the Game.
	 * *Singleton*
	 * @return the instance of game
	 */
	public static Game getInstance() {
		if(INSTANCE == null) INSTANCE = new Game();
		return INSTANCE;
	}
}
