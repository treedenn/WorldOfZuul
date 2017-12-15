package UI.refactoredUI.components;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ComponentLoader {

    private static Timeline timeline;
    private static List<KeyFrame> keyframes;


    /**
     * Method to load an component into a new parent of type {@link AnchorPane}.
     * @param newParent node to load component into.
     * @param component component to be loaded.
     * @param animated true if component's entrance is animated.
     */

    /**
     * Method to load an component into a new parent of type {@link AnchorPane}.
     * @param newParent node to load component into.
     * @param component component to be loaded.
     * @param topAnchor -1 to unset
     * @param rightAnchor -1 to unset
     * @param bottomAnchor -1 to unset
     * @param leftAnchor -1 to unset
     * @param animated  true if component's entrance is animated.
     */
    public static void loadComponent(AnchorPane newParent, Parent component, double topAnchor, double rightAnchor, double bottomAnchor, double leftAnchor, boolean animated){
        if(!newParent.getChildren().contains(component)) newParent.getChildren().add(component);
        if(topAnchor != -1) newParent.setTopAnchor(component, topAnchor);
        if(rightAnchor != -1) newParent.setRightAnchor(component, rightAnchor);
        if(bottomAnchor != -1) newParent.setBottomAnchor(component, bottomAnchor);
        if(leftAnchor != -1) newParent.setLeftAnchor(component, leftAnchor);
        if (animated) animateEntrance(newParent, component);
    }

    /**
     * Method to load an component int oa new parent of type {@link GridPane}.
     * @param newParent node to load component into.
     * @param component component to be loaded.
     * @param row   row index of parent.
     * @param col   colmn index of parent.
     * @param animated  true if the component's entrance is animated
     */
    public static void loadComponent(GridPane newParent, Parent component, int row, int col, boolean animated){
        if(!newParent.getChildren().contains(component)) newParent.getChildren().add(component);
        newParent.setConstraints(component,col, row);
        if (animated) animateEntrance(newParent, component);
    }

    /**
     * Method to load an component int oa new parent of type {@link Pane}.
     * @param newParent node to load component into.
     * @param component component to be loaded.
     */
    public static void loadComponent(Pane newParent, Parent component){
        newParent.getChildren().add(component);
    }

    /**
     * Method to load an component int oa new parent of type {@link Pane}.
     * This overloaded method also accepts coordinates for translating the component.
     * @param newParent node to load component into.
     * @param component component to be loaded.
     * @param coordX    translate X
     * @param coordY    translate Y
     */
    public static void loadComponent(Pane newParent, Parent component, double coordX, double coordY){
        if(!newParent.getChildren().contains(component)) newParent.getChildren().add(component);
        component.setTranslateX(coordX);
        component.setTranslateY(coordY);
    }

    /**
     * Method to load an component int oa new parent of type {@link Pane}.
     * This overloaded method also accepts coordinates for translating the component.
     * @param newParent node to load component into.
     * @param node component to be loaded.
     * @param coordX    translate X
     * @param coordY    translate Y
     */
    public static void loadComponent(Pane newParent, Node node, double coordX, double coordY){
        if(!newParent.getChildren().contains(node)) newParent.getChildren().add(node);
        node.setTranslateX(coordX);
        node.setTranslateY(coordY);
    }

    /**
     * Method to remove an component from a parent.
     * @param component node to be removed.
     */
    public static boolean removeComponent(Parent component){
        Pane parent = (Pane) component.getParent();
        animateExit(parent, component);
        return true;
    }

    private static void animateEntrance(Pane parent, Parent component){
        component.setOpacity(0);
        component.getChildrenUnmodifiable().get(0).translateYProperty().set(-100);
        timeline = new Timeline();
        keyframes = new ArrayList<>();
        keyframes.add(new KeyFrame(Duration.millis(330), new KeyValue(component.opacityProperty(),1, Interpolator.EASE_BOTH)));
        keyframes.add(new KeyFrame(Duration.millis(330), new KeyValue(component.getChildrenUnmodifiable().get(0).translateYProperty(),0, Interpolator.EASE_BOTH)));
        timeline.getKeyFrames().addAll(keyframes);
        timeline.playFromStart();
    }

    private static void animateExit(Pane parent, Parent component){
            timeline.stop();
            component.setOpacity(1);
            timeline = new Timeline();
            keyframes = new ArrayList<>();
            keyframes.add(new KeyFrame(Duration.millis(450), new KeyValue(component.opacityProperty(), 0, Interpolator.EASE_BOTH)));
            keyframes.add(new KeyFrame(Duration.millis(450), new KeyValue(component.getChildrenUnmodifiable().get(0).translateYProperty(), -100, Interpolator.EASE_BOTH)));
            timeline.getKeyFrames().addAll(keyframes);
            timeline.play();
            timeline.setOnFinished(event -> {
                if(parent != null && parent.getChildren().contains(component)) {
                    parent.getChildren().remove(component);
                }
            });

    }


}
