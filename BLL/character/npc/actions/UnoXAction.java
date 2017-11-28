/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.character.npc.NPC;
import BLL.character.npc.UnoX;
import BLL.character.player.Player;

/**
 *
 * @author lalal
 */
public class UnoXAction implements NPCActionCollection {
    private INPCAction[] actions;
    UnoX unox;
    
    public UnoXAction() {
        actions = new INPCAction[] {
            new NPCAction("Before leaving you make a quick stop at the local gas station Uno-X"),
            new NPCDialogAction("Would you like to play a small quiz in order to win some gas for your ship?") {
                @Override
                public void onEndEvent(Player player, NPC npc, Persistent persistent) {
                    super.onEndEvent(player, npc, persistent);

                    if(answerYes) {
                        unox.pickRandomQuiz();
                    }
                }
            },
            new NPCAction("... Till we meet again!")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
    
}
