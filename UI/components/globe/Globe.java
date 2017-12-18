package UI.components.globe;

import UI.components.icomponents.Component;
import UI.components.icomponents.IGlobe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This concrete GUI handles the Globe view.
 */
public class Globe extends Component implements IGlobe {

    private String labelText;
    private PhongMaterial texture;
    private String texturePath;

    @FXML
    private Sphere sphere;

    @FXML
    private Label name;

    /**
     * Constructor.
     * @param name name of the rendered planet.
     * @param texturePath path to the texture image.
     */
    public Globe(String name, String texturePath) {
        super("planet_view.fxml");
        labelText = name;
        this.texturePath = texturePath;
        texture = new PhongMaterial();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(labelText);
        texture.setDiffuseMap(new Image(texturePath));
        sphere.setMaterial(texture);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return name.getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double radius() {
        return sphere.getRadius();
    }
}
