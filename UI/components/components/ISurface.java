package UI.components.components;

import BLL.ACQ.IItemStack;
import BLL.entity.npc.NPC;

import java.util.List;

public interface ISurface extends IComponent {

    // Events
    void onExit(IEventListener listener);

    void onPickup(IEventListener<IItemStack> listener);

    void onSearch(IEventListener listener);

    void OnTickList(IEventListener listener);

    void onInteract(IEventListener<NPC> listener);


    // Methods

    /**
     * Method to setup
     * @param planetName
     * @param planetDescription
     * @param imagePath
     */
    void setup(String planetName, String planetDescription, String imagePath, boolean isSearched);

    void tickList(IItemStack[] items, List<NPC> characters);

    void setIsSearched(boolean isSearched);



}
