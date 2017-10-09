package DAL;

import DAL.item.Component;
import DAL.item.ItemRegister;

import java.util.Arrays;

public class Recipe {
    private Component[] clues = new Component[4];

    /**
     * No-arg constructor
     */
    public Recipe(){

    }

    public Component[] pickRequiredComponent(ItemRegister r){
        return clues;
    }

    /**
     *
     * @param c
     * @return
     */
    public boolean exists(Component c){
        return (Arrays.asList(clues).contains(c));
    }

}
