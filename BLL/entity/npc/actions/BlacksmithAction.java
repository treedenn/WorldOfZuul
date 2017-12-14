/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.Game;
import BLL.entity.Inventory;
import BLL.entity.npc.Blacksmith;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;
import BLL.entity.player.Recipe;
import BLL.item.ItemComponent;
import BLL.item.ItemPortalGun;
import BLL.item.ItemStack;

/**
 * Contains the actions of the {@link Blacksmith} NPC.
 */
public class BlacksmithAction implements NPCActionCollection {
    private INPCAction[] actions;

    /**
     * Constructs a new Blacksmith action.
     */
    public BlacksmithAction() {
        actions = new INPCAction[] {
            new NPCDialogAction("MNy dear Rick!" +
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
            new NPCAction("My tinker tools show what you already have, not what you need..." +
                    "I have heard rumours that clues are around the universe!" +
                    "\nThe recipe is:") {
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

                            String text;
                            ItemComponent itemComponent;
                            for(ItemStack item : items) {
                                itemComponent = (ItemComponent) item.getItem();

                                if(player.getInventory().contains(item)) {
                                    text = String.format("[\u2713] %s [%s]", itemComponent.getName(), itemComponent.getComponentType().name());
                                } else {
                                    text = String.format("[\u2715] %s [%s]", "XXXXXXXXXX", itemComponent.getComponentType().name());
                                }

                                sb.append(System.lineSeparator());
                                sb.append(text);
                            }

                            message += sb.toString();
                        }
                    }
                }
            },
            new NPCTerminateAction("... I hope I will see you again!")
        };
    }

    /**
     * {@inheritDoc}
     */
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
