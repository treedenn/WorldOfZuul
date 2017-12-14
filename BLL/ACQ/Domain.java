package BLL.ACQ;

import BLL.Game;
import BLL.MessageContainer;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;
import BLL.entity.player.buff.Buff;
import BLL.item.Item;
import BLL.world.Planet;

import java.util.List;
import java.util.Map;

public interface Domain {
	/**
	 * Injects an object of the {@link PersistenceLayer} interface.
	 * Additionally it runs a few methods inside the persistenceLayer interface immediately.
	 * @param persistenceLayer the object implementing {@link PersistenceLayer} interface.
	 */
	void injectPersistenceLayer(PersistenceLayer persistenceLayer);

	/**
	 * An initialization of the business layer.
	 * Is invoked by {@link #injectPersistenceLayer(PersistenceLayer)} function.
	 */
	void init();

	/**
	 * Adds a player to the highscore, sorts the list,
	 * removes the 'unused' one and saves the highscore.
	 * @param playerName the name of the player
	 */
	void addPlayerToHighscore(String playerName);

	/**
	 * * Returns an object with {@link IPlayer}, which limits access of GUI.
	 * The function comes from the {@link Domain} interface.
	 * @return the player, but casted to {@link IPlayer}.
	 */
	IPlayer getPlayer();

	/**
	 * Returns a map with all planets.
	 * The key is a planet name and the value is a {@link Planet}.
	 * @return a map of the planets and their associate names.
	 */
	Map<String, IPlanet> getPlayerPlanets();

	/**
	 * Gets the current highscore list as {@link IScore}.
	 * @return the highscore list
	 */
	List<IScore> getHighscore();

	/**
	 * Gets the {@link BLL.entity.player.Quiz} object as {@link IQuiz} to limit the functionality in GUI.
	 * @return returns the current quiz in play.
	 */
	IQuiz getQuiz();

	/**
	 * Returns a reference to the message container.
	 * @return the message container
	 */
	MessageContainer getMessageContainer();

	/**
	 * Checks whether a player has beaten any highscore.
	 * @return true, if player has beaten a highscore.
	 */
	boolean hasBeatenHighscore();

	/**
	 * A method to see if the player has won the game.
	 * Caution: Use {@link #isGameFinished()} before checking this one!
	 * @return true, if the player has won the game.
	 */
	boolean hasWonTheGame();

	/**
	 * Returns a boolean based the game is finished or not.
	 * @return true, if game is finished
	 */
	boolean isGameFinished();

	/**
	 * Player enters the planet.
	 * @param planetName the name of planet
	 * @return true, if player moved
	 */
	boolean planetEnter(String planetName);

	/**
	 * Player exits the planet.
	 * @return true, if player leaved
	 */
	boolean planetExit();

	/**
	 * An method to decrease fuel when moving,
	 * if decrease of fuel on move is desired with ticks.
	 * @param ticksPerSecond
	 */
	void decreaseFuelOnMove(int ticksPerSecond);

	/**
	 * Searches the planet. It sets the permanent/temporary search of the current planet.
	 * @return true, if searching was successful
	 */
	boolean searchPlanet();

	/**
	 * It loops through the buffs of the player.
	 * On each buff, it triggers {@link Buff#onGameTick(Player)}
	 * and {@link Buff#isExpired()}, if true, removes the buff.
	 */
	void updateBuffs();

	/**
	 * When an interaction between a NPC and a player have occurred within the business layer,
	 * this will return the NPC.
	 * An concrete example would be the pirates. Whenever the player is nearby,
	 * this will return them.
	 * @return the npc
	 */
	NPC interaction();

	/**
	 * It triggers the start event of the action inside a specific NPC.
	 * At every action, the GUI must invoke this function first before doing anything.
	 * @param npc is the specific NPC for interaction
	 * @param actionId is the specific action of the NPC
	 */
	void startInteract(NPC npc, int actionId);

	/**
	 * It triggers the end event of the action inside specific NPC.
	 * At every action, the GUI must invoke this function last.
	 * @param npc is the specific NPC for interaction
	 * @param actionId is the specific action of the NPC
	 */
	void endInteract(NPC npc, int actionId);

	/**
	 * If the itemstack's item is usable with {@link Item#hasUsable()} function,
	 * it will trigger the {@link Item#use(Player, Game)} function.
	 * @param iis the item stack containing the item.
	 * @return true, if it has a usable, false if it does not.
	 */
	boolean useItem(IItemStack iis);

	/**
	 * Picks an itemstack and sends it to the player's inventory.
	 * @param iis the item stack to add to the inventory
	 * @return true, if pickup successful
	 */
	boolean pickupItem(IItemStack iis);

	/**
	 * Drops the itemstack from the player's inventory to the current planet.
	 * @param iis the item stack to drop from the inventory
	 * @return true, if dropped successful
	 */
	boolean dropItem(IItemStack iis);

	/**
	 * Saves the game to a file.
	 * @return true, if saving was successful
	 */
	boolean save();

	/**
	 * Loads the game from a file.
	 * @return true, if loading was successful
	 */
	boolean load();

	/**
	 * Returns a boolean based on a file for loading is ready to be loaded.
	 * @return true, if file exists
	 */
	boolean hasLoadingFile();
}