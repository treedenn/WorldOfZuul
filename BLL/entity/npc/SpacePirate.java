package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.entity.MovableEntity;
import BLL.entity.npc.actions.NPCActionCollection;

import java.util.Timer;
import java.util.TimerTask;

public class SpacePirate extends MovableEntity implements NPC {
    private NPCActionCollection collection;

    private boolean canAttack;

    public SpacePirate() {
        canAttack = true;
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

    }
}