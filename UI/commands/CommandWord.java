package UI.commands;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/* enum class for all the commands */
public enum CommandWord
{
    /* command enums with their keyword */
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?");

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