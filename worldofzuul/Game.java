package worldofzuul;
import java.util.Scanner;
/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/* constructor for game class */
public class Game 
{
	/* constructor for game class */
	private Parser parser;
    private Planet currentPlanet;

	/* constructor for game class */
    public Game() 
    {
        createPlanets();
        parser = new Parser();
    }

    /* function to create rooms */
    private void createPlanets()
    {
        Planet centerUniverse, cleronOR7, scurn01K, hebrilles, xehna, gallifrey, skaro,
                orion, deineax, uskillon, j8AyrusZ420, amrifArret, newEarth;

        centerUniverse = new Planet("Center of the Universe","duh at the center of the universe"); // ~
        cleronOR7 = new Planet("Cleron OR7","arrived safely at Cleron_OR7"); // 0
        scurn01K = new Planet("Scurn 01K","arrived safely at Scurn_01K"); // 1
        hebrilles = new Planet("Hebrilles","arrived safely at Hebrilles"); // 2
        xehna = new Planet("Xehna","arrived safely at Xehna"); // 3
        gallifrey = new Planet("Gallifrey","arrived safely at Gallifrey"); // 4
        skaro = new Planet("Skaro","arrived safely at Skaro"); // 5
        orion = new Planet("Orion","arrived safely at Orion"); // 6
        deineax = new Planet("Deineax","arrived safely at Deineax"); // 7
        uskillon = new Planet("Uskillon","arrived safely at Uskillon"); // 8
        j8AyrusZ420 = new Planet("J8 Ayrus Z420","arrived safely at J8_Ayrus_z420"); // 9
        amrifArret = new Planet("Amrif Arret","arrived safely at Amrif Arret"); // 10
        newEarth = new Planet("New Earth","arrived safely at New Earth"); // 11

        Planet[] planets = new Planet[] {
                centerUniverse, cleronOR7, scurn01K, hebrilles, xehna, gallifrey, skaro,
                orion, deineax, uskillon, j8AyrusZ420, amrifArret, newEarth
        };

        for(int i = 0; i < planets.length; i++) {
            Planet planet = planets[i];

            for(int j = 0; j < planets.length; j++) {
                Planet otherPlanet = planets[j];

                if(i != j && !otherPlanet.equals(centerUniverse)) {
                    planet.setDestination(otherPlanet.getName().replace(" ", ""), otherPlanet);
                }
            }
        }

        currentPlanet = centerUniverse;
    }

	/* function to begin game */
    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

	/* function to print a welcome message */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the ridicoulous rick & morty spinoff!");
        System.out.println("rick & morty spinoff is a new, incredibly addictive adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentPlanet.getLongDescription());
    }

	/* function containing the actions of a command */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            
                
                    goPlanet(command);
            
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

	/* function to print the help section */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

	/* function to replace the current room by it exits */
    private void goPlanet(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Planet nextPlanet = currentPlanet.getExit(direction);

        if (nextPlanet == null) {
            System.out.println("There is no door!");
        }
        else {
            currentPlanet = nextPlanet;
            System.out.println(currentPlanet.getLongDescription());
        }
    }

    /* function to exit? */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}