package UI.components.highscore;

import UI.components.icomponents.Component;
import UI.components.icomponents.IEventListener;
import UI.components.icomponents.IHighscore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This concrete GUI handles the highscore view.
 */
public class Highscore extends Component implements IHighscore {

    /** List of observers */
    private List<IEventListener<String>> onSaveHighscoreSubscribers = new ArrayList<>();
    /** List of observers */
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

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public Highscore(){super("highscore_view.fxml");}

    /**
     * {@inheritDoc}
     */
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


    /**
     * Method to notify all subscribers of the onExit()-method.
     * @param event the type of event.
     */
    @FXML
    void exit(ActionEvent event) {
        onExitSubscribers.forEach(listener -> listener.onAction(null));
    }

    /**
     * Method to notify all subscribers of the onSaveHighscore()-event.
     * @param event the type of event.
     */
    @FXML
    void saveHighscore(ActionEvent event) {
        onSaveHighscoreSubscribers.forEach(listener -> listener.onAction(nameTextField.getText()));
    }

}
