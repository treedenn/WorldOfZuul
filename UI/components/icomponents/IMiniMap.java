package UI.components.icomponents;

import BLL.ACQ.IPlanet;

import java.util.Map;

/**
 * This interface defines the contract between components of type {@link IMiniMap} and a parent controller.
 */
public interface IMiniMap extends IComponent {

    /**
     * Mutator method for the x coordinate of the spaceship.
     * @param coordX
     */
    void setSpaceshipCoordX(double coordX);

    /**
     * Mutator method for the y coordinate of the spaceship.
     * @param coordY
     */
    void setSpaceshipCoordY(double coordY);

    /**
     * Method to render ui representations of planets on the minimap.
     * @param planets map of planets.
     */
    void renderPlanets(Map<String, ? extends IPlanet> planets);

}
