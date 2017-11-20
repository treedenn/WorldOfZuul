package UI.GameComponents;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Planet extends GameObject {

    private static List<Planet> planets = new ArrayList<>();

    public Planet(){
        super(new Circle(150,150,50, Color.YELLOW));
        planets.add(this);
    }

    public static List<Planet> getPlanets() {
        return planets;
    }
}
