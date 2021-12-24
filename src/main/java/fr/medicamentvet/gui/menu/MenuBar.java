package fr.medicamentvet.gui.menu;

import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.utils.Static;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;

/**
 * The class creates a JMenuBar with specific attached action for each menu item.
 */
public class MenuBar extends JMenuBar {

    public MenuBar() {

        Menu fileMenu = new Menu(Static.MENU_TITLE_FICHIER_TEXT, KeyEvent.VK_F);

        fileMenu.add(TabbedPane.actionPrint);
        fileMenu.add(TabbedPane.actionCreatePDF);
        fileMenu.add(TabbedPane.actionUpdateDataInput);
        fileMenu.add(TabbedPane.actionViewFavorite);
        fileMenu.add(TabbedPane.actionViewHistory);
        fileMenu.add(TabbedPane.actionDeleteMedicament);

        fileMenu.addSeparator();

        fileMenu.add(TabbedPane.actionExitProgram);

        add(fileMenu);

        Menu helpMenu = new Menu(Static.MENU_TITLE_AIDE_TEXT, KeyEvent.VK_A);

        helpMenu.add(TabbedPane.actionViewAbout);

        add(helpMenu);
    }
}
