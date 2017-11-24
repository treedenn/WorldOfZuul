package BLL.character.npc;

import BLL.character.npc.actions.NPCActions;

public interface NPC {
    String getName();
    boolean isGood();
    NPCAction[] getActions();
    void setActions(NPCActions actions);

    //What else?
}
