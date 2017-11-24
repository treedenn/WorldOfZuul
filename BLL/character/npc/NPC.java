package BLL.character.npc;

import BLL.ACQ.INPCAction;
import BLL.character.npc.actions.NPCAction;
import BLL.character.npc.actions.NPCActionCollection;

public interface NPC {
    String getName();
    boolean isGood();
    INPCAction[] getActions();
    void setActions(NPCActionCollection actions);

    //What else?
}
