package UI.components.game;

import BLL.ACQ.Domain;
import BLL.ACQ.IPlanet;
import BLL.entity.npc.NPC;
import BLL.entity.npc.actions.NPCAction;
import BLL.entity.npc.actions.NPCDialogAction;
import BLL.entity.npc.actions.NPCQuizAction;
import BLL.entity.npc.actions.NPCTerminateAction;
import UI.components.actionmessage.ActionMessage;
import UI.components.backpack.Backpack;
import UI.components.icomponents.*;
import UI.components.dashboard.Dashboard;
import UI.components.dialog.Dialog;
import UI.components.drawer.Drawer;
import UI.components.gameMap.GameMap;
import UI.components.guide.Guide;
import UI.components.hamburger.Hamburger;
import UI.components.highscore.Highscore;
import UI.components.launcher.LauncerController;
import UI.components.notification.Notification;
import UI.components.surface.Surface;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Super controller for the entire game view.
 */
public class GameController implements Initializable{

    /** Reference to the business logic. */
    private Domain domain;
    /** Reference to the window */
    private Stage stage;

    /** String attribute to keep track of name of planet the spaceship is hovering over. */
    private String planetHovering;

    /** Reference to the current interacting NPC. */
    private NPC interactingNPC;

    /** Attribute holding the current index of action during interaction with NPCs. */
    private int actionIndex;

    /** Attribute holding the current object of type {@link NPCAction}. */
    private NPCAction currentAction;

    /** Boolean attribute holds information about visibility of the action message component.  */
    private boolean actionMessageIsVisible;

    /** Object of type AnimationTimer used to tick components at a fixed tick rate. */
    private AnimationTimer animationTimer;

    /** Reference to the nested {@link IGameMap} component. */
    private IGameMap map;
    /** Reference to the nested {@link IBackpack} component. */
    private IBackpack backpack;
    /** Reference to the nested {@link IHamburger} component. */
    private IHamburger hamburger;
    /** Reference to the nested {@link IGuide} component. */
    private IGuide guide;
    /** Reference to the nested {@link IDrawer} component. */
    private IDrawer drawer;
    /** Reference to the nested {@link IDashboard} component. */
    private IDashboard dashboard;
    /** Reference to the nested {@link ISurface} component. */
    private ISurface surface;
    /** Reference to the nested {@link IActionMessage} component. */
    private IActionMessage actionMessage;
    /** Reference to the nested {@link INotification} component. */
    private INotification notification;
    /** Reference to the nested {@link IDialog} component. */
    private IDialog dialog;
    /** Reference to the nested {@link IHighscore} component. */
    private IHighscore highscore;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane menu;

    @FXML
    private AnchorPane bottom;

    /**
     * Constructor.
     * @param domain instance of the domain logic.
     * @param stage reference to the application window.
     */
    public GameController(Domain domain, Stage stage) {
        this.domain = domain;
        this.stage = stage;
        map = new GameMap();
        backpack = new Backpack();
        dashboard = new Dashboard();
        hamburger = new Hamburger();
        guide = new Guide();
        drawer = new Drawer();
        surface = new Surface();
        actionMessage = new ActionMessage();
        notification = new Notification();
        dialog = new Dialog();
        highscore = new Highscore();
        planetHovering = "";
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // Consume KeyEvent for Space and Escape.
        root.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
            if(key.getCode() == KeyCode.SPACE) {
                key.consume();
                spaceIsPressed();
            }
            if(key.getCode() == KeyCode.ESCAPE){
                key.consume();
                escapeIsPressed();
            }
        });


        // Loads the map, dashboard and hamburger component after initialization.
        ComponentLoader.loadComponent(main,map.getView(),0,0,0,0,false);
        ComponentLoader.loadComponent(bottom, dashboard.getView(),0,0,0,0, false);
        ComponentLoader.loadComponent(menu, hamburger.getView(), 0,0,0,0,false);

        // Subscribing to events of the map component.
        map.onMovement(data -> {
            domain.getPlayer().setCoordX(data.getKey());
            domain.getPlayer().setCoordY(data.getValue());
        });
        map.renderPlanets(domain.getPlayer().getPlanets());

        // Subscribing to events of the guide component.
        guide.onClose(data -> ComponentLoader.removeComponent(guide.getView()));

        // Subscribing to events of the hamburger component.
        hamburger.onOpenNav(data -> ComponentLoader.loadComponent(root, drawer.getView(), 0,-1,0,-1,true));

        // Subscribing to events of the drawer component.
        drawer.onClose(data -> ComponentLoader.removeComponent(drawer.getView()));
        drawer.onMaximizeMinimize(data -> {
            stage.setFullScreen(!stage.isFullScreen());
            drawer.setWindowState(stage.isFullScreen() ? "WINDOWED" : "FULLSCREEN");
        });

        drawer.onSaveAndQuit(data -> {
            domain.save();
            try {
                switchGameView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Subscribing to events of the dashboard component.
        dashboard.onHelp(data -> {
            ComponentLoader.loadComponent(root, guide.getView(),0,0,0,0,true);
        });

        dashboard.onBackpack(data -> {
            ComponentLoader.loadComponent(root, backpack.getView(), 0, 0, 0, 0, true);
            backpack.load(domain.getPlayer().getIInventory().getIContent());
        });

        // Subscribing to events of the backpack component.
        backpack.onUse(data -> {
            if (domain.useItem(data)) backpack.load(domain.getPlayer().getIInventory().getIContent());
            checkMessageContainer();
        });

        backpack.onClose(data -> ComponentLoader.removeComponent(backpack.getView()));

        backpack.onDrop(data -> {
            if(domain.dropItem(data)) backpack.load(domain.getPlayer().getIInventory().getIContent());
            checkMessageContainer();
        });

        // Subscribing to events of the surface component.

        surface.onExit(data -> {
            ComponentLoader.removeComponent(surface.getView());
            domain.planetExit();
            enableMovement();
        });

        surface.onPickup(data -> {
            domain.pickupItem(data);
            checkMessageContainer();
            surface.tickList(domain.getPlayer().getCurrentPlanet().getInventory().getIContent(), domain.getPlayer().getCurrentPlanet().getNPCs());
        });


        surface.OnTickList(data -> surface.tickList(domain.getPlayer().getCurrentPlanet().getInventory().getIContent(), domain.getPlayer().getCurrentPlanet().getNPCs()));

        surface.onSearch(data -> {
            surface.setIsSearched(domain.searchPlanet());
            checkMessageContainer();
            surface.tickList(domain.getPlayer().getCurrentPlanet().getInventory().getIContent(), domain.getPlayer().getCurrentPlanet().getNPCs());
        });

        surface.onInteract(data -> {
            ComponentLoader.loadComponent(root,dialog.getView(),0,0,bottom.getHeight(),0,true);
            startInteract(data, 0);
        });

        // Subscribing to events of the dialog component.
        dialog.onContinue(data -> nextAction());

        dialog.onAnswer(data -> setAnswer(data) );

        dialog.onQuizAnswer(data -> setQuizAnswer(data));

        // Subscribing to events of the highscore component.

        highscore.onSaveHighscore(data -> {
            domain.addPlayerToHighscore(data);
            domain.deleteLoadingFile();
            try {
                switchGameView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tickBars();
        highscore.onExit(data -> {
            domain.deleteLoadingFile();
            try {
                switchGameView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // Checks the domain for new messages to the user.
        checkMessageContainer();

        // Starts the fixed tick rate for rendering the game.
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                tick();
            }
        };
        animationTimer.start();

    }

    /**
     * Method to be called at a fixed tick rate by the instantiated {@link AnimationTimer}.
     * This method is important for fluid rendering of the game.
     */
    private void tick(){
        if(domain.isGameFinished()){
            ComponentLoader.loadComponent(root, highscore.getView(), 0,0,0,0,true);
            highscore.load(domain.getPlayerScore(), domain.hasWonTheGame());
            highscore.getView().toFront();
            animationTimer.stop();
        }

        if (domain.getPlayer().getMorphId() == -1){
            dashboard.getAvatar().setAvatar("rick");
        } else{
            dashboard.getAvatar().setAvatar("morty");
        }
        NPC initiatorNPC = domain.interaction();
        if(initiatorNPC != null) {
            ComponentLoader.loadComponent(root,dialog.getView(),0,0,bottom.getHeight(),0,true);
            startInteract(initiatorNPC, 0);
        }
        domain.updateBuffs();
        double deltaTime = System.nanoTime();
        map.tick(deltaTime);
        map.keepWithinBoundaries(domain.getPlayer().getCoordX(), domain.getPlayer().getCoordY());
        if(map.getSpaceship().isMoving()) domain.decreaseFuelOnMove(60);
        int count = 0;
        int countOnTrue = 0;
        /** Collision detection: Sets {@link planetHovering} to planet name if collision is detected. */
        for (IGlobe globe : map.getPlanetsOnGameMap()) {
            if(((Component) globe).isColliding((Component) map.getSpaceship())){
                planetHovering = globe.name().replace(" ", "");
                countOnTrue = count;
                break;
            } else if(!planetHovering.equals("")){
                if(count >= countOnTrue) planetHovering = "";
            }
            count++;
        }

        if(!planetHovering.equals("") && !actionMessageIsVisible){
            ComponentLoader.loadComponent(root, actionMessage.getView(),16,50,-1,50,true);
            actionMessage.setup("Press", "SPACE", "to land on planet");
            actionMessageIsVisible = true;
        } else if (planetHovering.equals("") && actionMessageIsVisible){
            ComponentLoader.removeComponent(actionMessage.getView());
            actionMessageIsVisible = false;
        }

        tickBars(); // Keep fuel and backpack value updated.
    }

    /**
     * Method to handle key press events.
     * @param event the key press event.
     */
    @FXML
    void keyIsPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.W){
            map.accelerateSpaceship(true, false);
        }

        if(event.getCode() == KeyCode.A){
            map.rotateSpaceshipLeft(true);
        }

        if(event.getCode() == KeyCode.S){
            map.accelerateSpaceship(true, true);
        }

        if(event.getCode() == KeyCode.D){
            map.rotateSpaceshipRight(true);
        }
    }

    /**
     * Method invoked when the space key is pressed to customize the functionality of space.
     */
    void spaceIsPressed(){
        if(planetHovering != "" && domain.getPlayer().getCurrentPlanet() == null && !domain.isGameFinished()){
            if(domain.planetEnter(planetHovering)){
                disableMovement();
                ComponentLoader.removeComponent(backpack.getView());
                ComponentLoader.loadComponent(root,surface.getView(),0,0,bottom.getHeight(),0,true);
                IPlanet currentPlanet = domain.getPlayer().getCurrentPlanet();
                String planetName = currentPlanet.getName();
                String planetDescription = currentPlanet.getDescription();
                String imagePath = currentPlanet.getImage().toURI().toString().replace("\\", "/");
                boolean permSearched = currentPlanet.hasSearched();
                surface.setup(planetName,planetDescription,imagePath,permSearched);
            }
            checkMessageContainer();
        }
    }

    /**
     * Method to be called when escape is pressed to avoid unwanted side effects.
     */
    private void escapeIsPressed(){
       drawer.setWindowState(stage.isFullScreen() ? "WINDOWED" : "FULLSCREEN");
    }

    /**
     * Method to handle when keys are released.
     * @param event the key press event.
     */
    @FXML
    void keyIsReleased(KeyEvent event) {
        if(event.getCode() == KeyCode.W){
            map.accelerateSpaceship(false, false);
            map.decelerateSpaceship(true);
        }

        if(event.getCode() == KeyCode.A){
            map.rotateSpaceshipLeft(false);
        }

        if(event.getCode() == KeyCode.S){
            map.accelerateSpaceship(false, true);
            map.decelerateSpaceship(true);
        }

        if(event.getCode() == KeyCode.D){
            map.rotateSpaceshipRight(false);

        }

    }

    /**
     * Method to update fuel and backpack bar.
     */
    private void tickBars(){
        dashboard.setFuelValue(domain.getPlayer().getFuel()/domain.getPlayer().getMaxFuel(), String.format(" %.2f %% ", domain.getPlayer().getFuel()/domain.getPlayer().getMaxFuel()*100));
        dashboard.setBackpackValue(domain.getPlayer().getIInventory().getCurrentCapacity()/domain.getPlayer().getIInventory().getMaxCapacity(), String.format(" [%.1f / %.1f] Kg", domain.getPlayer().getIInventory().getCurrentCapacity(), domain.getPlayer().getIInventory().getMaxCapacity()));
    }

    /**
     * Method to disable space ship movement.
     */
    private void disableMovement(){
        map.getSpaceship().setMoveable(false);
    }

    /**
     * Method to enable space ship movement.
     */
    private void enableMovement(){
        map.getSpaceship().setMoveable(true);
    }

    /**
     * Method to display message from domain via notification component.
     */
    private void checkMessageContainer(){
        if(domain.getMessageContainer().hasMessage()){
            String message = domain.getMessageContainer().getMessage();
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(message.length()*100)));
            ComponentLoader.loadComponent(root, notification.getView(), -1,0, bottom.getHeight(), 0, true);
            notification.setMessage(message);
            notification.getView().toFront();
            timeline.play();
            timeline.setOnFinished(event -> ComponentLoader.removeComponent(notification.getView()));
        }
    }

    /**
     * Method used to handle the start of an interactino with a NPC.
     * @param npc   the npc to interact with.
     * @param index the index of the npc's current action (0 on start).
     */
    private void startInteract(NPC npc, int index){
        if(npc != null) {
            disableMovement();
            interactingNPC = npc;
            actionIndex = index;
            NPCAction[] actions = npc.getActions();
            domain.startInteract(interactingNPC, actionIndex);
            if (actions[actionIndex] instanceof NPCDialogAction) {
                currentAction = actions[actionIndex];
            } else {
                currentAction = actions[actionIndex];
            }
            dialog.loadCharacterInformation(npc.getName(), currentAction.getMessage(), npc.getImage().getPath().replace("\\", "/"));
            dialog.addChoices(currentAction);
        }
    }

    /**
     * Method to handle the continuation of an interaction between the player and a NPC.
     */
    private void nextAction(){
        domain.endInteract(interactingNPC, actionIndex);
        if(currentAction instanceof NPCTerminateAction){
            ComponentLoader.removeComponent(dialog.getView());
            enableMovement();
        } else if (actionIndex < interactingNPC.getActions().length -1){
            actionIndex++;
            startInteract(interactingNPC, actionIndex);
        } else if(actionIndex >= interactingNPC.getActions().length - 1){
            ComponentLoader.removeComponent(dialog.getView());
            enableMovement();
        }
    }

    /**
     * Method to communicate with the NPC with boolean values.
     * @param answerYes
     */
    private void setAnswer(Boolean answerYes){
        if(answerYes){
            ((NPCDialogAction) currentAction).setAnswer(true);
        } else{
            ((NPCDialogAction) currentAction).setAnswer(false);
        }
        domain.endInteract(interactingNPC, actionIndex);
        checkMessageContainer();
        if(actionIndex < interactingNPC.getActions().length - 1){
            actionIndex = ((NPCDialogAction) currentAction).getActionId() == -1 ? ++actionIndex: ((NPCDialogAction) currentAction).getActionId();
            startInteract(interactingNPC, actionIndex);
        } else{
            ComponentLoader.removeComponent(dialog.getView());
            enableMovement();
        }
    }

    /**
     * Method to set the chosen quiz answer on interaction with the UnoX NPC.
     * @param i
     */
    private void setQuizAnswer(int i){
        NPCQuizAction npcQuizAction = (NPCQuizAction) currentAction;
        npcQuizAction.setAnswer(i);
        nextAction();
        checkMessageContainer();
    }

    /**
     * Method to switch back to the game launcher on exit, game won or game lost.
     * @throws IOException
     */
    private void switchGameView() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/components/launcher/launcher_view.fxml"));
        LauncerController controller = new LauncerController(domain,stage);
        loader.setController(controller);
        Pane newRoot = loader.load();
        Scene scene = new Scene(newRoot, newRoot.getPrefWidth(), newRoot.getPrefHeight());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);

    }

}

