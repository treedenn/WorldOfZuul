package UI.controller;

import BLL.ACQ.Domain;
import BLL.ACQ.INPCAction;
import BLL.ACQ.IPlanet;
import BLL.ACQ.IPlayer;
import UI.GameComponents.*;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import UI.SearchTask;
import javafx.concurrent.Task;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController implements Initializable {

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
    private MiniMap miniMapHandler;
    private HoverLabel hoverLabelHandler;
    private PlanetView planetViewHandler;
    private Dialog dialogHandler;
    private boolean mouseIsOnSubscene;
    private double dt;
    private boolean colliding;
    private boolean[] planetCollisions;
    boolean playerCollidedWithPlanet;
    private IPlanet currentPlanet;



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

    @FXML private Label labelFuelTitle;

    @FXML private Label labelBackpackTitle;

    @FXML private ProgressBar barBackpack;

    @FXML private Label labelBackpack;

    @FXML private AnchorPane miniMapWrapper;


    public GameController(Domain domain) {
        this.domain = domain;
        player = domain.getPlayer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configInnerscene();
        configGameMenuButton();
        configDrawer();
        configNotification();
        configAvatar();
        configFuelBar();
        configBackpackBar();
        configHoverLabel();
        configPlanetView();

        wrapper.setStyle("-fx-background-color: #081519;");


        innersceneHandler = new Innerscene(subScene, stage);
        innersceneHandler.getSubScene().heightProperty().bind(subsceneWrapper.heightProperty());
        innersceneHandler.getSubScene().widthProperty().bind(subsceneWrapper.widthProperty());
        innersceneHandler.createPlanets(domain.getPlayer().getPlanets());


        wrapper.requestFocus();

        planetCollisions = new boolean[Planet.getPlanets().size()];




        configMiniMap();

        configDialog();
        //showDialog();




        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            //miniMapHandler.maintainRatio(newValue.doubleValue(), stage);
        };
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);



        // CONSUME SPACE KEYEVENT
        wrapper.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
            if(key.getCode() == KeyCode.SPACE){
                key.consume();
                landOnPlanet();
            }
        });



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();


    }

    @FXML
    void displayDrawer(ActionEvent event) { drawerHandler.showDrawer();}

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

    public void leavePlanet(){
        miniMapHandler.show();
        planetViewHandler.leavePlanet();
        hoverLabelHandler.show();
    }

    void landOnPlanet(){
        if(!planetViewHandler.isVisible()) {
            if (colliding) {
                String planetName = currentPlanet.getName().replace(" ", "");
                if (domain.movePlayerToPlanet(planetName)) {
                    notificationHandler.loadNotification(domain.getMessageContainer().getMessage());
                    showNotification();
                    miniMapHandler.hide();
                    planetViewHandler.leavePlanet();
                    String planetImage = currentPlanet.getImage().toURI().toString().replace("\\", "/");
                    planetViewHandler.landOnPlanet(currentPlanet.getName(), currentPlanet.getDescription(), planetImage);
                    hoverLabelHandler.show();
                } else {
                    notificationHandler.loadNotification(domain.getMessageContainer().getMessage());
                    showNotification();
                }
            }
        }
    }

    public void showDialog(){
        //dialogHandler.showDialog();
        miniMapHandler.hide();
        fuelHandler.hide();
        backpackHandler.hide();

        int actionIndex = 0;

        miniMapHandler.show();
        fuelHandler.show();
        backpackHandler.show();



        /*



        NPC npc = domain.getPlayer().getCurrentPlanet().getNPCs().get(0);

        INPCAction[] actions = npc.getActions();

        domain.startInteract(npc, 0);

        System.out.println(actions[0].getMessage());

        int i = 0;
        if(actions[1] instanceof NPCDialogAction){
            NPCDialogAction a = (NPCDialogAction) actions[1];
            a.setAnswer(false);
            domain.endInteract(npc, 1);
            i = a.getActionId();
            System.out.println(i);
        }

        System.out.println(actions[i].getMessage());


        dialogHandler.updateDialog(npc.getName(), actions[0].getMessage(), "./UI/resources/img/nps/unoX.png");

*/


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

        if (domain.getPlayer().getMorphId() == -1){
            avatarHandler.isRick(true);
        } else{
            avatarHandler.isRick(false);
        }


        dt = System.nanoTime();
        fuelHandler.update();
        backpackHandler.update();
        innersceneHandler.getPlayer().update(dt);
        innersceneHandler.centerView(innersceneHandler.getPlayer());
        innersceneHandler.keepPlayerInMap();

        miniMapHandler.update();

        int count = 0;
        for(Planet planet : Planet.getPlanets()) {
            if (planet.isColliding(innersceneHandler.getPlayer())) {
                planetCollisions[count] = true;
                if(currentPlanet == null) {
                    currentPlanet = planet.getPlanet();
                }
            } else{
                planetCollisions[count] = false;
            }
            count++;
        }

        for (boolean value : planetCollisions) {
            if(value){
                playerCollidedWithPlanet = true;
                break;
            } else {
                playerCollidedWithPlanet = false;
            }
        }

        if(playerCollidedWithPlanet){
            colliding = true;
            hoverLabelHandler.show();

        } else if (!playerCollidedWithPlanet){
            hoverLabelHandler.hide();
            currentPlanet = null;
            colliding = false;
        }



        if (innersceneHandler.getPlayer().isAccelerate()){
            domain.decreaseFuelOnMove(60);
        }
    }




    public void setStage(Stage stage){ this.stage = stage; }

    public void configGameMenuButton(){ burgerMenuHandler = new BurgerMenu(gameMenuButton, gameMenuButton__rectangel1, gameMenuButton__rectangel2, gameMenuButton__rectangel3); }

    public void configDrawer(){ drawerHandler = new Drawer(drawer, darkOverlay); }

    public void configNotification(){ notificationHandler = new Notification(notification, notificationTitle, notificationText, stage.getHeight()); }

    public void hideNotification(){ notificationHandler.hideNotification(); }

    public void showNotification(){ notificationHandler.showNotification(dashBoard.getHeight() - 100);}

    public void configAvatar(){ avatarHandler = new Avatar(avatarImage);}

    public void configFuelBar(){ fuelHandler = new FuelBar(barFuel, labelFuelTitle, labelFuel); }

    public void configBackpackBar(){ backpackHandler = new BackpackBar(barBackpack, labelBackpackTitle, labelBackpack);}

    public void configMiniMap(){ miniMapHandler = new MiniMap(miniMapWrapper, innersceneHandler.getMap().mapWidth, innersceneHandler.getMap().mapHeight, innersceneHandler.getPlayer(), innersceneHandler.getMap().getPlanetsOnMap()); }

    public void configHoverLabel(){ hoverLabelHandler = new HoverLabel(wrapper); hoverLabelHandler.setup("Press", "SPACE", "to land on planet");}

    public void configPlanetView(){ planetViewHandler = new PlanetView(subsceneWrapper, this);}

    public void configInnerscene(){ innersceneHandler = new Innerscene(subScene, stage);}

    public void configDialog(){ dialogHandler = new Dialog(subsceneWrapper, this);}

}
