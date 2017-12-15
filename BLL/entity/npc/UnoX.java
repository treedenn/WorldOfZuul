package BLL.entity.npc;

import BLL.Game;
import BLL.entity.Entity;
import BLL.entity.npc.actions.*;
import BLL.entity.player.Quiz;

import java.io.File;
import java.util.List;

/**
 * Contains all the functionality of the UnoX NPC.
 * UnoX is the gas station, that allows the player to refill his fuel.
 * To get the refill, a correct answer in the quiz, has to happen.
 */
public class UnoX extends Entity implements NPC{
    private NPCAction[] actions;
    private List<Quiz> quizzes;
    private Quiz currentQuiz;
    private File image;

    /**
     * Constructs a new UnoX NPC.
     */
    public UnoX() {
        super();
        initActions();
    }

    /**
     * Gets the quiz that is current selected.
     * @return current quiz
     */
    public Quiz getCurrentQuiz() {
        return currentQuiz;
    }

    /**
     * Sets the quiz list.
     * @param quizzes List of the quizzes
     */
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    /**
     * Picks a random quiz from the list of quizzes.
     */
    public void pickRandomQuiz() {
        int index = (int) (Math.random() * quizzes.size());
        currentQuiz = quizzes.get(index);
    }

    /**
     * Checks if the inserted index is the correct answer.
     * NOTE: the given index should be increased by 1, compared to the total amount of quiz options.
     * 3 Options means the index should be 1, 2 or 3 - not 0, 1 or 2.
     * @param answer index number of the option
     * @return true, if the answer was correct
     */
    public boolean isAnswerCorrect(int answer) {
        return currentQuiz.getAnswer() == answer - 1;
    }

    /**
     * Gets a object of type File containing the UnoX's image
     * @return the file of the UnoX's image.
     */
    public File getImage() { return image;}

    /**
     * Sets the UnoX's image to a File reference
     * @param image	the file of the UnoX's image
     */
    public void setImage(File image) { this.image = image;}

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return 4;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "UnoX Station Manager";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGood() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NPCAction[] getActions() {
        return actions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActions(NPCActionCollection collection) {
        this.actions = collection.getActions();
    }

    private void initActions() {
        actions = new NPCAction[] {
                new NPCAction("Oy matey, I guess you need some gas, aye?" +
                        "\nYou'll just need to pass me test lad!"),
                new NPCDialogAction("Would you like to play a small quiz in order to win some gas for your ship?") {
                    @Override
                    public void onEndEvent(NPC npc, Game game) {
                        super.onEndEvent(npc, game);

                        if(answerYes) {
                            ((UnoX) npc).pickRandomQuiz();
                        } else {
                            setActionId(3);
                        }
                    }
                },
                new NPCQuizAction(""),
                new NPCTerminateAction("... C'ya next time!")
        };
    }
}