package worldofzuul;

import java.util.Set;
import java.util.HashMap;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

/* class for a planet */
public class Planet
{
    /* variables for the room class */
    private String description;
    private HashMap<String, Planet> exits;

    /* constructor for the room class */
    public Planet(String description)
    {
        this.description = description;
        exits = new HashMap<String, Planet>();
    }

    /* function to set/add an exit to the room */
    public void setDestination(String direction, Planet neighbor)
    {
        exits.put(direction, neighbor);
    }

    /* function to return the short description */
    public String getShortDescription()
    {
        return description;
    }

    /* function to return the long description */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /* function to return the exit string */
    // Exits: exit1 exit2 exit3
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /* function to return a room by the direction */
    public Planet getExit(String direction)
    {
        return exits.get(direction);
    }
}