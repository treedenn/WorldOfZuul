package UI.deleteWhenTransitioneToNewGUI.controller.gamecomponents.subscene.gamemap;

import BLL.ACQ.IPlanet;
import java.util.Map;

public interface IMap {

    /** GameMap constants */
    double mapWidth = 8000;
    double mapHeight = 8000;


    /**
     * Method to visually render planets.
     * @param planets a Collection of type GameMap containing objects extending {@link IPlanet}
     */
    void renderPlanets(Map<String, ? extends IPlanet> planets);


}
