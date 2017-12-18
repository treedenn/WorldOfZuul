package UI.components.minimap;

import BLL.ACQ.IPlanet;
import UI.components.icomponents.Component;
import UI.components.icomponents.ComponentLoader;
import UI.components.icomponents.IGameMap;
import UI.components.icomponents.IMiniMap;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This concrete GUI handles the Minimap view.
 */
public class MiniMap extends Component implements IMiniMap {

    /** Attribute holding the relative size of mini map. */
    private double scaleRatio;

    @FXML
    private Pane minimap;

    @FXML
    private Circle playerLocation;

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public MiniMap(){ super("minimap_view.fxml"); }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scaleRatio = minimap.getPrefWidth()/IGameMap.mapWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderPlanets(Map<String, ? extends IPlanet> planets) {

        for (IPlanet iPlanet : planets.values()) {
            ComponentLoader.loadComponent(minimap, new Circle(3, Color.WHITE), iPlanet.getX()*scaleRatio,iPlanet.getY()*scaleRatio);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpaceshipCoordX(double coordX) {
        playerLocation.setTranslateX(coordX*scaleRatio);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpaceshipCoordY(double coordY) {
        playerLocation.setTranslateY(coordY*scaleRatio);
    }
}
