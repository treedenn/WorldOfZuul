package BLL;

import BLL.character.npc.Blacksmith;
import BLL.character.npc.ProfessorPutricide;
import BLL.character.npc.SpacePirate;
import BLL.character.npc.actions.ProfessorPutricideAction;
import BLL.character.npc.UnoX;

public class NPCHandler {
	private Blacksmith blacksmith;
	private ProfessorPutricide professorPutricide;
	private UnoX unoX;
	private SpacePirate pirate;

	public NPCHandler() {
		blacksmith = new Blacksmith();
		professorPutricide = new ProfessorPutricide();
		unoX = new UnoX();
		pirate = new SpacePirate();

		initActions();
	}

	private void initActions() {
		professorPutricide.setActions(new ProfessorPutricideAction());
	}

	public Blacksmith getBlacksmith() {
		return blacksmith;
	}

	public ProfessorPutricide getProfessorPutricide() {
		return professorPutricide;
	}

	public SpacePirate getPirate() {
		return pirate;
	}

	public UnoX getUnoX() {
		return unoX;
	}
}
