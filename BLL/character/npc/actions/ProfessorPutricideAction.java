package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.character.npc.NPC;
import BLL.character.player.Player;
import BLL.item.ItemStack;

public class ProfessorPutricideAction implements NPCActionCollection {
    private INPCAction[] actions;

    public ProfessorPutricideAction() {
        actions = new INPCAction[] {
            new NPCAction("Hi there. My name is Professor Putricide!"),
            new NPCDialogAction("I have a transformation elixir. Do you want it?") {
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
            new NPCAction("I have placed the elixir in your inventory."),
            new NPCAction("I hope I will see you again!")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
}
