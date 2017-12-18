package UI.components.backpack;

import BLL.ACQ.IItemStack;
import UI.components.icomponents.Component;
import UI.components.icomponents.IBackpack;
import UI.components.icomponents.IEventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This concrete GUI handles the backpack view.
 */
public class Backpack extends Component implements IBackpack{

    /** List of observers */
    private List<IEventListener<IItemStack>> onUseSubscribers = new ArrayList<>();
    /** List of observers */
    private List<IEventListener<IItemStack>> dropSubscribers = new ArrayList<>();
    /** List of observers */
    private List<IEventListener<?>> closeSubscribers = new ArrayList<>();
    /** ObservableList holds objects of type {@link IItemStack} to be loaded into the backpack view. */
    private ObservableList<IItemStack> inventory;
    /** This attribute holds a reference to the current selceted {@link IItemStack} */
    private IItemStack selectedItem;

    @FXML
    private StackPane backpackOuterWrapper;

    @FXML
    private ListView<IItemStack> backpackItemList;

    @FXML
    private Button dropButton;

    @FXML
    private Button useButton;

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public Backpack() { super("backpack_view.fxml");}

    /**
     * Constructor.
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /** Setup {@link backpackItemList} cell factory. */
        backpackItemList.setCellFactory(param -> {return new InventoryFormatCell();});


        /** Handle click events on the {@link backpackItemList}. */
        backpackItemList.setOnMouseClicked(event -> {
            if(selectedItem == backpackItemList.getSelectionModel().getSelectedItem()){
                backpackItemList.getSelectionModel().select(null);
                selectedItem = null;
                dropButton.setDisable(true);
                useButton.setDisable(true);
            } else {
                selectedItem = backpackItemList.getSelectionModel().getSelectedItem();
                dropButton.setDisable(false);
                useButton.setDisable(false);
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUse(IEventListener<IItemStack> listener) {
        onUseSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDrop(IEventListener<IItemStack> listener) {
        dropSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClose(IEventListener<?> listener) {
        closeSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(IItemStack[] items) {
        inventory = FXCollections.observableArrayList(items);
        backpackItemList.setItems(inventory);
    }

    /**
     * Method to notify every subscriber of the onClose()-event.
     * @param event the type of user event.
     */
    @FXML
    void closeBackpack(ActionEvent event) {
        closeSubscribers.forEach(listener -> listener.onAction(null));
    }

    /**
     * Method to notify every subscriber of the onDrop()-event.
     * @param event the type of user event.
     */
    @FXML
    void drop(ActionEvent event) {
        IItemStack selectedItem = backpackItemList.getSelectionModel().getSelectedItem();
        dropSubscribers.forEach(listener -> listener.onAction(selectedItem));
    }

    /**
     * Method to notify every subscriber of the onUse()-event.
     * @param event the type of user event.
     */
    @FXML
    void use(ActionEvent event) {
        IItemStack selectedItem = backpackItemList.getSelectionModel().getSelectedItem();
        onUseSubscribers.forEach(listener -> listener.onAction(selectedItem));
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
            setText(itemStack == null? " " : itemStack.toString());
            setTextFill(new Color(1.,1.,1.,1.));

            if(itemStack != null){

            }

        }
    }
}
