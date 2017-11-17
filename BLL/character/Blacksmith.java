package BLL.character;

import BLL.Persistent;
import BLL.character.player.Recipe;
import BLL.item.Item;
import BLL.world.Planet;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class Blacksmith extends Character {
	private Recipe recipe;
	private String[] visitedPlanets;

	public Blacksmith() {
		recipe = null;
		visitedPlanets = new String[4]; // used for traces
	}
    
    public boolean hasAccepted(char c) {
        return c == 'Y' || c == 'y';
    }
        
    public String[] getBlacksmithMsg() {
        return new String[] {
            "[Blacksmith]: My dear Rick!",
            "It's already time to return the favor?",
            "I've heard that you somehow broke your portal gun?",
            "Here's a list of items needed for you to repair it!",
            "--------------",
        };
    }
    
    public String[] getLockedMsg() {
        return new String[] {
            "[BlackSmith]: Hello Fellow, I'm the blacksmith, name's Gearhead!",
            "Dear adventurer, I believe our meeting is Fate!",
            "I ran out of fuel and is now stranded on this planet ...",
            "Please spare me some fuel, I will be in your debt and do anything in return!",
            "[Would you like to help Gearhead, enter Y/N]",
        };
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
			case 3: msg = "Time has passed and the trace of the Blacksmith is almost gone!";
				break;
			default: msg = "No traces of the Blacksmith here!";
		}
		return msg;
	}

	private void generateRecipeRequirements(Persistent model) {
		// TODO: Call function when spoken to Blacksmith for the firs time.

		final int liquids = 14; // 0 -> 14
		final int canisters = 12; // 14 -> 26
		final int gears = 14; // 26 -> 40
		final int cpus = 16; // 40 -> 56

		Item[] requirements = new Item[4];

		requirements[0] = model.getItemById((int) (Math.random() * liquids));
		requirements[1] = model.getItemById((int) (liquids + Math.random() * canisters));
		requirements[2] = model.getItemById((int) (liquids + canisters + Math.random() * gears));
		requirements[3] = model.getItemById((int) (liquids + canisters + gears + Math.random() * cpus));

		recipe = new Recipe(requirements);

//		requirements[0] = Model.getItemById(0);
//		requirements[1] = Model.getItemById(liquids);
//		requirements[2] = Model.getItemById(liquids + canisters);
//		requirements[3] = Model.getItemById(liquids + canisters + gears);

	}
}
