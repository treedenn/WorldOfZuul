package UI.refactoredUI.game;

import BLL.ACQ.Domain;
import UI.refactoredUI.backpack.Backpack;
import UI.refactoredUI.components.*;
import UI.refactoredUI.dashboard.Dashboard;
import UI.refactoredUI.drawer.Drawer;
import UI.refactoredUI.gameMap.GameMap;
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
    IGameMap map;
    IBackpack backpack;
    IHamburger hamburger;
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
        drawer = new Drawer();



    }

    @Override
    public void initialize(URL location, ResourceBundle resources){




        root.setStyle("-fx-background-color: #181818;");
        ComponentLoader.loadComponent(main,map.getView(),0,0,0,0,false);
        ComponentLoader.loadComponent(bottom, dashboard.getView(),0,0,0,0, false);
        ComponentLoader.loadComponent(menu, hamburger.getView(), 0,0,0,0,false);

        map.onMovement(data -> {
            domain.getPlayer().setCoordX(data.getKey());
            domain.getPlayer().setCoordY(data.getValue());
        });

        map.renderPlanets(domain.getPlayer().getPlanets());

        dashboard.setFuelValue(.5, "50 ud af 100!");
        dashboard.setBackpackValue(.5, "DAMN");

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


        dashboard.onBackpackBarClick(data -> System.out.println("Bar Clicked!"));
        dashboard.onHelp(data -> {
            //ComponentLoader.loadComponent(root, backpack.getView(), 0,0,0,0, true);
            //backpack.load(domain.getPlayer().getIInventory().getIContent());
            map.centerCamera(domain.getPlayer().getCoordX(), domain.getPlayer().getCoordY());
        });


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                tick();
            }
        };
        timer.start();

    }

    private void tick(){

        /**  */
        map.setSpaceshipCoordX(domain.getPlayer().getCoordX());
        map.setSpaceshipCoordX(domain.getPlayer().getCoordY());


    }


    @FXML
    void keyIsPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.W){
            map.accelerateSpaceship(true);
        }

        if(event.getCode() == KeyCode.A){
            map.rotateSpaceshipLeft(true);
        }

        if(event.getCode() == KeyCode.S){
            map.rotateSpaceshipRight(true);
        }

        if(event.getCode() == KeyCode.D){
            map.decelerateSpaceship(true);
        }

    }

    @FXML
    void keyIsReleased(KeyEvent event) {

    }

}

