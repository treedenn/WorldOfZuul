package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.character.player.Player;
import BLL.item.ItemStack;

public class ProfessorPutricideAction implements NPCActionCollection {
    private INPCAction[] actions;

    public ProfessorPutricideAction() {
        actions = new INPCAction[] {
            new NPCAction("Hi there. My name is Professor Putricide!", false),
            new NPCAction("I have a transformation elixir. Do you want it?", true) {
                @Override
                public void endEvent(Player player, Persistent persistent) {
                    super.endEvent(player, persistent);

                    if(isAnswerYes) {
                        player.getInventory().add(new ItemStack(persistent.getItemById(57), 1));
                    }
                }
            },
            new NPCAction("... I hope I will see you again!", false)
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
}
