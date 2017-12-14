package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.Game;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;
import BLL.item.ItemStack;

/**
 * Contains the actions of the {@link BLL.entity.npc.ProfessorPutricide} NPC.
 */
public class ProfessorPutricideAction implements NPCActionCollection {
    private INPCAction[] actions;

    /**
     * Constructs a new Professor Putricide action.
     */
    public ProfessorPutricideAction() {
        actions = new INPCAction[] {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public INPCAction[] getActions() {
        return actions;
    }
}
