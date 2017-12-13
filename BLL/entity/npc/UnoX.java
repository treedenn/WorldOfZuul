package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.ACQ.IPlanet;
import BLL.entity.Entity;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.entity.player.Quiz;
import BLL.world.Planet;

import java.io.File;
import java.util.*;

public class UnoX extends Entity implements NPC{
    private NPCActionCollection collection;
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

    @Override
    public int getId() {
        return 4;
    }

    @Override
    public String getName() {
        return "UnoX Station Manager";
    }

    @Override
    public boolean isGood() {
        return true;
    }

    @Override
    public INPCAction[] getActions() {
        return collection.getActions();
    }

    @Override
    public void setActions(NPCActionCollection collection) {
        this.collection = collection;
    }

}
