package UI.refactoredUI.components;

public interface IHighscore extends IComponent {

    // Events
    void onSaveHighscore(IEventListener<String> listener);

    // Methods
    void load(double score, boolean win);

}
