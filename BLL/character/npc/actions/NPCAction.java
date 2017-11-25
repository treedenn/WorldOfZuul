package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.character.player.Player;

public class NPCAction implements INPCAction {
    String msg;
    boolean needAnswer;
    boolean isAnswerYes;

    public NPCAction(String msg, boolean needAnswer) {
        this.msg = msg;
        this.needAnswer = needAnswer;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public boolean needAnswer() {
        return needAnswer;
    }

    @Override
    public void setAnswer(boolean isYes) {
        isAnswerYes = isYes;
    }

    public void endEvent(Player player, Persistent persistent){ }
}
