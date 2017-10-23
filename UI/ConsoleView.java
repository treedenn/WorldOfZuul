package UI;

import UI.command.Parser;

public class ConsoleView {
	private Parser parser;
	public ConsoleView() {
		this.parser = new Parser();
	}

	public Parser getParser() {
		return parser;
	}

	public void print(String message) {
		System.out.print(message);
	}

	public void print(String ... messages) {
		for(String message : messages) {
			print(message + " ");
		}
	}

	public void println(String message) {
		System.out.println(message);
	}

	public void println(String ... messages) {
		for(String message : messages) {
			println(message);
		}
	}
}
