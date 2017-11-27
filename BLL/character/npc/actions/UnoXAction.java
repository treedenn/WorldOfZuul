/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
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
            new NPCAction("Before leaving you make a quick stop at the local gas station Uno-X", false),
            new NPCAction("Would you like to play a small quiz in order to win some gas for your ship?", true) {
                @Override
                public void endEvent(Player player, Persistent persistent) {
                    super.endEvent(player, persistent);

                    if(isAnswerYes) {
                        unox.pickRandomQuiz();
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
