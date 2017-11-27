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
public class StationaryBlacksmithAction implements NPCActionCollection {
    private INPCAction[] actions;

    public StationaryBlacksmithAction() {
        actions = new INPCAction[] {
            new NPCAction("Hello Fellow, I'm the blacksmith, name's Gearhead!" +
            "Dear adventurer, I believe our meeting is Fate!" +
            "I ran out of fuel and is now stranded on this planet ..." +
            "Please spare me some fuel, I will be in your debt and do anything in return!", false),
            new NPCAction("Would you like to help Gearhead?", true) {
                @Override
                public void endEvent(Player player, Persistent persistent) {
                    super.endEvent(player, persistent);

                    if(isAnswerYes) {
                        player.decreaseFuel(20);
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
