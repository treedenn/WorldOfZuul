package UI.refactoredUI.surface;

import BLL.ACQ.IItemStack;
import BLL.entity.npc.NPC;
import UI.SearchTask;
import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IEventListener;
import UI.refactoredUI.components.ISurface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Surface extends Component implements ISurface {

    List<IEventListener> onExitSubscribers = new ArrayList<>();
    List<IEventListener<IItemStack>> onPickupSubscribers = new ArrayList<>();
    List<IEventListener> onSearchSubscribers = new ArrayList<>();
    List<IEventListener<NPC>> onInteractSubscribers = new ArrayList<>();
    List<IEventListener> onTickListSubscribers = new ArrayList<>();

    private ObservableList<IItemStack> items;
    private ObservableList<NPC> characters;
    private IItemStack selectedItem;
    private boolean isPermSearched;
    private Task task;

    @FXML
    private Label name;

    @FXML
    private Label description;

    @FXML
    private AnchorPane image;

    @FXML
    private Label notSearchedLabel;

    @FXML
    private Label isSearchedLabel;

    @FXML
    private ScrollPane scroller;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonSearch;

    @FXML
    private ProgressBar barSearch;

    @FXML
    private Button buttonPickup;

    @FXML
    private ListView<IItemStack> itemList;

    @FXML
    private ListView<NPC> characterList;

    public Surface(){super("surface_view.fxml");}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroller.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            image.setOpacity(1 - (newValue.doubleValue())*2);
        });
    }

    @Override
    public void setup(String planetName, String planetDescription, String imagePath, boolean isSearched) {
        name.setText(planetName);
        description.setText(planetDescription);
        isPermSearched = isSearched;
        String backgroundImage =  "-fx-background-image: url(" + imagePath + ");";
        image.setStyle(backgroundImage);
        if(isPermSearched){
            onTickListSubscribers.forEach(listener -> listener.onAction(null));
            isSearchedLabel.setVisible(true);
            notSearchedLabel.setVisible(false);
        } else {
            itemList.getItems().clear();
            characterList.getItems().clear();
            isSearchedLabel.setVisible(false);
            notSearchedLabel.setVisible(true);
        }
    }

    @Override
    public void setIsSearched(boolean isSearched) {
        isPermSearched = isSearched;
        notSearchedLabel.setVisible(false);
        isSearchedLabel.setVisible(true);
    }

    @Override
    public void onExit(IEventListener listener) {
        onExitSubscribers.add(listener);
    }

    @Override
    public void OnTickList(IEventListener listener) {
        onTickListSubscribers.add(listener);
    }

    @Override
    public void onPickup(IEventListener<IItemStack> listener) {
        onPickupSubscribers.add(listener);
    }

    @Override
    public void onSearch(IEventListener listener) {
        onSearchSubscribers.add(listener);
    }

    @Override
    public void onInteract(IEventListener<NPC> listener) {
        onInteractSubscribers.add(listener);
    }

    @Override
    public void tickList(IItemStack[] items, List<NPC> characters) {
        this.items = FXCollections.observableArrayList(items);
        itemList.setItems(this.items);

        List<NPC> goodNPCS = new ArrayList<>();
        for (NPC character : characters) {
            if(character.isGood()) goodNPCS.add(character);
        }
        this.characters = FXCollections.observableArrayList(goodNPCS);
        characterList.setItems(this.characters);
        characterList.setCellFactory(param -> {return new CharacterFormatCell();});
    }

    @FXML
    void exit(ActionEvent event) {
        onExitSubscribers.forEach(listener -> listener.onAction(null));
    }

    @FXML
    void pickup(ActionEvent event) {
        IItemStack selectedItem = itemList.getSelectionModel().getSelectedItem();
        if(selectedItem != null) onPickupSubscribers.forEach(listener -> listener.onAction(selectedItem));
    }

    @FXML
    void search(ActionEvent event) {
        if (task == null || !task.isRunning()) {
            task = new SearchTask(isPermSearched);

            barSearch.setPrefWidth(buttonSearch.getWidth() * 5.7 / 10);
            barSearch.setVisible(true);
            buttonSearch.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            buttonSearch.setText("");

            Thread th = new Thread(task);

            barSearch.progressProperty().bind(task.progressProperty());
            task.setOnSucceeded(event1 -> {
                buttonSearch.setContentDisplay(ContentDisplay.TEXT_ONLY);
                buttonSearch.setText("SEARCH PLANET");
                barSearch.setVisible(false);
                barSearch.setPrefWidth(0);
                onSearchSubscribers.forEach(listener -> listener.onAction(null));
            });

            th.start();
        }
    }

    @FXML
    void interact(MouseEvent event) {
        NPC selectedCharacter = characterList.getSelectionModel().getSelectedItem();
        if(selectedCharacter != null) onInteractSubscribers.forEach(listener -> listener.onAction(selectedCharacter));
    }

    /**
     * Method to toggle disability of pickup button.
     * @param event
     */
    @FXML
    void selectItem(MouseEvent event) {
        if(selectedItem == itemList.getSelectionModel().getSelectedItem()){
            itemList.getSelectionModel().select(null);
            selectedItem = null;
            buttonPickup.setDisable(true);
        } else {
            selectedItem = itemList.getSelectionModel().getSelectedItem();
            buttonPickup.setDisable(false);
        }
    }


    /**
     * Innerclass overriding the updateItem method for cells in {@link ListView};
     * The updateItem method is called whenever the item in the cell changes.
     */
    public class CharacterFormatCell extends ListCell<NPC> {
        private ImageView imageView = new ImageView();

        public CharacterFormatCell() {
        }

        @Override
        protected void updateItem(NPC item, boolean empty) {
            super.updateItem(item, empty);
            setText(item == null ? " " : item.getName());
            setTextFill(new Color(1., 1., 1., 1.));

            if (item != null) {
                if(item.getImage() != null) {
                    try {
                        imageView.setImage(new Image(item.getImage().getPath()));
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                imageView.setFitHeight(40);
                imageView.setPreserveRatio(true);
                setGraphic(imageView);
            }

        }
    }





}
