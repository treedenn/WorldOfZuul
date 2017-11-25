import BLL.ACQ.Domain;
import BLL.ACQ.INPCAction;
import BLL.ACQ.Persistent;
import BLL.ACQ.UserInterface;
import BLL.Game;
import BLL.character.npc.NPC;
import BLL.character.npc.actions.NPCAction;
import BLL.character.player.Player;
import DAL.Model;
import UI.JavaFX;

public class Main {
	/* runs the game */
	public static void main(String[] args) {
		Persistent persistent = Model.getInstance();
		Domain domain = Game.getInstance();

		domain.injectPersistent(persistent);

//		NPC npc = domain.getPlayer().getCurrentPlanet().getNPCs().get(0);
//
//		INPCAction[] actions = npc.getActions();
//
//		for(int i = 0; i < npc.getActions().length; i++) {
//			if(actions[i].getMessage() != null) {
				// TODO: Print to notification or something..
//				System.out.println(actions[i].getMessage());
//			}
//
//			if(actions[i].needAnswer()) {
				// TODO: Show gui instead of setting true directly
//				actions[i].setAnswer(true);
//
//			}
//
//			domain.interact(0, i);
//		}

		UserInterface ui = new JavaFX();
		ui.injectDomain(domain);
		ui.startApplication();
	}
}