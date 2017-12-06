package BLL.entity.player;

import BLL.ACQ.IQuiz;

/**
 * Describes a Quiz with a question, options and a correct answer.
 */
public class Quiz implements IQuiz {
    private String question;
    private String[] options;
    private Integer answer;

    public Quiz(String question, String[] options, Integer answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuestion() {
        return question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getOptions() {
        return options;
    }

    /**
     * Get the answer as integer.
     * The integer is connected to the {@link #getOptions()}, which is an array containing indexes.
     * @return the correct answer
     */
    public Integer getAnswer() {
        return answer;
    }
}


















