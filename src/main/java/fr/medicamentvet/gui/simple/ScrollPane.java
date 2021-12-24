package fr.medicamentvet.gui.simple;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * The aim of the class is to create overloaded constructors that extends JScrollPane component.
 */
public class ScrollPane extends JScrollPane {

    /**
     * JScrollPane constructor
     *
     * @param component Component to display
     * @param vsbPolicy Vertical scroll bar policy of JScrollPane
     * @param hsbPolicy Horizontal scroll bar policy of JScrollPane
     */
    public ScrollPane(Component component, int vsbPolicy, int hsbPolicy) {
        super(component, vsbPolicy, hsbPolicy);

        JScrollBar verticalScrollBar = getVerticalScrollBar();

        // These listeners trigger a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        addMouseWheelListener(actionEvent -> requestFocus());

        verticalScrollBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                requestFocus();
            }
        });
    }

    /**
     * JScrollPane constructor
     *
     * @param component Component to display
     * @param vsbPolicy Vertical scroll bar policy of JScrollPane
     * @param hsbPolicy Horizontal scroll bar policy of JScrollPane
     * @param width     Width of the JScrollPane
     * @param height    Height of the JScrollPane
     */
    public ScrollPane(Component component, int vsbPolicy, int hsbPolicy, int width, int height) {
        super(component, vsbPolicy, hsbPolicy);

        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
    }
}
