package UI.refactoredUI;

import BLL.ACQ.Domain;
import BLL.ACQ.UserInterface;
import BLL.Game;
import UI.JavaFX;
import UI.controller.StartController;
import UI.refactoredUI.game.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewMain extends Application {

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game/game_view.fxml"));
        GameController controller = new GameController(Game.getInstance(), primaryStage);
        loader.setController(controller);
        AnchorPane root = loader.load();
        primaryStage.setScene(new Scene(root, root.getPrefWidth(), root.getPrefHeight()));
        primaryStage.show();
    }
}