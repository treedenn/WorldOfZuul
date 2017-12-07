package BLL.ACQ;

public interface UserInterface {
	/**
	 * Injects an objecct of the {@link Domain} interface
	 * Method to inject a business layer.
	 * @param domain the business layer object implementing {@link Domain} interface
	 */
	void injectDomain(Domain domain);

	/**
	 * Starter method to launch the GUI.
	 */
	void startApplication();
}