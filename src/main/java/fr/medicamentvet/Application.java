package fr.medicamentvet;

import fr.medicamentvet.gui.MainFrame;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.UIManagerClass;
import java.awt.EventQueue;
import java.util.Locale;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class represents the entry point of the program. The class sets the look and feel, and creates the main frame.
 */
public class Application {

    public static void main(String[] args) {

        Locale.setDefault(Locale.FRANCE);

        Runnable runner = () -> {

            try {
                UIManager.setLookAndFeel(new UIManagerClass());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            new MainFrame(Static.TITLE_APPLICATION);
        };
        EventQueue.invokeLater(runner);
    }
}
