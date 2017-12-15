package BLL;

import BLL.ACQ.*;
import BLL.ACQ.data.IPlanetData;
import BLL.ACQ.data.IPlayerData;
import BLL.ACQ.data.IWorldData;
import BLL.entity.Entity;
import BLL.entity.Inventory;
import BLL.entity.Movable;
import BLL.entity.MovableEntity;
import BLL.entity.npc.NPC;
import BLL.entity.npc.SpacePirate;
import BLL.entity.npc.actions.NPCJumpAction;
import BLL.entity.player.Player;
import BLL.entity.player.Recipe;
import BLL.entity.player.buff.Buff;
import BLL.item.*;
import BLL.scoring.Score;
import BLL.scoring.ScoreHandler;
import BLL.world.Lockable;
import BLL.world.Planet;

import java.io.IOException;
import java.util.*;

/**
 * The facade of the Business Layer (BLL).
 * **SINGLETON**
 */
public class Game implements Domain {
	private static Game INSTANCE;

	private PersistenceLayer model;
	private UsableHandler usableHandler;
	private ScoreHandler scoreHandler;
	private NPCHandler npcHandler;

	private int worldSeed;
	private boolean finished;
	private boolean gameWon;
	private Player player;
	private MessageContainer messageContainer;

	/**
	 * Constructs a new Game.
	 */
	private Game() {
		usableHandler = new UsableHandler();
		npcHandler = new NPCHandler();
		scoreHandler = new ScoreHandler();

		finished = false;
		gameWon = false;
		player = new Player();
		messageContainer = new MessageContainer();
	}

	/**
	 * Gets the Persistent Layer.
	 * @return persistent layer
	 */
	public PersistenceLayer getModel() {
		return model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void injectPersistenceLayer(PersistenceLayer persistenceLayer) {
		this.model = persistenceLayer;
		this.model.setUsableHandler(usableHandler);
		this.model.load();
	}

	/**
	 * Gets the seed of the game.
	 * @return the game seed
	 */
	public int getWorldSeed() {
		return worldSeed;
	}

	/**
	 * Returns the NPC handler.
	 * @return the npc handler
	 */
	public NPCHandler getNpcHandler() {
		return npcHandler;
	}

	/**
	 * Gets the ScoreHandler.
	 * Only invoked by data layer to get the current state of score.
	 * @return the score handler
	 */
	public ScoreHandler getScoreHandler() {
		return scoreHandler;
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
	public List<IScore> getHighscore() {
		return new ArrayList<>(model.getHighscore());
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
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasWonTheGame() {
		return this.gameWon;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGameFinished() {
		return this.finished;
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
	 * Sets a message to the {@link MessageContainer}.
	 * Invoked by components within the business layer.
	 * @param message a message..
	 */
	public void setMessageToContainer(String message) {messageContainer.setMessage(message);}

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
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		// generates a world seed
		this.worldSeed = (int) (Math.random() * Integer.MAX_VALUE);

		// Obtains the planet data from the persistent layer
		Map<String, Planet> planetMap = model.getPlanets();
		Planet[] planets = planetMap.values().toArray(new Planet[planetMap.size()]);

		// Sets the possible planets to the player and adds the portal gun
		player.setPlanets(planetMap);
		player.getInventory().add(new ItemStack(model.getItemById(57), 1));

		npcHandler.getBlacksmith().setPlanets(planetMap);

		// Sets the quizes to UnoX
		npcHandler.getUnoX().setQuizzes(model.getQuizes());

		// Adds the Stationary Blacksmith to Xehna, the locked planet.
		npcHandler.getStationaryBlacksmith().setCurrentPlanet(planetMap.get("xehna"));
		npcHandler.getStationaryBlacksmith().getCurrentPlanet().getNPCs().add(npcHandler.getStationaryBlacksmith());

		// Adds the pirate to a random planet
		npcHandler.getPirate().setPlanets(planetMap);
		npcHandler.getPirate().setCurrentPlanet(GameUtility.getRandomPlanetNotXehna(planets));
		npcHandler.getPirate().getCurrentPlanet().getNPCs().add(npcHandler.getPirate());

		// Adds the professor Putricide to a random planet
		npcHandler.getProfessorPutricide().setCurrentPlanet(GameUtility.getRandomPlanetNotXehna(planets));
		npcHandler.getProfessorPutricide().getCurrentPlanet().getNPCs().add(npcHandler.getProfessorPutricide());

		// Adds the UnoX to a New Earth
		npcHandler.getUnoX().setCurrentPlanet(planetMap.get("newearth"));
		npcHandler.getUnoX().getCurrentPlanet().getNPCs().add(npcHandler.getUnoX());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean searchPlanet() {
		boolean isSearching = false;
		String message = null;

		Planet currentPlanet = player.getCurrentPlanet();

		if(currentPlanet != null) {
			if(currentPlanet.getTempSearched()) {
				message = model.getMessage("planet-search-already");
			} else {
				player.getCurrentPlanet().setPermanentSearch(true);
				player.getCurrentPlanet().setTemporarySearch(true);
				isSearching = true;

				String bsKey = npcHandler.getBlacksmith().getVisitState(currentPlanet.getName()).getKey();

				if(npcHandler.getBlacksmith().getCurrentPlanet() != null) {
					message = model.getMessage(bsKey);
				}
			}
		} else {
			message = model.getMessage("player-deep-space");
		}

		messageContainer.setMessage(message);
		return isSearching;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateBuffs() {
		for(Buff buff : player.getBuffs()) {
			buff.onGameTick(player);

			if(buff.isExpired()) {
				player.removeBuff(buff);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NPC interaction() {
		SpacePirate pirate = npcHandler.getPirate();

		if(pirate.canAttack()) {
			if(Math.hypot(pirate.getCurrentPlanet().getX() - player.getCoordX(), pirate.getCurrentPlanet().getY() - player.getCoordY()) < 500) {
				pirate.attack();
				return pirate;
			}
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startInteract(NPC npc, int actionId) {
		if(npc != null) {
			INPCAction[] actions = npc.getActions();

			if(actions[actionId] instanceof NPCJumpAction) {
				((NPCJumpAction) actions[actionId]).resetActionId();
			}

			actions[actionId].onStartEvent(npc, this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endInteract(NPC npc, int actionId) {
		if(npc != null) {
			INPCAction[] actions = npc.getActions();
			actions[actionId].onEndEvent(npc, this);
		}
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
				if(i.use(player, this)) {
					player.getInventory().remove((ItemStack) iis);
					message = GameUtility.replacePlaceHolders(model.getMessage("item-use-successful"), "{ITEM}", i.getName());
					isUsed = true;
				} else {
					message = GameUtility.replacePlaceHolders(model.getMessage("item-use-unsuccessful"), "{ITEM}", i.getName());
				}
			} else {
				message = GameUtility.replacePlaceHolders(model.getMessage("item-use-not-usable"), "{ITEM}", i.getName());
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

		if(player.getCurrentPlanet() != null) {
			if(player.getCurrentPlanet().getPermSearched()) {
				if(iis instanceof ItemStack) {
					ItemStack is = (ItemStack) iis;
					Item item = is.getItem();

					if(item.isPickupable()) {
						Inventory playerInv = player.getInventory();
						Inventory planetInv = (Inventory) player.getCurrentPlanet().getInventory();

						if(planetInv.contains(is) && playerInv.add(is)) {
							planetInv.remove(is);

							isPickedUp = true;
							message = GameUtility.replacePlaceHolders(model.getMessage("item-pickup-successful"), "{ITEM}", item.getName());
						} else {
							message = GameUtility.replacePlaceHolders(model.getMessage("item-pickup-unsuccessful"), "{ITEM}", item.getName());
						}
					} else {
						message = GameUtility.replacePlaceHolders(model.getMessage("item-pickup-not-pickupable"), "{ITEM}", item.getName());
					}
				}
			} else {
				message = model.getMessage("planet-search-require");
			}
		} else {
			message = model.getMessage("player-deep-space");
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

		if(player.getCurrentPlanet() != null) {
			if(player.getCurrentPlanet().getPermSearched()) {
				if(iis instanceof ItemStack) {
					ItemStack is = (ItemStack) iis;
					Item item = is.getItem();

					if(item.isDropable()) {
						Inventory playerInv = player.getInventory();
						Inventory planetInv = (Inventory) player.getCurrentPlanet().getInventory();

						if(playerInv.contains(is) && playerInv.remove(is)) {
							planetInv.add(is);

							isDropped = true;
							message = GameUtility.replacePlaceHolders(model.getMessage("item-drop-successful"), "{ITEM}", item.getName(), "{QUANTITY}", is.getQuantity() + "");
						} else {
							message = GameUtility.replacePlaceHolders(model.getMessage("item-drop-unsuccessful"), "{ITEM}", item.getName(), "{QUANTITY}", is.getQuantity() + "");
						}
					} else {
						message = GameUtility.replacePlaceHolders(model.getMessage("item-drop-not-dropable"), "{ITEM}", item.getName(), "{QUANTITY}", is.getQuantity() + "");
					}
				}
			} else {
				message = model.getMessage("player-deep-space");
			}
		} else {
			message = model.getMessage("player-deep-space");
		}

		messageContainer.setMessage(message);
		return isDropped;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean planetEnter(String planetName) {
		boolean playerIsMoving = false;
		String message;

		planetName = planetName.toLowerCase();

		Map<String, Planet> planets = player.getPlanets();

		if(!planets.containsKey(planetName)) {
			message = model.getMessage("player-move-not-valid");
		} else {
			Planet planet = planets.get(planetName);

			if(!canPlayerMove(planet)) {
				message = GameUtility.replacePlaceHolders(model.getMessage("player-move-unsuccessful"), "{PLANET}", planet.getName());
			} else {
				player.setCurrentPlanet(planet);
				player.decreaseFuel(10);
				player.getCurrentPlanet().setTemporarySearch(false);

				List<Movable> movedNPC = new ArrayList<>();

				for(Planet value : planets.values()) {
					for(NPC npc : new ArrayList<>(value.getNPCs())) {
						if(npc instanceof Movable) {
							Movable movable = (Movable) npc;

							if(movable.canMove()) {
								movable.move();
								movable.setMove(false);
								movedNPC.add(movable);
							}
						}
					}
				}

				for(Movable movable : movedNPC) {
					movable.setMove(true);
				}

				playerIsMoving = true;
				message = GameUtility.replacePlaceHolders(model.getMessage("player-move-successful"), "{PLANET}", planet.getName());
			}
		}

		messageContainer.setMessage(message);
		return playerIsMoving;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean planetExit() {
		player.setCurrentPlanet(null);

		if(player.isFuelEmpty()) {
			setFinished(true);
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void decreaseFuelOnMove(int ticksPerSecond) {
		player.decreaseFuel(0.3 / ticksPerSecond);

		if(player.isFuelEmpty()) {
			finished = true;
		}
	}

	/**
	 * Add the clues to the planets through the world seed.
	 */
	public void addCluesToPlanets(){
		ItemStack[] items = npcHandler.getBlacksmith().getRecipe().getRequirements();
		ItemClue[] clues = new ItemClue[8];

		for (int i = 0; i < clues.length; i++) {
			clues[i] = (ItemClue) model.getItemById(59);
		}

		ItemComponent component;
		String s;
		String newDescription;

		for (int i = 0; i < items.length; i++) {
			if(items[i].getItem() instanceof ItemComponent) {
				component = (ItemComponent) items[i].getItem();

				for (int j = i * 2; j < i * 2 + 2; j++) {
					clues[j].setColor(component.getColor());
					clues[j].setState(component.getState());
					clues[j].setComponentType(component.getComponentType());
					s = j % 2 == 0 ? "{{color}}" : "{{state}}";
					newDescription = GameUtility.replacePlaceHolders(clues[j].getDescription(), "{{clues}}", s);
					clues[j].setDescription(newDescription);
				}
			}
		}

		List<Planet> planetsList = new ArrayList<>(player.getPlanets().values());
		Set<Integer> integers = new HashSet<>();

		Random rand = new Random(worldSeed);
		do {
			int num = rand.nextInt(12);
			integers.add(num);
		} while(integers.size() != clues.length);

		int i = 0;
		for(Integer integer : integers) {
			((Inventory) planetsList.get(integer).getInventory()).add(new ItemStack(clues[i]));
			i++;
		}
	}

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
	public boolean save() {
		IWorldData worldData = model.getWorldData();
		IPlayerData playerData = model.getPlayerData();
		IPlanetData planetData = model.getPlanetData();

		// WORLD

		worldData.setWorldSeed(worldSeed);
		worldData.setTimeElapsed((int) scoreHandler.calculateTimeElapsed());

		for(ItemStack is : player.getInventory().getContent()) {
			if(is.getItem() instanceof ItemPortalGun) {
				ItemPortalGun pg = (ItemPortalGun) is.getItem();
				worldData.setPortalGunBroken(pg.isBroken());
				break;
			}
		}

		if(npcHandler.getBlacksmith().getRecipe() != null) {
			worldData.setRequirements(npcHandler.getBlacksmith().getRecipe().getRequirements());
			worldData.setBlacksmthTraces(npcHandler.getBlacksmith().getVisitedPlanets());
		}

		// PLAYER

		if(player.getCurrentPlanet() != null) {
			playerData.setCurrentPlanet(player.getCurrentPlanet().getName().replace(" ", ""));
		} else {
			playerData.setCurrentPlanet(null);
		}

		playerData.setX(player.getCoordX());
		playerData.setY(player.getCoordY());
		playerData.setBuffs(player.getBuffs());
		playerData.setInventory(player.getInventory().getContent());
		playerData.setFuel(player.getFuel());
		playerData.setFuelConsumption(player.getTotalFuelConsumption());

		// PLANET

		List<Integer> NPCIds;
		int i = 0;
		for(Map.Entry<String, Planet> entry : player.getPlanets().entrySet()) {
			Planet p = entry.getValue();

			NPCIds = new ArrayList<>();

			for(NPC npc : p.getNPCs()) {
				NPCIds.add(npc.getId());
			}

			ItemStack[] content = GameUtility.removeCluesFromContent(((Inventory) p.getInventory()).getContent());

			if(planetData.size() == player.getPlanets().size()) {
				planetData.setName(i, entry.getKey());
				planetData.setX(i, p.getX());
				planetData.setY(i, p.getY());
				planetData.setNPCs(i, NPCIds);
				planetData.setInventory(i, content);
				i++;
			} else {
				planetData.addData(entry.getKey(), p.getX(), p.getY(), NPCIds, content);
			}
		}

		try {
			model.saveGame();
		} catch(IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean load() {

		try {
			model.loadGame();
			player.setPlanets(model.getPlanets());
			npcHandler.getUnoX().setQuizzes(model.getQuizes());

			IWorldData worldData = model.getWorldData();
			IPlayerData playerData = model.getPlayerData();
			IPlanetData planetData = model.getPlanetData();

			// PLAYER

			player.getInventory().clear();
			player.loadPlayer(playerData);

			// PLANETS

			Map<String, Planet> planetMap = new LinkedHashMap<>();

			Planet planet;
			Inventory inventory;
			NPC npc;

			for(int i = 0; i < planetData.size(); i++) {
				planet = player.getPlanets().get(planetData.getName(i));
				planet.setCoordinates(planetData.getX(i), planetData.getY(i));
				inventory = ((Inventory) planet.getInventory());
				inventory.clear();

				for(int j = 0; j < planetData.getInventory(i).length; j++) {
					inventory.add(planetData.getInventory(i)[j]);
				}

				planet.getNPCs().clear();

				for(Integer id : planetData.getNPCIds(i)) {
					npc = npcHandler.getNPCById(id);
					planet.getNPCs().add(npc);

					if(npc instanceof MovableEntity) {
						((MovableEntity) npc).setPlanets(planetMap);
						((MovableEntity) npc).setCurrentPlanet(planet);
					} else if(npc instanceof Entity) {
						((Entity) npc).setCurrentPlanet(planet);
					}
				}

				planetMap.put(planetData.getName(i), planet);
			}

			player.setPlanets(planetMap);
			npcHandler.getBlacksmith().setPlanets(planetMap);

			// WORLD

			this.worldSeed = worldData.getWorldSeed();
			scoreHandler.setStartTimeOffset(worldData.getTimeElapsed());

			for(ItemStack is : player.getInventory().getContent()) {
				if(is.getItem() instanceof ItemPortalGun) {
					ItemPortalGun pg = (ItemPortalGun) is.getItem();
					if(!worldData.isPortalGunBroken()) {
						pg.repair();
					}
					break;
				}
			}

			if(worldData.getRequirements() != null) {
				npcHandler.getBlacksmith().setRecipe(new Recipe(worldData.getRequirements()));
				addCluesToPlanets();
				npcHandler.getBlacksmith().setVisitedPlanets(worldData.getBlacksmithTraces());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasLoadingFile() {
		return model.hasLoadingFile();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteLoadingFile() {
		return model.deleteLoadingFile();
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