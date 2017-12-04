package BLL.ACQ;

/**
 * Describes what the user interface (UI) has to have.
 */
public interface UserInterface {
	/**
	 * Method to inject a business layer.
	 * @param domain the layer
	 */
	void injectDomain(Domain domain);

	/**
	 * Method to begin the program.
	 */
	void startApplication();
}