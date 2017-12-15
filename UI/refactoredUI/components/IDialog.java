package UI.refactoredUI.components;

import BLL.ACQ.INPCAction;

public interface IDialog extends IComponent {


    // Events
    void onLeave(IEventListener listener);

    void onAnswer(IEventListener<Boolean> listener);

    void onContinue(IEventListener listener);

    void onQuizAnswer(IEventListener<Integer> listener);

    // Methods
    void loadCharacterInformation(String characterName, String message, String imagePath);

    void addChoices(INPCAction action);

    void allowLeaveOption(boolean leaveOption);




}
