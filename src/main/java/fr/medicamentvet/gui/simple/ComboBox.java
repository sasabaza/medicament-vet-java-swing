package fr.medicamentvet.gui.simple;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

/**
 * The aim of the class is to create a custom constructor that extends JComboBox component.
 */
public class ComboBox extends JComboBox<String> {

    /**
     * JComboBox constructor
     *
     * @param model    Data model
     * @param renderer Renderer of the cell
     * @param count    Maximum number of rows
     */
    public ComboBox(ComboBoxModel<String> model, ListCellRenderer<String> renderer, int count) {
        super(model);
        setRenderer(renderer);
        setMaximumRowCount(count);
    }
}
