package BLL.character.player;

import BLL.ACQ.IQuiz;

public class Quiz implements IQuiz{
    private String question;
    private String[] options;
    private Integer answer;

    public Quiz(String question, String[] options, Integer answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String[] getOptions() {
        return options;
    }

    public Integer getAnswer() {
        return answer;
    }
}


















