package UI.controller;

import BLL.ACQ.Domain;
import BLL.ACQ.IPlayer;
import BLL.character.player.Player;
import UI.GameComponents.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.security.DomainCombiner;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

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

    @FXML private GridPane dashBoard;

    @FXML private Label notificationTitle;

    @FXML private Label notificationText;

    @FXML private ImageView avatarImage;

    @FXML private GridPane contentWrapper;

    @FXML private AnchorPane heightReference;

    @FXML private ProgressBar barFuel;

    @FXML private Label labelFuel;

    @FXML private ProgressBar barBackpack;

    @FXML private Label labelBackpack;


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
        innersceneHandler.getSubScene().heightProperty().bind(heightReference.heightProperty());
        innersceneHandler.getSubScene().widthProperty().bind(contentWrapper.widthProperty());

        innersceneHandler.createPlanets(domain.getPlayer().getPlanets());


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

        for(GameObject planet : Planet.getPlanets()){
            if(planet.isColliding(innersceneHandler.getPlayer())){
                notificationHandler.showNotification(dashBoard.getHeight() - 100);
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
