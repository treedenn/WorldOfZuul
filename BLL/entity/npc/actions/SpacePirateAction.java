/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;

/**
 *
 * @author lalal
 */
public class SpacePirateAction implements NPCActionCollection {
    private INPCAction[] actions;

    public SpacePirateAction() {
        actions = new INPCAction[] {
            new NPCAction("You have been intercepted and captured by space pirates!"),
            new NPCDialogAction("Would you like to pay the ransom to them in order to proceed on your voyage?") {
                @Override
                public void onEndEvent(Player player, NPC npc, Persistent persistent) {
                    super.onEndEvent(player, npc, persistent);

                    if(answerYes) {
                        player.decreaseFuel(10);
                    } else {
                        player.decreaseFuel(20);
                        setActionId(3);
                    }
                }
            },
            new NPCAction("... Arrr', we will be seeing you again, you landlubber!"),
            new NPCAction("... Arrr', you fool! No one denies my orders!")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
    
}
