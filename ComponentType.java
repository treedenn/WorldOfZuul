
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lalal
 */
public enum ComponentType {
    LIQUID,
    CANISTER,
    CPU,
    GEARS;
    
    public static ComponentType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
    
    
}
