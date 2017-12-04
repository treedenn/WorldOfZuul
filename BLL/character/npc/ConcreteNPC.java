package BLL.character.npc;

import BLL.ACQ.INPCAction;
import BLL.character.npc.actions.NPCActionCollection;
import BLL.world.Planet;

import java.util.Map;

/**
 * An concrete NPC object, the base of all NPCs.
 */
public abstract class ConcreteNPC implements NPC {
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

    @Override
    public INPCAction[] getActions() {
        return actionCollection.getActions();
    }

    @Override
    public void setActions(NPCActionCollection actions) {
        this.actionCollection = actions;
    }
}
