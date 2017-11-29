package BLL;

/**
 * The Message Container is an object, that allows the GUI and business layer to communicate with each other.
 * Whenever GUI invokes a method, the business will 'maybe' send a message through the message container,
 * and the GUI can receive it and send it to the user.
 */
public class MessageContainer {
	private String message;

	MessageContainer() {
		this.message = null;
	}

	/**
	 * Checks whether there is a message storing inside the message container.
	 * @return true, if message is available
	 */
	public boolean hasMessage() {
		return message == null || message.isEmpty();
	}

	/***
	 * Gets the message storing inside the message container.
	 * After retrieving, it will disappear.
	 * @return the message, if any
	 */
	public String getMessage() {
		String temp = message;
		message = null;
		return temp;
	}

	/***
	 * Gets the message as an array of characters, instead of a String.
	 * @return the message as chars, if any
	 */
	public char[] getMessageAsChars() {
		return getMessage().toCharArray();
	}

	/**
	 * Sets the message.
	 * Can only be invoked inside business layer.
	 * @param message
	 */
	void setMessage(String message) {
		this.message = message;
	}
}