package UI.refactoredUI.minimap;

import BLL.ACQ.IPlanet;
import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.ComponentLoader;
import UI.refactoredUI.components.IGameMap;
import UI.refactoredUI.components.IMiniMap;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

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
