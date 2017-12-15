package UI.refactoredUI.highscore;

import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IEventListener;
import UI.refactoredUI.components.IHighscore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Highscore extends Component implements IHighscore {

    private List<IEventListener<String>> onSaveHighscoreSubscribers = new ArrayList<>();

    @FXML
    private Label highscoreTitle;

    @FXML
    private Label highscoreSubTitle;


    @FXML
    private Label scoreValue;

    @FXML
    private TextField nameTextField;

    public Highscore(){super("highscore_view.fxml");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSaveHighscore(IEventListener<String> listener) {
        onSaveHighscoreSubscribers.add(listener);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(double score, boolean win) {
        if(win){
            highscoreTitle.setText("You win!");
            highscoreSubTitle.setText("You fixed the portal gun and saved Rick.");
            scoreValue.setText("" + score);
        } else{
            highscoreTitle.setText("You lost...");
            highscoreSubTitle.setText("No fuel doesn't get you anywhere!");
            scoreValue.setText("0");
        }
    }

    @FXML
    void saveHighscore(ActionEvent event) {
        onSaveHighscoreSubscribers.forEach(listener -> listener.onAction(nameTextField.getText()));
    }

}
