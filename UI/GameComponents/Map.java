package UI.GameComponents;

import BLL.Game;
import BLL.world.Planet;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Map {

    public static double mapWidth = 6000;
    public static double mapHeight = 6000;

    private List<Node> children;
    private Group root;
    private Pane rootPane;

    private int numberOfStars = 100;

    public Map(){
        children = new ArrayList<>();
        root = new Group();
        generateRootPane();

        configRoot();
    }

    private void configRoot(){
        root.getChildren().addAll(rootPane);
    }

    private void generateRootPane(){
        rootPane = new Pane();
        rootPane.setStyle("-fx-background-color: transparent, linear-gradient(from 0.5px 0px to 30.5px 0px, repeat, rgba(255,255,255,0.1), transparent 2%), linear-gradient(from 0px 0.5px to 0px 30.5px, repeat, rgba(255,255,255,0.1), transparent 2%);");
        rootPane.setCache(true);
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

        pane.getChildren().add(centerLabel);

    }

    private void addStars(Pane pane){
        for(int i = 0; i < numberOfStars; i++){
            Star star = new Star();
            pane.getChildren().add(star.getView());
        }
    }

    protected void createPlanets(java.util.Map<String, Planet> planets){
        for (Planet planet : planets.values()) {
            GameObject.addGameObject(new UI.GameComponents.Planet(planet), planet.getCoordinates().getX(), planet.getCoordinates().getY(), rootPane);
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
}
