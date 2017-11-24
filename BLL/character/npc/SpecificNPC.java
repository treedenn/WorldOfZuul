package BLL.character.npc;

import BLL.character.npc.actions.NPCActions;
import BLL.world.Planet;

import java.util.Map;

public abstract class SpecificNPC {
    private Planet currentPlanet;
    private Map<String, Planet> planets;
    private NPCActions actions;

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

    public NPCAction[] getActions() {
        return actions.getActions();
    }

    public void setActions(NPCActions actions) {
        this.actions = actions;
    }
}
