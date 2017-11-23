package UI.GameComponents;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Player extends GameObject {

    private boolean accelerate, right, left, decelerate;
    private double accelerationTime;
    private double decelerationTime;
    private double maxSpeed = 10;
    private double speed = 0;
    double acceleration = 1500;
    double deceleration = 500;

    double width, height;
    ImageView img = new ImageView("./UI/resources/img/spaceShip-01.png");


    public Player(double width, double height, Pane pane){
        super(pane);
        this.width = width;
        this.height = height;

    }

    @Override
    public void update(double dt) {
        if(accelerate && !decelerate){
            accelerate(dt);
        } else {
            accelerationTime = 0;
        }

        if(left){
            rotateLeft();
        }

        if(right){
            rotateRight();
        }
        if(decelerate){
            decelerate(dt);
        } else{
            decelerationTime = 0;
        }
    }

    public void accelerate(double dt){

        if(accelerationTime == 0){
            accelerationTime = dt;
        }
        speed = (acceleration * ((dt- accelerationTime)/10e9))/(1.1*speed+1);

        if(speed >= maxSpeed){
            speed = maxSpeed;
        }

        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())) * speed, Math.sin(Math.toRadians(getRotate())) * speed));

        getView().setTranslateX(getView().getTranslateX() + getVelocity().getX());

        getView().setTranslateY(getView().getTranslateY() + getVelocity().getY());

    }

    public void decelerate(double dt){

        if(decelerationTime == 0){
            decelerationTime = dt;
        }

        speed -= ((deceleration * ((dt- decelerationTime)/10e9))/(1.1*speed+1));

        if(speed <= 0){
            speed = 0;
            decelerate = false;
        }

        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())) * speed, Math.sin(Math.toRadians(getRotate())) * speed));

        getView().setTranslateX(getView().getTranslateX() + getVelocity().getX());

        getView().setTranslateY(getView().getTranslateY() + getVelocity().getY());

    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setAccelerate(boolean accelerate) {
        this.accelerate = accelerate;
    }

    public void setDecelerate(boolean decelerate) {
        this.decelerate = decelerate;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
