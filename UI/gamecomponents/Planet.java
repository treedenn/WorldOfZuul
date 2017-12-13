package UI.gamecomponents;

import BLL.ACQ.IPlanet;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Sphere;

import java.util.ArrayList;
import java.util.List;

public class Planet extends GameObject {


    private static List<Planet> planets = new ArrayList<>();
    private StackPane planetWrapper;
    private String planetName;
    private String description;
    private Label name;
    private Sphere sphere;
    PhongMaterial texture;
    IPlanet planet;

    public Planet(IPlanet planet, Image diffuseMap){
        super(new StackPane());
        this.planet = planet;
        this.description = planet.getDescription();
        this.planetName = planet.getName();
        planetWrapper = (StackPane) getView();
        this.name = new Label(planetName);
        texture = new PhongMaterial();
        sphere = new Sphere(280);

        texture.setDiffuseMap(diffuseMap);
        sphere.setMaterial(texture);

        name.setStyle("-fx-font-family: 'Circular Std Bold'; -fx-font-size: 20; -fx-text-fill: white;");
        name.setEffect(new DropShadow(20, Color.rgb(0,0,0,1)));
        name.setCache(true);

        Group planetGroup = new Group(new StackPane(sphere, name));

        planetWrapper.getChildren().addAll(planetGroup);

        planetGroup.setTranslateX(-sphere.getRadius());
        planetGroup.setTranslateY(-sphere.getRadius());


        planets.add(this);
    }

    public String getName() {
        return planetName;
    }

    public String getDescription() {
        return description;
    }

    public static List<Planet> getPlanets() {
        return planets;
    }

    public IPlanet getPlanet() {
        return planet;
    }
}
