/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.character.player.Player;

/**
 *
 * @author lalal
 */
public class SpacePirateAction implements NPCActionCollection {
    private INPCAction[] actions;

    public SpacePirateAction() {
        actions = new INPCAction[] {
            new NPCAction("You have been intercepted and captured by space pirates!", false),
            new NPCAction("Would you like to pay the ransom to them in order to proceed on your voyage?", true) {
                @Override
                public void endEvent(Player player, Persistent persistent) {
                    super.endEvent(player, persistent);

                    if(isAnswerYes) {
                        player.decreaseFuel(10);
                    } else {
                        player.decreaseFuel(20);
                    }
                }
            },
            new NPCAction("... Arrr', We will be seeing you again, you landlubber!", false)
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
    
}
