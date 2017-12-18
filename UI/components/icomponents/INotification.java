package UI.components.icomponents;

/**
 * This interface defines the contract between components of type {@link INotification} and a parent controller.
 */
public interface INotification extends IComponent {

    /**
     * Message to load message into notification component.
     * @param message   message to append.
     */
    void setMessage(String message);

}
