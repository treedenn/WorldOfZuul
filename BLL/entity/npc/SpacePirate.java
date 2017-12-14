package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.GameUtility;
import BLL.entity.MovableEntity;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.world.Planet;

import java.util.Timer;
import java.util.TimerTask;

public class SpacePirate extends MovableEntity implements NPC {
    private NPCActionCollection collection;

    private boolean canAttack;
    private int moveCounter;

    public SpacePirate() {
        canAttack = true;
        moveCounter = 0;
    }

    public boolean canAttack() {
        return canAttack;
    }

    @Override
    public int getId() {
        return 3;
    }

    @Override
    public String getName() {
        return "GameMap Pirate";
    }

    @Override
    public boolean isGood() { return false; }

    @Override
    public INPCAction[] getActions() {
        return collection.getActions();
    }

    @Override
    public void setActions(NPCActionCollection actions) {
        collection = actions;
    }

    public void attack() {
        canAttack = false;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canAttack = true;
            }
        }, 300000); // 300 seconds
    }

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