package fr.medicamentvet.gui.simple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The aim of the class is to create overloaded constructors that extends JButton component.
 */
public class Button extends JButton {

    /**
     * JButton constructor
     *
     * @param name     Name of the JButton
     * @param text     Text of the JButton
     * @param fontSize Font size of the text: Plain style is used for the font.
     * @param width    Width of the JButton
     * @param height   Height of the JButton
     */
    public Button(String name, String text, int fontSize, int width, int height) {
        super(text);
        setName(name);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        setFont(new Font(null, Font.PLAIN, fontSize));
    }

    /**
     * JButton constructor
     *
     * @param name     Name of the JButton
     * @param text     Text of the JButton
     * @param fontSize Font size of the text: Plain style is used for the font.
     * @param width    Width of the JButton
     * @param height   Height of the JButton
     * @param url      URL of the ImageIcon
     */
    public Button(String name, String text, int fontSize, int width, int height, URL url) {
        super(text);
        setName(name);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        setFont(new Font(null, Font.PLAIN, fontSize));
        setIcon(new ImageIcon(url));
    }

    /**
     * JButton constructor
     *
     * @param text     Text of the JButton
     * @param fontSize Font size of the text: Plain style is used for the font.
     * @param width    Width of the JButton
     * @param height   Height of the JButton
     * @param colorR   Color red value
     * @param colorG   Color green value
     * @param colorB   Color blue value
     */
    public Button(String text, int fontSize, int width, int height, int colorR, int colorG, int colorB) {
        super(text);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        setFont(new Font(null, Font.PLAIN, fontSize));
        setBackground(new Color(colorR, colorG, colorB));
    }
}
