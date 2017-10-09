package DAL.item;

import java.util.ArrayList;

import static DAL.item.ComponentType.*;

public class ItemRegister {

    private ArrayList<Component> gearComponents;
    private ArrayList<Component> canisterComponents;
    private ArrayList<Component> cpuComponents;
    private ArrayList<Component> liquidComponents;

    public ItemRegister(){
        gearComponents = new ArrayList<>();
        canisterComponents = new ArrayList<>();
        cpuComponents = new ArrayList<>();
        liquidComponents = new ArrayList<>();

    }

    /**
     * This method takes an object of the type Component and adds this object to the relevant Arraylist of similar components.
     * @param c An object of the type Component
     */
    public void registerComponent(Component c){
        switch (c.componentType){
            case CPU: cpuComponents.add(c);
            break;
            case GEARS: gearComponents.add(c);
            break;
            case LIQUID: liquidComponents.add(c);
            break;
            case CANISTER: canisterComponents.add(c);
            break;
            default: break;
        }
    }

    public ArrayList<Component> getCanisterComponents() {
        return canisterComponents;
    }

    public ArrayList<Component> getCpuComponents() {
        return cpuComponents;
    }

    public ArrayList<Component> getLiquidComponents() {
        return liquidComponents;
    }

    public ArrayList<Component> getGearComponents() {
        return gearComponents;
    }
}
