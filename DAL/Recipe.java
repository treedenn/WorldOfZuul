package DAL;

import DAL.item.Component;

import java.util.ArrayList;
import java.util.Arrays;

public class Recipe {
    private Component[] clues = new Component[4];

    /**
     * No-arg constructor
     */
    public Recipe(){

    }

/*
    public static ArrayList<Component> gearComponent = new ArrayList<>();
    public static ArrayList<Component> canisterComponent = new ArrayList<>();
    public static ArrayList<Component> cpuComponent = new ArrayList<>();
    public static ArrayList<Component> liqiuidComponent = new ArrayList<>();

    */




    public String[] examineRecipe{

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
