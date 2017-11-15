import BLL.Domain;
import BLL.Game;
import BLL.Persistent;
import BLL.UserInterface;
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



