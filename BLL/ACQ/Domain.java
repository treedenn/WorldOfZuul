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
	 * Moves the player to the specified planet.
	 * @param planetName the name of planet
	 * @return true, if player moved
	 */
	boolean movePlayerToPlanet(String planetName);

	/**
	 * Gets the current highscore list as {@link IScore}.
	 * @return the highscore list
	 */
	List<IScore> getHighscore();

	/**
	 * Checks whether a player has beaten any highscore.
	 * @return true, if player has beaten a highscore.
	 */
	boolean hasBeatenHighscore();

	/**
	 * It loops through the buff list of the player.
	 * On each buff, it triggers {@link Buff#onGameTick(Player)}
	 * and {@link Buff#isExpired()}, if true, removes the buff.
	 */
	void updateBuffs();

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
	 * @return true, if successful
	 */
	boolean pickupItem(IItemStack iis);

	/**
	 * Drops the itemstack from the player's inventory to the current planet.
	 * @param iis the item stack to drop from the inventory
	 * @return true, if successful
	 */
	boolean dropItem(IItemStack iis);

	/**
	 * Ignites the search. It sets the permanent/temporary search of the current planet.
	 * @return true, if search
	 */
	boolean searchPlanet();

	/**
	 * An method to decrease fuel when moving,
	 * if decrease of fuel on move is desired with ticks.
	 * @param ticksPerSecond
	 */
	void decreaseFuelOnMove(int ticksPerSecond);

	/**
	 * Gets the {@link BLL.entity.player.Quiz} object as {@link IQuiz} to limit the functionality in GUI.
	 * @return returns the current quiz in play.
	 */
	IQuiz getQuiz();

	/**
	 * Use to determine whether the player has given a correct answer from the GUI.
	 * Is invoked by GUI when an answer is given from the player.
	 * @param index is the answer of the multiple choice (exclude 0).
	 * @return a boolean whether the answer is true or not.
	 */
	boolean isAnswerCorrect(int index);

	/**
	 * Returns a reference to the message container.
	 * @return the message container
	 */
	MessageContainer getMessageContainer();
}