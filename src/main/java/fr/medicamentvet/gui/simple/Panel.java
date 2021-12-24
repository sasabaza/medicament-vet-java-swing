package fr.medicamentvet.gui.simple;

import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 * The aim of the class is to create overloaded constructors that extends JPanel component.
 */
public class Panel extends JPanel {

    /**
     * JPanel constructor
     *
     * @param layout Layout of JPanel
     * @param width  Width of the JPanel
     * @param height Height of the JPanel
     */
    public Panel(LayoutManager layout, int width, int height) {
        super(layout);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
    }
}
