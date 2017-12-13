package UI.refactoredUI.components;

import BLL.ACQ.IItemStack;

/**
 * Interface defining objects of type {@link IBackpack} interaction with the outside world.
 */
public interface IBackpack extends IComponent{

    // Events
    /**
     * Method to add an event listener.
     * Event to fired when an object of type {@link IItemStack} is picked up.
     * @param listener  listener to be added.
     */
    void onUse(IEventListener<IItemStack> listener);

    /**
     * Method to add an event listener.
     * Event to fired when an object of type {@link IItemStack} is dropped.
     * @param listener  listener to be added.
     */
    void onDrop(IEventListener<IItemStack> listener);

    /**
     * Method to add an event listener.
     * Event to fired when the backpack is closed.
     * @param listener  listener to be added.
     */
    void onClose(IEventListener<?> listener);


    // Methods

    /**
     * Method to load backpack with player's inventory.
     * @param items
     */
    void load(IItemStack[] items);
}
