package BLL.character.player;

import java.util.*;

public class UnoX {
    private Scanner scanner;
    private List<Quiz> quizes;
    private Quiz currentQuiz;

    public UnoX() {
        scanner = new Scanner(System.in);
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

    public boolean hasAcceptedOffer(char c) {
        return c == 'Y' || c == 'y';
    }

    public void pickRandomQuiz() {
        int index = (int) (Math.random() * quizes.size());
        currentQuiz = quizes.get(index);
    }

    public String[] getCurrentQuizMessage() {
        String[] strings = new String[1 + currentQuiz.getOptions().length];

        strings[0] = currentQuiz.getQuestion();
        for (int i = 0; i < currentQuiz.getOptions().length; i++) {
            strings[i + 1] = String.format("[%d] %s", i + 1, currentQuiz.getOptions()[i]);
        }

        return strings;
    }

    public int getOptionsSize() {
        return currentQuiz.getOptions().length;
    }

    public boolean isAnswerCorrect(int answer) {
        return currentQuiz.getAnswer() == answer - 1;
    }
}
