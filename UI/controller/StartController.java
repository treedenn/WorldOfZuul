package UI.controller;

import BLL.Game;
import BLL.Domain;
import BLL.scoring.Score;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StartController implements Initializable {
	private Domain domain;

	@FXML private Button buttonNewGame;
	@FXML private Button buttonExit;
	@FXML private Button buttonHighscore;
	@FXML private TableView<Score> tableviewHighscore;

	public StartController() {
		domain = Game.getInstance();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configListView();
	}

	@FXML
	void handleNewGameAction(ActionEvent event) {

	}

	@FXML
	void handleHighscoreAction(ActionEvent event) {
		tableviewHighscore.setVisible(!tableviewHighscore.isVisible());
	}

	@FXML
	void handleExitAction(ActionEvent event) {

	}

	private void configListView() {
		List<Score> highscore = domain.getHighscore();

		tableviewHighscore.setVisible(false);

		tableviewHighscore.setItems(FXCollections.observableArrayList(highscore));
		//tableviewHighscore.getColumns().get(0).setCellValueFactory();
		tableviewHighscore.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
		tableviewHighscore.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("score"));
	}
}