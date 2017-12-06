package UI.GameComponents;

import UI.controller.GameController;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class Dialog {

    private GameController controller;
    private AnchorPane parent;
    private AnchorPane dialogViewWrapper;
    private StackPane dialogViewInnerWrapper;
    private HBox dialogHbox;
    private VBox NPCrepresentation;
    private String imagePath;
    private Pane image;
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

    public void updateDialog(String NPCname, String message, String imagePath){
        dialogViewWrapper = new AnchorPane();
        dialogViewInnerWrapper = new StackPane();
        dialogHbox = new HBox();
        NPCrepresentation = new VBox();
        this.imagePath = imagePath;
        image = new Pane();
        name = new Label();
        dialog = new VBox();
        NPCMessage = new Label('"' + message + '"');
        choicesVBox = new VBox();
        exit = new Button();
        choicesScrollPane = new ScrollPane();
        choices = new VBox();


        NPCrepresentation.getChildren().addAll(image, name);
        name.setText(NPCname);
        name.setStyle("-fx-text-fill: white; -fx-font-size: 25; -fx-font-family: 'Circular Std Bold';");
        image.setBackground(new Background(new BackgroundImage(new Image(imagePath), null,null,null,BackgroundSize.DEFAULT)));
        image.setMinHeight(200);
        image.setMinWidth(200);
        NPCrepresentation.setSpacing(30);

        choices.getChildren().addAll(new Button("ANOTHER OPTION"), new Button("ANOTHER OPTION"), new Button("ANOTHER OPTION"),new Button("ANOTHER OPTION"), new Button("ANOTHER OPTION"), new Button("ANOTHER OPTION"), new Button("ANOTHER OPTION"), new Button("ANOTHER OPTION"), new Button("ANOTHER OPTION"), new Button("ANOTHER OPTION"), new Button("A THIRD OPTION"));
        for (Node node : choices.getChildren()) {
            node.getStyleClass().add("choiceButton");
        }

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
        dialogHbox.setHgrow(dialog, Priority.ALWAYS);
        dialog.setStyle("-fx-padding: 30px;");

        dialogViewInnerWrapper.getChildren().add(dialogHbox);
        dialogViewInnerWrapper.setAlignment(Pos.TOP_CENTER);
        dialogHbox.setMaxWidth(800);

        dialogViewWrapper.getChildren().add(dialogViewInnerWrapper);
        dialogViewWrapper.setLeftAnchor(dialogViewInnerWrapper, 0.0);
        dialogViewWrapper.setRightAnchor(dialogViewInnerWrapper, 0.0);
        dialogViewWrapper.setTopAnchor(dialogViewInnerWrapper, 0.0);
        dialogViewWrapper.setBottomAnchor(dialogViewInnerWrapper, 0.0);
        dialogViewWrapper.setStyle("-fx-background-color: rgba(0,0,0,0.5);");

        parent.getChildren().add(dialogViewWrapper);
        parent.setLeftAnchor(dialogViewWrapper, 0.0);
        parent.setRightAnchor(dialogViewWrapper, 0.0);
        parent.setTopAnchor(dialogViewWrapper, 0.0);
        parent.setBottomAnchor(dialogViewWrapper, 0.0);
    }

    public void closeDialog(){
        System.out.println("Clicked");
    }

}
