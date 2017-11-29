package BLL;

public class MessageContainer {
	private String message;

	public boolean hasMessage() {
		return message == null || message.isEmpty();
	}

	public String getMessage() {
		String temp = message;
		message = null;
		return temp;
	}
}
