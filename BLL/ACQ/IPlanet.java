package BLL.ACQ;

public interface IPlanet {
	String getName();
	String getDescription();
	String getArriveMessage();
	boolean getTempSearched();
	boolean getPermSearched();
	boolean hasSearched();
	double getX();
	double getY();
}
