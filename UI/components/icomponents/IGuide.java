package UI.components.icomponents;

/**
 * This interface defines the contract between components of type {@link IGuide} and a parent controller.
 */
public interface IGuide extends IComponent {

    /**
     * Method to add an event listener.
     * Event to be fired when the close button is clicked.
     * @param listener  listener to be added.
     */
    void onClose(IEventListener listener);


}
