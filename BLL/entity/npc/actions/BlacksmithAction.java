/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.PersistenceLayer;
import BLL.Game;
import BLL.entity.Inventory;
import BLL.entity.npc.Blacksmith;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;
import BLL.entity.player.Recipe;
import BLL.item.ItemPortalGun;
import BLL.item.ItemStack;

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
                public void onEndEvent(NPC npc, Game game) {
                    super.onEndEvent(npc, game);

                    if(!answerYes) {
                        setActionId(4);
                    }
                }
            },
            new NPCAction("") {
                @Override
                public void onStartEvent(NPC npc, Game game) {
                    super.onStartEvent(npc, game);

                    if(npc instanceof Blacksmith) {
                        Blacksmith bs = (Blacksmith) npc;

                        Player player = (Player) game.getPlayer();

                        Inventory inventory = player.getInventory();
                        Recipe recipe = bs.getRecipe();

                        boolean[] haveItems = recipe.haveItems(inventory.getContent());

                        ItemStack[] items = recipe.getRequirements();

                        if(allTrue(haveItems)) {
                            for(ItemStack itemStack : inventory.getContent()) {
                                if(itemStack.getItem() instanceof ItemPortalGun) {
                                    ItemPortalGun pg = (ItemPortalGun) itemStack.getItem();
                                    pg.repair();
                                    break;
                                }
                            }

                            for(ItemStack item : items) {
                                inventory.remove(item);
                            }

                            message = "Oh, since you had the materials to repair your Portal Gun, I did it.";
                        } else {
                            StringBuilder sb = new StringBuilder();
                            for(ItemStack item : items) {
                                sb.append(item.getItem().toString());
                                sb.append(System.lineSeparator());
                            }

                            sb.delete(sb.length() - 1, sb.length());

                            message = sb.toString();
                        }
                    }
                }
            },
            new NPCTerminateAction("... I hope I will see you again!")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }

    /**
     * Checks if all the booleans inside an array are true.
     * @param booleans any boolean array
     * @return true, if all booleans are true
     */
    private boolean allTrue(boolean[] booleans) {
        for(boolean b : booleans) {
            if(!b) { return false; }
        }

        return true;
    }
}
