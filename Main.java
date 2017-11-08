
import BLL.Game;
<<<<<<< HEAD
import DAL.scoring.HighscoreManager;

=======
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
>>>>>>> ac89e9ab96535dd29370010758ac4966706a3f8e

public class Main extends Application {
	/*
		Phase 1 is complete!
			Date: 03/11/2017
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("UI/view/start_view.fxml"));

		primaryStage.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
		primaryStage.setTitle("Rick's Adventure - Group 24");
		primaryStage.setResizable(false);

		primaryStage.show();
	}

	/* runs the game */
	public static void main(String[] args) {
<<<<<<< HEAD
		new Game().start();


=======
		Application.launch(args);
>>>>>>> ac89e9ab96535dd29370010758ac4966706a3f8e
	}
}



