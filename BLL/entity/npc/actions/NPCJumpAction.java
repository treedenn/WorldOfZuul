package BLL.entity.npc.actions;

/**
 * An action that allows other actions to jump by setting the action id to a new value.
 * This value can be read by the GUI.
 */
public abstract class NPCJumpAction extends NPCAction {
	private int actionId;

	/**
	 * Sets the default message for the base action.
	 * @param msg default message
	 */
	public NPCJumpAction(String msg) {
		super(msg);
		this.actionId = -1;
	}

	/**
	 * Gets the action id set by the derived class.
	 * @return action id to jump to
	 */
	public int getActionId() {
		return actionId;
	}

	/**
	 * Sets the action id.
	 * Can only be used by derived classes or inside package.
	 * @param actionId
	 */
	protected void setActionId(int actionId) {
		this.actionId = actionId;
	}

	/**
	 * Resets the action id.
	 * The default action id is -1.
	 * If {@link #getActionId()} returns -1, the index should follow the sequential order.
	 */
	public void resetActionId() {
		this.actionId = -1;
	}
}
