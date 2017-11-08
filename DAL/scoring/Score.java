package DAL.scoring;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {
    private int score;
    private String name;

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
        return this.score - o.score;
    }
}