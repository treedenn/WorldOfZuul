package UI.GameComponents;

import com.sun.javafx.geom.Ellipse2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class Dialog {

    private AnchorPane parent;
    private AnchorPane dialogViewWrapper;
    private StackPane dialogViewInnerWrapper;
    private HBox dialogHbox;
    private VBox NPCrepresentation;
    private String imagePath;
    private Pane image;
    private Label name;


    public Dialog (AnchorPane parent){
        this.parent = parent;
    }

    public void showDialog(){
        dialogViewWrapper = new AnchorPane();
        dialogViewInnerWrapper = new StackPane();
        dialogHbox = new HBox();
        NPCrepresentation = new VBox();
        imagePath = "./UI/resources/img/nps/unoX.png";
        image = new Pane();
        name = new Label();



        NPCrepresentation.getChildren().addAll(image, name);
        name.setText("UnoX Manager");
        name.setStyle("-fx-text-fill: white; -fx-font-size: 25; -fx-font-family: 'Circular Std Bold';");
        image.setBackground(new Background(new BackgroundImage(new Image(imagePath), null,null,null,BackgroundSize.DEFAULT)));
        image.setMinHeight(200);
        image.setMinWidth(200);
        NPCrepresentation.setSpacing(30);

        dialogHbox.getChildren().add(NPCrepresentation);

        dialogViewInnerWrapper.getChildren().add(dialogHbox);
        dialogViewInnerWrapper.setAlignment(Pos.TOP_CENTER);
        dialogHbox.setStyle("-fx-border-color: yellow;");
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

}
