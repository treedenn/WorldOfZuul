import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("UI/view/start_view.fxml"));

		primaryStage.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
		primaryStage.setTitle("Rick's Adventure");
		primaryStage.setResizable(false);

		primaryStage.show();
	}

	/* runs the game */
	public static void main(String[] args) {
		Application.launch(args);
	}
}



