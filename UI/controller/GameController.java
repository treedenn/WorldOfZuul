package UI.controller;

import BLL.Domain;
import BLL.Game;
import BLL.character.player.Backpack;
import BLL.character.player.Player;
import BLL.item.ItemStack;
import DAL.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
	private Domain domain;

	@FXML private Label labelFuel;
	@FXML private Label labelBackpack;
	@FXML private ProgressBar barFuel;
	@FXML private ProgressBar barBackpack;
	@FXML private Button buttonSearch;
	@FXML private Button buttonBackpack;
	@FXML private Button buttonInformation;

	private Player player;

	public GameController() {
		domain = Game.getInstance();
		player = domain.getPlayer();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		updateFuelProgressBar();
		updateBackpackProgressBar();
	}

	@FXML
	void handleSearchAction(ActionEvent event) {

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
