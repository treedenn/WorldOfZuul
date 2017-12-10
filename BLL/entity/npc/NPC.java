package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.entity.npc.actions.NPCActionCollection;

import java.io.File;

/**
 * Describes the fundamentals an NPC need.
 */
public interface NPC {
    /**
     * Gets the id of the NPC.
     * @return id
     */
    int getId();

    /**
     * Gets the name of the NPC.
     * @return name of NPC
     */
    String getName();

    /**
     * Gets the iamge of the NPC
     */
    File getImage();

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
     * @param collection object as {@link NPCActionCollection}
     */
    void setActions(NPCActionCollection collection);

    //What else?
}
