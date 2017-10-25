package DAL.character;

import DAL.character.player.Player;
import DAL.character.player.Recipe;
import DAL.world.Planet;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class Blacksmith extends Character {
	private Recipe recipe;
	private String[] visitedPlanets;

	public Blacksmith() {
		recipe = new Recipe();
		visitedPlanets = new String[4]; // used for traces
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void move() {
		LinkedHashMap<String, Planet> map = (LinkedHashMap<String, Planet>) getPlanets();

		Iterator<Planet> iterator = map.values().iterator();

		while(iterator.hasNext()) {
			if(iterator.next() == getCurrentPlanet()) {
				if(iterator.hasNext()) {
					setCurrentPlanet(iterator.next());
				} else {
					setCurrentPlanet(map.values().iterator().next());
				}

				pushTraces();
				visitedPlanets[0] = getCurrentPlanet().getName().toLowerCase();

				break;
			}
		}
	}

	private void pushTraces() {
		for(int i = visitedPlanets.length - 1; i > 0; i--) {
			visitedPlanets[i] = visitedPlanets[i - 1];
		}
	}

	public String getVisitMessage(String planetName) {
		String msg;
		int traceIndex = -1;

		for(int i = 0; i < visitedPlanets.length; i++) {
			if(visitedPlanets[i] != null) {
				if(visitedPlanets[i].equals(planetName.toLowerCase())) {
					traceIndex = i;
					break;
				}
			}
		}

		switch(traceIndex) {
			case 0: msg = "The Blacksmith is on the planet!";
				break;
			case 1: msg = "The Blacksmith just left this planet!";
				break;
			case 2: msg = "The Blacksmith was here recently.";
				break;
			case 3: msg = "The time has passed and the trace of the Blacksmith is almost gone!";
				break;
			default: msg = "No traces of the Blacksmith here!";
		}
		return msg;
	}
}
