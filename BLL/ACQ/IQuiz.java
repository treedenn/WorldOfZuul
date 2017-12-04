package BLL.ACQ;

/**
 * Limits the functionality of {@link BLL.character.player.Quiz}.
 * Casted from Quiz and sent to the GUI.
 */
public interface IQuiz {
    /**
     * Gets the question of the current quiz.
     * @return the question of the quiz
     */
    String getQuestion();

    /**
     * Gets the possible answers to the current quiz.
     * @return the possible answers
     */
    String[] getOptions();
}