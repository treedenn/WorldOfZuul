package UI.components.components;

public interface INotification extends IComponent {

    /**
     * Message to load message into notification component.
     * @param message   message to append.
     */
    void setMessage(String message);

}
