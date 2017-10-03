package worldofzuul;

import java.util.Scanner;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/* used primarily to read the inputs */
public class Parser
{
    /* variables for the parser class */
    private CommandWords commands;
    private Scanner reader;

    /* constructor for the parser class */
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /* function to wait for an input, then returns the commands */
    public Command getCommand()
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    /* function to run show all commands */
    public void showCommands()
    {
        commands.showAll();
    }
}