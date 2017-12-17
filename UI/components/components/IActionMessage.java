package UI.components.components;

public interface IActionMessage extends IComponent {

    /**
     * Method to load component with texts.
     * @param getureText    type of gesture to perform. "Press", for instance.
     * @param keyHint   the key hinted to be pressed. "SPACE", for instance.
     * @param actionText    the performing action. "to open the door", for instance.
     */
    void setup(String getureText, String keyHint, String actionText);
}
