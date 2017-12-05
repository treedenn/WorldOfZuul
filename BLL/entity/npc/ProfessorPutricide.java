package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.entity.Entity;
import BLL.entity.npc.actions.NPCActionCollection;

public class ProfessorPutricide extends Entity implements NPC {
    public ProfessorPutricide(){
        super();
    }

    @Override
    public String getName() {
        return "Professor Putricide";
    }

    @Override
    public boolean isGood() {
        return true;
    }

    @Override
    public INPCAction[] getActions() {
        return new INPCAction[0];
    }

    @Override
    public void setActions(NPCActionCollection actions) {

    }
}