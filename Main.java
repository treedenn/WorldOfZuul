import BLL.ACQ.Domain;
import BLL.Game;
import BLL.ACQ.Persistent;
import BLL.ACQ.UserInterface;
import BLL.world.Planet;
import DAL.Model;
import UI.JavaFX;


public class Main {
	/* runs the game */
	public static void main(String[] args) {
		UserInterface ui = new JavaFX();
		Domain domain = Game.getInstance();
		Persistent persistent = Model.getInstance();

		domain.injectPersistent(persistent);

		ui.injectDomain(domain);
		ui.startApplication();
	}
}



