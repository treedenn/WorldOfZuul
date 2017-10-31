package UI.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * Parser is used to read the inputs from the user interface.
 */
public class Parser
{
	/**
	 * The reference variable for the CommandWords class.
	 */
	private CommandWords commands;

	/**
	 * The reference variable for the reader class.
	 */
    private Scanner reader;

    /**
     * Initializes CommandWords() and Scanner(System.in).
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Resets the scanner by reading the next line.
     */
    public void resetReader() {
        reader.nextLine();
    }

    /**
     * Scans user input for a word/character.
     * Continues until it receives an n/N or y/Y.
     * Function is connected to QuizManager.
     * @return the first character from the scanner input word.
     */
    public char getQuizOfferAnswer() {
        char answer = ' ';

        do {
            if(reader.hasNext()) {
                answer = reader.next().charAt(0);
            }
        } while(answer != 'n' && answer != 'N' && answer != 'y' && answer != 'Y');

        return answer;
    }

	/**
	 * Scans user input for a number.
	 * Continues until it receives a number between 1 and max.
	 * Function is connected to QuizManager.
	 * @param max the maximum value the input can be (inclusive).
	 * @return
	 */
	public int getQuizAnswer(int max) {
        int answer = -1;

        do {
            if(reader.hasNextInt()) {
                answer = reader.nextInt();
            } else {
                reader.nextLine();
            }
        } while(answer < 1 || answer > max);

        return answer;
    }

    /* function to wait for an input, then returns the commands */

	/**
	 * Scans a line and separates it into a command and arguments.
	 * Function is connected to Game Loop.
	 * @return a command containing the command and the arguments.
	 */
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

	/***
	 * Combines all the available commands into a single line.
	 * @return the combined line with all the commands separated by a space.
	 */
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