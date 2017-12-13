package UI.refactoredUI.components;

import BLL.item.ItemStack;

/**
 * Interface defining objects of type {@link IBackpack} interaction with the outside world.
 */
public interface IBackpack extends IComponent{

    // Events
    /**
     * Method to add an event listener.
     * Event to fired when an object of type {@link ItemStack} is picked up.
     * @param listener  listener to be added.
     */
    void onUse(IEventListener<ItemStack> listener);

    /**
     * Method to add an event listener.
     * Event to fired when an object of type {@link ItemStack} is dropped.
     * @param listener  listener to be added.
     */
    void onDrop(IEventListener<ItemStack> listener);

    /**
     * Method to add an event listener.
     * Event to fired when the backpack is closed.
     * @param listener  listener to be added.
     */
    void onClose(IEventListener<?> listener);


    // Methods

}
