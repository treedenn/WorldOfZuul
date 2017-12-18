package UI.components.surface;

import BLL.ACQ.IItemStack;
import BLL.entity.npc.NPC;
import UI.components.icomponents.Component;
import UI.components.icomponents.IEventListener;
import UI.components.icomponents.ISurface;
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

/**
 * This concrete GUI handles the spaceships view.
 */
public class Surface extends Component implements ISurface {

    /** List of observers */
    List<IEventListener> onExitSubscribers = new ArrayList<>();
    /** List of observers */
    List<IEventListener<IItemStack>> onPickupSubscribers = new ArrayList<>();
    /** List of observers */
    List<IEventListener> onSearchSubscribers = new ArrayList<>();
    /** List of observers */
    List<IEventListener<NPC>> onInteractSubscribers = new ArrayList<>();
    /** List of observers */
    List<IEventListener> onTickListSubscribers = new ArrayList<>();

    /** This list holds the reference to the items to be loaded into the component. */
    private ObservableList<IItemStack> items;
    /** This list holds the reference to the NPCs to be loaded into the component. */
    private ObservableList<NPC> characters;
    /** This attribute holds the reference to the currently selected item. */
    private IItemStack selectedItem;
    /** This attribute is true if the planet has been searched. */
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

    /**
     * Constructor.
     * {@inheritDoc}
     */
    public Surface(){super("surface_view.fxml");}

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroller.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            image.setOpacity(1 - (newValue.doubleValue())*2);
        });
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIsSearched(boolean isSearched) {
        isPermSearched = isSearched;
        notSearchedLabel.setVisible(false);
        isSearchedLabel.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onExit(IEventListener listener) {
        onExitSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void OnTickList(IEventListener listener) {
        onTickListSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPickup(IEventListener<IItemStack> listener) {
        onPickupSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSearch(IEventListener listener) {
        onSearchSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInteract(IEventListener<NPC> listener) {
        onInteractSubscribers.add(listener);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * Method to notify all subscribers of the onExit()-event.
     * @param event the type of event.
     */
    @FXML
    void exit(ActionEvent event) {
        onExitSubscribers.forEach(listener -> listener.onAction(null));
    }


    /**
     * Method to notify all subscribers of the onPickup()-event.
     * @param event the type of event.
     */
    @FXML
    void pickup(ActionEvent event) {
        IItemStack selectedItem = itemList.getSelectionModel().getSelectedItem();
        if(selectedItem != null) onPickupSubscribers.forEach(listener -> listener.onAction(selectedItem));
    }

    /**
     * Method to notify all subscribers of the onSearch()-event.
     * @param event the type of event.
     */
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

    /**
     * Method to notify all subscribers of the onInteract()-event.
     * @param event the type of event.
     */
    @FXML
    void interact(MouseEvent event) {
        NPC selectedCharacter = characterList.getSelectionModel().getSelectedItem();
        if(selectedCharacter != null) onInteractSubscribers.forEach(listener -> listener.onAction(selectedCharacter));
    }

    /**
     * Method to toggle disability of pickup button.
     * @param event the type of event.
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
