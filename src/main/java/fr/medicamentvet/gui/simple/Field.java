package fr.medicamentvet.gui.simple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 * The aim of the class is to create a custom constructor that extends JTextField component.
 */
public class Field extends JTextField {

    /**
     * JTextField constructor
     *
     * @param name   Name of the JTextField
     * @param width  Width of the JTextField
     * @param height Height of the JTextField
     */
    public Field(String name, int width, int height) {
        setName(name);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(Color.WHITE);
            }
        });
    }
}
