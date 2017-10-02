package worldofzuul;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game 
{
    private Parser parser;
    private Planet currentPlanet;
        
    public Game() 
    {
        createPlanets();
        parser = new Parser();
    }

    private void createPlanets()
    {
        Planet Center_Universe, Cleron_OR7, Scurn_01K, Hebrilles, Xehna, Gallifrey, Skaro, Orion, Deineax, Uskillon, J8_Ayrus_Z420, Amrif_Arret, New_Earth;
        Center_Universe = new Planet("duh at the center of the universe");
        Cleron_OR7 = new Planet("safely at Cleron_OR7");
        Scurn_01K = new Planet("semidrunk but finally arrived at Scurn_01K");
        Hebrilles = new Planet("safely at Hebrilles");
        Xehna = new Planet("safely at Xehna");
        Gallifrey = new Planet("safely at Gallifrey");
        Skaro = new Planet("safely at Skaro");
        Orion = new Planet("safely at Orion");
        Deineax = new Planet("safely at Deineax");
        Uskillon = new Planet("safely at Uskillon");
        J8_Ayrus_Z420 = new Planet("safely at J8_Ayrus_z420");
        Amrif_Arret = new Planet("safely at Amrif Arret");
        New_Earth = new Planet("safely at New Earth");


        Center_Universe.setDestination("Cleron_OR7", Cleron_OR7);
        Center_Universe.setDestination("Scurn_01K", Scurn_01K);
        Center_Universe.setDestination("Hebrilles", Hebrilles);
        Center_Universe.setDestination("Xehna", Xehna);
        Center_Universe.setDestination("Gallifrey", Gallifrey);
        Center_Universe.setDestination("Skaro", Skaro);
        Center_Universe.setDestination("Orion", Orion);
        Center_Universe.setDestination("Deineax", Deineax);
        Center_Universe.setDestination("Uskillon", Uskillon);
        Center_Universe.setDestination("J8_Ayrus_Z420", J8_Ayrus_Z420);
        Center_Universe.setDestination("Amrif_Arret", Amrif_Arret);
        Center_Universe.setDestination("New_Earth", New_Earth);


        Cleron_OR7.setDestination("Scurn_01K", Scurn_01K);
        Cleron_OR7.setDestination("Hebrilles", Hebrilles);
        Cleron_OR7.setDestination("Xehna", Xehna);
        Cleron_OR7.setDestination("Gallifrey", Gallifrey);
        Cleron_OR7.setDestination("Skaro", Skaro);
        Cleron_OR7.setDestination("Orion", Orion);
        Cleron_OR7.setDestination("Deineax", Deineax);
        Cleron_OR7.setDestination("Uskillon", Uskillon);
        Cleron_OR7.setDestination("J8_Ayrus_Z420", J8_Ayrus_Z420);
        Cleron_OR7.setDestination("Amrif_Arret", Amrif_Arret);
        Cleron_OR7.setDestination("New_Earth", New_Earth);

        Scurn_01K.setDestination("Cleron_OR7", Cleron_OR7);
        Scurn_01K.setDestination("Hebrilles", Hebrilles);
        Scurn_01K.setDestination("Xehna", Xehna);
        Scurn_01K.setDestination("Gallifrey", Gallifrey);
        Scurn_01K.setDestination("Skaro", Skaro);
        Scurn_01K.setDestination("Orion", Orion);
        Scurn_01K.setDestination("Deineax", Deineax);
        Scurn_01K.setDestination("Uskillon", Uskillon);
        Scurn_01K.setDestination("J8_Ayrus_Z420", J8_Ayrus_Z420);
        Scurn_01K.setDestination("Amrif_Arret", Amrif_Arret);
        Scurn_01K.setDestination("New_Earth", New_Earth);

        Hebrilles.setDestination("Cleron_OR7", Cleron_OR7);
        Hebrilles.setDestination("Scurn_01K", Scurn_01K);
        Hebrilles.setDestination("Xehna", Xehna);
        Hebrilles.setDestination("Gallifrey", Gallifrey);
        Hebrilles.setDestination("Skaro", Skaro);
        Hebrilles.setDestination("Orion", Orion);
        Hebrilles.setDestination("Deineax", Deineax);
        Hebrilles.setDestination("Uskillon", Uskillon);
        Hebrilles.setDestination("J8_Ayrus_Z420", J8_Ayrus_Z420);
        Hebrilles.setDestination("Amrif_Arret", Amrif_Arret);
        Hebrilles.setDestination("New_Earth", New_Earth);



        currentPlanet = Center_Universe;
    }

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

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the ridicoulous rick & morty spinoff!");
        System.out.println("rick & morty spinoff is a new, incredibly addictive adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentPlanet.getLongDescription());
    }

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

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

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
