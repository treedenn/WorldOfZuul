/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lalal
 */
public abstract class Item {
    private String name;
    private String description;
    private int quantity;
    
    public Item(String name, String description, int quantity){
        this.name = name; 
        this.description = description; 
        this.quantity = quantity;
    }
   
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) { 
        this.description = description; 
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }
}
