/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author farti
 */
public class Component extends Item {
    ComponentType componentType;
    public Component(ComponentType componentType){
        this.componentType=componentType;
        
    }
    public Component(String name, String description, int quantity, ComponentType componentType){
        super(name, description, quantity);
        this.componentType=componentType;
    }
    public Component(String name, String description, ComponentType componentType){
        this(name, description,1,componentType);
    }
    public Component(String name, ComponentType componentType){
        this(name,"",1,componentType);
}
