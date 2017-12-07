package UI.GameComponents;

import javafx.scene.Node;

public interface Reusable {

    /**
     * Method to remove component from scene graph
     * @param parent the most outer node in a component
     */
    void remove(Node parent);

    /**
     * Method to load content into element.
     * @param content
     */
    void append(String... content);

    /**
     * Method to clear element content.
     */
    void clear();

}
