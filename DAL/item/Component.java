/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.item;

/**
 * This Class extends item to create a component for the game.
 * It is used for the portal gun.
 * @author farti
 */
public class Component extends Item implements Pickupable {
    ComponentType componentType;
  
    /**
     * Constructor for component.
     * @param name name of component.
     * @param description Description of component.
     * @param quantity Quantity of component.
     * @param componentType CompoentType of component
     */
    public Component(String name, String description, int quantity, ComponentType componentType){
        super(name, description, quantity);
        this.componentType=componentType;
    }
    
     /**
     * Constructor for component.
     * @param name name of component.
     * @param description Description of component.
     * @param componentType CompoentType of component
     */
    public Component(String name, String description, ComponentType componentType){
        this(name, description,1,componentType);
    }
    
      /**
     * Constructor for component.
     * @param name name of component.
     * @param componentType CompoentType of component
     */
    public Component(String name, ComponentType componentType){
        this(name,"",1,componentType);
    }

    
}
