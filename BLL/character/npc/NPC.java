package BLL.character.npc;

import BLL.ACQ.INPCAction;
import BLL.character.npc.actions.NPCActionCollection;

/**
 * Describes the fundamentals an NPC need.
 */
public interface NPC {
    /**
     * Gets the name of the NPC.
     * @return name of NPC
     */
    String getName();

    /**
     * Returns whether the NPC is good or bad.
     * @return true, if good
     */
    boolean isGood();

    /**
     * Gets the actions of the NPC as {@link INPCAction}.
     * @return Array of actions as INPCAction
     */
    INPCAction[] getActions();

    /**
     * Sets the actions of the NPC.
     * @param actions object as {@link NPCActionCollection}
     */
    void setActions(NPCActionCollection actions);

    //What else?
}
