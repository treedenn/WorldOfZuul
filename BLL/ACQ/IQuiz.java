package BLL.ACQ;

/**
 * IQuiz limits the functionality granted when working with {@link BLL.character.player.Quiz}.
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