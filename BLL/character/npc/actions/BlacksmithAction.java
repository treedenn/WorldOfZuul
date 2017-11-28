/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.character.npc.Blacksmith;
import BLL.character.npc.NPC;
import BLL.character.player.Player;
import BLL.item.Item;

/**
 *
 * @author lalal
 */
public class BlacksmithAction implements NPCActionCollection {
    private INPCAction[] actions;

    public BlacksmithAction() {
        actions = new INPCAction[] {
            new NPCDialogAction("My dear Rick!" +
            "\nIt's already time to return the favor?" +
            "\nI've heard that you somehow broke your portal gun?"),
            new NPCDialogAction("Would you like to see my recipe for the Portal Gun?") {
                @Override
                public void onEndEvent(Player player, NPC npc, Persistent persistent) {
                    super.onEndEvent(player, npc, persistent);

                    if(answerYes) {
                        setActionId(3);
                    }
                }
            },
            new NPCAction("... I hope I will see you again!"),
            new NPCAction("") {
                @Override
                public void onStartEvent(Player player, NPC npc, Persistent persistent) {
                    super.onStartEvent(player, npc, persistent);

                    if(npc instanceof Blacksmith) {
                        Blacksmith bs = (Blacksmith) npc;

                        Item[] items = bs.getRecipe().getRequirements();
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < items.length; i++) {
                            sb.append(items[i].toString());
                            sb.append(System.lineSeparator());
                        }

                        message = sb.toString();
                    }
                }
            }
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
    
}
