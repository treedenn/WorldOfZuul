package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.entity.Entity;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.world.Planet;

public class SpacePirate extends Entity implements NPC {
    private NPCActionCollection collection;

    public SpacePirate() {

    }

    public String[] getPirateMsg() {
        //TODO implement x,y coordiantes to initiate get piratemsg
        return new String[]{

                "you have been intercepted and captured by space pirates",
                "Would you like to pay the ransom to them in order to proceed on your voyage?",
                "Answer Y/N"


        };

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
}