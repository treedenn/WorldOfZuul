package UI.gameComponents;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Avatar{

    private ImageView imageView;
    private List<String> imagePaths1;
    private List<String> imagePaths2;
    private static final File rickdir = new File("./src/UI/resources/img/ricksHead");
    private static final File mortydir = new File("./src/UI/resources/img/mortysHead");

    Timer timer;

    private boolean isRick, wasRickBefore;


    public Avatar(ImageView imageView){
        this.imageView = imageView;
        imagePaths1 = new ArrayList<>();
        imagePaths2 = new ArrayList<>();
        isRick = true;
        loadIMGs();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                changeAvatarIMG();
            }
        }, 100,8000);
    }

    private void loadIMGs() {
        if (rickdir.isDirectory()) {
            for (File f : rickdir.listFiles()) {
                String imgPath = f.toURI().toString().replace("\\", "/");
                imagePaths1.add(imgPath);
            }
        }
        if (mortydir.isDirectory()) {
            for (File f : mortydir.listFiles()) {
                String imgPath = f.toURI().toString().replace("\\", "/");
                imagePaths2.add(imgPath);
            }
        }
    }


    public void changeAvatarIMG(){
        if(isRick){
            int index = (int) (Math.random() * (imagePaths1.size()));
            imageView.setImage(new Image(imagePaths1.get(index)));
        } else{
            int index = (int) (Math.random() * (imagePaths2.size()));
            imageView.setImage(new Image(imagePaths2.get(index)));
        }
    }

    /**
     *
     * @param rick
     */
    public void isRick(boolean rick) {
        wasRickBefore = isRick ? true : false;
        isRick = rick;
        if(isRick != wasRickBefore){ changeAvatarIMG();}
    }

}
