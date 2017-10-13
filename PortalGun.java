
import DAL.item.Item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lalal
 */
public class PortalGun extends Item {
    public void shoot() {
        if (isComplete() == true) {
            System.out.println("You have opened a portal to another dimension!");
        } else {
            System.out.println("Portal gun is incomplete");
        }
    }
    
}
