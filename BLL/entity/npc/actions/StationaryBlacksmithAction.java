/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.entity.npc.actions;

import BLL.ACQ.INPCAction;
import BLL.ACQ.PersistenceLayer;
import BLL.Game;
import BLL.entity.Entity;
import BLL.entity.npc.Blacksmith;
import BLL.entity.npc.NPC;
import BLL.entity.player.Player;
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

                        Planet planet = (Planet) game.getPlayerPlanets().get("newearth");
                        Blacksmith blacksmith = game.getNpcHandler().getBlacksmith();

                        blacksmith.setCurrentPlanet(planet);
                        planet.getNPCs().add(blacksmith);

                        blacksmith.generateRecipeRequirements(game.getModel());
                        game.addCluesToPlanets();
                    } else {
                        setActionId(3);
                    }
                }
            },
            new NPCTerminateAction("... Thank you Rick! I hope I will see you again!"),
            new NPCAction("... Maybe next time then?")
        };
    }

    @Override
    public INPCAction[] getActions() {
        return actions;
    }
    
}
