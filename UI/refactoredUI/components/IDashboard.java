package UI.refactoredUI.components;

/**
 * Interface defining objects of type {@link IBackpack} interaction with the outside world.
 */
public interface IDashboard extends IComponent {

    // Events

    /**
     * Method to add an event listener.
     * Event to be fired when backpack visibility is toggled to visible.
     * @param listener  listener to be added
     */
    void onBackpackBarClick(IEventListener<?> listener);

    /**
     * Method to add an event listener.
     * Event to be fired when help is opened.
     * @param listener  listener to be added
     */
    void onHelp(IEventListener<?> listener);

}
