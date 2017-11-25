package UI.GameComponents;

import BLL.ACQ.IPlanet;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Planet extends GameObject {


    private static List<Planet> planets = new ArrayList<>();

    public Planet(IPlanet planet){
        super(new StackPane(new Circle(200,200,100 + (int) (Math.random() * (200) + 1), Color.WHITE), new Label(planet.getName())));
        planets.add(this);
    }

    public static List<Planet> getPlanets() {
        return planets;
    }

}
