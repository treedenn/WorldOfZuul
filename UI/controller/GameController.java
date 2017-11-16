package UI.controller;

import BLL.Domain;
import BLL.character.player.Backpack;
import BLL.character.player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
	private Domain domain;

	@FXML private Label labelFuel;
	@FXML private Label labelBackpack;
	@FXML private ProgressBar barFuel;
	@FXML private ProgressBar barBackpack;
	@FXML private ProgressBar barSearch;
	@FXML private Button buttonSearch;
	@FXML private Button buttonBackpack;
	@FXML private Button buttonInformation;

	private Player player;

	public GameController(Domain domain) {
		this.domain = domain;
		player = domain.getPlayer();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		updateFuelProgressBar();
		updateBackpackProgressBar();
	}

	@FXML
	void handleSearchAction(ActionEvent event) {
		/*
		barSearch.setPrefWidth(buttonSearch.getPrefWidth());
		barSearch.progressProperty().set(0);
		barSearch.setVisible(true);
		buttonSearch.setText("");

		for(int i = 1; i <= 10; i++) {
			try {
				Thread.sleep(200);
				barSearch.progressProperty().set(i * 0.1);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

		//barSearch.setVisible(false);
		//barSearch.setPrefWidth(0);
		buttonSearch.setText("Search");
		*/
	}

	@FXML
	void handleBackpackAction(ActionEvent event) {

	}

	@FXML
	void handleInformationAction(ActionEvent event) {

	}

	private void updateFuelProgressBar() {
		barFuel.progressProperty().setValue(player.getFuel() / player.getMaxFuel());
		labelFuel.setText(String.format("[%.0f / %d]", player.getFuel(), player.getMaxFuel()));
	}

	private void updateBackpackProgressBar() {
		Backpack bp = player.getBackpack();
		barBackpack.progressProperty().setValue(bp.getCurrentCapacity() / bp.getMaxCapacity());
		labelBackpack.setText(String.format("[%.1f / %.1f]", bp.getCurrentCapacity(), bp.getMaxCapacity()));
	}
}
