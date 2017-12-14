package BLL.entity.npc;

import BLL.ACQ.INPCAction;
import BLL.entity.Entity;
import BLL.entity.npc.actions.NPCActionCollection;

/**
 * Contains all the functionality of the Professor Putricide NPC.
 * Professor Putricide can give the player a potion to transform.
 */
public class ProfessorPutricide extends Entity implements NPC {
    private NPCActionCollection collection;

    /**
     * Constructs a new Professor Putricide NPC.
     */
    public ProfessorPutricide(){
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Professor Putricide";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGood() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public INPCAction[] getActions() {
        return collection.getActions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActions(NPCActionCollection collection) {
        this.collection = collection;
    }

}