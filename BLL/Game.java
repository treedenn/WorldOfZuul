package BLL;

import BLL.character.ProfessorPutricide;
import BLL.scoring.Score;
import DAL.Model;
import BLL.character.Blacksmith;
import BLL.character.player.*;
import BLL.item.Item;
import BLL.item.ItemStack;
import BLL.item.PortalGun;
import BLL.scoring.ScoreHandler;
import BLL.world.Planet;
import UI.command.Command;
import UI.command.CommandWord;
import UI.ConsoleView;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game implements Domain {
	private static Game INSTANCE;

	private ConsoleView view;
	private Persistent model;

	private boolean finished;
	private boolean gameWon;
    private boolean trapped;
	private Player player;
	private Blacksmith blacksmith;
	private ProfessorPutricide professorPutricide;
	private UnoX manager;
	private ScoreHandler scoreHandler;
	private SpacePirate manager2;

	private Game() {
		view = new ConsoleView();
		model = Model.getInstance();

		finished = false;
		gameWon = false;
		player = new Player();
        blacksmith = new Blacksmith();
        professorPutricide = new ProfessorPutricide();
		manager = new UnoX();
		scoreHandler = new ScoreHandler();
		manager2=new SpacePirate();
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	/* function to begin game */
	private void start() {
		init();

		view.println(welcomeMessage());
		view.println(player.getCurrentPlanet().getDescription());
		view.println("\n[ Planets: " + player.getPlanetNames() + "]");
        
		gameLoop();
	}

	private void init() {
		Map<String, Planet> planetMap = model.getPlanets();
		Planet[] planets = planetMap.values().toArray(new Planet[planetMap.size()]);

		Planet centerUniverse = new Planet("Center of the Universe", "This is not exactly the center, since a black hole exists in the center of every Universe.");
		player.setCurrentPlanet(centerUniverse);
		player.setPlanets(planetMap);
		blacksmith.setCurrentPlanet(planets[(int) (Math.random() * planets.length)]);
		blacksmith.setPlanets(planetMap);
		manager.setQuizes(model.getQuizes());
		addCluesToPlanets();
        trapped = true;
	}

	private void gameLoop() {
		while (!finished) {
			Command command = view.getParser().getCommand();
			view.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			processCommand(command);
			view.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		}

		gameIsFinished();
	}

	/* function containing the actions of a command */
	private void processCommand(Command command) {
		CommandWord commandWord = command.getCommandWord();

		switch(commandWord) {
			case UNKNOWN:
				view.println("I don't know what you mean...");
			case HELP:
				view.println(helpMessage());
                view.println(blacksmith.getCurrentPlanet().getName());
                //view.println(professorPutricide.getCurrentPlant().getName());
				break;
            case INFO:
                view.println(descriptionMessage());
                view.println(hintMessage());
                break;
			case GO:
				goPlanet(command);
				break;
			case PICKUP:
				pickupItem(command);
				break;
			case DROP:
				dropItem(command);
				break;
			case BACKPACK:
				showBackpackContent(command);
				break;
			case SEARCH:
				searchOnPlanet(command);
				break;
			case INTERACT:
				interact(command);
				break;
			case FUEL:
				showFuel(command);
				break;
			case QUIT:
				quit(command);
				break;
			case RESTART:
				restart(command);
		}
	}

	private void showFuel(Command command){
		double fuel = player.getFuel();
		StringBuilder sb = new StringBuilder();

		char[] fuelBars = new char[10];
		Arrays.fill(fuelBars, ' ');

		for(int i = 0; i < fuel/10; i++){
			fuelBars[i] = '=';
		}

		sb.append("Fuel: \n");
		sb.append("[");
		sb.append(fuelBars);
		sb.append("]");
		view.println(sb.toString());
	}

	private void interact(Command command) {
		if(command.hasArguments()) {
			view.println("Interact does not need any arguments.");
		} else {
			if(!player.getCurrentPlanet().getTempSearched()) {
				view.println("You have not searched the planet!");
			} else {
				if(!blacksmith.samePlanet(player.getCurrentPlanet())) {
					view.println("The blacksmith is not here.");
				} else {
                    int trappedInt = trapped ? 1 : 2;
                    switch(trappedInt) {
                        case 1:
                            view.println(blacksmith.getLockedMsg());
                            if(blacksmith.hasAccepted(view.getParser().getQuizOfferAnswer())) {
                                player.decreaseFuel(10);
                                view.println("You chose to help Gearhead! Fuel has decreased by 10!");
                                trapped = false;
                            }
                            view.getParser().resetReader();
                            break;
                        case 2:
                            view.println(blacksmith.getBlacksmithMsg());
                            Recipe recipe = blacksmith.getRecipe();
                            Item[] items = recipe.getRequirements();
                            boolean[] containItems = recipe.haveItems(player.getBackpack().getContent());

                            for(int i = 0; i < items.length; i++) {
                                view.println((containItems[i] ? "[\u2713] " : "[\u2715] ") + "XXXXXXXX " + items[i].getItemType().name());
                            }

                            if(allTrue(containItems)) {
                                // TODO: remove items from the players backpack
                                view.println("");
                                view.println("Portalgun has been repaired!");
                                player.getBackpack().getPortalGun().repair();
                            }
                            break;
                    }
//                    view.println(lockedBlacksmith.getBlacksmithMsg());
//					Recipe recipe = lockedBlacksmith.getRecipe();
//					Item[] items = recipe.getRequirements();
//					boolean[] containItems = recipe.haveItems(player.getBackpack().getContent());
//
//					for(int i = 0; i < items.length; i++) {
//						view.println((containItems[i] ? "[\u2713] " : "[\u2715] ") + "XXXXXXXX " + items[i].getItemType().name());
//					}
//
//					if(allTrue(containItems)) {
//						// TODO: remove items from the players backpack
//						view.println("");
//						view.println("Portalgun has been repaired!");
//						player.getBackpack().getPortalGun().repair();
//					}
				}
			}
		}
	}

	private void searchOnPlanet(Command command) {
		if(command.hasArguments()) {
			view.println("Search does not need any argument.");
		} else {
			Planet planet = player.getCurrentPlanet();

			if(planet.hasSearched()) {
				view.println("Planet has already been searched!");
			} else {
				view.println("Searching planet...");

				/* loading phase... */

				char[] loading = new char[10];
				Arrays.fill(loading, ' ');

				long sleep;

				for(int i = 0; i < loading.length; i++) {
					loading[i] = '=';

					if(!planet.getPermSearched()) {
						sleep = (long) (300 * (-0.022 * i * i + 0.20 * i + 0.15));
					} else {
						sleep = (long) (300 * (-0.004329 * i * i + 0.02165 * i + 0.15));
					}

					//System.out.println("Sleep: " + sleep);

					try {
						TimeUnit.MILLISECONDS.sleep(sleep);
						view.print("\r["+ new String(loading) +"]");
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}

					if(i == loading.length - 1) { view.println(""); }
				}

				view.println("Search complete!");
				view.println("----------------");
				view.println(planet.getContentDescription());
				view.println("");
                if(!trapped) { 
                    view.println(blacksmith.getVisitMessage(player.getCurrentPlanet().getName())); 
                } else if (player.samePlanet(blacksmith.getCurrentPlanet())) {
                    view.println("The blacksmith is here!");
                }
				view.println("----------------");
				planet.setPermanentSearch(true);
				planet.setTemporarySearch(true);
			}
		}
	}

	private void pickupItem(Command command) {
		ItemStack[] itemsOnPlanet = player.getCurrentPlanet().getContent();

		if(player.getCurrentPlanet().getPermSearched()) {
			if(!command.hasArguments()) {
				view.println(player.getCurrentPlanet().getContentDescription());
			} else {
				if(command.getArgumentLength() > 2) {
					view.println(argumentMessage("pickup <item-index> [quantity]"));
				} else {
					int index = Integer.parseInt(command.getArgument(0)) - 1;

					if(index >= 0 && index < itemsOnPlanet.length) {
						ItemStack selected = itemsOnPlanet[index];

						if(selected.getItem().isPickupable()) {
							int quantity = command.containsIndex(1) ? Integer.parseInt(command.getArgument(1)) : selected.getQuantity();
							quantity = Math.min(quantity, selected.getQuantity());

							ItemStack is = new ItemStack(selected.getItem(), quantity);

							if(player.getBackpack().add(is)) {
								player.getCurrentPlanet().removeItemStack(is);
								view.println("You successfully picked " + is.getQuantity() + "x [" + is.getItem().getName() + "] from the planet!");
							} else {
								Backpack bp = player.getBackpack();

								view.println(String.format("The backpack [%.2f/%.2f] does not have enough space for %dx %.2f.",
								bp.getCurrentCapacity(), bp.getMaxCapacity(),
								is.getQuantity(), is.getItem().getWeight()));
							}
						} else {
							view.println(selected.getItem().getName() + " is not pickupable.");
						}
					} else {
						view.println("The entered index does not match.");
					}
				}
			}
		} else {
			view.println("You have not searched the planet!");
		}
	}

	private void dropItem(Command command) {
		if(player.getCurrentPlanet().getPermSearched()) {
			if(!command.hasArguments()) {
				showBackpackContent(command);
			} else {
				if(command.getArgumentLength() > 2) {
					view.println(argumentMessage("drop <item-index> [quantity]"));
				} else {
					Backpack backpack = player.getBackpack();
					ItemStack[] content = backpack.getContent();

					int index = Integer.parseInt(command.getArgument(0)) - 1;

					if(index >= 0 && index < content.length) {
						ItemStack selected = content[index];

						if(selected.getItem().isDropable()) {
							int quantity = command.containsIndex(1) ? Integer.parseInt(command.getArgument(1)) : selected.getQuantity();
							quantity = Math.max(Math.min(quantity, selected.getQuantity()), 0);

							ItemStack is = new ItemStack(selected.getItem(), quantity);

							if(backpack.remove(is)) {
								player.getCurrentPlanet().addItemStack(is);
								view.println("You successfully dropped " +is.toString()+ " from the backpack!");
							}
						} else {
							view.println(selected.getItem().getName() + " is not dropable!");
						}
					} else {
						view.println("The entered index does not match.");
					}
				}
			}
		} else {
			view.println("It is not a good idea to drop items, before you have searched the planet.");
		}
	}

	private void showBackpackContent(Command command) {
		Backpack bp = player.getBackpack();

		ItemStack[] content = bp.getContent();

		if(command.hasArguments()) {
			if(command.getArgumentLength() > 1) {
				view.println(argumentMessage("backpack [item-index]"));
			} else {
				int index = Integer.parseInt(command.getArgument(0)) - 1;

				if(index >= 0 && index < content.length) {
					ItemStack is = content[index];

					view.println(is.getItem().getName());
					view.println("\t" + is.getItem().getPHDescription());
				} else if(index == content.length) {
					PortalGun pg = bp.getPortalGun();

					if(pg.isBroken()) {
						view.println("The Portal Gun is broken. You'll have to fix it!");
					} else {
						finished = true;
						gameWon = true;
					}
				} else {
					view.println("The entered index does not match.");
				}
			}
		} else {
			view.println(String.format("Backpack capacity: %.2f [kg] / %.2f [kg]", bp.getCurrentCapacity(), bp.getMaxCapacity()));
			view.println("----");
			for(int i = 0; i < content.length; i++) {
				view.println(String.format("[%d] %s", (1 + i), content[i].toString()));
			}

			view.println(String.format("[%d] %s", content.length + 1, bp.getPortalGun().toString()));
		}
	}

	/* function to replace the current room by it exits */
	private void goPlanet(Command command) {

		// run the quiz

		if(!command.hasArguments() || command.getArgumentLength() > 1) {
			view.println(argumentMessage("go <planet-name> (not case-sensitive)"));
		} else {
			String planetName = command.getArgument(0).toLowerCase();
			Map<String, Planet> planets = player.getPlanets();

			if(!planets.containsKey(planetName)) {
				view.println("Not a valid destination!");
				view.println("Planets: " + player.getPlanetNames());
			} else if(player.samePlanet(planets.get(planetName))) {
				view.println("You cannot travel to the same planet!");
			} else {
				if(player.getFuel() == 0){
					finished = true;
					gameWon = false;
					player.setFuelEmpty(true);
					return;
				}
                view.println(manager.getUnoXMessage());

                if(manager.hasAcceptedOffer(view.getParser().getQuizOfferAnswer())) {
                    manager.pickRandomQuiz();
                    view.println(manager.getCurrentQuizMessage());
                    view.println("Answer: ");

                    int answer = view.getParser().getQuizAnswer(manager.getOptionsSize());

                    if(manager.isAnswerCorrect(answer)) {
	                    view.println("Correct answer! Fuel increased by 10!");
                        player.increaseFuel(10);
                    } else {
                        view.println("Wrong answer! Fuel decreased by 10!");
                        player.decreaseFuel(10);
                    }
                }
                view.println(manager2.getPirateMsg());
                if (manager2.hasAcceptedOffer()){
                	view.println("im glad we could come to an understanding, now be on your war");
                	player.decreaseFuel(30);
				}

                view.getParser().resetReader();
                            
				player.go(planetName);
				player.decreaseFuel(10);

				player.getCurrentPlanet().setTemporarySearch(false);

				if(!trapped) { blacksmith.move(); }

				view.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                view.println(player.getCurrentPlanet().getDescription());
				view.println(player.getCurrentPlanet().getArriveMessage());
				view.println("Planets: " + player.getPlanetNames());
			}
		}
	}

	/* function to exit? */
	private void quit(Command command) {
		if(command.getArgumentLength() > 0) {
			view.println("Quit what?");
		} else {
			finished = true;
		}
	}

	private void restart(Command command){
		if(command.getArgumentLength() > 0) {
			view.println("Restart what?");
		} else {
			view.println("\n \n \n \n \n NEW GAME STARTED \n \n \n \n \n \n");
			Game game = new Game();
			game.start();
		}
	}

	private void addCluesToPlanets(){
		Item[] items = blacksmith.getRecipe().getRequirements();
		Item[] clues = new Item[8];

		for (int i = 0; i < clues.length; i++) {
			clues[i] = Model.getItemById(56);
		}

		Item item;
		Item clue;
		String s;
		String newDescription;

		for (int i = 0; i < items.length; i++) {
			item = items[i];
			for (int j = i * 2; j < i * 2 + 2; j++) {
				clue = clues[j];
				clue.setColor(item.getColor());
				clue.setState(item.getState());
				clue.setItemType(item.getItemType());
				s = j % 2 == 0 ? "{{color}}" : "{{state}}";
				newDescription = clue.getDescription().replace("{{clue}}", s);
				clue.setDescription(newDescription);
			}
		}

		List<Planet> planetsList = new ArrayList<>(player.getPlanets().values());
		Collections.shuffle(planetsList);
		for (int i = 0; i < clues.length; i++) {
			planetsList.get(i).addItemStack(new ItemStack(clues[i]));
		}
	}

	// TODO: Perhaps change boolean to int to get the direct location
	@Override
	public boolean hasBeatenHighscore() {
		int points = scoreHandler.calculatePoints(player.getTotalFuelConsumption());
		List<Score> scores = model.getHighscore();

		for(Score score : scores) {
			if(points > score.getScore()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void addPlayerToHighscore(String playerName) {
		int points = scoreHandler.calculatePoints(player.getTotalFuelConsumption());
		List<Score> scores = model.getHighscore();
		scores.add(new Score(playerName, points));
		Collections.sort(scores);
		scores.remove(scores.size() - 1);
		model.saveHighscore();
	}

	@Override
	public List<Score> getHighscore() {
		return model.getHighscore();
	}

	/* function to print a welcome message */
	private String[] welcomeMessage() {
		return new String[] {
			"",
			"Welcome to the ridicoulous Rick & Morty spinoff!",
			"Rick & Morty spinoff is a new and incredibly addictive adventure game!",
			"[Type '" + CommandWord.HELP + "' if you need help]",
            "[Type '" + CommandWord.INFO + "' if you need more information]",
			"",
		};
	}
        
    private String[] descriptionMessage() {
        return new String[] {
                            "GAME DESCRIPTION",
			"--------------",
			"You are Rick, the brilliant scientist. But you have mistakenly destroyed Earth in your current dimension.",
			"Normally, you would use your Portal Gun to teleport yourself to a new dimension... But it's broken!",
			"Your mission is now to fix your Portal Gun and travel safely to a new dimension. Good luck!",
			"--------------",
                            "",
        };
    }
        
    private String[] hintMessage() {
        return new String[] {
            "OBJECTIVE:",
            "--------------",
            "> You need to find the blacksmith named Gearhead!",
            "> Gearhead will show you his recipe for the Portal Gun!",
            "> It is now your job to find all the items needed!",
            "> Return to Gearhead and repair your Portal Gun!",
            "--------------",
        };
    }

	/* function to print the help section */
	private String[] helpMessage() {
		return new String[] {
				"You are lost. You are alone.",
				"You wander around in the universe.",
				"",
				"Your command words are:",
				view.getParser().getAllCommands()
		};
	}

	private String[] argumentMessage(String usage) {
		return new String[]{
				"You have entered too many arguments!",
				"Usage: " + usage
		};
	}

	private boolean allTrue(boolean[] booleans) {
		for(boolean b : booleans) {
			if(!b) { return false; }
		}

		return true;
	}

	private void gameIsFinished() {
		StringBuilder sb = new StringBuilder();

		if(gameWon){
			double millisecondsElapsed = scoreHandler.calculateTimeElapsed();
			int seconds = (int) ((millisecondsElapsed / 1000) % 60);
			int minutes = (int) ((millisecondsElapsed / 1000) / 60);

			int points = scoreHandler.calculatePoints(player.getTotalFuelConsumption());
			int stars = scoreHandler.getStars(points);

			char[] earnedStars = new char[stars];
			Arrays.fill(earnedStars, '\u26e4');

			sb.append("---------------------- GAME WON! -----------------------\n");
			sb.append("Here are some stats for you to brag about...\n");
			sb.append("You played for ").append(minutes).append(":").append(seconds).append(" minutes\n");
			sb.append("Your total fuel consumption was ").append(player.getTotalFuelConsumption()).append(" liters\n");
			sb.append("Out of 5 stars ").append("you earned: ").append(earnedStars).append("\n");
			sb.append("--------------------------------------------------------");

			view.println(sb.toString());
		} else{
			sb.append("---------------------- GAME OVER! ----------------------\n");
			if(player.isFuelEmpty()){
				sb.append("You ran out of fuel!\n");
			}
			/*sb.append("If you want to play again - type '" + CommandWord.RESTART + "'\n");
			sb.append("If you want to quit - type '" + CommandWord.QUIT + "'\n");*/
			sb.append("--------------------------------------------------------");

			view.println(sb.toString());
		}
	}

	public static Game getInstance() {
		if(INSTANCE == null) INSTANCE = new Game();
		return INSTANCE;
	}
}
