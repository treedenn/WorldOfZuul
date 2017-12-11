package UI.controller;

import BLL.ACQ.Domain;
import BLL.ACQ.IItemStack;
import BLL.ACQ.INPCAction;
import BLL.ACQ.IPlanet;
import BLL.entity.npc.NPC;
import BLL.entity.npc.actions.NPCAction;
import BLL.entity.npc.actions.NPCDialogAction;
import BLL.entity.npc.actions.NPCJumpAction;
import BLL.item.ItemStack;
import DAL.Model;
import UI.GameComponents.*;
import UI.GameComponents.Subscene.GameMap.MiniMap;
import UI.GameComponents.Subscene.Innerscene;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
/**
 * The primary controller class for actually playing the game.
 */
public class GameController extends Controller implements IGameLoop {

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
    private Backpack inventoryHandler;

    /** Double value used to keep track of time. dt stands for delta time. */
    private double dt;

    /** Boolean array to keep track of individual collisions between objects of type {@link Player} and objects of type {@link Player} */
    private boolean[] planetCollisions;

    /** Boolean value to keep track of collision between object of type {@link Player} and objects of type {@link Planet} */
    boolean playerCollidingdWithPlanet;


    private IPlanet currentPlanet;


    /** Most outer node in the scene graph */
    @FXML private AnchorPane wrapper;

    /** 2nd level wrapper node containing the primary visual elements */
    @FXML private GridPane contentWrapper;

    /** Most outer node used in {@link Innerscene} */
    @FXML private AnchorPane subsceneWrapper;

    /** Wrapper for the {@link BurgerMenu} */
    @FXML private Button gameMenuButton;

    /** One third of the animated {@link BurgerMenu} */
    @FXML private Rectangle gameMenuButton__rectangel1;

    /** One third of the animated {@link BurgerMenu} */
    @FXML private Rectangle gameMenuButton__rectangel2;

    /** One third of the animated {@link BurgerMenu} */
    @FXML private Rectangle gameMenuButton__rectangel3;

    /** Button to close the game menu */
    @FXML private Button exitButton__drawer;

    /** Node with the purpose to dim the background */
    @FXML private AnchorPane darkOverlay;

    /** Game menu wrapper */
    @FXML private GridPane drawer;

    /** Wrapper for the contents of {@link Notification} */
    @FXML private AnchorPane notification;

    /** Wrapper for the {@link Avatar}, objects extending {@link Meter}, etc. */
    @FXML private AnchorPane dashBoard;

    /** Child node used in {@link Notification} */
    @FXML private Label notificationTitle;

    /** Child node used in {@link Notification} */
    @FXML private Label notificationText;

    /** Child node used in {@link Avatar} to hold an image */
    @FXML private ImageView avatarImage;

    /** GUI component used in object extending {@link Meter} */
    @FXML private ProgressBar barFuel;

    /** GUI component used in object extending {@link Meter} */
    @FXML private Label labelFuel;

    /** GUI component used in object extending {@link Meter} */
    @FXML private Label labelFuelTitle;

    /** GUI component used in object extending {@link Meter} */
    @FXML private Label labelBackpackTitle;

    /** GUI component used in object extending {@link Meter} */
    @FXML private ProgressBar barBackpack;

    /** GUI component used in object extending {@link Meter} */
    @FXML private Label labelBackpack;

    /** Most outer node used in {@link MiniMap} */
    @FXML private AnchorPane miniMapWrapper;

    /** GridPane containing the UI  with exception of overlays*/
    @FXML private GridPane interfaceGrid;

    @FXML
    private Label hintLabel;

    @FXML
    private Button hints;

    /**
     * Constructor.
     * @param domain reference to domain logic.
     */
    public GameController(Domain domain) {
        super(domain);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {

        innersceneHandler = new Innerscene(subsceneWrapper);
        miniMapHandler = new MiniMap(interfaceGrid);
        GridPane.setColumnIndex(miniMapHandler.getElement(), interfaceGrid.getColumnConstraints().size()-1);
        miniMapHandler.renderPlanets(getDomain().getPlayer().getPlanets());
        layout(miniMapHandler);

        inventoryHandler = new Backpack(wrapper, this);
        inventoryHandler.layout();

/*
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < getDomain().getPlayer().getIInventory().getIContent().length; j++) {
                System.out.println(getDomain().getPlayer().getIInventory().getIContent()[j]);
                getDomain().getPlayer().getIInventory().getIContent()[j] = null;
            }
        }
*/


        configGameMenuButton();
        configDrawer();
        configNotification();
        configAvatar();
        configFuelBar();
        configBackpackBar();
        configHoverLabel();
        configPlanetView();

        wrapper.setStyle("-fx-background-color: #081519;");


        innersceneHandler.getSubScene().heightProperty().bind(subsceneWrapper.heightProperty());
        innersceneHandler.getSubScene().widthProperty().bind(subsceneWrapper.widthProperty());
        innersceneHandler.renderPlanets(getDomain().getPlayer().getPlanets());



        wrapper.requestFocus();

        planetCollisions = new boolean[Planet.getPlanets().size()];
        //configMiniMap();

       // configMiniMap();

        configDialog();




        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            //miniMapHandler.maintainRatio(newValue.doubleValue(), stage);
        };
        getStage().widthProperty().addListener(stageSizeListener);
        getStage().heightProperty().addListener(stageSizeListener);



        // CONSUME SPACE KEYEVENT
        wrapper.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
            if(key.getCode() == KeyCode.SPACE){
                key.consume();
                landOnPlanet();

                

            }
        });

        backpackHandler.getBar().setOnMouseClicked(event -> showBackpack());



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                tick();
            }
        };
        timer.start();


    }

    /**
     * Method that invokes the layout method on an element.
     * @param element object of type {@link InterfaceElement} to be rendered.
     */
    private void layout(InterfaceElement element){
        element.layout();
    }

    @FXML
    void displayDrawer(ActionEvent event) { drawerHandler.showDrawer();}

    @FXML
    void closeDrawer(ActionEvent event) {
        drawerHandler.hideDrawer();
        hintLabel.setText("");
    }

    @FXML
    void gameMenuButtonHovered(MouseEvent event) { burgerMenuHandler.burgerMenuHover(); }

    @FXML
    void gameMenuButtonNormal(MouseEvent event) { burgerMenuHandler.burgerMenuNormal();}

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
        planetViewHandler.leavePlanet();
        hoverLabelHandler.show();
        miniMapHandler.show();
        fuelHandler.show();
        backpackHandler.show();
    }

    /**
     * {@inheritDoc}
     */
    public void landOnPlanet(){
        if(!planetViewHandler.isVisible()) {
            if (playerCollidingdWithPlanet) {
                if (getDomain().movePlayerToPlanet(currentPlanet.getName().replace(" ", ""))) {
                    notificationHandler.loadNotification(getDomain().getMessageContainer().getMessage());
                    showNotification();
                    miniMapHandler.hide();
                    planetViewHandler.leavePlanet();
                    String planetImage = currentPlanet.getImage().toURI().toString().replace("\\", "/");
                    planetViewHandler.landOnPlanet(currentPlanet.getName(), currentPlanet.getDescription(), planetImage);
                    hoverLabelHandler.hide();
                } else {
                    notificationHandler.loadNotification(getDomain().getMessageContainer().getMessage());
                    showNotification();
                }
            }
        }
    }

    public void tickPlanetView(){
        planetViewHandler.tickLists();
    }

    public void showBackpack(){
        inventoryHandler.layout();
        inventoryHandler.show();
    }
    public void hideBackpack(){
        inventoryHandler.hide();
    }


    NPC npc;
    int index;
    NPCAction currentAction;

    public void setAnswer(boolean answerYes){
        if(answerYes){
            ((NPCDialogAction) currentAction).setAnswer(true);
        } else{
            ((NPCDialogAction) currentAction).setAnswer(false);
        }

        getDomain().endInteract(npc, index);

        if(index < npc.getActions().length - 1){
            index = ((NPCDialogAction) currentAction).getActionId() == -1 ? ++index : ((NPCDialogAction) currentAction).getActionId();
            dialogHandler.clear();
            startInteract(npc, index);
        } else{
            // TERMINATE INTERACTION
            dialogHandler.clear();
        }

        planetViewHandler.tickLists();

    }

    public void nextAction(){
        getDomain().endInteract(npc, index);
        if(index < npc.getActions().length - 1){
            index++;
            dialogHandler.clear();
            startInteract(npc, index);
        } else if(index >= npc.getActions().length - 1){
            dialogHandler.clear();
        }
    }

    public void startInteract(NPC npc, int index){
        miniMapHandler.hide();
        fuelHandler.hide();
        backpackHandler.hide();

        this.npc = npc;
        this.index = index;

        INPCAction[] actions = npc.getActions();

        getDomain().startInteract(npc, index);

        if(actions[index] instanceof NPCDialogAction){
            currentAction = (NPCDialogAction) actions[index];
            dialogHandler.updateDialog(npc.getName(), currentAction.getMessage(), npc.getImage().toURI().toString().replace("\\", "/"));
            dialogHandler.addChoice(true);
        } else {
            currentAction = (NPCAction) actions[index];
            dialogHandler.updateDialog(npc.getName(), currentAction.getMessage(), npc.getImage().toURI().toString().replace("\\", "/"));
            dialogHandler.addChoice(false);
        }

    }



    @FXML
    void displayHints(ActionEvent event) {
        hintLabel.setText("1. Find the Blacksmith `Gearhead`\n       -located on one of the nearby planets");

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {


        if (getDomain().getPlayer().getMorphId() == -1){
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


        getDomain().getPlayer().setCoordX(innersceneHandler.getPlayer().getView().getTranslateX());
        getDomain().getPlayer().setCoordY(innersceneHandler.getPlayer().getView().getTranslateY());

        miniMapHandler.update(getDomain().getPlayer().getCoordX(), getDomain().getPlayer().getCoordY());

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
                playerCollidingdWithPlanet = true;
                break;
            } else {
                playerCollidingdWithPlanet = false;
            }
        }

        if(playerCollidingdWithPlanet){
            if(!planetViewHandler.isVisible()) {
                hoverLabelHandler.show();
            }

        } else if (!playerCollidingdWithPlanet){
            hoverLabelHandler.hide();
            currentPlanet = null;
        }



        if (innersceneHandler.getPlayer().isAccelerate()){
            getDomain().decreaseFuelOnMove(60);
        }

    }



    public void configGameMenuButton(){ burgerMenuHandler = new BurgerMenu(gameMenuButton, gameMenuButton__rectangel1, gameMenuButton__rectangel2, gameMenuButton__rectangel3); }

    public void configDrawer(){ drawerHandler = new Drawer(drawer, darkOverlay); }

    public void configNotification(){ notificationHandler = new Notification(notification, notificationTitle, notificationText, getStage().getHeight()); }

    public void hideNotification(){ notificationHandler.hideNotification(); }

    public void showNotification(){ notificationHandler.showNotification(dashBoard.getHeight() - 100);}

    public void configAvatar(){ avatarHandler = new Avatar(avatarImage);}

    public void configFuelBar(){ fuelHandler = new FuelBar(barFuel, labelFuelTitle, labelFuel); }

    public void configBackpackBar(){ backpackHandler = new BackpackBar(barBackpack, labelBackpackTitle, labelBackpack);}

    //public void configMiniMap(){ miniMapHandler = new MiniMap(miniMapWrapper, innersceneHandler.getPlayer()); }

    public void configHoverLabel(){ hoverLabelHandler = new HoverLabel(wrapper); hoverLabelHandler.setup("Press", "SPACE", "to land on planet");}

    public void configPlanetView(){ planetViewHandler = new PlanetView(subsceneWrapper, this);}

   // public void configInnerscene(){ innersceneHandler = new Innerscene(subScene);}

    public void configDialog(){ dialogHandler = new Dialog(subsceneWrapper, this);}


}

