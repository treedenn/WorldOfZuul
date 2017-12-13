package UI.refactoredUI.backpack;

import BLL.item.Item;
import BLL.item.ItemStack;
import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IBackpack;
import UI.refactoredUI.components.IEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Backpack extends Component implements IBackpack{

    private List<IEventListener<ItemStack>> useSubscribers = new ArrayList<>();
    private List<IEventListener<ItemStack>> dropSubscribers = new ArrayList<>();
    private List<IEventListener<?>> closeSubscribers = new ArrayList<>();

    @FXML
    private ListView<ItemStack> backpackItemList;

    @FXML
    private Button dropButton;

    @FXML
    private Button useButton;

    public Backpack() {
        super("backpack_view.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void tick() {

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void onUse(IEventListener<ItemStack> listener) {
        useSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDrop(IEventListener<ItemStack> listener) {
        dropSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClose(IEventListener<?> listener) {
        closeSubscribers.add(listener);
    }

    @FXML
    void closeBackpack(ActionEvent event) {
        closeSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void drop(ActionEvent event) {
        ItemStack selectedItem = backpackItemList.getSelectionModel().getSelectedItem();
        dropSubscribers.forEach(listener -> listener.onAction(selectedItem));

    }

    @FXML
    void use(ActionEvent event) {
        ItemStack selectedItem = backpackItemList.getSelectionModel().getSelectedItem();
        useSubscribers.forEach(listener -> listener.onAction(selectedItem));
    }

}
