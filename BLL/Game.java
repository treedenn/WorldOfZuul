package BLL;

import DAL.Model;
import BLL.character.Blacksmith;
import BLL.character.player.*;
import BLL.item.Item;
import BLL.item.ItemStack;
import BLL.item.PortalGun;
import DAL.scoring.PointSystem;
import BLL.world.Planet;
import UI.command.Command;
import UI.command.CommandWord;
import UI.ConsoleView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Game {
	private ConsoleView view;
	private Model model;

	private boolean finished;
	private boolean gameWon;
	private Player player;
	private Blacksmith blacksmith;
	private QuizManager manager;

	public Game() {
		view = new ConsoleView();
		model = new Model();

		finished = false;
		gameWon = false;
		player = new Player();
		blacksmith = new Blacksmith();
		manager = new QuizManager();
	}

	/* function to begin game */
	public void start() {
		init();

		view.println(welcomeMessage());
                view.println(descriptionMessage());
                view.println(hintMessage());
                view.println(player.getCurrentPlanet().getDescription());
		view.println("\n[ Planets: " + player.getPlanetNames() + "]");

		gameLoop();
	}

	private void init() {
		Map<String, Planet> planetMap = model.createPlanets();
		Planet[] planets = planetMap.values().toArray(new Planet[planetMap.size()]);

		Planet centerUniverse = new Planet("Center of the Universe", "This is not exactly the center, since a black hole exists in the center of every Universe.");

		player.setCurrentPlanet(centerUniverse);
		player.setPlanets(planetMap);

		blacksmith.setCurrentPlanet(planets[(int) (Math.random() * planets.length)]);
		blacksmith.setPlanets(planetMap);

		manager.setQuizes(model.getQuizes());
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
						sleep = (long) (1000 * (-0.022 * i * i + 0.20 * i + 0.15));
					} else {
						sleep = (long) (1000 * (-0.004329 * i * i + 0.02165 * i + 0.15));
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
				view.println(blacksmith.getVisitMessage(player.getCurrentPlanet().getName()));

				planet.setPermanentSearch(true);
				planet.setTemporarySearch(true);
			}
		}
	}

	private void pickupItem(Command command) {
		ItemStack[] itemsOnPlanet = player.getCurrentPlanet().getContent();

		if(player.getCurrentPlanet().getPermSearched()) {
			if(!command.hasArguments()) {
				for(int i = 0; i < itemsOnPlanet.length; i++) {
					 view.println(String.format("[%d] %s", (1 + i), itemsOnPlanet[i].toString()));
				}
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
								view.println("You successfully picked " +is.toString()+ " from the planet!");
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
					view.println("\t" + is.getItem().getDescription());
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

                    view.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                }

                view.getParser().resetReader();
                            
				player.go(planetName);
				player.decreaseFuel(10);

				player.getCurrentPlanet().setTemporarySearch(false);

				blacksmith.move();

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

	/* function to print a welcome message */
	private String[] welcomeMessage() {
		return new String[] {
				"",
				"Welcome to the ridicoulous Rick & Morty spinoff!",
				"Rick & Morty spinoff is a new and incredibly addictive adventure game!",
				"[Type '" + CommandWord.HELP + "' if you need help]",
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
                                "HINT:",
                                "--------------",
                                "Find the blacksmith named Gearhad!",
                                "Find all items in recipe!",
                                "Return to blacksmith and repair the Portal Gun!",
                                "--------------",
                                "",
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

	public void gameIsFinished() {
		StringBuilder sb = new StringBuilder();
		PointSystem pointSystem = model.getPointSystem();

		if(gameWon){
			pointSystem.setFinishTime();

			double millisecondsElapsed = pointSystem.calculateTimeElapsed();
			int seconds = (int) ((millisecondsElapsed / 1000) % 60);
			int minutes = (int) ((millisecondsElapsed / 1000) / 60);

			int stars = pointSystem.calculatePoints(player.getTotalFuelConsumption());
			char[] earnedStars = new char[stars];
			Arrays.fill(earnedStars, '\u26e4');
			sb.append("--------------------------------------------------------\n");
			sb.append("---------------------- GAME WON! -----------------------\n");
			sb.append("Here are some stats for you to brag about...\n");
			sb.append("You played for ").append(minutes).append(":").append(seconds).append(" minutes\n");
			sb.append("Your total fuel consumption was ").append(player.getTotalFuelConsumption()).append(" liters\n");
			sb.append("Out of 5 stars ").append("you earned: ").append(earnedStars).append("\n");
			sb.append("--------------------------------------------------------");
			view.println(sb.toString());
		} else{
			sb.append("--------------------------------------------------------\n");
			sb.append("---------------------- GAME OVER! ----------------------\n");
			sb.append("You ran out of fuel!\n");
			sb.append("If you want to play again - type 'restart'\n");
			sb.append("If you want to quit - type '" + CommandWord.QUIT + "'\n");
			sb.append("--------------------------------------------------------");
			view.println(sb.toString());
		}

	}
}
