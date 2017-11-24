package BLL.character.npc;

public class ProfessorPutricide extends SpecificNPC implements NPC, Stationary {
    public ProfessorPutricide(){
        super();

    }

    @Override
    public String getName() {
        return "ProfessorPutricide";
    }

    @Override
    public boolean isGood() {
        return true;
    }

    @Override
    public boolean isStationary() {
        return true;
    }


}




