package UI;

import BLL.ACQ.Domain;
import BLL.ACQ.UserInterface;
import BLL.Game;
import UI.controller.StartController;
import UI.refactoredUI.game.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JavaFX extends Application implements UserInterface {

	/** Business layer object implementing {@link Domain} interface */
	private static Domain domain;

	/**
	 * Loads the initial scene and controller and shows the window
	 * {@inheritDoc}
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO: LASSE ARBEJDER HER
		//FXMLLoader loader = new FXMLLoader(getClass().getResource("refactoredUI/game/game_view.fxml"));
		//GameController controller = new GameController(Game.getInstance(), primaryStage);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/start_view.fxml"));
		StartController controller = new StartController(Game.getInstance());
		controller.setStage(primaryStage);
		loader.setController(controller);
		AnchorPane pane = loader.load();
		primaryStage.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
		primaryStage.setTitle("Rick's Adventure The Game// Gruppe 24");
		primaryStage.show();


	}

	/**
	 *
	 * {@inheritDoc}
	 */
	@Override
	public void injectDomain(Domain domain) {
		JavaFX.domain = domain;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startApplication() {
		launch(getClass());
	}
}
