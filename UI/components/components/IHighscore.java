package UI.components.components;

public interface IHighscore extends IComponent {

    // Events
    void onSaveHighscore(IEventListener<String> listener);


    // Events
    void onExit(IEventListener<String> listener);

    // Methods
    void load(double score, boolean win);

}
