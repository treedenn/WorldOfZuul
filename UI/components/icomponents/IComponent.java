package UI.components.icomponents;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * Interface defining fundamental methods for every component to implement.
 */
public interface IComponent extends Initializable {
     /**
      * Returns an object of type {@link Parent} that can be added to the scene graph.
      * @return     the loaded object hierarchy.
      */
     Parent getView();
}
