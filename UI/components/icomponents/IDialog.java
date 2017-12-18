package UI.components.icomponents;

import BLL.entity.npc.actions.NPCAction;

/**
 * This interface defines the contract between components of type {@link IDialog} and a parent controller.
 */
public interface IDialog extends IComponent {

    /**
     * Method to add an event listener.
     * Event to be fired when an answer is selected.
     * @param listener  listener to be added
     */
    void onAnswer(IEventListener<Boolean> listener);

    /**
     * Method to add an event listener.
     * Event to be fired when a dialogue is continued.
     * @param listener  listener to be added
     */
    void onContinue(IEventListener listener);

    /**
     * Method to add an event listener.
     * Event to be fired when a quiz answer is selected.
     * @param listener  listener to be added
     */
    void onQuizAnswer(IEventListener<Integer> listener);

    /**
     * Method to load dialog component with information about the interacting non-player character.
     * @param characterName name of the NPC.
     * @param message   the NPC's current message.
     * @param imagePath path to the image resource of the NPC.
     */
    void loadCharacterInformation(String characterName, String message, String imagePath);

    /**
     * Method to add choices based on the current type dialogue.
     * @param action    the current NPC action.
     */
    void addChoices(NPCAction action);

}
