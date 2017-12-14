/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.Game;
import BLL.GameUtility;
import BLL.entity.Entity;
import BLL.entity.npc.Blacksmith;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;
import BLL.item.ItemStack;
import BLL.world.Planet;

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
            "\nPlease spare me some fuel, and I will forever be in your debt!"),
            new NPCDialogAction("Would you like to help Gearhead?") {
                @Override
                public void onEndEvent(NPC npc, Game game) {
                    super.onEndEvent(npc, game);

                    Player player = (Player) game.getPlayer();

                    if(answerYes) {
                        player.decreaseFuel(20);

                        ((Entity) npc).getCurrentPlanet().getNPCs().remove(npc);
                        ((Entity) npc).setCurrentPlanet(null);

                        Planet planet = GameUtility.getRandomPlanetNotXehna(player.getPlanets().values().toArray(new Planet[player.getPlanets().size()]));
                        Blacksmith blacksmith = game.getNpcHandler().getBlacksmith();

                        blacksmith.setCurrentPlanet(planet);
                        blacksmith.addTrace(planet.getName());
                        planet.getNPCs().add(blacksmith);

                        blacksmith.generateRecipeRequirements(game.getModel());
                        game.addCluesToPlanets();

                        // TODO REMOVE DEBUG WHEN FINISHED
                        for (ItemStack itemStack : blacksmith.getRecipe().getRequirements()) {
                            player.getInventory().add(itemStack);
                        }
                        // ---------------
                    } else {
                        setActionId(3);
                    }
                }
            },
            new NPCTerminateAction("... Thank you Rick! I will be leaving!" +
                    "\nIf you need to find me again, I will be leaving traces on the planets - I hope this helps!" +
                    "\nBy the way rick, should you need more fuel for your ship, i noticed a gas station  located on New Earth."),
            new NPCAction("... Thrr, another time perhaps?")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
    
}
