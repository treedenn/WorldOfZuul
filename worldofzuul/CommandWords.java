package worldofzuul;

import java.util.HashMap;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/* class to wrap all the commands together */
public class CommandWords
{
    /* variable to hold all the commands */
    private HashMap<String, CommandWord> validCommands;

    /* constructor, that inits every word into the variable */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /* function to obtain the CommandWord instance from command word */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }

    /* function to see if the input is a command */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /* function to show all the possible commands */
    public void showAll()
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}