package BLL.ACQ;

public interface INPCAction {
	String getMessage();
	boolean needAnswer();
	void setAnswer(boolean isYes);
}
