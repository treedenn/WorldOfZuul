package BLL.character.player;

import java.util.*;

public class UnoX {
    private List<Quiz> quizes;
    private Quiz currentQuiz;

    public UnoX() {
        
    }

    public Quiz getCurrentQuiz() {
        return currentQuiz;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }

    public String[] getUnoXMessage() {
        return new String[] {
                "Before leaving you make a quick stop at the local gas station Uno-X",
                "[Station Manager]: Would you like to play a small quiz in order to win some gas for your ship?",
                "Enter Y/N"
        };
    }

    public void pickRandomQuiz() {
        int index = (int) (Math.random() * quizes.size());
        currentQuiz = quizes.get(index);
    }

    public boolean isAnswerCorrect(int answer) {
        return currentQuiz.getAnswer() == answer - 1;
    }
}
