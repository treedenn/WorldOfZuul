package UI.refactoredUI.hamburger;

import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IEventListener;
import UI.refactoredUI.components.IHamburger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Hamburger extends Component implements IHamburger {

    private List<IEventListener> onOpenNavSubscribers = new ArrayList<>();

    @FXML
    private Rectangle hamburgerRectangel1;

    @FXML
    private Rectangle hamburgerRectangel2;

    @FXML
    private Rectangle hamburgerRectangel3;

    public Hamburger(){super("hamburger_view.fxml");}

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
    public void onOpenNav(IEventListener listener) {
        onOpenNavSubscribers.add(listener);
    }

    @FXML
    void openNav(ActionEvent event) {
        onOpenNavSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void hover(MouseEvent event) {
        animateRectangleWidth(hamburgerRectangel1, 16);
        animateRectangleWidth(hamburgerRectangel2, 24);
        animateRectangleWidth(hamburgerRectangel3, 19);
    }

    @FXML
    void leave(MouseEvent event) {
        animateRectangleWidth(hamburgerRectangel1, 21);
        animateRectangleWidth(hamburgerRectangel2, 16);
        animateRectangleWidth(hamburgerRectangel3, 24);
    }

    /**
     * Method to animate hamburger button.
     * @param r rectangle line of hamburger button.
     * @param endWidth  animated to this width.
     */
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
