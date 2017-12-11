package BLL.entity.npc;

import BLL.entity.player.Quiz;

import java.io.File;
import java.util.*;

public class UnoX {
    private List<Quiz> quizes;
    private Quiz currentQuiz;
    private File image;

    public UnoX() {
        
    }

    public Quiz getCurrentQuiz() {
        return currentQuiz;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }

    public void pickRandomQuiz() {
        int index = (int) (Math.random() * quizes.size());
        currentQuiz = quizes.get(index);
    }

    public boolean isAnswerCorrect(int answer) {
        return currentQuiz.getAnswer() == answer - 1;
    }

    /**
     * Gets a object of type File containing the UnoX's image
     * @return	the file of the UnoX's image.
     */
    public File getImage() { return image;}

    /**
     * Sets the UnoX's image to a File reference
     * @param image	the file of the UnoX's image
     */
    public void setImage(File image) { this.image = image;}
}
