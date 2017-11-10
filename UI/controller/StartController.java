package UI.controller;

import BLL.Game;
import BLL.Domain;
import BLL.scoring.Score;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class StartController {
	private Domain domain;

	@FXML private Button buttonNewGame;
	@FXML private Button buttonExit;
	@FXML private Button buttonHighscore;
	@FXML private ListView<Score> listviewHighscore;

	public StartController() {
		domain = (Domain) Game.getInstance();
	}

	@FXML
	void handleExitAction(ActionEvent event) {

	}

	@FXML
	void handleHighscoreAction(ActionEvent event) {

	}

	@FXML
	void handleNewGameAction(ActionEvent event) {

	}

}