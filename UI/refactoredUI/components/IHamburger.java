package UI.refactoredUI.components;

public interface IHamburger extends IComponent {

    // Events

    /**
     * Method to add an event listener.
     * Event to be fired when hamburger button is clicked.
     * @param listener  listener to be added.
     */
    void onOpenNav(IEventListener listener);


}
