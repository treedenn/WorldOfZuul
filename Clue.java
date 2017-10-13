
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lalal
 * http://codr.io/wdfft0q
 */
public class Clue {
    private int seed;
    private static String[] clues;
    
    public Clue() {
        int seed = 5;
        clues = new String[seed];
    }
    
    public static String[] examine() {
        String[] clue = new String[ComponentType.values().length];
        int index = 0;
        for (ComponentType cT : ComponentType.values()) {
           clue[index++] = cT.name();
        }
        return clue;
    }
    
    public static void main(String[] args) {
        Clue c = new Clue();
        
        int ran = new Random().nextInt(c.examine().length);
        
        System.out.println("Random component: " + c.examine()[ran]);
        
        System.out.println("Random compoent: " + ComponentType.getRandom());
        
    }
    
}
