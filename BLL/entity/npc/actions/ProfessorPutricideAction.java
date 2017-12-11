package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.PersistenceLayer;
import BLL.Game;
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
                public void onEndEvent(NPC npc, Game game) {
                    super.onEndEvent(npc, game);

                    if(answerYes) {
                        ItemStack is = new ItemStack(game.getModel().getItemById(58), 1);

                        Player player = (Player) game.getPlayer();

                        if(!player.getInventory().contains(is)) {
                            player.getInventory().add(is);
                        }
                    } else {
                        setActionId(3);
                    }
                }
            },
            new NPCTerminateAction("... I have placed the elixir in your inventory!"),
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
