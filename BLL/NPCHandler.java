package BLL;

import BLL.entity.npc.*;

import java.io.File;
import java.io.FileNotFoundException;

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

	/**
	 * Constructs the NPC handler.
	 */
	NPCHandler() {
		blacksmith = new Blacksmith();
		stationaryBlacksmith = new StationaryBlacksmith();
		professorPutricide = new ProfessorPutricide();
		unoX = new UnoX();
		pirate = new SpacePirate();

		try {
			blacksmith.setImage(new File("/DAL/resource/images/npcs/gearhead.png"));
			stationaryBlacksmith.setImage(new File("/DAL/resource/images/npcs/gearhead.png"));
			professorPutricide.setImage(new File("/DAL/resource/images/npcs/profputri.png"));
			unoX.setImage(new File("/DAL/resource/images/npcs/unox.png"));
			pirate.setImage(new File("/DAL/resource/images/npcs/spacepirate.png"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
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
			case 1: return stationaryBlacksmith;
			case 2: return professorPutricide;
			case 3: return pirate;
			case 4: return unoX;
			default: return null;
		}
	}
}
