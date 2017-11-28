package UI;

import BLL.ACQ.Domain;
import BLL.Game;
import BLL.ACQ.UserInterface;
import UI.controller.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JavaFX extends Application implements UserInterface {
	private static Domain domain;

	// TODO: find the error why domain cannot be used!

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("./view/start_view.fxml"));

		loader.setController(new StartController(domain));

		AnchorPane pane = loader.load();

		primaryStage.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
		primaryStage.setTitle("Rick's Adventure");
		primaryStage.setResizable(true);

		primaryStage.show();

	}

	@Override
	public void injectDomain(Domain domain) {
		JavaFX.domain = domain;
	}

	@Override
	public void startApplication() {
		launch(getClass());
	}
}
