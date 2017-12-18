package UI.components.icomponents;

import BLL.ACQ.IItemStack;

/**
 * This interface defines the contract between components of type {@link IBackpack} and a parent controller.
 */
public interface IBackpack extends IComponent{

    /**
     * Method to add an event listener.
     * Event to be fired when an object of type {@link IItemStack} is picked up.
     * @param listener  listener to be added.
     */
    void onUse(IEventListener<IItemStack> listener);

    /**
     * Method to add an event listener.
     * Event to be fired when an object of type {@link IItemStack} is dropped.
     * @param listener  listener to be added.
     */
    void onDrop(IEventListener<IItemStack> listener);

    /**
     * Method to add an event listener.
     * Event to be fired when the backpack is closed.
     * @param listener  listener to be added.
     */
    void onClose(IEventListener<?> listener);


    /**
     * Method to load backpack with player's inventory.
     * @param items
     */
    void load(IItemStack[] items);
}
