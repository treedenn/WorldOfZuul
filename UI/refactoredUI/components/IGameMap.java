package UI.refactoredUI.components;

import BLL.ACQ.IPlanet;
import com.sun.istack.internal.Nullable;
import javafx.scene.SubScene;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public interface IGameMap extends IComponent {

    double mapWidth = 8000;
    double mapHeight = 8000;

    // Methods

    /**
     * Method to render game map.
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

    void rotateSpaceshipLeft(boolean state);

    void rotateSpaceshipRight(boolean state);

    void accelerateSpaceship(boolean state, @Nullable Boolean reverse);

    void decelerateSpaceship(boolean state);

    /**
     * Returns the x coordinate of the space ship component.
     * @return  x coordinate.
     */
    void setSpaceshipCoordX(double coordX);

    /**
     * Returns the y coordinate of the space ship component.
     * @return  y coordinate.
     */
    void setSpaceshipCoordY(double coordY);

    /**
     * Returns the space ship component.
     * @return
     */
    ISpaceship getSpaceship();

    /**
     * Method to a list of rendered globes on map.
     * @return  list of objects of type {@link IGlobe}.
     */
    List<IGlobe> getPlanetsOnGameMap();


}
