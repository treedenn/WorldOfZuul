package UI.GameComponents;

import BLL.ACQ.IPlanet;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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

        pane.getChildren().add(centerLabel);

    }

    private void addStars(Pane pane){
        for(int i = 0; i < numberOfStars; i++){
            Star star = new Star();
            pane.getChildren().add(star.getView());
        }
    }

    void createPlanets(java.util.Map<String, ? extends IPlanet> planets){
        for (IPlanet planet : planets.values()) {
            GameObject.addGameObject(new UI.GameComponents.Planet(planet), planet.getX(), planet.getY(), rootPane);
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
