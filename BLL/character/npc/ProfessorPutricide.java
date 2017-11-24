package BLL.character.npc;

public class ProfessorPutricide extends ConcreteNPC implements NPC, Stationary {
    public ProfessorPutricide(){
        super();
    }

    @Override
    public String getName() {
        return "Professor Putricide";
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




