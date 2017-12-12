package BLL.ACQ.data;

import BLL.item.ItemStack;

import java.util.List;

public interface IPlanetData {
	int size();
	void addData(String name, double x, double y, List<Integer> NPCs, ItemStack[] itemStacks);

	String getName(int index);
	void setName(int index, String name);
	double getX(int index);
	void setX(int index, double x);

	double getY(int index);
	void setY(int index, double y);

	void setNPCs(int index, List<Integer> npcs);
	List<Integer> getNPCIds(int index);

	ItemStack[] getInventory(int index);
	void setInventory(int index, ItemStack[] itemStacks);
}
