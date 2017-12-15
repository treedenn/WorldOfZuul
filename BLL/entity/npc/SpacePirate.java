package BLL.entity.npc;

import BLL.Game;
import BLL.GameUtility;
import BLL.entity.MovableEntity;
import BLL.entity.npc.actions.NPCAction;
import BLL.entity.npc.actions.NPCActionCollection;
import BLL.entity.npc.actions.NPCDialogAction;
import BLL.entity.npc.actions.NPCTerminateAction;
import BLL.entity.player.Player;
import BLL.world.Planet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Contains all the functionality of the Blacksmith NPC.
 * SpacePirate is an NPC that attacks the player when the player is moving towards its destination.
 */
public class SpacePirate extends MovableEntity implements NPC {
    private NPCAction[] actions;

    private boolean canAttack;
    private int moveCounter;

    /**
     * Instantiates a new pirate.
     */
    public SpacePirate() {
        super();
        initActions();
        canAttack = true;
        moveCounter = 0;
    }

    /**
     * Attacks by settings the canAttack variable to false.
     * In addition it creates a timer.
     */
    public void attack() {
        canAttack = false;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canAttack = true;
            }
        }, 300000); // 300 seconds
    }

    /**
     * Returns a value based on the pirate can attack
     * @return true, if it can attack
     */
    public boolean canAttack() {
        return canAttack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return 3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Space Pirate";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGood() { return false; }

    /**
     * {@inheritDoc}
     */
    @Override
    public NPCAction[] getActions() {
        return actions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActions(NPCActionCollection collection) {
        this.actions = collection.getActions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        moveCounter++;

        if(moveCounter == 3) {
            moveCounter = 0;

            getCurrentPlanet().getNPCs().remove(this);
            setCurrentPlanet(GameUtility.getRandomPlanetNotXehna(getPlanets().values().toArray(new Planet[getPlanets().size()])));
            getCurrentPlanet().getNPCs().add(this);
        }
    }

    private void initActions() {
        actions = new NPCAction[] {
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
}