// Made by Lasse and Rasmus 09/10/2017

package worldofzuul;

import java.util.Scanner;

public class Backpack {
    private int maxCapacity = 6;
    private Item[] capacity = new Item[maxCapacity]; // The capacity is set to 6.

    /**
     * No-args constructor
     */
    public Backpack() {

    }

    /**
     * Adds an object of the type Item to the Item[] capacity in the backpack.
     * If the backpack is full, the user is prompted to choose which item in the backpack to replace.
     * @param item  an object of the type Item
     * @return  true/false if succeeded/failed
     * @see Item, addItem(), removeItem()
     */
    public boolean addItem(Item item) {
        if (getCurrentCapacity() == capacity.length) {
            Scanner input = new Scanner(System.in);
            System.out.println("Oh no, your backpack is full! Choose which thing in your backpack to replace: ");
            for (int i = 0; i < capacity.length; i++) {
                System.out.println((i + 1) + ": " + capacity[i]);
            }

            int chosenIndex;

            while (true) {
                System.out.print("\nEnter an integer from 1 to 6. Enter -1 to cancel: ");
                chosenIndex = input.nextInt();
                if (chosenIndex >= 1 && chosenIndex <= 6) {
                    removeItem(chosenIndex);
                    addItem(item, chosenIndex);
                } else if (chosenIndex == -1) {
                    break;
                } else {
                    System.out.println("Not a valid input, try again!");
                }
            }

        } else {
            int index = -1;
            for (int i = 0; i < capacity.length; i++) {
                if (capacity[i] == null) {
                    index = i;
                }
            }
            addItem(item, index);
        }
        return true;
    }

    /**
     * Adds an object of the type Item to a specified index
     * @param item  an object of the type Item
     * @param index the index of Item[] capacity to which the item should be added, specified as an int.
     * @return  returns true when item is added to the Item[] capacity.
     */
    public boolean addItem(Item item, int index) {
        capacity[index] = item;
        return true;
    }

    /**
     * Empties Item[] capacity at a specified index by overriding with null.
     * @param index the index of Item[] capacity which should be emptied, specified as an int.
     * @return  returns true when an index of the Item[] capacity is emptied.
     */
    public boolean removeItem(int index) {
        capacity[index] = null;
        return true; // Has to be redone, item must be replaced.
    }

    /**
     * Overrides a given object of the type Item with null in Item[] capacity.
     * @param item  an object of the type Item
     * @return returns true if item is found in Item[] capacity, and the item is removed.
     */
    public boolean removeItem(Item item) {
        for (int i = 0; i < capacity.length; i++) {
            if (capacity[i] == item) {
                removeItem(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the current capacity of the backpack
     * @return  the remaining space in form of an int
     */
    public int getCurrentCapacity() {
        int count = 0;
        for (Item item : capacity) {
            if (item != null) {
                count++;
            }
        }
        return maxCapacity - count;
    }

    /**
     * Gets the maxCapacity variable
     * @return  max capacity in form of an int
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Sets the maxCapacity variable
     * @param integer
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

}
