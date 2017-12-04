package BLL.scoring;

import BLL.ACQ.IScore;

import java.io.Serializable;

/**
 * Score contains information for the highscore.
 * It contains a name and a score.
 */
public class Score implements IScore, Serializable, Comparable<Score> {
    private String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * {@inheritDoc}
     *
     * Compares this Score object with another.
     */
    @Override
    public int compareTo(Score o) {
        return o.score - this.score;
    }
}