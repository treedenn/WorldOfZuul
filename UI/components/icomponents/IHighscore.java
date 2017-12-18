package UI.components.icomponents;

/**
 * This interface defines the contract between components of type {@link IHighscore} and a parent controller.
 */
public interface IHighscore extends IComponent {

    /**
     * Method to add an event listener.
     * Event to be fired when the save highscore button is clicked.
     * @param listener  listener to be added.
     */
    void onSaveHighscore(IEventListener<String> listener);


    /**
     * Method to add an event listener.
     * Event to be fired when the exit button is clicked.
     * @param listener  listener to be added.
     */
    void onExit(IEventListener<String> listener);

    /**
     * Method to load component with information.
     * @param score the player's score.
     * @param win   true if the player has won the gamem
     */
    void load(double score, boolean win);

}
