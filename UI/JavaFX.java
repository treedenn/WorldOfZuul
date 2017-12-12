package UI;

import BLL.ACQ.Domain;
import BLL.ACQ.UserInterface;
import UI.controller.StartController;
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("./view/start_view.fxml"));
		StartController controller = new StartController(domain);
		loader.setController(controller);
		controller.setStage(primaryStage);
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
