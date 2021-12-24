package fr.medicamentvet.gui;

import fr.medicamentvet.gui.simple.Label;
import fr.medicamentvet.utils.Static;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The class is the status bar component. It should display a message when there is an error.
 */
public class StatusBar extends JPanel {

    private static Label label;

    public StatusBar(LayoutManager layout) {
        super(layout);
        setBorder(BorderFactory.createMatteBorder(1, Static.MATTEBORDER_WIDTH_ZERO, 0, Static.MATTEBORDER_WIDTH_ZERO, Static.COLOR_BORDER));
        setPreferredSize(new Dimension(Static.FRAME_WIDTH, Static.STATUSBAR_HEIGHT));
        setMinimumSize(new Dimension(Static.FRAME_WIDTH, Static.STATUSBAR_HEIGHT));

        label = new Label(null, Static.FONT_SIZE_TEN, Static.LABEL_COLOR_COLOR1, Static.LABEL_COLOR_COLOR2);
        add(label);
    }

    public static void setLabelMessage(String text) {
        label.setText(text);
    }
}
