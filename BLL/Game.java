package BLL;

import DAL.Model;
import DAL.character.Blacksmith;
import DAL.character.player.Backpack;
import DAL.character.player.Player;
import DAL.character.player.Recipe;
import DAL.item.Item;
import DAL.item.ItemStack;
import DAL.item.PortalGun;
import DAL.world.Planet;
import UI.command.Command;
import UI.command.CommandWord;
import UI.ConsoleView;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Game {
	private ConsoleView view;
	private Model model;

	public Game() {
		view = new ConsoleView();
		model = new Model();
	}

	/* function to begin game */
	public void start() {
		view.println(welcomeMessage());
		view.println("Planets: " + model.getPlayer().getPlanetNames());

		gameLoop();
	}

	private void gameLoop() {
		while (!model.isFinished()) {
			Command command = view.getParser().getCommand();
			view.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			processCommand(command);
			view.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		}
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
			case QUIT:
				quit(command);
		}
	}

	private void interact(Command command) {
		Player player = model.getPlayer();
		Blacksmith blacksmith = model.getBlacksmith();

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
		Player player = model.getPlayer();

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

				for(int i = 0; i < loading.length; i++) {
					loading[i] = '=';

					long sleep;
					if(!planet.getPermSearched()) {
						sleep = (long) (1000 * (-0.022 * i * i + 0.20 * i + 0.15));
					} else {
						sleep = (long) (1000 * (-0.004329 * i * i + 0.02165 * i + 0.15));
					}

					//System.out.println("Sleep: " + sleep);

					try {
						TimeUnit.MILLISECONDS.sleep(sleep);
						view.println("["+ new String(loading) +"]");
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}

				view.println("Search complete!");
				view.println(model.getBlacksmith().getVisitMessage(player.getCurrentPlanet().getName()));

				planet.setPermanentSearch(true);
				planet.setTemporarySearch(true);
			}
		}
	}

	private void pickupItem(Command command) {
		Player player = model.getPlayer();

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
		Player player = model.getPlayer();

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
		Backpack bp = model.getPlayer().getBackpack();

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
						view.println("Congratulation, you have created a portal to another dimension!");
						view.println("You won the game!");
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
		Player player = model.getPlayer();

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
				player.goPlanet(planetName);
				player.getCurrentPlanet().setTemporarySearch(false);

				model.getBlacksmith().move();

				view.println(player.getCurrentPlanet().getDescription());
				view.println(player.getCurrentPlanet().getArriveMessage());
			}
		}
	}

	/* function to exit? */
	private void quit(Command command) {
		if(command.getArgumentLength() > 0) {
			view.println("Quit what?");
		} else {
			model.setFinished(true);
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
				"GAME OBJECTIVE",
				"--------------",
				"You are Rick, the brilliant scientist. But you have mistakenly destroyed earth in your current dimension.",
				"Normally, you would use your Portal Gun to teleport yourself to a new dimension... But it's broken!",
				"Your mission is now to fix your Portal Gun and travel safely to a new dimension. Good luck!",
				"--------------",
				"",
				model.getPlayer().getCurrentPlanet().getDescription()
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
}
