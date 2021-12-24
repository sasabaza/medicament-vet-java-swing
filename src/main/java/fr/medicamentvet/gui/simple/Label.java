package fr.medicamentvet.gui.simple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * The aim of the class is to create overloaded constructors that extends JLabel component.
 */
public class Label extends JLabel {

    /**
     * JLabel constructor
     *
     * @param name     Name of the JLabel
     * @param text     Text of the JLabel
     * @param fontSize Font size of the JLabel
     */
    public Label(String name, String text, int fontSize) {
        super(text);
        setName(name);
        setFont(new Font(null, Font.PLAIN, fontSize));
    }

    /**
     * JLabel constructor
     *
     * @param name     Name of the JLabel
     * @param text     Text of the JLabel
     * @param fontSize Font size of the JLabel
     * @param width    Width of the JLabel
     * @param height   Height of the JLabel
     */
    public Label(String name, String text, int fontSize, int width, int height) {
        super(text);
        setName(name);
        setFont(new Font(null, Font.PLAIN, fontSize));
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * JLabel constructor
     *
     * @param text     Name of the JLabel
     * @param fontSize Font size of the JLabel
     * @param color1   Color red and green value
     * @param color2   Color blue value
     */
    public Label(String text, int fontSize, int color1, int color2) {
        super(text);
        setForeground(new Color(color1, color2, color2));
        setFont(new Font(null, Font.PLAIN, fontSize));
    }

    /**
     * JLabel constructor
     *
     * @param fontSize Font size of the JLabel
     * @param color1   Color red value
     * @param color2   Color green value
     * @param color3   Color blue value
     * @param text     Text of the JLabel
     */
    public Label(int fontSize, int color1, int color2, int color3, String text) {
        super(text);
        setForeground(new Color(color1, color2, color3));
        setFont(new Font(null, Font.PLAIN, fontSize));
    }

    /**
     * JLabel constructor
     *
     * @param text     Text of the JLabel
     * @param fontSize Font size of the JLabel
     * @param width    Width of the JLabel
     * @param height   Height of the JLabel
     * @param color    Color red, and green, and blue value
     */
    public Label(String text, int fontSize, int width, int height, int color) {
        super(text);
        setForeground(new Color(color, color, color));
        setFont(new Font(null, Font.PLAIN, fontSize));
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
    }
}
