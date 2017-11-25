package BLL.ACQ;

import BLL.character.npc.NPC;

import java.util.List;

public interface IPlanet {
	String getName();
	String getDescription();
	String getArriveMessage();
	boolean getTempSearched();
	boolean getPermSearched();
	boolean hasSearched();
	List<NPC> getNPCs();
	double getX();
	double getY();
}
