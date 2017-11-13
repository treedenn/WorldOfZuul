package UI.controller;

import BLL.Domain;
import BLL.Game;
import BLL.character.player.Player;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
	private SimpleDoubleProperty fuel;
	private ObservableValue<Double> backpackItems;

	public GameController() {
		domain = Game.getInstance();

		player = domain.getPlayer();
		fuel = new SimpleDoubleProperty(player.getFuel());
		backpackItems = new SimpleObjectProperty<>(player.getBackpack().getCurrentCapacity());

		fuel.addListener((observableValue, aDouble, t1) -> {
			System.out.println(observableValue);
			System.out.println(aDouble);
			System.out.println(t1);
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		barFuel.progressProperty().bind(fuel.divide(100));
	}

	@FXML
	void handleSearchAction(ActionEvent event) {
		//player.decreaseFuel(5);
		//barFuel.progressProperty().set(player.getFuel() / 100);

		//System.out.println(player.getFuel());
		//System.out.println(fuel.getValue());
	}

	@FXML
	void handleBackpackAction(ActionEvent event) {

	}

	@FXML
	void handleInformationAction(ActionEvent event) {

	}
}
