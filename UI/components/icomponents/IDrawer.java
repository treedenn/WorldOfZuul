package UI.components.icomponents;

/**
 * This interface defines the contract between components of type {@link IDrawer} and a parent controller.
 */
public interface IDrawer extends IComponent {

    /**
     * Method to add an event listener.
     * Event to be fired when drawer is closed.
     * @param listener  listener to be added.
     */
    void onClose(IEventListener listener);

    /**
     * Method to add an event listener.
     * Event to be fired when user quits game.
     * @param listener  listener to be added.
     */
    void onSaveAndQuit(IEventListener listener);

    /**
     * Method to add an event listener.
     * Event to be fired when windows size is toggled.
     * @param listener  listener to be added.
     */
    void onMaximizeMinimize(IEventListener listener);

    /**
     * Method to keep the component updated on the window state.
     * Fullscreen or windowed.
     * @param state the state as presented to the user.
     */
    void setWindowState(String state);

}
