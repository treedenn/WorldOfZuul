package BLL.ACQ;

/**
 * Limits and describes the functionality of {@link BLL.scoring.Score}.
 * Casted from Score and sent to the GUI.
 */
public interface IScore {
	/**
	 * Gets the player name.
	 * @return player name
	 */
	String getName();

	/**
	 * Gets the score of the player.
	 * @return the score
	 */
	int getScore();
}
