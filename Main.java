import BLL.ACQ.Domain;
import BLL.ACQ.PersistenceLayer;
import BLL.ACQ.UserInterface;
import BLL.Game;
import DAL.Model;
import UI.JavaFX;

public class Main {
	/**
	 * Main method that runs the game
	 * The layers in are injected via their respective contracts
	 */
	public static void main(String[] args) {
		PersistenceLayer persistenceLayer = Model.getInstance();
		Domain domain = Game.getInstance();
		domain.injectPersistenceLayer(persistenceLayer);
		UserInterface ui = new JavaFX();
		ui.injectDomain(domain);
		ui.startApplication();
	}
}