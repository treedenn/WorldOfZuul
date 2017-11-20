package BLL.world;

import BLL.character.NPC;
import BLL.item.ItemStack;
import javafx.geometry.Point2D;

import java.util.*;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/* class for a planet */
public class Planet
{
    /* variables for the room class */
    private String name;
    private String description;
    private Point2D coordinates;
    private boolean[] searched;
    private List<ItemStack> itemList;
    private List<NPC> npcList;

    /* constructor for the planet class */
    public Planet(String name, String description, double x, double y) {
        this.name = name;
        this.description = description;
        this.coordinates = new Point2D(x, y);
        this.itemList = new ArrayList<>();
        this.npcList = new ArrayList<>();
        searched = new boolean[] {false, false};
    }

    public String getName() {
        return name;
    }

    /* function to return the short description */
    public String getDescription() {
        return description;
    }

    public String getArriveMessage() {
    	return "You arrived safely at " + getName() + ".";
    }

    public Point2D getCoordinates() {
        return coordinates;
    }

    public boolean getTempSearched() {
        return searched[1];
    }

    public void setTemporarySearch(boolean value) {
        searched[1] = value;
    }

    public boolean getPermSearched() {
        return searched[0];
    }

    public void setPermanentSearch(boolean value) {
        searched[0] = value;
    }

	public boolean hasSearched() {
		return searched[0] && searched[1];
	}

    public void addItemStack(ItemStack itemStack) {
        itemList.add(itemStack);
    }

    public void removeItemStack(ItemStack itemStack) {
        int index = findItem(itemStack);

        ItemStack is = itemList.get(index);

        is.decreaseQuantity(itemStack.getQuantity());

        if(is.getQuantity() == 0) {
            itemList.remove(index);
        }
    }

    public ItemStack[] getContent() {
    	return itemList.toArray(new ItemStack[itemList.size()]);
    }

    public String[] getContentDescription(){
        ItemStack[] itemsOnPlanet = this.getContent();
        String[] contentDescription = new String[itemsOnPlanet.length];
        for(int i = 0; i < itemsOnPlanet.length; i++) {
            contentDescription[i] = String.format("[%d] %s", (1 + i), itemsOnPlanet[i].toString());
        }
        return contentDescription;
    }

    private int findItem(ItemStack itemStack) {
        for(int i = 0; i < itemList.size(); i++) {
            ItemStack existingStack = itemList.get(i);

            if(existingStack.getItem().equals(itemStack.getItem())) {
                return i;
            }
        }

        return -1;
    }

}