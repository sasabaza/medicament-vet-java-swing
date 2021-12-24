package fr.medicamentvet.gui.tabbedpane;

import fr.medicamentvet.utils.Static;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * This class customizes the rendering of the cell for JComboBox and JList.<br>
 * - Change background and foreground color<br>
 * - Change cell selected background color<br>
 * - Modify the size of the font<br>
 * - Change border spacing
 */
public class Renderer implements ListCellRenderer<String> {

    private final DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);

        if (isSelected) {
            label.setBackground(Static.COLOR_BACKGROUND_GRAY);
        }

        label.setFont(new Font(null, Font.PLAIN, Static.FONT_SIZE_ELEVEN));
        label.setBorder(BorderFactory.createEmptyBorder(5, Static.BORDER_WIDTH_FIVE, 5, Static.BORDER_WIDTH_FIVE));

        return label;
    }
}
