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

    public int possibleAnswers(){ return uno.getCurrentQuiz().getOptions().length; }

    @Override
    public void onStartEvent(NPC npc, Game game) {
        super.onStartEvent(npc, game);

        uno = (UnoX) npc;
        Quiz quiz = ((UnoX) npc).getCurrentQuiz();
        StringBuilder sb = new StringBuilder(quiz.getQuestion());
        for (int i = 0; i < quiz.getOptions().length; i++) {
            sb.append(System.lineSeparator());
            sb.append(String.valueOf(i + 1) + ". ");
            sb.append(quiz.getOptions()[i]);
        }
        message = sb.toString();
    }

    @Override
    public void onEndEvent(NPC npc, Game game) {
        super.onEndEvent(npc, game);

        uno = (UnoX) npc;
        Player player = (Player) game.getPlayer();
        if(uno.isAnswerCorrect(answer)) {
            player.increaseFuel(50);
            game.setMessageToContainer("Correct answer! Your fuel has been increased by 50.");
        } else {
            player.decreaseFuel(10);
            game.setMessageToContainer("Wrong answer! Your fuel has been decreased by 10.");
        }
    }
}
