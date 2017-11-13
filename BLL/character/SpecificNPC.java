package BLL.character;

import BLL.world.Planet;

import java.util.Map;

public abstract class SpecificNPC {
    private Planet currentPlanet;
    private Map<String, Planet> planets;

    public SpecificNPC() {
        this.currentPlanet = currentPlanet;
        this.planets = planets;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(Planet p) {
        this.currentPlanet = p;
    }
}
