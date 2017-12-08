package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.PersistenceLayer;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;
import BLL.item.ItemStack;

public class ProfessorPutricideAction implements NPCActionCollection {
    private INPCAction[] actions;

    public ProfessorPutricideAction() {
        actions = new INPCAction[] {
            new NPCAction("G'day Rick. It's your old pal, the one and only Professor Putricide!" +
                    "\nI heard you're in some kind of trouble?"),
            new NPCDialogAction("I've invented this transformation elixir. Do you want it?") {
                @Override
                public void onEndEvent(Player player, NPC npc, PersistenceLayer persistenceLayer) {
                    super.onEndEvent(player, npc, persistenceLayer);

                    if(answerYes) {
                        ItemStack is = new ItemStack(persistenceLayer.getItemById(58), 1);

                        if(!player.getInventory().contains(is)) {
                            player.getInventory().add(is);
                        }
                    } else {
                        setActionId(3);
                    }
                }
            },
            new NPCAction("... I have placed the elixir in your inventory."),
            new NPCAction("... Till we meet again old friend!")
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
