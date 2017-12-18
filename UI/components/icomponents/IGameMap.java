package UI.components.icomponents;

import BLL.ACQ.IPlanet;
import com.sun.istack.internal.Nullable;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * This interface defines the contract between components of type {@link IGameMap} and a parent controller.
 */
public interface IGameMap extends IComponent {

    /** Constant defining the width of the game map. */
    double mapWidth = 8000;
    /** Constant defining the height of the game map. */
    double mapHeight = 8000;

    /**
     * The tick method is used to render and update components.
     * The interval between each tick is based on the parent controller's tick rate.
     * @param deltaTime a time value passed from the parent.
     */
    void tick(double deltaTime);

    /**
     * Method to render ui representations of planets.
     * @param planets   map of planets.
     */
    void renderPlanets(Map<String, ? extends IPlanet> planets);

    /**
     * Method to add an event listener.
     * Event to be fired when player / space ship gets updated coordinates.
     * Key is x, value is y.
     * @param listener  listener to be added.
     */
    void onMovement(IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>> listener);


    /**
     * Method to center viewport around player and keep player in map.
     * @param coordX    x coordinate of player.
     * @param coordY    y coordinate of player.
     */
    void keepWithinBoundaries(double coordX, double coordY);

    /**
     * Method to control the rotation of the spaceship.
     * @param state true if spaceships rotates left.
     */
    void rotateSpaceshipLeft(boolean state);

    /**
     * Method to control the rotation of the spaceship.
     * @param state true if spaceships rotates right.
     */
    void rotateSpaceshipRight(boolean state);

    /**
     * Method to control the movement of the spaceship.
     * @param state true if the spaceship is accelerating.
     * @param reverse   true if the spaceship is reversing.
     */
    void accelerateSpaceship(boolean state, @Nullable Boolean reverse);

    /**
     * Method to control the movement of the spaceship.
     * @param state true if the spaceship is decelerating.
     */
    void decelerateSpaceship(boolean state);

    /**
     * Accessor method to the x coordinate of the space ship component.
     * @return  x coordinate.
     */
    void setSpaceshipCoordX(double coordX);

    /**
     * Accessor to the y coordinate of the space ship component.
     * @return  y coordinate.
     */
    void setSpaceshipCoordY(double coordY);

    /**
     * Accessor method to the space ship component.
     * @return
     */
    ISpaceship getSpaceship();

    /**
     * Accessor method to the list of rendered globes on map.
     * @return  list of objects of type {@link IGlobe}.
     */
    List<IGlobe> getPlanetsOnGameMap();


}
