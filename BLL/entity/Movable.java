package BLL.entity;

public interface Movable {
	/**
	 * A method that allows the entity the move.
	 * The only exception is the player.
	 */
	void move();

	/**
	 * Gets a boolean based on the npc can move.
	 * Primarily used to only allow on 'move' for every
	 * @return true, if object can move
	 */
	boolean canMove();

	/**
	 * Sets the canMove variable.
	 */
	void setMove(boolean canMove);
}
