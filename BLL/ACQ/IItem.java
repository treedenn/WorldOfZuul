package BLL.ACQ;

public interface IItem {
	String getName();
	String getDescription();
	boolean isPickupable();
	boolean isDropable();
	double getWeight();
	boolean equals(Object object);
}
