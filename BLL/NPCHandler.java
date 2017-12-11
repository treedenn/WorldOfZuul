package BLL;

import BLL.entity.StationaryBlacksmith;
import BLL.entity.npc.*;
import BLL.entity.npc.actions.*;

import java.io.File;

/**
 * Handles all the functions to NPCs (No Player Characters) within the game.
 * It contains all the references and all the get methods.
 * In addition it sets the needed {@link BLL.entity.npc.actions.NPCActionCollection} to each NPC.
 */
public class NPCHandler {
	private Blacksmith blacksmith;
	private ProfessorPutricide professorPutricide;
	private StationaryBlacksmith stationaryBlacksmith;
	private UnoX unoX;
	private SpacePirate pirate;

	NPCHandler() {
		blacksmith = new Blacksmith();
		blacksmith.setImage(new File("./DAL/resource/images/npcs/gearhead.png"));
		professorPutricide = new ProfessorPutricide();
		professorPutricide.setImage(new File("./DAL/resource/images/npcs/profputri.png"));
		unoX = new UnoX();
		unoX.setImage(new File("./DAL/resource/images/npcs/unox.png"));
		pirate = new SpacePirate();
		pirate.setImage(new File("./DAL/resource/images/npcs/spacepirate.png"));
		stationaryBlacksmith = new StationaryBlacksmith();
		stationaryBlacksmith.setImage(new File("./DAL/resource/images/npcs/gearhead.png"));

		initActions();
	}

	/**
	 * Inits all the actions to the NPCs.
	 */
	private void initActions() {
		professorPutricide.setActions(new ProfessorPutricideAction());
		stationaryBlacksmith.setActions(new StationaryBlacksmithAction());
		blacksmith.setActions(new BlacksmithAction());
		pirate.setActions(new SpacePirateAction());
		unoX.setActions(new UnoXAction());
	}

	/**
	 * Gets the Blacksmith NPC.
	 * @return the blacksmith
	 */
	public Blacksmith getBlacksmith() {
		return blacksmith;
	}

	/**
	 * Gets the Professor Putricide NPC.
	 * @return the professor putricide
	 */
	public ProfessorPutricide getProfessorPutricide() {
		return professorPutricide;
	}

	/**
	 * Gets the Pirate NPC.
	 * @return the pirate
	 */
	public SpacePirate getPirate() {
		return pirate;
	}

	/**
	 * Gets the UnoX NPC.
	 * @return the unox
	 */
	public UnoX getUnoX() {
		return unoX;
	}

	/**
	 * Gets the stationary Blacksmith
	 * @return the stationary Blacksmith
	 */
	public StationaryBlacksmith getStationaryBlacksmith() {
		return stationaryBlacksmith;
	}

	public NPC getNPCById(int index) {
		switch(index) {
			case 0: return blacksmith;
			case 1: return professorPutricide;
			case 2: return stationaryBlacksmith;
			case 3: return pirate;
			default: return null;
		}
	}
}
