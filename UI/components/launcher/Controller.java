package UI.components.launcher;

import BLL.ACQ.Domain;
import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class Controller {

    /** Reference to the domain logic */
    private Domain domain;

    /** Reference to the window */
    private Stage stage;

    /**
     * Constructor.
     * @param domain reference to the domain logic (Business Logic Layer (BLL)).
     */
    public Controller(Domain domain) {this.domain = domain;}

    /**
     * Method to be called by the {@link javafx.fxml.FXMLLoader} after root element is completely processed.
     */
    public abstract void initialize();

    /**
     * Sets the reference to the window
     * @param node any node in the scene graph
     */
    public void setStage(Node node) {
        this.stage = (Stage) node.getScene().getWindow();
    }

    /**
     * Sets the reference to the window
     * @param stage the application window
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Getter method for the reference to the window
     * @return  a reference to an object of type Stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Getter method for the reference to the domain logic
     * @return  the reference to the domain logic.
     */
    public Domain getDomain() {
        return domain;
    }


}

