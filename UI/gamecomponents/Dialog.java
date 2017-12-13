package UI.gameComponents;

import BLL.entity.npc.actions.NPCAction;
import BLL.entity.npc.actions.NPCDialogAction;
import BLL.entity.npc.actions.NPCQuizAction;
import UI.controller.GameController;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class Dialog implements Reusable{

    private GameController controller;
    private AnchorPane parent;
    private AnchorPane dialogViewWrapper;
    private StackPane dialogViewInnerWrapper;
    private HBox dialogHbox;
    private VBox NPCrepresentation;
    private String imagePath;
    private ImageView image;
    private Label name;
    private VBox dialog;
    private Label NPCMessage;
    private VBox choicesVBox;
    private Button exit;
    private ScrollPane choicesScrollPane;
    private VBox choices;


    public Dialog (AnchorPane parent, GameController controller){
        this.parent = parent;
        this.controller = controller;

    }

    public void addChoice(NPCAction action){
        choices.getChildren().clear();
        if(action instanceof NPCDialogAction){
            Button yesButton = new Button("Yes");
            Button noButton = new Button("No");
            choices.getChildren().addAll(yesButton, noButton);
            yesButton.setOnAction(event -> controller.setAnswer(true));
            noButton.setOnAction(event -> controller.setAnswer(false));
        } else if (action instanceof NPCQuizAction){
            NPCQuizAction quizAction = (NPCQuizAction) action;
            Button[] options = new Button[quizAction.possibleAnswers()];
            for (int i = 0; i < options.length; i++) {
                options[i] = new Button(String.valueOf(i + 1));
                int finalI = i;
                options[i].setOnAction(event -> controller.setQuizAnswer(finalI + 1));
            }
            choices.getChildren().addAll(options);
        } else {
            Button continueButton = new Button("Continue...");
            choices.getChildren().add(continueButton);
            continueButton.setOnAction(event -> controller.nextAction());
        }

        for (Node node : choices.getChildren()) {
            node.getStyleClass().add("choiceButton");
        }

    }

    public void updateDialog(String NPCname, String message, String imagePath){

        dialogViewWrapper = new AnchorPane();
        dialogViewInnerWrapper = new StackPane();
        dialogHbox = new HBox();
        NPCrepresentation = new VBox();
        this.imagePath = imagePath;
        image = new ImageView();
        name = new Label();
        dialog = new VBox();
        NPCMessage = new Label('"' + message + '"');
        NPCMessage.setWrapText(true);
        choicesVBox = new VBox();
        exit = new Button();
        choicesScrollPane = new ScrollPane();
        choices = new VBox();


        NPCrepresentation.getChildren().addAll(image, name);
        name.setText(NPCname);
        name.setWrapText(true);
        name.setStyle("-fx-text-fill: white; -fx-font-size: 25; -fx-font-family: 'Circular Std Bold';");
        image.setImage(new Image("DAL/resource/images/npcs/profputri.png"));
        image.setPreserveRatio(true);
        image.setFitWidth(200);
        NPCrepresentation.setSpacing(30);

        choicesScrollPane.setContent(choices);
        choicesScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        choicesScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        choicesScrollPane.getStyleClass().add("view__scroll-pane");
        choicesScrollPane.setStyle("-fx-background-color: transparent;");
        choicesScrollPane.setFitToWidth(true);
        choices.setSpacing(15);
        choices.setFillWidth(true);
        choices.setAlignment(Pos.CENTER);

        choicesVBox.getChildren().addAll(choicesScrollPane, exit);
        choicesVBox.setAlignment(Pos.TOP_LEFT);
        choicesVBox.setFillWidth(true);
        choicesVBox.setSpacing(30);
        exit.setMinWidth(choicesScrollPane.getWidth());
        exit.setText("LEAVE THIS DIALOG");
        exit.setOnAction(event -> closeDialog());
        exit.getStyleClass().add("button__dialog-exit");

        dialog.getChildren().addAll(NPCMessage, choicesVBox);
        dialog.setSpacing(30);
        NPCMessage.setStyle("-fx-text-fill: white; -fx-font-size: 18; -fx-font-family: 'Circular Std Book';");
        dialog.widthProperty().addListener((observable, oldValue, newValue) -> {
            exit.setPrefWidth(newValue.doubleValue() - 76);
            ObservableList<Button> children = (ObservableList)choices.getChildren();
            for (Button child : children) {
                child.setPrefWidth(newValue.doubleValue());
            }
        });


        dialogHbox.getChildren().addAll(NPCrepresentation, dialog);
        HBox.setHgrow(dialog, Priority.ALWAYS);
        dialog.setStyle("-fx-padding: 30px;");

        dialogViewInnerWrapper.getChildren().add(dialogHbox);
        dialogViewInnerWrapper.setAlignment(Pos.CENTER);
        dialogViewInnerWrapper.setStyle("-fx-padding: 50 0 0 0;");
        dialogHbox.setMaxWidth(800);

        dialogViewWrapper.getChildren().add(dialogViewInnerWrapper);
        AnchorPane.setLeftAnchor(dialogViewInnerWrapper, 0.0);
        AnchorPane.setRightAnchor(dialogViewInnerWrapper, 0.0);
        AnchorPane.setTopAnchor(dialogViewInnerWrapper, 80.0);
        AnchorPane.setBottomAnchor(dialogViewInnerWrapper, 0.0);
        dialogViewWrapper.setStyle("-fx-background-color: rgba(0,0,0,0.8);");

        parent.getChildren().add(dialogViewWrapper);
        AnchorPane.setLeftAnchor(dialogViewWrapper, 0.0);
        AnchorPane.setRightAnchor(dialogViewWrapper, 0.0);
        AnchorPane.setTopAnchor(dialogViewWrapper, 0.0);
        AnchorPane.setBottomAnchor(dialogViewWrapper, 0.0);

    }

    public void closeDialog(){
        controller.showDashboard();
        controller.enableMovement();
        clear();
    }


    @Override
    public <T extends Pane> void remove(T parent) {
        parent.getChildren().remove(dialogViewWrapper);
    }

    @Override
    public void append(String... content) {

    }

    @Override
    public void clear() {
        if(dialogViewWrapper.getChildren().size() > 0) {
            dialogViewWrapper.getChildren().clear();
        }
        parent.getChildren().remove(dialogViewWrapper);
    }
}
