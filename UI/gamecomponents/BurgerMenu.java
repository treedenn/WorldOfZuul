package UI.gamecomponents;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BurgerMenu {

    private Button button;
    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;

    public BurgerMenu(Button button, Rectangle r1, Rectangle r2, Rectangle r3){
        this.button = button;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }

    public void burgerMenuHover(){
        animateRectangleWidth(r1, 16);
        animateRectangleWidth(r2, 24);
        animateRectangleWidth(r3, 19);
    }

    public void burgerMenuNormal(){
        animateRectangleWidth(r1, 21);
        animateRectangleWidth(r2, 16);
        animateRectangleWidth(r3, 24);
    }

    private void animateRectangleWidth(Rectangle r, double endWidth){

        Timeline timeline = new Timeline();

        List<KeyFrame> keyFrames = new ArrayList<>();
        double currentWidth = r.getWidth();
        double bounceSize = 2;
        double startBounce, endBounce;

        if(currentWidth < endWidth){
            startBounce = currentWidth - bounceSize;
            endBounce = endWidth + bounceSize;
        } else{
            startBounce = currentWidth + bounceSize;
            endBounce = endWidth - bounceSize;
        }

        keyFrames.add(new KeyFrame(Duration.millis(50), new KeyValue(r.widthProperty(),startBounce, Interpolator.EASE_BOTH)));
        keyFrames.add(new KeyFrame(Duration.millis(180), new KeyValue(r.widthProperty(), endBounce, Interpolator.EASE_BOTH)));
        keyFrames.add(new KeyFrame(Duration.millis(230), new KeyValue(r.widthProperty(),endWidth, Interpolator.EASE_BOTH)));
        timeline.getKeyFrames().addAll(keyFrames);
        timeline.play();

    }

}
