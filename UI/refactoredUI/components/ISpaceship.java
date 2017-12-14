package UI.refactoredUI.components;

import java.util.AbstractMap;

public interface ISpaceship extends IComponent {

    // Events
    /**
     * Method to add an event listener.
     * Event to be fired when player / space ship gets updated coordinates.
     * Key is x, value is y.
     * @param listener  listener to be added.
     */
    void onMovement(IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>> listener);



    // Methods

    /**
     * Method to update rendering of spaceship.
     * @param deltaTime start of update.
     */
    void tick(double deltaTime);

    void setAccelerate(boolean state);
    void setDecelerate(boolean state);
    void setRotateLeft(boolean state);
    void setRotateRight(boolean state);

}
