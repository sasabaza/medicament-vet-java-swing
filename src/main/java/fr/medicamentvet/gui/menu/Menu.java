package fr.medicamentvet.gui.menu;

import fr.medicamentvet.utils.Static;
import java.awt.Font;
import javax.swing.JMenu;

/**
 * The class helps to create a customized menu.
 */
public class Menu extends JMenu {

    public Menu(String name, int mnemonic) {
        super(name);
        setFont(new Font(null, Font.PLAIN, Static.FONT_SIZE_TWELVE));
        setMnemonic(mnemonic);
    }
}
