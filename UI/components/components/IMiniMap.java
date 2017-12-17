package UI.components.components;

import BLL.ACQ.IPlanet;

import java.util.Map;

public interface IMiniMap extends IComponent {



    // Methods

    void setSpaceshipCoordX(double coordX);
    void setSpaceshipCoordY(double coordY);

    void renderPlanets(Map<String, ? extends IPlanet> planets);

}
