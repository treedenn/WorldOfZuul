package UI.command;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/* class to store a command */
public class Command {
    /* variables inside the command class */
    private CommandWord commandWord;
    private String[] arguments;

    /* constructor to command */
    public Command(CommandWord commandWord, String[] arguments) {
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    /* function to get first command word */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    public String getArgument(int index) {
        return arguments[index];
    }

    public int getArgumentLength() {
        return arguments.length;
    }

    public boolean hasArguments() {
        return arguments.length > 0;
    }

    public boolean containsIndex(int index) {
        return arguments.length > index && index >= -1;
    }

    /* function to see if the first command is unknown */
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }

}