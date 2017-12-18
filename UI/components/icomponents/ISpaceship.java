package UI.components.icomponents;

import com.sun.istack.internal.Nullable;

import java.util.AbstractMap;

/**
 * This interface defines the contract between components of type {@link ISpaceship} and a parent controller.
 */
public interface ISpaceship extends IComponent {

    /**
     * Method to add an event listener.
     * Event to be fired when player / space ship gets updated coordinates.
     * Key is x, value is y.
     * @param listener  listener to be added.
     */
    void onMovement(IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>> listener);

    /**
     * Method to update rendering of spaceship.
     * @param deltaTime time value holding the start time of the method invocation.
     */
    void tick(double deltaTime);

    /**
     * Method to control the movement of the spaceship.
     * @param state true if the spaceship is accelerating.
     * @param reverse   true if the spaceship is reversing.
     */
    void setAccelerate(boolean state, @Nullable Boolean reverse);

    /**
     * Method to control the movement of the spaceship.
     * @param state true if the spaceship is decelerating.
     */
    void setDecelerate(boolean state);

    /**
     * Method to control the rotation of the spaceship.
     * @param state true if spaceships rotates left.
     */
    void setRotateLeft(boolean state);

    /**
     * Method to control the rotation of the spaceship.
     * @param state true if spaceships rotates right.
     */
    void setRotateRight(boolean state);

    /**
     * Return true if space ship is moving.
     * @return
     */
    boolean isMoving();

    /**
     * Accessor method for the attribute moveable.
     * @param moveable  true if the spaceship is allowed to move.
     */
    void setMoveable(boolean moveable);

}
