package UI.refactoredUI.components;

import java.awt.event.ActionEvent;

public interface IEventListener<T>  {

    void onAction(T data);

}
