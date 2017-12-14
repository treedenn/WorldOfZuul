package UI.refactoredUI.spaceship;

import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IEventListener;
import UI.refactoredUI.components.ISpaceship;
import javafx.geometry.Point2D;

import java.net.URL;
import java.util.AbstractMap;
import java.util.List;
import java.util.ResourceBundle;

public class Spaceship extends Component implements ISpaceship {

    private List<IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>>> onMovementSubscribers;

    private boolean accelerate, right, left, decelerate;
    private double accelerationTime, decelerationTime;
    private double speed, maxSpeed, accelerationRatio, decelerationRatio;
    private Point2D velocity;

    public Spaceship(){super("spaceship_view.fxml");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(double deltaTime) {


        getView().setTranslateX(getView().getTranslateX() + velocity.getX());

        getView().setTranslateY(getView().getTranslateY() + velocity.getY());

        if(accelerate && !decelerate) {
            accelerate(deltaTime);
        } else {accelerationTime = 0;}

        if(left) getView().setRotate(getView().getRotate() - 5);

        if (right) getView().setRotate(getView().getRotate() + 5);

        if(decelerate){
            decelerate(deltaTime);
        } else {decelerationTime = 0;}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccelerate(boolean state) {
        accelerate = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDecelerate(boolean state) {
        decelerate = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRotateLeft(boolean state) {
        left = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRotateRight(boolean state) {
        right = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onMovement(IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>> listener) {
        onMovementSubscribers.add(listener);
    }

    /**
     * Method to
     * @param deltaTime
     */
    private void accelerate(double deltaTime){

        if(accelerationTime == 0){
            accelerationTime = deltaTime;
        }
        speed = (accelerationRatio * ((deltaTime- accelerationTime)/10e9))/(1.1*speed+1);

        if(speed >= maxSpeed){
            speed = maxSpeed;
        }

        velocity = new Point2D(Math.cos(Math.toRadians(getView().getRotate())) * speed, Math.sin(Math.toRadians(getView().getRotate())) * speed);

        onMovementSubscribers.forEach(listener -> listener.onAction(new AbstractMap.SimpleImmutableEntry<Double, Double>(getView().getTranslateX() + velocity.getX(), getView().getTranslateY() + velocity.getY())));

    }

    private void decelerate(double deltaTime){

    }

}
