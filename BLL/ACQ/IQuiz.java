package BLL.ACQ;

/**
 * gets question for quiz
 * @returns question
 * gets possiple answers
 * @returns possible answers
 */
public interface IQuiz {
    String getQuestion();
    String[] getOptions();
}