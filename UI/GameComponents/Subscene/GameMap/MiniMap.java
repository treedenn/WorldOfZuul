package UI.GameComponents.Subscene.GameMap;

import BLL.ACQ.IPlanet;
import UI.GameComponents.InterfaceElement;
import UI.GameComponents.Planet;
import UI.GameComponents.Player;
import UI.controller.GameController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class MiniMap extends InterfaceElement implements IMap {

    Pane miniMapWrapper;
    Circle playerLocation;
    double scaleRatio;

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public MiniMap(Pane parent) {
        super(parent);
        miniMapWrapper = new AnchorPane();
        configWrapper();
        getElement().getChildren().add(miniMapWrapper);
        addInterfaceElement(parent, getElement());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {

    }

    public void update(double coordX, double coordY){
        scaleRatio = miniMapWrapper.getWidth() / IMap.mapWidth;
        playerLocation.setTranslateX(coordX * scaleRatio);
        playerLocation.setTranslateY(coordY * scaleRatio);
    }

    public void hide(){
        miniMapWrapper.setVisible(false);
    }

    public void show(){
        miniMapWrapper.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void layout() {



    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderPlanets(Map<String, ? extends IPlanet> planets) {
        for (IPlanet planet : planets.values()) {
            double scaleRatio = miniMapWrapper.getPrefWidth() / IMap.mapWidth;
            double radius = 90;
            miniMapWrapper.getChildren().add(new Circle(planet.getX()*scaleRatio, planet.getY()*scaleRatio, radius, Color.rgb(255,255,255)));
        }
    }

    private void addPlayer(){
        playerLocation = new Circle(100, Color.rgb(0,229,255));
        miniMapWrapper.getChildren().add(playerLocation);
    }

    private void configWrapper(){
        double width = 200;
        miniMapWrapper.setPrefWidth(width);
        miniMapWrapper.setMinWidth(width);
        miniMapWrapper.setMaxWidth(width);
        miniMapWrapper.setPrefHeight(width);
        miniMapWrapper.setMinHeight(width);
        miniMapWrapper.setMaxHeight(width);
        miniMapWrapper.setTranslateX(-16);
        miniMapWrapper.setTranslateY(16);
        miniMapWrapper.setStyle("-fx-border-color: rgba(255,255,255,0.2); -fx-border-radius: 5px; -fx-background-color: linear-gradient(to top,  rgba(0,229, 255, 0.1),  rgba(0,229, 255, 0.05)); -fx-background-radius: 5px;");
        miniMapWrapper.setEffect(new DropShadow());

        DoubleProperty scaleX = new SimpleDoubleProperty(1.0);
        DoubleProperty scaleY = new SimpleDoubleProperty(1.0);

        scaleX.bind(miniMapWrapper.widthProperty().divide(IMap.mapWidth));
        scaleY.bind(miniMapWrapper.heightProperty().divide(IMap.mapHeight));

        miniMapWrapper.getChildren().addListener((ListChangeListener.Change<? extends Node> c) -> {
            while(c.next()){
                if(c.wasAdded()){
                    for (Node child : c.getAddedSubList()) {
                        child.scaleXProperty().bind(scaleX);
                        child.scaleYProperty().bind(scaleY);
                    }
                }
            }
        });

        addPlayer();
    }

}
