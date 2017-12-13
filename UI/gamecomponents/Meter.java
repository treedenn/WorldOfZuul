package UI.gameComponents;

import BLL.ACQ.Domain;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public abstract class Meter {

    private Domain domain;
    private ProgressBar bar;
    private Label title;
    private Label label;

    public Meter(Domain domain, ProgressBar bar, Label title, Label label){
        this.domain = domain;
        this.bar = bar;
        this.label = label;
        this.title = title;
    }

    public Domain getDomain() {
        return domain;
    }

    public ProgressBar getBar() {
        return bar;
    }

    public Label getLabel() {
        return label;
    }

    public abstract void update();

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
