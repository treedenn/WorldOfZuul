package BLL.scoring;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {
    private String name;
    private int score;

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Score o) {
        return o.score - this.score;
    }
}