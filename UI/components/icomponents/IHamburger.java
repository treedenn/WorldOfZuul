package UI.components.icomponents;

/**
 * This interface defines the contract between components of type {@link IHamburger} and a parent controller.
 */
public interface IHamburger extends IComponent {

    /**
     * Method to add an event listener.
     * Event to be fired when hamburger button is clicked.
     * @param listener  listener to be added.
     */
    void onOpenNav(IEventListener listener);


}
