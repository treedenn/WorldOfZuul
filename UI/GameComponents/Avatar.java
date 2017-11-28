package UI.GameComponents;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Avatar{

    private ImageView imageView;
    private List<String> imagePaths;
    private static final File rickdir = new File("./src/UI/resources/img/ricksHead");
    private static final File mortydir = new File("./src/UI/resources/img/ricksHead/mortyshead");
    Timer timer;

    boolean isRick;

    public Avatar(ImageView imageView){
        this.imageView = imageView;
        imagePaths = new ArrayList<>();
        loadIMGs();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                changeAvatarIMG();
            }
        }, 100,8000);
    }

    private void loadIMGs(){
        if(isRick){
            if(rickdir.isDirectory()){
                for(final File f : rickdir.listFiles()){
                    String imgPath = f.toURI().toString().replace("\\", "/");

                    imagePaths.add(imgPath);
                }
            }
        } else{
            if(mortydir.isDirectory()){
                for(final File f : mortydir.listFiles()){
                    String imgPath = f.toURI().toString().replace("\\", "/");

                    imagePaths.add(imgPath);
                }
            }
        }
    }

    public void changeAvatarIMG(){
        int index = (int) (Math.random() * (imagePaths.size()));
        imageView.setImage(new Image(imagePaths.get(index)));
    }

    public void isRick(boolean rick) {
        isRick = rick;
    }
}

