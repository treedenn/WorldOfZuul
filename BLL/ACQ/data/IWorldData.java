package BLL.ACQ.data;

import BLL.item.ItemStack;

/**
 * Interface makes a contract by telling what is the required data to save and load within the world.
 * When the business layer sets, the persistent layer gets them.
 * When the persistent layer sets, the business layer gets them.
 */
public interface IWorldData {
	/**
	 * Gets the seed of the world.
	 * @return the seed
	 */
	int getWorldSeed();

	/**
	 * Sets the world seed.
	 * @param worldSeed game seed
	 */
	void setWorldSeed(int worldSeed);

	/**
	 * Gets the playtime (time elapsed) in current game.
	 * @return time in milliseconds
	 */
	int getTimeElapsed();

	/**
	 * Sets the playtime (time elapsed) in current game.
	 * @param time in milliseconds
	 */
	void setTimeElapsed(int time);

	/**
	 * Returns a boolean based on the condition of the portal gun.
	 * @return true, if broken
	 */
	boolean isPortalGunBroken();

	/**
	 * Sets the boolean based on the portal gun's condition.
	 * @param isBroken portal gun's condition
	 */
	void setPortalGunBroken(boolean isBroken);

	/**
	 * Gets the requirements to repair the portal gun.
	 * @return
	 */
	ItemStack[] getRequirements();

	/**
	 * Sets the requirements to repair the portal gun.
	 * @param requirements based on the recipe generation
	 */
	void setRequirements(ItemStack[] requirements);

	/**
	 * Gets the traces of the blacksmith.
	 * @return an array of planet names.
	 */
	String[] getBlacksmithTraces();

	/**
	 * Sets the traces of the blacksmith.
	 * @param traces the traces of the blacksmith
	 */
	void setBlacksmthTraces(String[] traces);
}
