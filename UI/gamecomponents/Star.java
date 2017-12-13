package UI.gamecomponents;

import UI.gamecomponents.subscene.gamemap.GameMap;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class Star {

    private Circle view;

    public Star(){
        createStar();
    }

    private void createStar(){
        Circle star = new Circle((int) (Math.random() * GameMap.mapWidth), (int) (Math.random() * GameMap.mapHeight),10 + (int)(Math.random() * ((80 - 10) + 1)));
        Stop[] stops = {new Stop(0, Color.WHITE), new Stop(0.02,Color.WHITE), new Stop(0.025,Color.rgb(255,255,255,0.2)), new Stop(1, Color.TRANSPARENT)};
        RadialGradient rg = new RadialGradient(0,0.1,star.getCenterX(), star.getCenterY(), star.getRadius(), false, CycleMethod.NO_CYCLE, stops);
        star.setFill(rg);
        star.setCache(true);

        view = star;

    }


    public Circle getView() {
        return view;
    }

}
