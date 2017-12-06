package BLL.world;

import BLL.ACQ.IPlanet;
import BLL.character.npc.NPC;
import BLL.item.ItemStack;
import javafx.geometry.Point2D;

import java.io.File;
import java.util.*;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/**
 * A Planet is a room/place in the game.
 */
public class Planet implements IPlanet {
    /* variables for the room class */
    private String name;
    private String description;
    private Point2D coordinates;
    private File map2D;
    private File image;
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
        this.searched = new boolean[] {false, false};
    }

    /* expanded constructor for the planet class to include resource paths */
    public Planet(String name, String description, String imagePath, String map2DPath, double x, double y) {
        this(name, description, x, y);
        this.image = new File(imagePath);
        this.map2D = new File(map2DPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getArriveMessage() {
    	return "You arrived safely at " + getName() + ".";
    }

    /**
     * Gets a {@link Point2D} containing the coordinates (X and Y) of the planet.
     * @return a point2D with coordinates
     */
    public Point2D getCoordinates() {
        return coordinates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return coordinates.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getImage(){ return image; }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getMap2D(){ return map2D; }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return coordinates.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getTempSearched() {
        return searched[1];
    }

    /**
     * Sets the temporary search to a new value.
     * @param value the new value
     */
    public void setTemporarySearch(boolean value) {
        searched[1] = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getPermSearched() {
        return searched[0];
    }

    /**
     * Sets the permanent search value.
     * @param value the new value
     */
    public void setPermanentSearch(boolean value) {
        searched[0] = value;
    }

    @Override
	public boolean hasSearched() {
		return searched[0] && searched[1];
	}

    /**
     * Adds the argument (item stack) to the planet.
     * @param itemStack the item stack to add
     */
    public void addItemStack(ItemStack itemStack) {
        itemList.add(itemStack);
    }

    /**
     * Removes the argument (item stack) from the planet.
     * @param itemStack the item stack to remove
     */
    public void removeItemStack(ItemStack itemStack) {
        int index = findItem(itemStack);

        ItemStack is = itemList.get(index);

        is.decreaseQuantity(itemStack.getQuantity());

        if(is.getQuantity() == 0) {
            itemList.remove(index);
        }
    }

    /**
     * Gets the content of the planet in an array of {@link ItemStack}.
     * @return the content of the planet
     */
    public ItemStack[] getContent() {
    	return itemList.toArray(new ItemStack[itemList.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NPC> getNPCs() {
        return npcList;
    }

    /**
     * Looks for a specific item stack inside the planet's content.
     * @param itemStack the item stack to look for
     * @return the index where it is located
     */
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