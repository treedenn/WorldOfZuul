package BLL.entity.npc.actions;

import BLL.Game;
import BLL.entity.npc.NPC;
import BLL.entity.npc.UnoX;
import BLL.entity.player.Player;
import BLL.entity.player.Quiz;

public class NPCQuizAction extends NPCAction {
    private UnoX uno;
    private int answer;

    public NPCQuizAction(String message) {
        super(message);
        answer = -1;
    }

    public void setAnswer(int index) {
        answer = index;
    }

    @Override
    public void onStartEvent(NPC npc, Game game) {
        super.onStartEvent(npc, game);

        uno = (UnoX) npc;
        Quiz quiz = ((UnoX) npc).getCurrentQuiz();
        StringBuilder sb = new StringBuilder(quiz.getQuestion());
        for (int i = 0; i < quiz.getOptions().length; i++) {
            sb.append(String.valueOf(i) + ". ");
            sb.append(quiz.getOptions()[i]);
            sb.append(System.lineSeparator());
        }

        sb.delete(sb.length() - 1, sb.length());

        message = sb.toString();
    }

    @Override
    public void onEndEvent(NPC npc, Game game) {
        super.onEndEvent(npc, game);

        uno = (UnoX) npc;
        Player player = (Player) game.getPlayer();
        if(uno.isAnswerCorrect(answer)) {
            player.increaseFuel(40);
        } else {
            player.decreaseFuel(10);
        }
    }
}
