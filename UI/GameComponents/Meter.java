package UI.GameComponents;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public abstract class Meter {

    private ProgressBar bar;
    private Label label;

    public Meter(ProgressBar bar, Label label){
        this.bar = bar;
        this.label = label;
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
}
