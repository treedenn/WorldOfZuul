package UI.controller;

import BLL.ACQ.Domain;
import BLL.scoring.Score;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StartController implements Initializable {
	private Domain domain;

	@FXML private Button buttonNewGame;
	@FXML private Button buttonExit;
	@FXML private Button buttonHighscore;
	@FXML private TableView<Score> tableviewHighscore;
	@FXML private AnchorPane aboutWrapper;
	@FXML private Button exitButton__about;
	@FXML private Button button__about;

	public StartController(Domain domain) {this.domain = domain;	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configListView();
	}

	@FXML
	void handleNewGameAction(ActionEvent event) {
		try {
			switchToGameView();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML void displayAboutScreen(ActionEvent event) { aboutWrapper.setVisible(!aboutWrapper.isVisible()); }

	@FXML void closeAboutScreen(ActionEvent event) { displayAboutScreen(event); }

	@FXML
	void handleExitAction(ActionEvent event) {
		Platform.exit();
	}

	private void switchToGameView() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LasseGame_view.fxml"));
		LasseGameController controller = new LasseGameController(domain);
		controller.setStage((Stage) buttonNewGame.getScene().getWindow());
		loader.setController(controller);

		Pane pane = loader.load();


		Scene scene = new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight());
		scene.setRoot(pane);

		Stage stage = (Stage) buttonNewGame.getScene().getWindow();
		stage.setScene(scene);
		stage.centerOnScreen();
	}


	private void configListView() {
		List<Score> highscore = domain.getHighscore();

		aboutWrapper.setVisible(false);

		tableviewHighscore.setSelectionModel(null);
		tableviewHighscore.setItems(FXCollections.observableArrayList(highscore));
		//tableviewHighscore.getColumns().get(0).setCellValueFactory();
		tableviewHighscore.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
		tableviewHighscore.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("score"));
	}
}