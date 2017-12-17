package UI.components.components;

public interface IDrawer extends IComponent {


    // Events

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

    //  Methods

    /**
     * Method to keep the component updated on the window state.
     * Fullscreen or windowed.
     */
    void setWindowState(String state);

}
