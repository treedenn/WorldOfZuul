package UI.GameComponents.Subscene;

import BLL.ACQ.IPlanet;
import UI.GameComponents.InterfaceElement;
import UI.GameComponents.Subscene.GameMap.GameMap;
import UI.GameComponents.GameObject;
import UI.GameComponents.Player;
import com.sun.istack.internal.Nullable;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Map;


public class Innerscene extends InterfaceElement {

    /** Subscene to encapsulate and render the 2D game scene */
    private SubScene subScene;

    /** Camera */
    private Camera camera;
    private Player player;
    private GameMap map;


    public Innerscene(Pane parent){
        super(parent);
        map = new GameMap();
        subScene = new SubScene(map.getRoot(), parent.getWidth(), parent.getHeight());
        addInterfaceElement(parent, new Group(subScene));
        camera = new ParallelCamera();
        buildSubscene();

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

    @Override
    public void tick() {

    }

    private void buildSubscene(){

        subScene.setRoot(map.getRoot());

        subScene.setCamera(camera);

        ImageView img = new ImageView("./UI/resources/img/spaceShip-01-01.png");
        img.setFitWidth(50);
        img.setFitHeight(88);
        img.setRotate(90);
        img.setCache(true);
        img.setTranslateY(0);
        img.setTranslateX(20);

        StackPane playerRoot = new StackPane(img);
        playerRoot.setEffect(new DropShadow(20,0,0,Color.rgb(0,0,0,0.3)));
        playerRoot.setCenterShape(true);

        player = new UI.GameComponents.Player(50,50, playerRoot);
        player.setVelocity(new Point2D(0,0));
        GameObject.addGameObject(player, GameMap.mapWidth /2, GameMap.mapHeight /2, map.getRootPane());
    }


    public void centerView(GameObject o) {

        camera.setTranslateX((o.getView().getTranslateX()) - (subScene.getWidth()/2) + player.getWidth()/2);
        camera.setTranslateY((o.getView().getTranslateY()) - (subScene.getHeight()/2) + player.getHeight()/2);

    }

    public void keepPlayerInMap(){
        if(player.getView().getTranslateX() < map.getRootPane().getTranslateX()){
            player.getView().setTranslateX(map.getRootPane().getTranslateX());
        }

        if (player.getView().getTranslateX() > map.getRootPane().getWidth() - player.getWidth()){
            player.getView().setTranslateX(map.getRootPane().getWidth() - player.getWidth());
        }

        if (player.getView().getTranslateY() < map.getRootPane().getTranslateY()){
            player.getView().setTranslateY(map.getRootPane().getTranslateY());
        }

        if (player.getView().getTranslateY() > map.getRootPane().getHeight() - player.getHeight()){
            player.getView().setTranslateY(map.getRootPane().getHeight() - player.getHeight());
        }
    }

    public void renderPlanets(java.util.Map<String, ? extends IPlanet> planets){
        map.renderPlanets(planets);
    }


    public Player getPlayer() {
        return player;
    }

    public SubScene getSubScene() {
        return subScene;
    }

    public GameMap getMap() {
        return map;
    }
}
