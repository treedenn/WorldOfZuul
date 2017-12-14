package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.GameUtility;
import BLL.entity.MovableEntity;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.world.Planet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * SpacePirate is an NPC that attacks the player when the player is moving towards its destination.
 */
public class SpacePirate extends MovableEntity implements NPC {
    private NPCActionCollection collection;

    private boolean canAttack;
    private int moveCounter;

    /**
     * Instantiates a new pirate.
     */
    public SpacePirate() {
        canAttack = true;
        moveCounter = 0;
    }

    /**
     * Attacks by settings the canAttack variable to false.
     * In addition it creates a timer.
     */
    public void attack() {
        canAttack = false;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canAttack = true;
            }
        }, 300000); // 300 seconds
    }

    /**
     * Returns a value based on the pirate can attack
     * @return true, if it can attack
     */
    public boolean canAttack() {
        return canAttack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return 3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Space Pirate";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGood() { return false; }

    /**
     * {@inheritDoc}
     */
    @Override
    public INPCAction[] getActions() {
        return collection.getActions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActions(NPCActionCollection actions) {
        collection = actions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        moveCounter++;

        if(moveCounter == 3) {
            moveCounter = 0;

            getCurrentPlanet().getNPCs().remove(this);
            setCurrentPlanet(GameUtility.getRandomPlanetNotXehna(getPlanets().values().toArray(new Planet[getPlanets().size()])));
            getCurrentPlanet().getNPCs().add(this);
        }
    }
}