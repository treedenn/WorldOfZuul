import BLL.ACQ.Domain;
import BLL.ACQ.Persistent;
import BLL.ACQ.UserInterface;
import BLL.Game;
import BLL.character.npc.NPCAction;
import BLL.character.player.Player;
import DAL.Model;
import UI.JavaFX;

public class Main {
	/* runs the game */
	public static void main(String[] args) {
		Persistent persistent = Model.getInstance();
		Domain domain = Game.getInstance();

		domain.injectPersistent(persistent);

		UserInterface ui = new JavaFX();
		ui.injectDomain(domain);
		ui.startApplication();

		NPCAction action = new NPCAction() {
			@Override
			public void endEvent(Player player) {
				super.endEvent(player);
			}
		};
	}
}