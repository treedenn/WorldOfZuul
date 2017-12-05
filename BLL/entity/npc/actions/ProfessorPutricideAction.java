package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
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
                public void onEndEvent(Player player, NPC npc, Persistent persistent) {
                    super.onEndEvent(player, npc, persistent);

                    if(answerYes) {
                        player.getInventory().add(new ItemStack(persistent.getItemById(57), 1));
                    } else {
                        setActionId(3);
                    }
                }
            },
            new NPCAction("... I have placed the elixir in your inventory."),
            new NPCAction("... Till we meet again old friend!")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
}
