package BLL.character.npc;

import BLL.ACQ.INPCAction;
import BLL.character.npc.actions.NPCAction;
import BLL.character.npc.actions.NPCActionCollection;
import BLL.world.Planet;

import java.util.Map;

public abstract class ConcreteNPC {
    private Planet currentPlanet;
    private Map<String, Planet> planets;
    private NPCActionCollection actionCollection;

    ConcreteNPC() {
        this.currentPlanet = currentPlanet;
        this.planets = planets;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(Planet p) {
        this.currentPlanet = p;
    }

    public INPCAction[] getActions() {
        return actionCollection.getActions();
    }

    public void setActions(NPCActionCollection actions) {
        this.actionCollection = actions;
    }
}
