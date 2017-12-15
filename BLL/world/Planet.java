package BLL.world;

import BLL.ACQ.IInventory;
import BLL.ACQ.IPlanet;
import BLL.entity.Inventory;
import BLL.entity.npc.NPC;
import BLL.entity.player.Backpack;
import javafx.geometry.Point2D;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/**
 * A Planet is a room in the game, where possibilities await!
 */
public class Planet implements IPlanet {
    /* variables for the room class */
    private String name;
    private String description;
    private Point2D coordinates;
    private File map2D;
    private File image;
    private boolean searched;
    private Inventory inventory;
    private List<NPC> npcList;

    /**
     * Constructs a new Planet.
     * @param name name of the planet
     * @param description description of the planet
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Planet(String name, String description, double x, double y) {
        this.name = name;
        this.description = description;
        this.coordinates = new Point2D(x, y);
        this.inventory = new Backpack(10000);
        this.npcList = new ArrayList<>();
        this.searched = false;
    }

    /**
     * An extended version of constructing a new Planet.
     * @param name name of the planet
     * @param description description of the planet
     * @param imagePath image when entering the planet
     * @param map2DPath the sphere image as 2D
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
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
     * Sets the coordinates of a planet.
     * @param x coordinate for x direction
     * @param y coordinate for y direction
     */
    public void setCoordinates(double x, double y) {
        this.coordinates = new Point2D(x, y);
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
    public double getY() {
        return coordinates.getY();
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
	public boolean hasSearched() {
		return searched;
	}

    /**
     * Sets the search of the planet.
     * @param searched
     */
    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IInventory getInventory() {
        return inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NPC> getNPCs() {
        return npcList;
    }
}