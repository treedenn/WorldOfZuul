package UI.components.icomponents;

import BLL.ACQ.IItemStack;
import BLL.entity.npc.NPC;

import java.util.List;

/**
 * This interface defines the contract between components of type {@link ISurface} and a parent controller.
 */
public interface ISurface extends IComponent {

    /**
     * Method to add an event listener.
     * Event to be fired the leave planet button is clicked.
     * @param listener  listener to be added.
     */
    void onExit(IEventListener listener);

    /**
     * Method to add an event listener.
     * Event to be fired the player picks up an item.
     * @param listener  listener to be added.
     */
    void onPickup(IEventListener<IItemStack> listener);

    /**
     * Method to add an event listener.
     * Event to be fired the player searches the planet.
     * @param listener  listener to be added.
     */
    void onSearch(IEventListener listener);

    /**
     * Method to add an event listener.
     * Event to be fired when the state of the lists have been changed.
     * @param listener  listener to be added.
     */
    void OnTickList(IEventListener listener);

    /**
     * Method to add an event listener.
     * Event to be fired the player interacts with a NPC.
     * @param listener  listener to be added.
     */
    void onInteract(IEventListener<NPC> listener);

    /**
     * Method to load the component with information.
     * @param planetName    name of the planet.
     * @param planetDescription description of the planet.
     * @param imagePath path to the planet's image.
     */
    void setup(String planetName, String planetDescription, String imagePath, boolean isSearched);

    /**
     * Method to update the content of the item and character list.
     * @param items array of items to be loaded.
     * @param characters    list of NPC to be loaded.
     */
    void tickList(IItemStack[] items, List<NPC> characters);

    /**
     * Mutator method for the boolean set searched attribute.
     * @param isSearched    true if the planet has been searched before.
     */
    void setIsSearched(boolean isSearched);



}
