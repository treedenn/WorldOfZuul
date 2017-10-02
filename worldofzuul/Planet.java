package worldofzuul;

import java.util.Set;
import java.util.HashMap;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Planet
{
    private String description;
    private HashMap<String, Planet> exits;

    public Planet(String description)
    {
        this.description = description;
        exits = new HashMap<String, Planet>();
    }

    public void setDestination(String direction, Planet neighbor)
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

	private String getExitString()
    {
        String returnString = "Destinations:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Planet getExit(String direction)
    {
        return exits.get(direction);
    }
}

