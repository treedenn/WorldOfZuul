package UI.commands;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/* class to store a command */
public class Command
{
    /* variables inside the command class */
    private CommandWord commandWord;
    private String secondWord;

    /* constructor to command */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /* function to get first command word */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /* function to get second command word */
    public String getSecondWord()
    {
        return secondWord;
    }

    /* function to see if the first command is unknown */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /* function to see if the command has a second word */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}