/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.PersistenceLayer;
import BLL.entity.npc.Blacksmith;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;
import BLL.item.Item;

/**
 * Describes the actions of the Blacksmith NPC.
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
                public void onEndEvent(Player player, NPC npc, PersistenceLayer persistenceLayer) {
                    super.onEndEvent(player, npc, persistenceLayer);

                    if(answerYes) {
                        setActionId(3);
                    }
                }
            },
            new NPCAction("... I hope I will see you again!"),
            new NPCAction("") {
                @Override
                public void onStartEvent(Player player, NPC npc, PersistenceLayer persistenceLayer) {
                    super.onStartEvent(player, npc, persistenceLayer);

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
