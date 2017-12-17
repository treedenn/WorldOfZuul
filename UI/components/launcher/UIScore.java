package UI.components.launcher;

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
