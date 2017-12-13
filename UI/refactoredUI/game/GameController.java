package UI.refactoredUI.game;

import BLL.ACQ.Domain;
import BLL.ACQ.IItemStack;
import BLL.Game;
import BLL.item.Item;
import BLL.item.ItemStack;
import DAL.Model;
import UI.refactoredUI.backpack.Backpack;
import UI.refactoredUI.components.IBackpack;
import UI.refactoredUI.components.IDashboard;
import UI.refactoredUI.dashboard.Dashboard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable{

    /** Reference to the business logic. */
    private Domain domain;
    IBackpack backpack;
    IDashboard dashboard;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane menu;

    @FXML
    private AnchorPane map;

    @FXML
    private AnchorPane bottom;

    public GameController(Domain domain) {
        this.domain = domain;

        // TODO: REMOVE DEBUG
        domain.init();

        backpack = new Backpack();
        dashboard = new Dashboard();


        //backpack.load(domain.getPlayer().getIInventory().getIContent());
        backpack.onUse(data -> { if (domain.useItem(data)) backpack.load(domain.getPlayer().getIInventory().getIContent());});
        backpack.onDrop(data -> {
            domain.dropItem(data);
            backpack.load(domain.getPlayer().getIInventory().getIContent());
        });
        backpack.onClose(data -> root.getChildren().remove(backpack.getView()));


        dashboard.onBackpackBarClick(data -> System.out.println("Bar Clicked!"));
        dashboard.onHelp(data -> System.out.println("Help Clicked!"));



    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        //root.getChildren().add(backpack.getView());
        bottom.getChildren().add(dashboard.getView());

    }

    @FXML
    void keyIsPressed(KeyEvent event) {

    }

    @FXML
    void keyIsReleased(KeyEvent event) {

    }

}

