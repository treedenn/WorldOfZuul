package UI.components.launcher;

/**
 * This class is used as a datatype for the highscore list.
 */
public class UIScore extends BLL.scoring.Score {
	private int place;

	public UIScore(String name, int score, int place) {
		super(name, score);
		this.place = place;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
}
