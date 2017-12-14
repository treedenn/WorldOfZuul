package UI.refactoredUI.avatar;

import UI.refactoredUI.components.Component;
import UI.refactoredUI.components.IAvatar;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Avatar extends Component implements IAvatar {

    /** Used to run change avatar method regularly. */
    private Timer timer;
    private List<String> rickImagePaths;
    private List<String> mortyImagePaths;
    private String currentAvatar;
    private boolean avatarHasChanged;
    /** Paths to directories containing resources. */
    private static final File rickDir = new File("./src/UI/resources/img/ricksHead");
    private static final File mortyDir = new File("./src/UI/resources/img/mortysHead");


    @FXML
    private ImageView avatar;

    public Avatar(){
        super("avatar_view.fxml");
        timer = new Timer();
        rickImagePaths = new ArrayList<>();
        mortyImagePaths = new ArrayList<>();
        currentAvatar = "rick";
        loadImages();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                changeAvatar();
            }
        }, 100, 3000);
    }

    @Override
    public void setAvatar(String name) {
        avatarHasChanged = currentAvatar.equals(name) ? false : true;
        currentAvatar = name;
        if(avatarHasChanged) changeAvatar();
    }

    /**
     * Method to change avatar image.
     */
    public void changeAvatar() {
        if(currentAvatar.equals("rick")) {
            int index = (int) (Math.random() * (rickImagePaths.size()));
            avatar.setImage(new Image(rickImagePaths.get(index)));
        }
        if(currentAvatar.equals("morty")) {
            int index = (int) (Math.random() * (mortyImagePaths.size()));
            avatar.setImage(new Image(mortyImagePaths.get(index)));
        }
    }

    /**
     * Method to load paths for each image into collections.
     */
    private void loadImages(){
        if(rickDir.isDirectory()){
            for (File file : rickDir.listFiles()) {
                String imgPath = file.toURI().toString().replace("\\", "/");
                rickImagePaths.add(imgPath);
            }
        }
        if(mortyDir.isDirectory()){
            for (File file : mortyDir.listFiles()) {
                String imgPath = file.toURI().toString().replace("\\", "/");
                mortyImagePaths.add(imgPath);
            }
        }
    }
}
