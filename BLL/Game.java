package BLL;

import BLL.ACQ.*;
import BLL.entity.Inventory;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;
import BLL.entity.player.buff.Buff;
import BLL.item.Item;
import BLL.item.ItemStack;
import BLL.scoring.Score;
import BLL.scoring.ScoreHandler;
import BLL.world.Lockable;
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

		Planet centerUniverse = new Planet("Center of the Universe", "This is not exactly the center, since a black hole exists in the center of every Universe.",0,0);
		player.setCurrentPlanet(centerUniverse);
		player.setPlanets(planetMap);
		npcHandler.getBlacksmith().setCurrentPlanet(planets[(int) (Math.random() * planets.length)]);
		npcHandler.getBlacksmith().setPlanets(planetMap);
		npcHandler.getUnoX().setQuizes(model.getQuizes());
		//addCluesToPlanets();
        trapped = true;

        // TODO: remove temp statement when testing is done.
        player.getCurrentPlanet().getNPCs().add(npcHandler.getProfessorPutricide());

        String m = replacePlaceHolders("HALLO WORLD MY NAME IS {NAME} and I'M FROM {COUNTRY}!", "{NAME}", "Dennis", "{COUNTRY}", "Denmark");

		System.out.println(m);

        //useItem(new ItemStack(model.getItemById(58)));
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
	public boolean searchPlanet() {
		boolean isSearching = false;
		String message;

		Planet currentPlanet = player.getCurrentPlanet();

		if(currentPlanet.getTempSearched()) {
			message = model.getMessage("planet-search-already");
		} else {
			player.getCurrentPlanet().setPermanentSearch(true);
			player.getCurrentPlanet().setTemporarySearch(true);
			isSearching = true;

			String bsKey = npcHandler.getBlacksmith().getVisitState(currentPlanet.getName()).getKey();

			message = model.getMessage(bsKey);
		}

		messageContainer.setMessage(message);
		return isSearching;
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
//				if(!blacksmith.isOnPlanet(player.getCurrentPlanet())) {
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
		boolean isUsed = false;
		String message = null;

		if(iis.getIItem() instanceof Item) {
			Item i = (Item) iis.getIItem();

			if(i.hasUsable()) {
				i.use(player, this);

				isUsed = true;
				message = replacePlaceHolders(model.getMessage("item-use-successful"), "{ITEM}", i.getName());
			} else {
				message = replacePlaceHolders(model.getMessage("item-use-not-usable"), "{ITEM}", i.getName());
			}
		}

		messageContainer.setMessage(message);
		return isUsed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean pickupItem(IItemStack iis) {
		boolean isPickedUp = false;
		String message = null;

		if(player.getCurrentPlanet().getPermSearched()) {
			if(iis instanceof ItemStack) {
				ItemStack is = (ItemStack) iis;
				Item item = is.getItem();

				if(item.isPickupable()) {
					Inventory bp = player.getInventory();

					if(bp.add(is)) {
						player.getCurrentPlanet().removeItemStack(is);

						isPickedUp = true;
						message = replacePlaceHolders(model.getMessage("item-pickup-successful"), "{ITEM}", item.getName());
					} else {
						message = replacePlaceHolders(model.getMessage("item-pickup-unsuccessful"), "{ITEM}", item.getName());
					}
				} else {
					message = replacePlaceHolders(model.getMessage("item-pickup-not-pickupable"), "{ITEM}", item.getName());
				}
			}
		} else {
			message = model.getMessage("planet-search-require");
		}

		messageContainer.setMessage(message);
		return isPickedUp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean dropItem(IItemStack iis) {
		boolean isDropped = false;
		String message = null;

		if(player.getCurrentPlanet().getPermSearched()) {
			if(iis instanceof ItemStack) {
				ItemStack is = (ItemStack) iis;
				Item item = is.getItem();

				if(item.isDropable()) {
					Inventory bp = player.getInventory();

					if(bp.remove(is)) {
						player.getCurrentPlanet().addItemStack(is);

						isDropped = true;
						message = replacePlaceHolders(model.getMessage("item-drop-successful"), "{ITEM}", item.getName(), "{QUANTITY}", is.getQuantity() + "");
					} else {
						message = replacePlaceHolders(model.getMessage("item-drop-unsuccessful"), "{ITEM}", item.getName(), "{QUANTITY}", is.getQuantity() + "");
					}
				} else {
					message = replacePlaceHolders(model.getMessage("item-drop-not-dropable"), "{ITEM}", item.getName(), "{QUANTITY}", is.getQuantity() + "");
				}
			}
		} else {
			message = model.getMessage("planet-search-require");
		}

		messageContainer.setMessage(message);
		return isDropped;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean movePlayerToPlanet(String planetName) {
		boolean playerIsMoving = false;
		String message;

		planetName = planetName.toLowerCase();

		Map<String, Planet> planets = player.getPlanets();

		if(!planets.containsKey(planetName)) {
			message = model.getMessage("player-move-not-valid");
		} else {
			Planet planet = planets.get(planetName);

			if(!canPlayerMove(planet)) {
				message = replacePlaceHolders(model.getMessage("player-move-unsuccessful"), "{PLANET}", planet.getName());
			} else {
				player.setCurrentPlanet(planet);
				player.decreaseFuel(10);
				player.getCurrentPlanet().setTemporarySearch(false);

				playerIsMoving = true;
				message = replacePlaceHolders(model.getMessage("player-move-successful"), "{PLANET}", planet.getName());
			}
		}

		messageContainer.setMessage(message);
		return playerIsMoving;
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

		if(scores.add(new Score(playerName, points))) {
			Collections.sort(scores);
			scores.remove(scores.size() - 1);
			model.saveHighscore();
		}
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
	 * Sets a message from a key to the {@link MessageContainer}.
	 * Invoked by components within the business layer.
	 * @param key a signature/key from the localization database.
	 */
	public void setMessageToContainer(String key) {
		messageContainer.setMessage(model.getMessage(key));
	}

	/**
	 * Looks inside a string for placeholders and converts them.
	 * EXAMPLE:
	 * replacePlaceHolder("Group {X} is {STATE}", "{X}", "24", "{STATE}", "AWESOME!");
	 * -> Group 24 is AWESOME!
	 * @param text with the placeholders
	 * @param strings every first string is the placeholder and the second is the string to replace at the given placeholder
	 * @return a new string, where the placeholders has been replaced with their corresponding value.
	 */
	public String replacePlaceHolders(String text, String ... strings) {
		if(strings.length % 2 == 1) { return null; }

		StringBuilder sb = new StringBuilder(text);

		int startIndex;
		char[] signature, value;
		for(int i = 0; i < strings.length / 2; i++) {
			signature = strings[i * 2].toCharArray();
			value = strings[i * 2 + 1].toCharArray();

			startIndex = sb.indexOf(String.valueOf(signature));

			sb.delete(startIndex, startIndex + signature.length);
			sb.insert(startIndex, value);
		}

		return sb.toString();
	}

	/**
	 * Checks whether the player is allowed to enter the planet.
	 * If the planet is not lockable, then it has no restrictions.
	 * @param planet to see if allowed
	 * @return true, if player is allowed
	 */
	private boolean canPlayerMove(Planet planet) {
		boolean lockable = planet instanceof Lockable;
		return !lockable || (lockable && ((BLL.world.Lockable) planet).isUnlocked());
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