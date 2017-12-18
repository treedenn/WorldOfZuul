package UI.components.icomponents;

import UI.components.bar.BarStyle;

/**
 * This interface defines the contract between components of type {@link IBar} and a parent controller.
 */
public interface IBar extends IComponent {

    /**
     * Method to set progress bar value.
     * @param value the value to be set.
     * @param textualValue the textual value to be set.
     */
    void setValue(double value, String textualValue);

    /**
     * Method to set progress bar legend.
     * @param text legend text.
     */
    void setLegend(String text);

    /**
     * Method to set the progress bar style.
     * @param barStyle  the style.
     */
    boolean setStyle(BarStyle barStyle);


}
