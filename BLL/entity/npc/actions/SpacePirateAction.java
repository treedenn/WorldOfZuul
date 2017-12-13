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
import BLL.entity.player.Player;

/**
 *
 * @author lalal
 */
public class SpacePirateAction implements NPCActionCollection {
    private INPCAction[] actions;

    public SpacePirateAction() {
        actions = new INPCAction[] {
            new NPCAction("[You have been intercepted and captured by spacepirates!]" +
                    "\nAll hand hoay! It's your lucky day lassie - you are now prisoner of my pirate crew!"),
            new NPCDialogAction("Would you like to pay a ransom in order to proceed your voyage?" +
                    "\n(By saying no, you might have a chance to flee the scenario, no paying)") {
                @Override
                public void onEndEvent(NPC npc, Game game) {
                    super.onEndEvent(npc, game);

                    Player player = (Player) game.getPlayer();

                    if(answerYes) {
                        player.decreaseFuel(10);
                        game.setMessageToContainer("Fuel has been decreased by 10.");
                        setActionId(3);
                    } else {
                        if((int) (Math.random() * 3) == 0) {
                            game.setMessageToContainer("You escaped from the pirate!");
                            setActionId(4);
                        } else {
                            player.decreaseFuel(20);
                            game.setMessageToContainer("Fuel has been decreased by 20.");
                            setActionId(3);
                        }
                    }
                }
            },
            new NPCTerminateAction("... Arrr', we will be seeing you again, you landlubber!"),
            new NPCTerminateAction("... Arrr', you fool! No one denies my orders!"),
            new NPCAction("No one flees from me! I WILL BE BACK!")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
}
