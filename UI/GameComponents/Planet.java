package UI.GameComponents;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Planet extends GameObject {


    private static List<Planet> planets = new ArrayList<>();

    public Planet(BLL.world.Planet planet){
        super(new StackPane(new Circle(150,150,100 + (int) (Math.random() * (200) + 1), Color.WHITE), new Label(planet.getName())));
        planets.add(this);
    }

    public static List<Planet> getPlanets() {
        return planets;
    }
}
