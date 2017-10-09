package DAL.item;

import java.util.ArrayList;

import static DAL.item.ComponentType.*;

public class ComponentRegister {
    private ArrayList<Component> gearComponents = new ArrayList<>();
    private ArrayList<Component> canisterComponents = new ArrayList<>();
    private ArrayList<Component> cpuComponents = new ArrayList<>();
    private ArrayList<Component> liquidComponents = new ArrayList<>();

    /**
     * This method takes an object of the type Component and adds this object to the relevant Arraylist of similar components.
     * @param c An object of the type Component
     */
    public void registerComponent(Component c){
        Enum t = c.componentType;
        if (t == LIQUID){
            liquidComponents.add(c);
        } else if (t == GEAR){
            gearComponents.add(c);
        } else if (t == CANISTER){
            canisterComponents.add(c);
        } else if (t == CPU){
            cpuComponents.add(c);
        }
    }


    public ArrayList<Component> getCanisterComponents() {
        return canisterComponents;
    }

    public ArrayList<Component> getCpuComponents() {
        return cpuComponents;
    }

    public ArrayList<Component> getGearComponents() {
        return gearComponents;
    }

    public ArrayList<Component> getLiquidComponents() {
        return liquidComponents;
    }
}
