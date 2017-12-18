package UI.components.spaceship;

import UI.components.icomponents.Component;
import UI.components.icomponents.IEventListener;
import UI.components.icomponents.ISpaceship;
import com.sun.istack.internal.Nullable;
import javafx.geometry.Point2D;

import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This concrete GUI handles the spaceship view.
 */
public class Spaceship extends Component implements ISpaceship {

    /** List of observers */
    private List<IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>>> onMovementSubscribers = new ArrayList<>();

    /** When this attribute is true the spaceship cannot move. */
    private boolean isMoveable;
    /** Each boolean attribute is true if the player is performing the action. */
    private boolean accelerate, right, left, decelerate, reverse;
    /** These attributes sets the speed of acceleration and deceleration. */
    private double accelerationTime, decelerationTime;
    /** These attributes holds the spaceship's speed as well as restrictions to this. */
    private double speed, maxSpeed, accelerationRatio, decelerationRatio;
    /** This Point2D holds the spaceship velocity in x and y direction. */
    private Point2D velocity;

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public Spaceship(){
        super("spaceship_view.fxml");
        maxSpeed = 10;
        accelerationRatio = 1500;
        decelerationRatio = 500;
        velocity = new Point2D(0,0);
        isMoveable = true;
    }

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
    public void tick(double deltaTime) {
        if(isMoveable) {
            if (accelerate && !decelerate) {
                accelerate(deltaTime);
            } else {
                accelerationTime = 0;
            }

            if (left && !right) getView().setRotate(getView().getRotate() - 5);

            if (right && !left) getView().setRotate(getView().getRotate() + 5);

            if (decelerate) {
                decelerate(deltaTime);
            } else {
                decelerationTime = 0;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccelerate(boolean state, @Nullable Boolean reverse) {
        accelerate = state;
        this.reverse = reverse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDecelerate(boolean state) {decelerate = state;}

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRotateLeft(boolean state) {
        left = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRotateRight(boolean state) {
        right = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onMovement(IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>> listener) {
        onMovementSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoving() {
        return isMoveable && accelerate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMoveable(boolean moveable) {
        isMoveable = moveable;
    }

    /**
     * Method to simulate real acceleration of the spaceship.
     * @param deltaTime the time of the method invocation.
     */
    private void accelerate(double deltaTime){
        if(accelerationTime == 0){
            accelerationTime = deltaTime;
        }

        if(reverse && !decelerate){
            speed = (accelerationRatio * ((deltaTime - accelerationTime)/10e9))/(1.1*-speed+1);
            speed = speed >= -maxSpeed*.6 ? -maxSpeed*.6 : speed;
        } else{
            speed = (accelerationRatio * ((deltaTime - accelerationTime)/10e9))/(1.1*speed+1);
            speed = speed >= maxSpeed ? maxSpeed : speed;
        }



        velocity = new Point2D(Math.cos(Math.toRadians(getView().getRotate())) * speed, Math.sin(Math.toRadians(getView().getRotate())) * speed);

        onMovementSubscribers.forEach(listener -> listener.onAction(new AbstractMap.SimpleImmutableEntry<Double, Double>(getView().getTranslateX() + velocity.getX(), getView().getTranslateY() + velocity.getY())));

    }

    /**
     * Method to simulate real deceleration of the spaceship.
     * @param deltaTime the time of the method invocation.
     */
    private void decelerate(double deltaTime){
        if(decelerationTime == 0){
            decelerationTime = deltaTime;
        }
        speed -= ((decelerationRatio * ((deltaTime- decelerationTime)/10e9))/(1.1*speed+1));

        if(speed <= 0){
            speed = 0;
            decelerate = false;
        }

        velocity = new Point2D(Math.cos(Math.toRadians(getView().getRotate())) * speed, Math.sin(Math.toRadians(getView().getRotate())) * speed);
        onMovementSubscribers.forEach(listener -> listener.onAction(new AbstractMap.SimpleImmutableEntry<Double, Double>(getView().getTranslateX() + velocity.getX(), getView().getTranslateY() + velocity.getY())));


    }

}
