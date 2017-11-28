/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.character.npc.NPC;
import BLL.character.player.Player;

/**
 *
 * @author lalal
 */
public class StationaryBlacksmithAction implements NPCActionCollection {
    private INPCAction[] actions;

    public StationaryBlacksmithAction() {
        actions = new INPCAction[] {
            new NPCAction("Hello Fellow, I'm the blacksmith, name's Gearhead!" +
            "\nDear adventurer, I believe our meeting is Fate!" +
            "\nI ran out of fuel and is now stranded on this planet ..." +
            "\nPlease spare me some fuel, I will be in your debt and do anything in return!"),
            new NPCDialogAction("Would you like to help Gearhead?") {
                @Override
                public void onEndEvent(Player player, NPC npc, Persistent persistent) {
                    super.onEndEvent(player, npc, persistent);

                    if(answerYes) {
                        player.decreaseFuel(20);
                    } else {
                        setActionId(3);
                    }
                }
            },
            new NPCAction("... I hope I will see you again!"),
            new NPCAction("... Maybe next time then?")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
    
}
