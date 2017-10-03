package worldofzuul;

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
    	// TODO: maybe insert planet as a prefix to each variable?
        Planet centerUniverse, cleronOR7, scurn01K, hebrilles, xehna, gallifrey, skaro, orion, deineax, uskillon, j8AyrusZ420, amrifArret, newEarth;
        centerUniverse = new Planet("duh at the center of the universe"); // ~
        cleronOR7 = new Planet("arrived safely at Cleron_OR7"); // 0
        scurn01K = new Planet("arrived safely at Scurn_01K"); // 1
        hebrilles = new Planet("arrived safely at Hebrilles"); // 2
        xehna = new Planet("arrived safely at Xehna"); // 3
        gallifrey = new Planet("arrived safely at Gallifrey"); // 4
        skaro = new Planet("arrived safely at Skaro"); // 5
        orion = new Planet("arrived safely at Orion"); // 6
        deineax = new Planet("arrived safely at Deineax"); // 7
        uskillon = new Planet("arrived safely at Uskillon"); // 8
        j8AyrusZ420 = new Planet("arrived safely at J8_Ayrus_z420"); // 9
        amrifArret = new Planet("arrived safely at Amrif Arret"); // 10
        newEarth = new Planet("arrived safely at New Earth"); // 11

        centerUniverse.setDestination("Cleron_OR7", cleronOR7);
        centerUniverse.setDestination("Scurn_01K", scurn01K);
        centerUniverse.setDestination("Hebrilles", hebrilles);
        centerUniverse.setDestination("Xehna", xehna);
        centerUniverse.setDestination("Gallifrey", gallifrey);
        centerUniverse.setDestination("Skaro", skaro);
        centerUniverse.setDestination("Orion", orion);
        centerUniverse.setDestination("Deineax", deineax);
        centerUniverse.setDestination("Uskillon", uskillon);
        centerUniverse.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        centerUniverse.setDestination("Amrif_Arret", amrifArret);
        centerUniverse.setDestination("New_Earth", newEarth);

        cleronOR7.setDestination("Scurn_01K", scurn01K);
        cleronOR7.setDestination("Hebrilles", hebrilles);
        cleronOR7.setDestination("Xehna", xehna);
        cleronOR7.setDestination("Gallifrey", gallifrey);
        cleronOR7.setDestination("Skaro", skaro);
        cleronOR7.setDestination("Orion", orion);
        cleronOR7.setDestination("Deineax", deineax);
        cleronOR7.setDestination("Uskillon", uskillon);
        cleronOR7.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        cleronOR7.setDestination("Amrif_Arret", amrifArret);
        cleronOR7.setDestination("New_Earth", newEarth);

        scurn01K.setDestination("Cleron_OR7", cleronOR7);
        scurn01K.setDestination("Hebrilles", hebrilles);
        scurn01K.setDestination("Xehna", xehna);
        scurn01K.setDestination("Gallifrey", gallifrey);
        scurn01K.setDestination("Skaro", skaro);
        scurn01K.setDestination("Orion", orion);
        scurn01K.setDestination("Deineax", deineax);
        scurn01K.setDestination("Uskillon", uskillon);
        scurn01K.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        scurn01K.setDestination("Amrif_Arret", amrifArret);
        scurn01K.setDestination("New_Earth", newEarth);

        hebrilles.setDestination("Cleron_OR7", cleronOR7);
        hebrilles.setDestination("Scurn_01K", scurn01K);
        hebrilles.setDestination("Xehna", xehna);
        hebrilles.setDestination("Gallifrey", gallifrey);
        hebrilles.setDestination("Skaro", skaro);
        hebrilles.setDestination("Orion", orion);
        hebrilles.setDestination("Deineax", deineax);
        hebrilles.setDestination("Uskillon", uskillon);
        hebrilles.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        hebrilles.setDestination("Amrif_Arret", amrifArret);
        hebrilles.setDestination("New_Earth", newEarth);

        xehna.setDestination("Cleron_OR7", cleronOR7);
        xehna.setDestination("Scurn_01K", scurn01K);
        xehna.setDestination("Hebrilles", hebrilles);
        xehna.setDestination("Gallifrey", gallifrey);
        xehna.setDestination("Skaro", skaro);
        xehna.setDestination("Orion", orion);
        xehna.setDestination("Deineax", deineax);
        xehna.setDestination("Uskillon", uskillon);
        xehna.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        xehna.setDestination("Amrif_Arret", amrifArret);
        xehna.setDestination("New_Earth", newEarth);

        gallifrey.setDestination("Cleron_OR7", cleronOR7);
        gallifrey.setDestination("Scurn_01K", scurn01K);
        gallifrey.setDestination("Hebrilles", hebrilles);
        gallifrey.setDestination("Xehna", xehna);
        gallifrey.setDestination("Skaro", skaro);
        gallifrey.setDestination("Orion", orion);
        gallifrey.setDestination("Deineax", deineax);
        gallifrey.setDestination("Uskillon", uskillon);
        gallifrey.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        gallifrey.setDestination("Amrif_Arret", amrifArret);
        gallifrey.setDestination("New_Earth", newEarth);

        skaro.setDestination("Cleron_OR7", cleronOR7);
        skaro.setDestination("Scurn_01K", scurn01K);
        skaro.setDestination("Hebrilles", hebrilles);
        skaro.setDestination("Xehna", xehna);
        skaro.setDestination("Gallifrey", gallifrey);
        skaro.setDestination("Orion", orion);
        skaro.setDestination("Deineax", deineax);
        skaro.setDestination("Uskillon", uskillon);
        skaro.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        skaro.setDestination("Amrif_Arret", amrifArret);
        skaro.setDestination("New_Earth", newEarth);

        orion.setDestination("Cleron_OR7", cleronOR7);
        orion.setDestination("Scurn_01K", scurn01K);
        orion.setDestination("Hebrilles", hebrilles);
        orion.setDestination("Xehna", xehna);
        orion.setDestination("Gallifrey", gallifrey);
        orion.setDestination("Skaro", skaro);
        orion.setDestination("Deineax", deineax);
        orion.setDestination("Uskillon", uskillon);
        orion.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        orion.setDestination("Amrif_Arret", amrifArret);
        orion.setDestination("New_Earth", newEarth);

        deineax.setDestination("Cleron_OR7", cleronOR7);
        deineax.setDestination("Scurn_01K", scurn01K);
        deineax.setDestination("Hebrilles", hebrilles);
        deineax.setDestination("Xehna", xehna);
        deineax.setDestination("Gallifrey", gallifrey);
        deineax.setDestination("Skaro", skaro);
        deineax.setDestination("Orion", orion);
        deineax.setDestination("Uskillon", uskillon);
        deineax.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        deineax.setDestination("Amrif_Arret", amrifArret);
        deineax.setDestination("New_Earth", newEarth);

        uskillon.setDestination("Cleron_OR7", cleronOR7);
        uskillon.setDestination("Scurn_01K", scurn01K);
        uskillon.setDestination("Hebrilles", hebrilles);
        uskillon.setDestination("Xehna", xehna);
        uskillon.setDestination("Gallifrey", gallifrey);
        uskillon.setDestination("Skaro", skaro);
        uskillon.setDestination("Orion", orion);
        uskillon.setDestination("Deineax", deineax);
        uskillon.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        uskillon.setDestination("Amrif_Arret", amrifArret);
        uskillon.setDestination("New_Earth", newEarth);

        j8AyrusZ420.setDestination("Cleron_OR7", cleronOR7);
        j8AyrusZ420.setDestination("Scurn_01K", scurn01K);
        j8AyrusZ420.setDestination("Hebrilles", hebrilles);
        j8AyrusZ420.setDestination("Xehna", xehna);
        j8AyrusZ420.setDestination("Gallifrey", gallifrey);
        j8AyrusZ420.setDestination("Skaro", skaro);
        j8AyrusZ420.setDestination("Orion", orion);
        j8AyrusZ420.setDestination("Deineax", deineax);
        j8AyrusZ420.setDestination("Uskillon", uskillon);
        j8AyrusZ420.setDestination("Amrif_Arret", amrifArret);
        j8AyrusZ420.setDestination("New_Earth", newEarth);

        amrifArret.setDestination("Cleron_OR7", cleronOR7);
        amrifArret.setDestination("Scurn_01K", scurn01K);
        amrifArret.setDestination("Hebrilles", hebrilles);
        amrifArret.setDestination("Xehna", xehna);
        amrifArret.setDestination("Gallifrey", gallifrey);
        amrifArret.setDestination("Skaro", skaro);
        amrifArret.setDestination("Orion", orion);
        amrifArret.setDestination("Deineax", deineax);
        amrifArret.setDestination("Uskillon", uskillon);
        amrifArret.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        amrifArret.setDestination("New_Earth", newEarth);

        newEarth.setDestination("Cleron_OR7", cleronOR7);
        newEarth.setDestination("Scurn_01K", scurn01K);
        newEarth.setDestination("Hebrilles", hebrilles);
        newEarth.setDestination("Xehna", xehna);
        newEarth.setDestination("Gallifrey", gallifrey);
        newEarth.setDestination("Skaro", skaro);
        newEarth.setDestination("Orion", orion);
        newEarth.setDestination("Deineax", deineax);
        newEarth.setDestination("Uskillon", uskillon);
        newEarth.setDestination("J8_Ayrus_Z420", j8AyrusZ420);
        newEarth.setDestination("Amrif_Arret", amrifArret);

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