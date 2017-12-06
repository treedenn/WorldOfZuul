package UI.GameComponents;

import BLL.ACQ.IPlanet;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {

    public static double mapWidth = 8000;
    public static double mapHeight = 8000;

    private List<Node> children;
    private Group root;
    private Pane rootPane;
    private Map<Planet, javafx.geometry.Point2D> planetsOnMap;

    private int numberOfStars = 200;

    public GameMap(){
        children = new ArrayList<>();
        planetsOnMap = new HashMap<>();
        root = new Group();
        generateRootPane();
        configRoot();
    }

    private void configRoot(){
        root.getChildren().addAll(rootPane);
    }

    private void generateRootPane(){
        rootPane = new Pane();
        rootPane.setBackground(new Background(new BackgroundImage(new Image("./UI/resources/img/grid.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        rootPane.setMinHeight(mapHeight);
        rootPane.setMinWidth(mapWidth);

        addStars(rootPane);
        addLabels(rootPane);

        rootPane.getChildren().addAll(children);

    }

    private void addLabels(Pane pane){
        Label centerLabel = new Label("Center of the Universe");
        centerLabel.setStyle("-fx-text-fill: rgba(255,255,255,0.5); -fx-font-size: 20px; -fx-font-family: 'Circular Std Bold';");
        centerLabel.setTranslateX(pane.getMinWidth()/2 - centerLabel.getWidth()/2);
        centerLabel.setTranslateY(pane.getMinHeight()/2 - centerLabel.getHeight()/2);
        centerLabel.setCache(true);
        pane.getChildren().add(centerLabel);

    }

    private void addStars(Pane pane){
        for(int i = 0; i < numberOfStars; i++){
            Star star = new Star();
            pane.getChildren().add(star.getView());
        }
    }

    /**
     * Generates visible planets on the map.
     * @param planets   a map containing objects extending IPlanet
     */
    public void createPlanets(Map<String, ? extends IPlanet> planets){
        for (IPlanet planet : planets.values()) {

            String map2DPath = planet.getMap2D().toURI().toString().replace("\\", "/");

            Planet newUIPlanet = new UI.GameComponents.Planet(planet, new Image(map2DPath));

            Point2D coordinates = new Point2D(planet.getX(), planet.getY());

            GameObject.addGameObject(newUIPlanet, coordinates.getX(), coordinates.getY(), rootPane);

            planetsOnMap.put(newUIPlanet, coordinates);
        }
    }


    public List<Node> getChildren() {
        return children;
    }

    public Group getRoot() {
        return root;
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public java.util.Map<Planet, Point2D> getPlanetsOnMap() {
        return planetsOnMap;
    }
}
