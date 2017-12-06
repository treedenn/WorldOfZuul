package UI.controller;

import BLL.ACQ.Domain;
import BLL.ACQ.IInventory;
import BLL.ACQ.IPlayer;
import UI.SearchTask;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class OldGameController implements Initializable {
	private Domain domain;

	@FXML private Label labelFuel;
	@FXML private Label labelBackpack;
	@FXML private ProgressBar barFuel;
	@FXML private ProgressBar barBackpack;
	@FXML private ProgressBar barSearch;
	@FXML private Button buttonSearch;
	@FXML private Button buttonBackpack;
	@FXML private Button buttonInformation;

	private IPlayer player;
	private Task task;

	OldGameController(Domain domain) {
		this.domain = domain;
		this.player = domain.getPlayer();
		this.task = null;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		updateFuelProgressBar();
		updateBackpackProgressBar();
	}

	@FXML
	void handleSearchAction(ActionEvent event) {
		if(task == null || !task.isRunning()) {
			this.task = new SearchTask(false);

			barSearch.setPrefWidth(buttonSearch.getWidth() * 8/10);
			barSearch.setVisible(true);
			buttonSearch.setText("");

			// player.getCurrentPlanet().getPermSearched()
			Thread th = new Thread(task);

			barSearch.progressProperty().bind(task.progressProperty());
			task.setOnSucceeded(event1 -> {
				buttonSearch.setText("Search");
				barSearch.setVisible(false);
				barSearch.setPrefWidth(0);

				// Place action here!
			});

			th.start();
		}
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
		IInventory inv = domain.getPlayer().getIInventory();
		barBackpack.progressProperty().setValue(inv.getCurrentCapacity() / inv.getMaxCapacity());
		labelBackpack.setText(String.format("[%.1f / %.1f]", inv.getCurrentCapacity(), inv.getMaxCapacity()));
	}
}
