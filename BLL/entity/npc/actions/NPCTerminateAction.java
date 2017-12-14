package BLL.entity.npc.actions;

/**
 * An action, that allows the GUI to determine when the actions should stop.
 * Whenever it meets this action, it should terminate afterwards (the message should be sent).
 * 'An alternative would be placing a boolean on the base'
 */
public class NPCTerminateAction extends NPCAction {
    /**
     * Constructs a new NPC Terminate action with a default message.
     * @param message default message
     */
    public NPCTerminateAction(String message) {
        super(message);
    }
}
