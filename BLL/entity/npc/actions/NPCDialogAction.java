package BLL.entity.npc.actions;

public class NPCDialogAction extends NPCJumpAction {
	boolean answerYes;

	public NPCDialogAction(String msg) {
		super(msg);
		answerYes = false;
	}

	public void setAnswer(boolean answerYes) {
		this.answerYes = answerYes;
	}
}
