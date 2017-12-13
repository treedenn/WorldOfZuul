package UI.refactoredUI.components;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.awt.event.ActionListener;
import java.io.IOException;

public interface IComponent extends Initializable {

     Parent getView();

}
