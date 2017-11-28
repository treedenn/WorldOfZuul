/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.character.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.character.player.Player;
import BLL.character.npc.Blacksmith;
import BLL.character.player.Recipe;
import BLL.item.Item;

/**
 *
 * @author lalal
 */
public class BlacksmithAction implements NPCActionCollection {
    private INPCAction[] actions;
    Blacksmith bs = new Blacksmith();
    Recipe recipe = bs.getRecipe();

    public BlacksmithAction() {
        actions = new INPCAction[] {
            new NPCAction("My dear Rick!" +
            "It's already time to return the favor?" +
            "I've heard that you somehow broke your portal gun?", false),
            new NPCAction("Would you like to see my recipe for the Portal Gun?", true) {
                @Override
                public void endEvent(Player player, Persistent persistent) {
                    super.endEvent(player, persistent);

                    if(isAnswerYes) {
                        Item[] items = recipe.getRequirements();
                        int i;
                        for (i = 0; i < items.length; i++) {
                            INPCAction action = actions[i];
                            
                        }
                        new NPCAction(items[i].toString(), true);
                        
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
