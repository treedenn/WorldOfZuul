package UI.deleteWhenTransitioneToNewGUI.controller.gamecomponents;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Drawer {

    private GridPane drawer;
    private Node overlay;
    private Timeline showTimeline;
    private Timeline hideTimeline;
    private double standbyPosition;
    private List<KeyFrame> keyFrames;

    public Drawer(Node drawer, Node overlay){
        this.drawer = (GridPane) drawer;
        this.overlay = overlay;
        showTimeline = new Timeline();
        hideTimeline = new Timeline();
        keyFrames = new ArrayList<>();
        hideDrawer();
    }

    public void showDrawer(){

        keyFrames.clear();
        keyFrames.add(new KeyFrame(Duration.millis(2), new KeyValue(drawer.visibleProperty(), true), new KeyValue(overlay.visibleProperty(), true)));
        keyFrames.add(new KeyFrame(Duration.millis(280), new KeyValue(overlay.opacityProperty(), 1, Interpolator.EASE_BOTH), new KeyValue(drawer.translateXProperty(), 0,Interpolator.EASE_BOTH)));

        showTimeline.getKeyFrames().addAll(keyFrames);

        showTimeline.play();
    }


    public void hideDrawer(){

        keyFrames.clear();
        keyFrames.add(new KeyFrame(Duration.millis(220), new KeyValue(overlay.opacityProperty(), 0,Interpolator.EASE_BOTH), new KeyValue(drawer.translateXProperty(), 0 - drawer.getWidth(), Interpolator.EASE_BOTH)));
        keyFrames.add(new KeyFrame(Duration.millis(2), new KeyValue(drawer.visibleProperty(), false), new KeyValue(overlay.visibleProperty(), false)));

        hideTimeline.getKeyFrames().addAll(keyFrames);

        hideTimeline.play();
    }






}
