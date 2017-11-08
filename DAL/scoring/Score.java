package DAL.scoring;

import java.io.Serializable;

public class Score  implements Serializable {
    private int score;
    private String naam;

    public Score(String naam, int score) {
        this.score = score;
        this.naam = naam;
    }

    public int getScore() {
        return score;
    }

    public String getNaam() {
        return naam;
    }


}