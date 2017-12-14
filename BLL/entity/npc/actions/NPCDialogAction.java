package BLL.entity.npc.actions;

/**
 * An action, that makes it possible to make a dialog,
 * between the NPC and the user. The dialog is a yes/no.
 * Derives from the {@link NPCJumpAction}, since it should be able to jump between actions.
 */
public class NPCDialogAction extends NPCJumpAction {
	boolean answerYes;

	/**
	 * Constructs a new NPC Dialog action.
	 * @param msg default message
	 */
	public NPCDialogAction(String msg) {
		super(msg);
		answerYes = false;
	}

	/**
	 * Function to give the action a yes.
	 * Default is no.
	 * @param answerYes
	 */
	public void setAnswer(boolean answerYes) {
		this.answerYes = answerYes;
	}
}
