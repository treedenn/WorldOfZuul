package UI.gamecomponents;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class GameObject {

    private Node view;
    private Point2D velocity;


    public GameObject(Node view){
        this.view = view;
    }

    public void update(double dt){}

    public void rotateRight(){
        view.setRotate(view.getRotate() + 5);
    }

    public void rotateLeft(){
        view.setRotate(view.getRotate() - 5);
    }

    public static void addGameObject(GameObject o, double x, double y, Pane root){
        o.getView().setTranslateY(y);
        o.getView().setTranslateX(x);
        root.getChildren().add(o.getView());
    }

    public boolean isColliding(GameObject other){
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }


    /* GETTER */
    public Node getView() {
        return view;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public double getRotate(){
        return view.getRotate();
    }

    public Point2D getVelocity() {
        return velocity;
    }
}
