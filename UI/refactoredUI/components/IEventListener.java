package UI.refactoredUI.components;

/**
 * This generic interface is used as a part of the observer pattern.
 * to add a new listener and implement the functionality based on the event.
 * @param <T>   Any object of type {@link Object} to be passed as data.
 */
public interface IEventListener<T>  {
    /**
     * Method to be called on observers when an event fires.
     * @param data  event data to be passed to observer.
     */
    void onAction(T data);
}
