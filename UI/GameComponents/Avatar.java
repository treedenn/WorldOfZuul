package UI.GameComponents;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Avatar{

    private ImageView imageView;
    private List<String> imagePaths;
    private static final File dir = new File("./src/UI/resources/img/ricksHead");
    Timer timer;

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
        if(dir.isDirectory()){
            for(final File f : dir.listFiles()){
                String imgPath = f.toURI().toString().replace("\\", "/");

                imagePaths.add(imgPath);
            }

        }
    }

    public void changeAvatarIMG(){
        int index = (int) (Math.random() * (imagePaths.size()));
        imageView.setImage(new Image(imagePaths.get(index)));
    }
}
