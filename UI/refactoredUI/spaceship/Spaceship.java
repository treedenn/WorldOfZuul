package UI.refactoredUI.spaceship;

import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IEventListener;
import UI.refactoredUI.components.ISpaceship;
import com.sun.istack.internal.Nullable;
import javafx.geometry.Point2D;

import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Spaceship extends Component implements ISpaceship {

    private List<IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>>> onMovementSubscribers = new ArrayList<>();

    private boolean accelerate, right, left, decelerate, reverse;
    private double accelerationTime, decelerationTime;
    private double speed, maxSpeed, accelerationRatio, decelerationRatio;
    private Point2D velocity;

    public Spaceship(){
        super("spaceship_view.fxml");
        maxSpeed = 10;
        accelerationRatio = 1500;
        decelerationRatio = 500;
        velocity = new Point2D(0,0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(double deltaTime) {
        if(accelerate && !decelerate) {
            accelerate(deltaTime);
        } else {accelerationTime = 0;}

        if(left && !right) getView().setRotate(getView().getRotate() - 5);

        if (right && !left) getView().setRotate(getView().getRotate() + 5);

        if(decelerate){
            decelerate(deltaTime);
        } else {decelerationTime = 0;}
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
        return accelerate;
    }

    /**
     * Method to
     * @param deltaTime
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
