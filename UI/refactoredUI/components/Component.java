package UI.refactoredUI.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public abstract class Component {

    /** Reference to the component root. */
    private static Parent parent;
    /** Reference to the FXML path. */
    private String resource;

    /**
     * Constructor.
     * @param resource  path to the FXML file.
     */
    public Component(String resource) {
        this.resource = resource;
    }

    /**
     * Returns an object of type {@link Parent} that can be added to the scene graph.
     * @return  the root of the component.
     */
    public Parent getView(){
        if(parent != null) return parent;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            loader.setControllerFactory(param -> this);
            return (parent = (loader.load()));
        } catch (IOException e) { return null;}
    }

    /**
     * Accessor method for the component root.
     * @return  the root of the component.
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * Mutator method for the component root.
     * @param parent    the root fo the element.
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    /**
     * Accessor method for the FXML file path.
     * @return  the FXML file path.
     */
    public String getResource() {
        return resource;
    }
}
