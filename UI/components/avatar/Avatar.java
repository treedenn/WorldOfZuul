package UI.components.avatar;

import UI.components.icomponents.Component;
import UI.components.icomponents.IAvatar;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * This concrete GUI component controls the avatar picture.
 */
public class Avatar extends Component implements IAvatar {

    /** Reference to Timer class used to run change avatar method regularly. */
    private Timer timer;
    /** Reference to a list of image paths. */
    private List<String> rickImagePaths;
    /** Reference to a list of image paths. */
    private List<String> mortyImagePaths;
    /** String attribute holding the name of the current avatar. */
    private String currentAvatar;
    /** Boolean attribute true if the avatar has changed. */
    private boolean avatarHasChanged;
    /** Paths to directories containing resources. */
    private static final File rickDir = new File("./src/UI/resources/img/ricksHead");
    private static final File mortyDir = new File("./src/UI/resources/img/mortysHead");

    @FXML
    private ImageView avatar;

    /**
     * Constructor.
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAvatar(String name) {
        avatarHasChanged = !currentAvatar.equals(name);
        currentAvatar = name;
        if(avatarHasChanged) changeAvatar();
    }

    /**
     * Method to change avatar image to a random image a list of paths derived from a directory.
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
