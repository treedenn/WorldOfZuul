/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.PersistenceLayer;
import BLL.Game;
import BLL.entity.npc.NPC;
import BLL.entity.npc.UnoX;
import BLL.entity.player.Player;

/**
 *
 * @author lalal
 */
public class UnoXAction implements NPCActionCollection {
    private INPCAction[] actions;
    
    public UnoXAction() {
        actions = new INPCAction[] {
            new NPCAction("Oy matey, I guess you need some gas, aye?" +
                    "\nYou'll just need to pass me test lad!"),
            new NPCDialogAction("Would you like to play a small quiz in order to win some gas for your ship?") {
                @Override
                public void onEndEvent(NPC npc, Game game) {
                    super.onEndEvent(npc, game);

                    if(answerYes) {
                        ((UnoX) npc).pickRandomQuiz();
                    } else {
                        setActionId(3);
                    }
                }
            },
            new NPCQuizAction(""),
            new NPCTerminateAction("... C'ya next time!")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
    
}
