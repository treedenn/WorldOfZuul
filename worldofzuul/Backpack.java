
// Made by Lasse and Rasmus 09/10/2017

package worldofzuul;

import java.util.Scanner;

// Constructor
// The capacity is set to 6.
public class Backpack {
    private Item[] capacity = new Item[6];
    private int maxCapacity = 6;

    public Backpack() {

    }

    // Methods,
    public boolean addItem(Item item) {
        if (getCurrentCapacity() == capacity.length) {
            Scanner input = new Scanner(System.in);
            System.out.println("Oh no, your backpack is full! Choose which thing in your backpack to replace: ");
            for (int i = 0; i < capacity.length; i++) {
                System.out.println((i + 1) + ": " + Item[i]);
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

    public boolean addItem(Item item, int index) {
        capacity[index] = item;
        return true;
    }

    // Has to be redone, item must be replaced.
    public boolean removeItem(int index) {
        capacity[index] = null;
        return true;
    }

    public boolean removeItem(Item item) {
        for (int i = 0; i < capacity.length; i++) {
            if (capacity[i] == item) {
                removeItem(i);
                return true;
            }
        }
        return false;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        int count = 0;
        for (Item item : capacity) {
            if (item != null) {
                count++;
            }
        }
        return maxCapacity - count;
    }

}
