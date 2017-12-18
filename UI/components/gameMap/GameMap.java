package UI.components.gameMap;

import BLL.ACQ.IPlanet;
import UI.components.icomponents.*;
import UI.components.minimap.MiniMap;
import UI.components.globe.Globe;
import UI.components.spaceship.Spaceship;
import javafx.fxml.FXML;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.*;

/**
 * This concrete GUI handles the GameMap view.
 */
public class GameMap extends Component implements IGameMap {

    /** List of observers */
    private List<IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>>> onMovementSubscribers = new ArrayList<>();
    /** List of added UI repreentations of planets. */
    private List<IGlobe> planetsOnGameMap = new ArrayList<>();

    /** Reference to the nested minimap component. */
    private IMiniMap miniMap;
    /** Reference to the nested spaceship component. */
    private ISpaceship spaceship;
    /** The camera is used to render the game as a pannable 2D game. */
    private Camera camera;
    /** Reference to the root of the component. */
    private Group root;
    /** This attribute hols the reference to the most outer node. */
    private Pane map;
    /** Integer value defining the number of stars to be rendered on the game map. */
    private int numberOfStars = 200;

    @FXML
    private AnchorPane wrapper;

    @FXML
    private SubScene subscene;

    @FXML
    private AnchorPane upperRightCorner;

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public GameMap(){
        super("gamemap_view.fxml");
        camera = new ParallelCamera();
        map = new Pane();
        root = new Group();
        miniMap = new MiniMap();
        spaceship = new Spaceship();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComponentLoader.loadComponent(upperRightCorner, miniMap.getView(), 0,0,0,0,false);
        ComponentLoader.loadComponent(map, spaceship.getView());

        /** Listen to spaceship velocity and pass this to subscribers. */
        spaceship.onMovement(data -> onMovementSubscribers.forEach(listener -> listener.onAction(data)));

        map.setMinWidth(wrapper.widthProperty().getValue());
        map.setMinHeight(wrapper.heightProperty().getValue());
        map.setBackground(new Background(new BackgroundImage(new Image("./UI/resources/img/grid.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        map.setStyle("-fx-border-color: #333333");
        map.setMinWidth(mapWidth);
        map.setMinHeight(mapHeight);
        subscene.widthProperty().bind(wrapper.widthProperty());
        subscene.heightProperty().bind(wrapper.heightProperty());
        root.getChildren().add(map);
        subscene.setRoot(root);
        subscene.setCamera(camera);
        renderStars();
        renderCenterLabel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(double deltaTime) {
        spaceship.tick(deltaTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onMovement(IEventListener<AbstractMap.SimpleImmutableEntry<Double, Double>> listener) {
        onMovementSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotateSpaceshipLeft(boolean state) {
        spaceship.setRotateLeft(state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotateSpaceshipRight(boolean state) {
        spaceship.setRotateRight(state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accelerateSpaceship(boolean state, Boolean reverse) {
        spaceship.setAccelerate(state, reverse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decelerateSpaceship(boolean state) {spaceship.setDecelerate(state);}

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderPlanets(Map<String, ? extends IPlanet> planets) {
        for (IPlanet iPlanet : planets.values()) {
            IGlobe newGlobe = new Globe(iPlanet.getName(), iPlanet.getMap2D().toURI().toString().replace("\\","/"));
            ComponentLoader.loadComponent(map, newGlobe.getView(),  iPlanet.getX() - newGlobe.radius(), iPlanet.getY() - newGlobe.radius());
            newGlobe.getView().toBack();
            planetsOnGameMap.add(newGlobe);
        }
        miniMap.renderPlanets(planets);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keepWithinBoundaries(double coordX, double coordY) {
        setSpaceshipCoordX(coordX);
        setSpaceshipCoordY(coordY);

        camera.setTranslateX(coordX - (subscene.getWidth()/2));
        camera.setTranslateY(coordY - (subscene.getHeight()/2));
        if(spaceship.getView().getTranslateX() < subscene.getRoot().getTranslateX()){
            spaceship.getView().setTranslateX(subscene.getRoot().getTranslateX());
        }

        if (spaceship.getView().getTranslateX() > mapWidth - ((Pane)spaceship.getView()).getWidth()){
            spaceship.getView().setTranslateX(mapWidth - ((Pane)spaceship.getView()).getWidth());
        }

        if (spaceship.getView().getTranslateY() < subscene.getRoot().getTranslateY()){
            spaceship.getView().setTranslateY(subscene.getRoot().getTranslateY());
        }

        if (spaceship.getView().getTranslateY() > mapHeight - ((Pane)spaceship.getView()).getHeight()){
            spaceship.getView().setTranslateY(mapHeight - ((Pane)spaceship.getView()).getHeight());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpaceshipCoordX(double coordX) {
        spaceship.getView().setTranslateX(coordX);
        miniMap.setSpaceshipCoordX(coordX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpaceshipCoordY(double coordY) {
        spaceship.getView().setTranslateY(coordY);
        miniMap.setSpaceshipCoordY(coordY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISpaceship getSpaceship() {
        return spaceship;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<IGlobe> getPlanetsOnGameMap() {
        return planetsOnGameMap;
    }

    /**
     * Method to render graphical elements that visualize stars.
     */
    private void renderStars(){
        for (int i = 0; i < numberOfStars; i++){
            Circle star = new Circle((int) (Math.random() * mapWidth), (int) (Math.random() * mapHeight),10 + (int)(Math.random() * ((80 - 10) + 1)));
            Stop[] stops = {new Stop(0, Color.WHITE), new Stop(0.02,Color.WHITE), new Stop(0.025,Color.rgb(255,255,255,0.2)), new Stop(1, Color.TRANSPARENT)};
            RadialGradient rg = new RadialGradient(0,0.1,star.getCenterX(), star.getCenterY(), star.getRadius(), false, CycleMethod.NO_CYCLE, stops);
            star.setFill(rg);
            star.setCache(true);
            map.getChildren().add(star);
        }
    }

    /**
     * Method to render label in the center of the game map.
     */
    private void renderCenterLabel(){
        Label centerLabel = new Label("Center of the Univserse");
        centerLabel.setStyle("-fx-font-family: 'Circular Std Bold'; -fx-text-fill: rgba(255,255,255,0.5); -fx-font-size: 20;");
        centerLabel.setTranslateX(mapWidth/2 - centerLabel.getWidth()/2);
        centerLabel.setTranslateY(mapHeight/2 - centerLabel.getHeight()/2);
        centerLabel.setCache(true);
        map.getChildren().add(centerLabel);
    }

}
