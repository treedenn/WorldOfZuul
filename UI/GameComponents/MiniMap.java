package UI.GameComponents;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class MiniMap {

    Pane miniMapWrapper;
    java.util.Map<Planet, Point2D> children;
    double widthReference;
    double heightReference;
    Player player;
    Circle playerLocation;


    public MiniMap(Pane miniMapWrapper, double widthReference, double heightReference, Player player, Map<Planet, Point2D> children){
        this.miniMapWrapper = miniMapWrapper;
        this.children = children;
        this.player = player;
        this.widthReference = widthReference;
        this.heightReference = heightReference;

        configWrapper();
    }

    public void update(){
        double scaleRatio = miniMapWrapper.getWidth() / widthReference;
        double absolutePositionX = player.getView().getTranslateX();
        double absolutePositionY = player.getView().getTranslateY();
        double relativePositionX = absolutePositionX * scaleRatio;
        double relativePositionY = absolutePositionY * scaleRatio;

        playerLocation.setTranslateX(relativePositionX);
        playerLocation.setTranslateY(relativePositionY);

    }

    public void hide(){
        miniMapWrapper.setVisible(false);
    }

    public void show(){
        miniMapWrapper.setVisible(true);
    }

    private void addPlanets(){
        for (Map.Entry<Planet, Point2D> planetPoint2DEntry : children.entrySet()) {
            double scaleRatio = miniMapWrapper.getPrefWidth() / widthReference;
            double radius = 90;
            double translateX = (planetPoint2DEntry.getValue().getX() * scaleRatio);
            double translateY = (planetPoint2DEntry.getValue().getY() * scaleRatio);
            miniMapWrapper.getChildren().add(new Circle(translateX,translateY, radius, Color.rgb(255,255,255)));
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

        scaleX.bind(miniMapWrapper.widthProperty().divide(widthReference));
        scaleY.bind(miniMapWrapper.heightProperty().divide(heightReference));

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

        addPlanets();
        addPlayer();


    }


















    public void maintainRatio(double newValue, Stage stage){

    }

}
