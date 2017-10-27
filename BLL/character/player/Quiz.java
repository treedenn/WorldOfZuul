package BLL.character.player;

public class Quiz {
    private String question;
    private String[] options;
    private Integer answer;

    public Quiz(String question, String[] options, Integer answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public Integer getAnswer() {
        return answer;
    }
}


















