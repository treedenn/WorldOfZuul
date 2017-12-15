package BLL.entity.npc;

import BLL.Game;
import BLL.entity.Entity;
import BLL.entity.npc.actions.NPCAction;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.entity.npc.actions.NPCDialogAction;
import BLL.entity.npc.actions.NPCTerminateAction;
import BLL.entity.player.Player;
import BLL.item.ItemStack;

/**
 * Contains all the functionality of the Professor Putricide NPC.
 * Professor Putricide can give the player a potion to transform.
 */
public class ProfessorPutricide extends Entity implements NPC {
    private NPCAction[] actions;

    /**
     * Constructs a new Professor Putricide NPC.
     */
    public ProfessorPutricide(){
        super();
        initActions();
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
    public NPCAction[] getActions() {
        return actions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActions(NPCActionCollection collection) {
        this.actions = collection.getActions();
    }

    private void initActions() {
        actions = new NPCAction[] {
                new NPCAction("G'day Rick. It's your old pal, the one and only Professor Putricide!" +
                        "I heard you're in some kind of trouble?"),
                new NPCDialogAction("I have invented this *hih* transformation elixir. Do you want it?") {
                    @Override
                    public void onEndEvent(NPC npc, Game game) {
                        super.onEndEvent(npc, game);

                        if(answerYes) {
                            ItemStack is = new ItemStack(game.getModel().getItemById(58), 1);

                            Player player = (Player) game.getPlayer();

                            if(!player.getInventory().contains(is)) {
                                player.getInventory().add(is);
                            }

                            game.setMessageToContainer(is.getItem().getName() + " has been added to your backpack");
                        } else {
                            setActionId(3);
                        }
                    }
                },
                new NPCTerminateAction("I have placed the elixir in your inventory!" +
                        "\n*blob* I nearly forgot! I recently met Gearhead (The Blacksmith), your buddy, he might still be on Xehna!" +
                        "\n... What made him go there? .. I *hih* .. forgot."),
                new NPCAction("... Till we meet *hih* again old friend!")
        };
    }
}