package UI.refactoredUI.dialog;

import BLL.ACQ.INPCAction;
import BLL.entity.npc.actions.NPCAction;
import BLL.entity.npc.actions.NPCDialogAction;
import BLL.entity.npc.actions.NPCQuizAction;
import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IDialog;
import UI.refactoredUI.components.IEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Dialog extends Component implements IDialog {

    private List<IEventListener> onLeaveSubscribers = new ArrayList<>();
    private List<IEventListener<Boolean>> onAnswerSubscribers = new ArrayList<>();
    private List<IEventListener> onContinueSubscribers = new ArrayList<>();
    private List<IEventListener<Integer>> onQuizAnswerSubscribers = new ArrayList<>();

    @FXML
    private AnchorPane image;

    @FXML
    private Button leaveButton;

    @FXML
    private Text message;

    @FXML
    private VBox buttonWrapper;

    public Dialog(){super("dialog_view.fxml");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void onLeave(IEventListener listener) {
        onLeaveSubscribers.add(listener);
    }

    @Override
    public void onAnswer(IEventListener<Boolean> listener) {
        onAnswerSubscribers.add(listener);
    }

    @Override
    public void onContinue(IEventListener listener) {
        onContinueSubscribers.add(listener);
    }

    @Override
    public void onQuizAnswer(IEventListener<Integer> listener) {
        onQuizAnswerSubscribers.add(listener);
    }

    @Override
    public void loadCharacterInformation(String characterName, String message, String imagePath) {
        String backgroundImage =  "-fx-background-image: url(" + imagePath + ");";
        if(imagePath != null) image.setStyle(backgroundImage);
        this.message.setText(characterName + " says: \n" + message);
    }

    @Override
    public void addChoices(INPCAction action) {
        buttonWrapper.getChildren().clear();
        if(action instanceof NPCDialogAction){
            Button yesButton = new Button("Yes");
            Button noButton = new Button("No");
            buttonWrapper.getChildren().addAll(yesButton, noButton);
            yesButton.setOnAction(event -> onAnswerSubscribers.forEach(listener -> listener.onAction(true)));
            noButton.setOnAction(event -> onAnswerSubscribers.forEach(listener -> listener.onAction(false)));
        } else if (action instanceof NPCQuizAction){
            NPCQuizAction quizAction = (NPCQuizAction) action;
            Button[] options = new Button[quizAction.possibleAnswers()];
            for (int i = 0; i < options.length; i++) {
                options[i] = new Button(String.valueOf(i + 1));
                int finalI = i;
                options[i].setOnAction(event -> onQuizAnswerSubscribers.forEach(listener -> listener.onAction(finalI + 1)));
            }
            buttonWrapper.getChildren().addAll(options);
        } else {
            Button continueButton = new Button("Continue...");
            buttonWrapper.getChildren().add(continueButton);
            continueButton.setOnAction(event -> onContinueSubscribers.forEach(listener -> listener.onAction(null)));
        }

        for (Node node : buttonWrapper.getChildren()) {
            node.getStyleClass().add("dialog_button");
        }

    }

    @Override
    public void allowLeaveOption(boolean leaveOption) {
        leaveButton.setDisable(leaveOption);
    }

    @FXML
    void leave(ActionEvent event) {
        onLeaveSubscribers.forEach(listener -> listener.onAction(null));
    }

}
