package UI.GameComponents;

import UI.controller.GameController;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public abstract class InterfaceElement<T extends Pane> {

    GameController controller;

    /** Reference to the parent of this interface element. Must extend {@link Pane}. */
    T parent;

    /** {@link javafx.collections.ObservableList} of children representing the element to be rendered. */
    Group element;

    /**
     * No-args constructor.
     */
    public InterfaceElement(){}

    /**
     * Constructor.
     * @param parent Parent of this interface element.
     */
    public InterfaceElement(T parent){ this.parent = parent; element = new Group();}

    /**
     * Method that encapsulates the necessary layout for the element.
     */
    public abstract void layout();

    /**
     * Method to update the element.
     */
    public abstract void tick();

    /**
     * Method to toggle the visibility of the element to visible.
     */
    public abstract void show();

    /**
     * Mehtod to toggle the visibility of the element to hidden
     */
    public abstract void hide();

    /**
     * Method to render a list of children in a parent.
     * @param parent the node to be the parent of the interface element.
     * @param element {@link javafx.collections.ObservableList} of children to be rendered.
     */
    public static <T extends Pane> void addInterfaceElement(T parent, Group element){
        parent.getChildren().add(element);
    }

    /**
     * Getter method for children representing the element.
     * @return  {@link javafx.collections.ObservableList} of children
     */
    public Group getElement() { return element; }

    /**
     * Getter method for the parent holding this element.
     * @return  parent node.
     */
    public T getParent() { return parent; }
}
