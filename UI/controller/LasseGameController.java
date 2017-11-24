package UI.controller;

import BLL.ACQ.Domain;
import BLL.ACQ.IPlayer;
import UI.GameComponents.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class LasseGameController implements Initializable {

    private Domain domain;
    private IPlayer player;
    private Stage stage;
    private Scene scene;
    private Pane pane;
    private Notification notificationHandler;
    private Drawer drawerHandler;
    private Innerscene innersceneHandler;
    private BurgerMenu burgerMenuHandler;
    private Avatar avatarHandler;
    private FuelBar fuelHandler;
    private BackpackBar backpackHandler;
    private boolean mouseIsOnSubscene;
    private double dt;

    @FXML private AnchorPane wrapper;

    @FXML private SubScene subScene;

    @FXML private Button gameMenuButton;

    @FXML private Button exitButton__drawer;

    @FXML private Rectangle gameMenuButton__rectangel1;

    @FXML private Rectangle gameMenuButton__rectangel2;

    @FXML private Rectangle gameMenuButton__rectangel3;

    @FXML private AnchorPane darkOverlay;

    @FXML private GridPane drawer;

    @FXML private AnchorPane notification;

    @FXML private AnchorPane dashBoard;

    @FXML private Label notificationTitle;

    @FXML private Label notificationText;

    @FXML private ImageView avatarImage;

    @FXML private GridPane contentWrapper;

    @FXML private AnchorPane subsceneWrapper;

    @FXML private ProgressBar barFuel;

    @FXML private Label labelFuel;

    @FXML private ProgressBar barBackpack;

    @FXML private Label labelBackpack;

    @FXML private AnchorPane miniMapWrapper;


    public LasseGameController(Domain domain) {
        this.domain = domain;
        player = domain.getPlayer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configGameMenuButton();
        configDrawer();
        configNotification();
        configAvatar();
        configFuelBar();
        configBackpackBar();

        innersceneHandler = new Innerscene(subScene, stage);
        innersceneHandler.getSubScene().heightProperty().bind(subsceneWrapper.heightProperty());
        innersceneHandler.getSubScene().widthProperty().bind(subsceneWrapper.widthProperty());

        innersceneHandler.createPlanets(domain.getPlayer().getPlanets());

        //Stop[] stops = {new Stop(0, Color.WHITE), new Stop(1, Color.BLACK)};
        //wrapper.setBackground(new Background(new BackgroundFill(new RadialGradient(0,0.1,500,500,10000,false,CycleMethod.NO_CYCLE, stops),null,new Insets(-10))));

        wrapper.setStyle("-fx-background-color: black;");







        miniMapWrapper.setMinWidth(innersceneHandler.getMap().mapWidth);
        miniMapWrapper.setPrefWidth(innersceneHandler.getMap().mapWidth);
        miniMapWrapper.setMaxWidth(innersceneHandler.getMap().mapWidth);
        miniMapWrapper.setMinHeight(innersceneHandler.getMap().mapHeight);
        miniMapWrapper.setPrefHeight(innersceneHandler.getMap().mapHeight);
        miniMapWrapper.setMaxHeight(innersceneHandler.getMap().mapHeight);

        subsceneWrapper.widthProperty().addListener((observable, oldValue, newValue) -> {
            double prefScreenRealEstate = 0.3;
            double maxScreenRealEstate = 200;
            double newWidth = ((innersceneHandler.getMap().mapWidth/(innersceneHandler.getMap().mapWidth/newValue.doubleValue())))*prefScreenRealEstate;
            miniMapWrapper.setMinWidth(newWidth);
            miniMapWrapper.setPrefWidth(newWidth);
            miniMapWrapper.setMaxWidth(maxScreenRealEstate);
        });

        miniMapWrapper.widthProperty().addListener((observable, oldValue, newValue) -> {
            double widthRatio = (newValue.doubleValue()/innersceneHandler.getMap().mapWidth);
            double maxScreenRealEstate = 200;
            double newHeight = innersceneHandler.getMap().mapHeight*widthRatio;
            miniMapWrapper.setMinHeight(newHeight);
            miniMapWrapper.setPrefHeight(newHeight);
            miniMapWrapper.setMaxHeight(newHeight);
        });


        miniMapWrapper.setStyle("-fx-border-color: white; -fx-border-size: 2;");

        for (Map.Entry<Planet, Point2D> planet : innersceneHandler.getMap().getPlanetsOnMap().entrySet()) {
            int count = 1;
            Circle newUiPlanet = new Circle(5,Color.WHITE);
            miniMapWrapper.getChildren().add(newUiPlanet);
            newUiPlanet.setTranslateX(count);
            newUiPlanet.setTranslateY(count);
            count += 25;

        }








        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();


    }

    @FXML
    void displayDrawer(ActionEvent event) { drawerHandler.showDrawer(); }

    @FXML
    void closeDrawer(ActionEvent event) { drawerHandler.hideDrawer(); }

    @FXML
    void gameMenuButtonHovered(MouseEvent event) { burgerMenuHandler.burgerMenuHover(); }


    @FXML
    void gameMenuButtonNormal(MouseEvent event) { burgerMenuHandler.burgerMenuNormal();}


    @FXML
    void mouseEntered(MouseEvent event) {
        mouseIsOnSubscene = true;
    }

    @FXML
    void mouseExited(MouseEvent event) {
        mouseIsOnSubscene = false;
    }

    @FXML
    void keyIsPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.A){
            innersceneHandler.getPlayer().setLeft(true);
        }
        if(event.getCode() == KeyCode.D){
            innersceneHandler.getPlayer().setRight(true);
        }
        if(event.getCode() == KeyCode.W){
            innersceneHandler.getPlayer().setDecelerate(false);
            innersceneHandler.getPlayer().setAccelerate(true);
        }
        if(event.getCode() == KeyCode.S){

        }
    }

    @FXML
    void keyIsReleased(KeyEvent event) {
        if(event.getCode() == KeyCode.A){
            innersceneHandler.getPlayer().setLeft(false);

        }
        if(event.getCode() == KeyCode.D){
            innersceneHandler.getPlayer().setRight(false);
        }
        if(event.getCode() == KeyCode.W){
            innersceneHandler.getPlayer().setAccelerate(false);
            innersceneHandler.getPlayer().setDecelerate(true);
        }
        if(event.getCode() == KeyCode.S){

        }
    }




    private void onUpdate(){
        dt = System.nanoTime();
        fuelHandler.update();
        backpackHandler.update();
        innersceneHandler.getPlayer().update(dt);
        innersceneHandler.centerView(innersceneHandler.getPlayer());
        innersceneHandler.keepPlayerInMap();
        if (innersceneHandler.getPlayer().isAccelerate()){
            domain.decreaseFuelOnMove();
        }

        for(GameObject planet : Planet.getPlanets()){
            if(planet.isColliding(innersceneHandler.getPlayer())){
                notificationHandler.showNotification(dashBoard.heightProperty().doubleValue() - 100);
            }
        }
    }

    public void setStage(Stage stage){ this.stage = stage; }

    public void configGameMenuButton(){ burgerMenuHandler = new BurgerMenu(gameMenuButton, gameMenuButton__rectangel1, gameMenuButton__rectangel2, gameMenuButton__rectangel3); }

    public void configDrawer(){ drawerHandler = new Drawer(drawer, darkOverlay); }

    public void configNotification(){ notificationHandler = new Notification(notification, stage.getHeight()); }

    public void hideNotification(){ notificationHandler.hideNotification(); }

    public void showNotification(){ notificationHandler.showNotification(dashBoard.getHeight() - 100);}

    public void configAvatar(){ avatarHandler = new Avatar(avatarImage);}

    public void configFuelBar(){ fuelHandler = new FuelBar(barFuel, labelFuel); }

    public void configBackpackBar(){ backpackHandler = new BackpackBar(barBackpack, labelBackpack);}


}
