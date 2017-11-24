package BLL.character.npc.actions;

import BLL.character.player.Player;

public class ProfessorPutricideAction implements NPCActionCollection {
    private NPCAction[] actions;

    public ProfessorPutricideAction() {
        actions = new NPCAction[] {
            new NPCAction() {
                @Override
                public void endEvent(Player player) {
                    super.endEvent(player);
                }
            },
            new NPCAction() {
                @Override
                public void endEvent(Player player) {
                    super.endEvent(player);
                }
            },
            new NPCAction() {
                @Override
                public void endEvent(Player player) {
                    super.endEvent(player);
                }
            }
        };
    }

    @Override
    public NPCAction[] getActions() {
        return actions;
    }
}
