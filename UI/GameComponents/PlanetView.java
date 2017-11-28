package UI.GameComponents;

import BLL.character.npc.NPC;
import BLL.item.Item;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class PlanetView {

    private AnchorPane parent;
    private AnchorPane planetViewWrapper;
    private AnchorPane planetViewOverlay;
    private StackPane planetViewInnerWrapper;
    private VBox vbox;
    private HBox hbox;
    private HBox header;
    private ScrollPane scrollPane;
    private Region headerSpacer;
    private Label planetName;
    private Label planetDescription;
    private Button button__leavePlanet;
    private ListView<Item> itemList;
    private ListView<NPC> NPCList;



    public PlanetView(AnchorPane parent){
        this.parent = parent;
        planetViewWrapper = new AnchorPane();
        planetViewOverlay = new AnchorPane();
        planetViewInnerWrapper = new StackPane();
        vbox = new VBox();
        hbox = new HBox();
        header = new HBox();
        headerSpacer = new Region();
        scrollPane = new ScrollPane();
        planetName = new Label();
        planetDescription = new Label();
        button__leavePlanet = new Button();
        itemList = new ListView<>();
        NPCList = new ListView<>();

        layout();

    }

    private void layout(){
        planetViewOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.8);");

        planetViewWrapper.getChildren().add(planetViewOverlay);
        planetViewWrapper.setLeftAnchor(planetViewOverlay, 0.0);
        planetViewWrapper.setRightAnchor(planetViewOverlay, 0.0);
        planetViewWrapper.setTopAnchor(planetViewOverlay, 0.0);
        planetViewWrapper.setBottomAnchor(planetViewOverlay, 0.0);


        planetViewInnerWrapper.setAlignment(Pos.TOP_CENTER);
        planetViewWrapper.getChildren().add(planetViewInnerWrapper);
        planetViewWrapper.setLeftAnchor(planetViewInnerWrapper, 0.0);
        planetViewWrapper.setRightAnchor(planetViewInnerWrapper, 0.0);
        planetViewWrapper.setTopAnchor(planetViewInnerWrapper, 0.0);
        planetViewWrapper.setBottomAnchor(planetViewInnerWrapper, 0.0);

        planetViewInnerWrapper.getChildren().add(vbox);
        vbox.setPrefWidth(650);
        vbox.setMaxWidth(vbox.getPrefWidth());
        vbox.setMinWidth(0);
        vbox.setPrefHeight(500);
        vbox.setMaxHeight(500);
        vbox.setMinHeight(500);
        //vbox.setStyle("-fx-background-color: rgba(255,255,255,0.1);");

        header.getChildren().addAll(planetName, headerSpacer, button__leavePlanet);
        header.setHgrow(headerSpacer, Priority.ALWAYS);
        header.setMinWidth(vbox.getPrefWidth());
        header.setAlignment(Pos.CENTER);
        button__leavePlanet.setText("LEAVE PLANET");
        button__leavePlanet.getStyleClass().add("button__leavePlanet");
        planetName.setStyle("-fx-text-fill: white; -fx-font-size: 60; -fx-font-family: 'Circular Std Black';");



        hbox.getChildren().addAll(itemList, NPCList);
        Group scrollGroup = new Group(hbox, planetDescription);
        scrollPane.setContent(scrollGroup);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        vbox.getChildren().addAll(header, scrollPane);
        vbox.widthProperty().addListener((observable, oldValue, newValue) -> {
           header.setPrefWidth(newValue.doubleValue());
           header.setMinWidth(newValue.doubleValue());
           header.setMaxWidth(newValue.doubleValue());
           itemList.setPrefWidth(newValue.doubleValue()/2);
           itemList.setMinWidth(newValue.doubleValue()/2);
           itemList.setMaxWidth(newValue.doubleValue()/2);
           NPCList.setPrefWidth(newValue.doubleValue()/2);
           NPCList.setMinWidth(newValue.doubleValue()/2);
           NPCList.setMaxWidth(newValue.doubleValue()/2);
        });

        planetName.setText("J8 Ayrus Z420");


        parent.getChildren().add(planetViewWrapper);
        parent.setLeftAnchor(planetViewWrapper, 0.0);
        parent.setRightAnchor(planetViewWrapper, 0.0);
        parent.setTopAnchor(planetViewWrapper, 0.0);
        parent.setBottomAnchor(planetViewWrapper, 0.0);



    }

}