package UI.GameComponents;

import BLL.ACQ.IPlanet;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

    public static double mapWidth = 6000;
    public static double mapHeight = 6000;

    private List<Node> children;
    private Group root;
    private Pane rootPane;
    private java.util.Map<Planet, javafx.geometry.Point2D> planetsOnMap;

    private int numberOfStars = 200;

    public Map(){
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

    void createPlanets(java.util.Map<String, ? extends IPlanet> planets){
        String imageURL;
        for (IPlanet planet : planets.values()) {
            switch (planet.getName()){
                case "Scurn 01K":
                    imageURL = "./UI/resources/img/planetMaps/uranusmap.jpg";
                    break;
                case "Skaro":
                    imageURL = "./UI/resources/img/planetMaps/saturnmap.jpg";
                    break;
                case "Orion":
                    imageURL = "./UI/resources/img/planetMaps/jupitermap.jpg";
                    break;
                case "Cleron OR7":
                    imageURL = "./UI/resources/img/planetMaps/mars_1k_color.jpg";
                    break;
                case "Gallifrey":
                    imageURL = "./UI/resources/img/planetMaps/mercurymap.jpg";
                    break;
                case "Uskillon":
                    imageURL = "./UI/resources/img/planetMaps/neptunemap.jpg";
                    break;
                case "Deineax":
                    imageURL = "./UI/resources/img/planetMaps/venusmap.jpg";
                    break;
                case "Amrif Arret":
                    imageURL = "./UI/resources/img/planetMaps/plutomap2k.jpg";
                    break;
                case "Hebrilles":
                    imageURL = "./UI/resources/img/planetMaps/uvbluesun.jpg";
                    break;
                case "New Earth":
                    imageURL = "./UI/resources/img/planetMaps/earthmap1k.jpg";
                    break;
                case "Xehna":
                    imageURL = "./UI/resources/img/planetMaps/dgnyre.jpg";
                    break;
                case "J8 Ayrus Z420":
                    imageURL = "./UI/resources/img/planetMaps/barrenRock.jpg";
                    break;
                default:
                    imageURL = "./UI/resources/img/planetMaps/earthmap1k.jpg";
                    break;
            }

            Planet newUIPlanet = new UI.GameComponents.Planet(planet, new Image(imageURL));
            javafx.geometry.Point2D coordinates = new javafx.geometry.Point2D(planet.getX(), planet.getY());
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
