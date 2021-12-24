package fr.medicamentvet.gui.tabbedpane;

import fr.medicamentvet.gui.TabbedPane;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * The class is JPopupMenu component with three items.
 */
public class PopupMenu extends JPopupMenu {

    public PopupMenu() {

        add(new JMenuItem(TabbedPane.actionCopyTextPopupMenu));
        add(new JMenuItem(TabbedPane.actionPrintPopupMenu));
        add(new JMenuItem(TabbedPane.actionCreatePDFPopMenu));

        pack();
    }
}
