package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.GameUtility;
import BLL.entity.MovableEntity;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.world.Planet;

import java.util.Collections;
import java.util.Iterator;
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
        return "Space Pirate";
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

            Iterator<NPC> iterator = getCurrentPlanet().getNPCs().iterator();

            // TODO: LOOKS AT THIS BULLSHIT>>>>>>>>>>>>LADMJKASDJAS LJLK
//            while(iterator.hasNext()) {
//                NPC npc = iterator.next();
//
//                if(npc instanceof SpacePirate) {
//                    iterator.remove();
//                }
//            }

            setCurrentPlanet(GameUtility.getRandomPlanetNotXehna(getPlanets().values().toArray(new Planet[getPlanets().size()])));
            getCurrentPlanet().getNPCs().add(this);

            System.out.println("Pirate: " + getCurrentPlanet().getName());
        }
    }
}