package UI.GameComponents.Subscene.GameMap;

import BLL.ACQ.IPlanet;
import UI.GameComponents.GameObject;
import UI.GameComponents.InterfaceElement;
import UI.GameComponents.Planet;
import UI.GameComponents.Star;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameMap extends InterfaceElement implements IMap {

    private List<Node> children;
    private Group root;
    private Pane rootPane;

    private int numberOfStars = 200;

    public GameMap(){
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
        centerLabel.setTranslateX(mapWidth/2 - centerLabel.getWidth()/2);
        centerLabel.setTranslateY(mapHeight/2 - centerLabel.getHeight()/2);
        centerLabel.setCache(true);
        pane.getChildren().add(centerLabel);
    }

    private void addStars(Pane pane){
        for(int i = 0; i < numberOfStars; i++){
            Star star = new Star();
            pane.getChildren().add(star.getView());
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void layout() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    /**
     * {@inheritDoc}
     */
    public void renderPlanets(Map<String, ? extends IPlanet> planets){
        for (IPlanet planet : planets.values()) {
            Planet newUIPlanet = new UI.GameComponents.Planet(planet, new Image(planet.getMap2D().toURI().toString().replace("\\", "/")));
            Point2D coordinates = new Point2D(planet.getX(), planet.getY());
            GameObject.addGameObject(newUIPlanet, coordinates.getX(), coordinates.getY(), rootPane);
        }
    }

    public Group getRoot() {
        return root;
    }

    public Pane getRootPane() {
        return rootPane;
    }

}
