package UI.gamecomponents;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public abstract class Meter {

    private ProgressBar bar;
    private Label title;
    private Label label;

    public Meter(ProgressBar bar, Label title, Label label){
        this.bar = bar;
        this.label = label;
        this.title = title;
    }

    public void update(){
        System.out.println("Override update functionality");
    }

    public ProgressBar getBar() {
        return bar;
    }

    public Label getLabel() {
        return label;
    }

    public void hide(){
        bar.setVisible(false);
        label.setVisible(false);
        title.setVisible(false);
    }

    public void show(){
        bar.setVisible(true);
        label.setVisible(true);
        title.setVisible(true);
    }

}
