package UI.deleteWhenTransitioneToNewGUI.controller.gamecomponents;

import javafx.scene.layout.Pane;

public interface Reusable {

    /**
     * Method to remove component from scene graph
     * @param parent the most outer node in a component
     */
    <T extends Pane> void remove(T parent);

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
