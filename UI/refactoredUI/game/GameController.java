package UI.refactoredUI.game;

import BLL.ACQ.Domain;
import UI.refactoredUI.backpack.Backpack;
import UI.refactoredUI.components.*;
import UI.refactoredUI.dashboard.Dashboard;
import UI.refactoredUI.drawer.Drawer;
import UI.refactoredUI.gameMap.GameMap;
import UI.refactoredUI.guide.Guide;
import UI.refactoredUI.hamburger.Hamburger;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable{

    /** Reference to the business logic. */
    private Domain domain;
    /** Reference to the window */
    private Stage stage;
    /** Attribute holding time value. */
    private double deltaTime;

    IGameMap map;
    IBackpack backpack;
    IHamburger hamburger;
    IGuide guide;
    IDrawer drawer;
    IDashboard dashboard;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane menu;

    @FXML
    private AnchorPane bottom;

    public GameController(Domain domain, Stage stage) {
        this.domain = domain;
        this.stage = stage;

        // TODO: REMOVE DEBUG
        domain.init();

        map = new GameMap();
        backpack = new Backpack();
        dashboard = new Dashboard();
        hamburger = new Hamburger();
        guide = new Guide();
        drawer = new Drawer();



    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ComponentLoader.loadComponent(main,map.getView(),0,0,0,0,false);
        ComponentLoader.loadComponent(bottom, dashboard.getView(),0,0,0,0, false);
        ComponentLoader.loadComponent(menu, hamburger.getView(), 0,0,0,0,false);

        map.onMovement(data -> {
            domain.getPlayer().setCoordX(data.getKey());
            domain.getPlayer().setCoordY(data.getValue());
        });

        guide.onClose(data -> ComponentLoader.removeComponent(guide.getView()));

        map.renderPlanets(domain.getPlayer().getPlanets());

        hamburger.onOpenNav(data -> ComponentLoader.loadComponent(root, drawer.getView(), 0,-1,0,-1,true));

        drawer.onClose(data -> ComponentLoader.removeComponent(drawer.getView()));
        drawer.onMaximizeMinimize(data -> {
            stage.setFullScreen(!stage.isFullScreen());
            drawer.setWindowState(stage.isFullScreen() ? "WINDOWED" : "FULLSCREEN");
        });


        backpack.onUse(data -> { if (domain.useItem(data)) backpack.load(domain.getPlayer().getIInventory().getIContent());});
        backpack.onDrop(data -> {
            domain.dropItem(data);
            backpack.load(domain.getPlayer().getIInventory().getIContent());
        });

        backpack.onClose(data -> ComponentLoader.removeComponent(backpack.getView()));


        dashboard.onBackpack(data -> {
            ComponentLoader.loadComponent(root, backpack.getView(), 0, 0, 0, 0, true);
            backpack.load(domain.getPlayer().getIInventory().getIContent());

        });

        dashboard.onHelp(data -> {
            ComponentLoader.loadComponent(root, guide.getView(),0,0,0,0,true);
        });

        /** Method that handles continuation of game etc. */
        init();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                tick();
            }
        };
        timer.start();

    }

    private void tick(){
        deltaTime = System.nanoTime();
        map.tick(deltaTime);
        map.keepWithinBoundaries(domain.getPlayer().getCoordX(), domain.getPlayer().getCoordY());
        if(map.getSpaceship().isMoving()) domain.decreaseFuelOnMove(60);
        init();
    }

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

    private void init(){
        dashboard.setFuelValue(domain.getPlayer().getFuel()/domain.getPlayer().getMaxFuel(), String.format(" %.2f %% ", domain.getPlayer().getFuel()/domain.getPlayer().getMaxFuel()*100));
        dashboard.setBackpackValue(domain.getPlayer().getIInventory().getCurrentCapacity()/domain.getPlayer().getIInventory().getMaxCapacity(), String.format(" [%.1f / %.1f] Kg", domain.getPlayer().getIInventory().getCurrentCapacity(), domain.getPlayer().getIInventory().getMaxCapacity()));
    }

}

