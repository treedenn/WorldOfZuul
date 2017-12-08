package UI.GameComponents;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class HoverLabel {

    private AnchorPane parent;
    private StackPane outerWrapper;
    private StackPane innerWrapper;
    private HBox layout;
    private Label actionText;
    private Label keyHint;
    private Label gestureText;
    private Timeline showTimeline;
    private Timeline hideTimeline;
    private List<KeyFrame> keyFrames;

    public HoverLabel(AnchorPane anchorPaneParent){
        this.parent = anchorPaneParent;
        outerWrapper = new StackPane();
        innerWrapper = new StackPane();
        layout = new HBox();
        actionText = new Label();
        keyHint = new Label();
        gestureText = new Label();

        showTimeline = new Timeline();
        hideTimeline = new Timeline();
        keyFrames = new ArrayList<>();


        layout.getChildren().addAll(gestureText, keyHint, actionText);
        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setSpacing(8);
        innerWrapper.getChildren().add(layout);
        outerWrapper.getChildren().add(innerWrapper);

        innerWrapper.setMaxHeight(layout.getHeight());

        outerWrapper.setPrefHeight(60);
        outerWrapper.setMinHeight(outerWrapper.getPrefHeight());
        outerWrapper.setMaxHeight(outerWrapper.getPrefHeight());

        parent.getChildren().add(outerWrapper);

        AnchorPane.setLeftAnchor(outerWrapper,100.0);
        AnchorPane.setRightAnchor(outerWrapper,100.0);
        AnchorPane.setTopAnchor(outerWrapper,0.0);

        keyHint.setStyle("-fx-border-color: white; -fx-padding: 2 5 2 5; -fx-border-width: 1; -fx-text-fill: white; -fx-font-family: 'Circular Std Book'; -fx-font-size: 22;");
        actionText.setStyle("-fx-text-fill: white; -fx-font-family: 'Circular Std Book'; -fx-font-size: 22;");
        gestureText.setStyle("-fx-text-fill: white; -fx-font-family: 'Circular Std Book'; -fx-font-size: 22;");

    }

    public void setup( String typeOfGesture, String keyToBePressed, String action){
        gestureText.setText(typeOfGesture);
        actionText.setText(action);
        keyHint.setText(keyToBePressed);
    }

    public void show(){
        keyFrames.clear();
        keyFrames.add(new KeyFrame(Duration.millis(250), new KeyValue(outerWrapper.opacityProperty(),1, Interpolator.EASE_BOTH)));
        showTimeline.getKeyFrames().addAll(keyFrames);
        showTimeline.play();
        showTimeline.setOnFinished(event -> {
            outerWrapper.opacityProperty().set(1);
        });
    }

    public void hide(){
        keyFrames.clear();
        keyFrames.add(new KeyFrame(Duration.millis(250), new KeyValue(outerWrapper.opacityProperty(),0, Interpolator.EASE_BOTH)));
        hideTimeline.getKeyFrames().addAll(keyFrames);
        hideTimeline.play();
        hideTimeline.setOnFinished(event -> {
            outerWrapper.opacityProperty().set(0);
        });
    }

}
