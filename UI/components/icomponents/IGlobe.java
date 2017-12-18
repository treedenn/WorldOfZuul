package UI.components.icomponents;

/**
 * This interface defines the contract between components of type {@link IGlobe} and a parent controller.
 */
public interface IGlobe extends IComponent {


    /**
     * Accessor method to the planet's name.
     * @return
     */
    String name();

    /**
     * Accessor method to the planet's radius.
     * @return
     */
    double radius();
}
