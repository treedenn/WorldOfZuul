package UI.deleteWhenTransitioneToNewGUI.controller;

/**
 * Describes fundamental functionality for the game loop in GUI.
 */
public interface IGameLoop {

    /**
     * Method
     */
    void tick();

    /**
     * Method to be invoked to land on a planet.
     */
    void landOnPlanet();

}
