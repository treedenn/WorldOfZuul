package UI.command;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/* enum class for all the commands */
public enum CommandWord {
    /* command enums with their keyword */
    GO("go"), SEARCH("search"), BACKPACK("backpack"), PICKUP("pickup"), DROP("drop"), INTERACT("interact"), FUEL("fuel"), HELP("help"), QUIT("quit"), UNKNOWN("?");

    /* stores the keyword */
    private String commandString;

    /* constructor for the enums */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    /* function to return their keyword */
    public String toString()
    {
        return commandString;
    }
}