package BLL.ACQ;

import BLL.entity.npc.NPC;

import java.util.List;

/**
 * Limits the functionality of {@link BLL.world.Planet}.
 * Casted from Planet and sent to the GUI.
 */
public interface IPlanet {

	/**
	 * Gets the name of the planet.
	 * @return name
	 */
	String getName();

	/**
	 * Gets the description of the planet.
	 * @return description
	 */
	String getDescription();

	/**
	 * Gets the arrive message.
	 * @return arrive message
	 */
	String getArriveMessage();

	/**
	 * Gets the temporary search variable.
	 * @return temporary search variable
	 */
	boolean getTempSearched();

	/**
	 * Gets the permanent search variable.
	 * @return permanent search variable
	 */
	boolean getPermSearched();

	/**
	 * Returns a boolean based on both the temporary and permanent search is true.
	 * If both ({@link #getTempSearched()} & {@link #getPermSearched()}) return true,
	 * this function will return true, otherwise false.
	 * @return if planet has been searched
	 */
	boolean hasSearched();

	/**
	 * Returns a list of all the NPCs currently who are on the planet.
	 * @return a list of NPC
	 */
	List<NPC> getNPCs();

	/**
	 * Gets the X-coordinate of the planet.
	 * It comes from a {@link javafx.geometry.Point2D} object within the planet.
	 * @return the x-coordinate of the planet
	 */
	double getX();

	/**
	 * Gets the Y-coordinate of the planet.
	 * It comes from a {@link javafx.geometry.Point2D} object within the planet.
	 * @return the y-coordinate of the planet
	 */
	double getY();
}
