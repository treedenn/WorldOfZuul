package BLL;

public interface Domain {
	boolean hasBeatenHighscore();
	void addPlayerToHighscore(String playerName);
}
