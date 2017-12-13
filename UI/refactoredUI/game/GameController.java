package UI.refactoredUI.game;

import BLL.ACQ.Domain;
import UI.refactoredUI.backpack.Backpack;
import UI.refactoredUI.components.IBackpack;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable{

    private Domain domain;
    IBackpack backpack;

    @FXML
    AnchorPane root;

    public GameController(Domain domain) {
        this.domain = domain;
        backpack = new Backpack();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        root.getChildren().add(backpack.getView());


        backpack.onUse(data -> domain.useItem(data));
        backpack.onDrop(data -> domain.dropItem(data));
        backpack.onClose(data -> root.getChildren().remove(backpack.getView()));

    }


    @FXML
    void keyIsPressed(KeyEvent event) {

    }

    @FXML
    void keyIsReleased(KeyEvent event) {

    }




}

