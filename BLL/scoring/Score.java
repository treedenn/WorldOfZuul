package BLL.scoring;

import BLL.ACQ.IScore;

import java.io.Serializable;

public class Score implements IScore, Serializable, Comparable<Score> {
    private String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Score o) {
        return o.score - this.score;
    }
}