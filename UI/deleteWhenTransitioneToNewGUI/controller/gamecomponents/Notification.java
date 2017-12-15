package UI.deleteWhenTransitioneToNewGUI.controller.gamecomponents;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class Notification{

    private Node notification;
    private Timeline showTimeline;
    private Timeline hideTimeline;
    private double standbyPosition;
    private List<KeyFrame> keyFrames;
    private Label notificationTitle;
    private Label notificationText;

    public Notification(Node node, Label notificationTitle, Label notificationText, double standbyYPosition){
        this.notification = node;
        notification.setVisible(false);
        this.standbyPosition = standbyYPosition;
        this.notificationTitle = notificationTitle;
        this.notificationText = notificationText;
        keyFrames = new ArrayList<>();
        showTimeline = new Timeline();
        hideTimeline = new Timeline();
        notificationTitle.setText("Spaceship AI:");
        hideNotification();
    }

    public void loadNotification(String message){
        notificationText.setText(message);
    }

    public void showNotification(double translateY){
        if(!notification.visibleProperty().getValue()){
            notification.setVisible(true);
            double millisBeforeAutohide = 4500;

            keyFrames.clear();
            keyFrames.add(new KeyFrame(Duration.millis(450), new KeyValue(notification.translateYProperty(),translateY - 20, Interpolator.EASE_BOTH)));
            keyFrames.add(new KeyFrame(Duration.millis(530), new KeyValue(notification.translateYProperty(),translateY + 15, Interpolator.EASE_BOTH)));
            keyFrames.add(new KeyFrame(Duration.millis(600), new KeyValue(notification.translateYProperty(),translateY, Interpolator.EASE_BOTH)));
            keyFrames.add(new KeyFrame(Duration.millis(millisBeforeAutohide)));

            showTimeline.getKeyFrames().addAll(keyFrames);
            showTimeline.play();
            showTimeline.setOnFinished(event -> {
                notification.translateYProperty().set(translateY);
                hideNotification();
            });
        }

    }

    public void hideNotification(){
        showTimeline.stop();
        double offset = -100;

        keyFrames.clear();
        keyFrames.add(new KeyFrame(Duration.millis(50), new KeyValue(notification.translateYProperty(),notification.translateYProperty().doubleValue() - 20, Interpolator.EASE_BOTH)));
        keyFrames.add(new KeyFrame(Duration.millis(400), new KeyValue(notification.translateYProperty(), standbyPosition, Interpolator.EASE_BOTH)));

        hideTimeline.getKeyFrames().addAll(keyFrames);
        hideTimeline.play();
        hideTimeline.setOnFinished(event -> notification.setVisible(false));
    }

    public Node getNotification() {
        return notification;
    }

}
