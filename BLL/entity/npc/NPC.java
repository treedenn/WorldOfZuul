package BLL.entity.npc;

import BLL.entity.npc.actions.NPCAction;
import BLL.entity.npc.actions.NPCActionCollection;

import java.io.File;

/**
 * Describes the fundamentals of an NPC.
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
     * Currently, it tells whether you can talk to the NPC or not.
     * Good NPCs can be talked to, whereas not good NPCs, will contract the player.
     * @return true, if good
     */
    boolean isGood();

    /**
     * Gets the actions of the NPC as {@link NPCAction}.
     * @return Array of actions as INPCAction
     */
    NPCAction[] getActions();

    /**
     * Sets the actions of the NPC.
     * @param collection an object implementing {@link NPCActionCollection}
     */
    void setActions(NPCActionCollection collection);
}
