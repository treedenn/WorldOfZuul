package UI.command;

import java.util.ArrayList;
import java.util.List;
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

    public char getQuizOfferAnswer() {
        char answer = ' ';

        do {
            if(reader.hasNext()) {
                answer = reader.next().charAt(0);
            }
        } while(answer != 'n' && answer != 'N' && answer != 'y' && answer != 'Y');

        return answer;
    }

    public int getQuizAnswer(int max) {
        int answer = -1;

        do {
            if(reader.hasNextInt()) {
                answer = reader.nextInt();
            } else {
                reader.nextLine();
            }
        } while(answer < 1 || answer > max);

        reader.nextLine();

        return answer;
    }

    /* function to wait for an input, then returns the commands */
    public Command getCommand() {
        String inputLine;
        String command = null;
        List<String> arguments = new ArrayList<>();

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);

        if(tokenizer.hasNext()) {
            command = tokenizer.next();
        }

        while(tokenizer.hasNext()) {
            arguments.add(tokenizer.next());
        }

        return new Command(commands.getCommandWord(command), arguments.toArray(new String[arguments.size()]));
    }

    /* function to get all commands */
    public String getAllCommands()
    {
        StringBuilder builder = new StringBuilder();

        for(String command : this.commands.getAllCommands()) {
            builder.append(command);
            builder.append(" ");
        }

        return builder.toString();
    }
}