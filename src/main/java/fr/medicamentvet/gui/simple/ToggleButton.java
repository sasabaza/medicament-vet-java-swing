package fr.medicamentvet.gui.simple;

import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 * The aim of the class is to create a custom constructor that extends JToggleButton component.
 */
public class ToggleButton extends JToggleButton {

    /**
     * JToggleButton constructor
     *
     * @param text   Text of the JToggleButton
     * @param width  Width of the JToggleButton
     * @param height Height of the JToggleButton
     * @param url    URL of the ImageIcon
     */
    public ToggleButton(String text, int width, int height, URL url) {
        super(text);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        setIcon(new ImageIcon(url));
    }
}
