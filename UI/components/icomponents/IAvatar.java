package UI.components.icomponents;

/**
 * This interface defines the contract between components of type {@link IAvatar} and a parent controller.
 */
public interface IAvatar extends IComponent {

    /**
     * Method to set which avatar to be presented.
     * @param name avatar name.
     */
    void setAvatar(String name);

}
