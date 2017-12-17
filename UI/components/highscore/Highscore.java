package UI.components.highscore;

import UI.components.components.Component;
import UI.components.components.IEventListener;
import UI.components.components.IHighscore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Highscore extends Component implements IHighscore {

    private List<IEventListener<String>> onSaveHighscoreSubscribers = new ArrayList<>();
    private List<IEventListener> onExitSubscribers = new ArrayList<>();


    @FXML
    private Button saveHighscore;

    @FXML
    private Button exitGame;

    @FXML
    private Label highscoreTitle;

    @FXML
    private Label highscoreSubTitle;

    @FXML
    private Label scoreTopLabel;

    @FXML
    private Label scoreValue;

    @FXML
    private Label enterNameField;

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
    public void onExit(IEventListener<String> listener) {
        onExitSubscribers.add(listener);
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
            saveHighscore.setVisible(true);
            exitGame.setVisible(false);
        } else{
            highscoreTitle.setText("You lost...");
            highscoreSubTitle.setText("No fuel doesn't get you anywhere!");
            saveHighscore.setVisible(false);
            exitGame.setVisible(true);
            scoreValue.setVisible(false);
            nameTextField.setVisible(false);
            enterNameField.setVisible(false);
            scoreTopLabel.setVisible(false);
        }
    }


    @FXML
    void exit(ActionEvent event) {
        onExitSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void saveHighscore(ActionEvent event) {
        onSaveHighscoreSubscribers.forEach(listener -> listener.onAction(nameTextField.getText()));
    }

}
