package UI.GameComponents;

import BLL.ACQ.IItemStack;
import BLL.item.ItemStack;
import UI.controller.GameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

import java.util.List;

public class Backpack extends InterfaceElement {

    private GameController controller;
    private AnchorPane backpackWrapper;
    private StackPane backpackInnerWrapper;
    private VBox backpackVBox;
    private HBox headerHbox;
    private Region headerSpacer;
    private Label backpackTitle;
    private Button exit;
    private ListView<IItemStack> inventory;
    private ObservableList<IItemStack> items;
    private HBox buttonGroup;
    private Button dropButton;
    private Button useButton;
    private Button inspectButton;

    /**
     * Constructor.
     */
    public Backpack(Pane parent, GameController controller){super(parent); this.controller = controller;}

    /**
     * {@inheritDoc}
     */
    @Override
    public void layout() {
        backpackWrapper = new AnchorPane();
        backpackInnerWrapper = new StackPane();
        backpackVBox = new VBox();
        headerHbox = new HBox();
        headerSpacer = new Region();
        backpackTitle = new Label("Your backpack");
        exit = new Button();
        inventory = new ListView<>();
        buttonGroup = new HBox();
        dropButton = new Button("Drop");
        useButton = new Button("Use");
        inspectButton = new Button("Inspect");

        inventory.setStyle("-fx-background-color: transparent; -fx-border-color: #282828;");
        backpackTitle.setStyle("-fx-text-fill: white; -fx-font-size: 24; -fx-font-family: 'Circular Std Black';");

        buttonGroup.getChildren().addAll(dropButton, inspectButton, useButton);
        buttonGroup.setHgrow(dropButton, Priority.ALWAYS);
        buttonGroup.setHgrow(inspectButton, Priority.ALWAYS);
        buttonGroup.setHgrow(useButton, Priority.ALWAYS);
        dropButton.getStyleClass().add("backpackButton--dropButton");
        inspectButton.getStyleClass().add("backpackButton--inspectButton");
        useButton.getStyleClass().add("backpackButton--useButton");
        ObservableList<Button> children = (ObservableList)buttonGroup.getChildren();
        for (Button child : children) {
            child.setDisable(true);
        }
        buttonGroup.widthProperty().addListener((observable, oldValue, newValue) -> {;
            for (Button child : children) {
                child.setPrefWidth(newValue.doubleValue() / 3);
            }
        });

        headerHbox.getChildren().addAll(backpackTitle, headerSpacer, exit);
        headerHbox.setHgrow(headerSpacer, Priority.ALWAYS);
        SVGPath cross = new SVGPath();
        cross.setContent("M19,6.4L17.6,5L12,10.6L6.4,5L5,6.4l5.6,5.6L5,17.6L6.4,19l5.6-5.6l5.6,5.6l1.4-1.4L13.4,12L19,6.4z");
        cross.setFill(Color.rgb(255,255,255));
        exit.setGraphic(cross);
        exit.setStyle("-fx-background-color: transparent;");
        exit.getStyleClass().add("buttonScale");

        backpackVBox.getChildren().addAll(headerHbox, inventory, buttonGroup);
        backpackVBox.setFillWidth(true);
        backpackVBox.setSpacing(25);
        backpackVBox.setMaxWidth(600);
        backpackVBox.setMaxHeight(500);
        backpackVBox.setStyle("-fx-padding: 10; -fx-background-color: #181818; -fx-background-radius: 8 8 8 8;");
        backpackVBox.setEffect(new DropShadow());

        backpackInnerWrapper.getChildren().add(backpackVBox);
        backpackInnerWrapper.setAlignment(Pos.CENTER);

        backpackWrapper.getChildren().add(backpackInnerWrapper);
        backpackWrapper.setLeftAnchor(backpackInnerWrapper, 0.);
        backpackWrapper.setRightAnchor(backpackInnerWrapper, 0.);
        backpackWrapper.setTopAnchor(backpackInnerWrapper, 0.);
        backpackWrapper.setBottomAnchor(backpackInnerWrapper, 0.);

        getElement().getChildren().add(backpackWrapper);



        // Load ListView with items!
 //      items = FXCollections.observableArrayList(controller.getDomain().getPlayer().getIInventory().getIContent());
//       inventory.setItems(items);
        inventory.setCellFactory(param -> {return new InventoryFormatCell();});
        inventory.setOnMouseClicked(event -> {
            if(inventory.getSelectionModel().getSelectedItem() != null){
                for (Button child : children) {
                    child.setDisable(false);
                }
            } else{
                for (Button child : children) {
                    child.setDisable(true);
                }
            }
        });

        // Event handlers
        exit.setOnAction(event -> {
            hide();
        });

        dropButton.setOnAction(event -> {
            IItemStack selectedItem = inventory.getSelectionModel().getSelectedItem();
            if(selectedItem != null){
                controller.getDomain().getPlayer().getIInventory().remove((ItemStack) selectedItem);
            }
        });

        inspectButton.setOnAction(event -> {
            IItemStack selectedItem = inventory.getSelectionModel().getSelectedItem();
            if(selectedItem != null){
                System.out.println(selectedItem.getIItem().getName());
                System.out.println(selectedItem.getIItem().getDescription());
                System.out.println(selectedItem.getIItem().getWeight());
            }
        });

        useButton.setOnAction(event -> {
            IItemStack selectedItem = inventory.getSelectionModel().getSelectedItem();
            if(selectedItem != null){
                controller.getDomain().useItem(selectedItem);
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        layout();
        //addInterfaceElement(getParent(), getElement());
        getParent().getChildren().add(backpackWrapper);

        ((AnchorPane) getParent()).setLeftAnchor(backpackWrapper, 0.);
        ((AnchorPane) getParent()).setRightAnchor(backpackWrapper, 0.);
        ((AnchorPane) getParent()).setTopAnchor(backpackWrapper, 0.);
        ((AnchorPane) getParent()).setBottomAnchor(backpackWrapper, 0.);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        if(backpackWrapper.getChildren().size() > 0){
            backpackWrapper.getChildren().clear();
        }
        parent.getChildren().remove(backpackWrapper);
    }


    /**
     * Inner class that extends {@link ListCell}, overriding the updateItem method.
     * The updateItem method is called whenever the item in the cell changes.
     */
    public class InventoryFormatCell extends ListCell<IItemStack> {
        public InventoryFormatCell(){}

        @Override
        protected void updateItem(IItemStack itemStack, boolean empty) {
            super.updateItem(itemStack, empty);
            setText(itemStack == null? " " : itemStack.getIItem().getName());
            setTextFill(new Color(1.,1.,1.,1.));

            if(itemStack != null){

            }

        }
    }


}
