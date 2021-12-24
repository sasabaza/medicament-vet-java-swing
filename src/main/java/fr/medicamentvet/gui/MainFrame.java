package fr.medicamentvet.gui;

import fr.medicamentvet.gui.menu.MenuBar;
import fr.medicamentvet.utils.Static;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This class represents the main frame and creates the main components of the UI.
 */
public class MainFrame extends JFrame {

    public MainFrame(String title) {
        super(title);

        createDirectoryAndFiles();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(Static.FRAME_WIDTH, Static.FRAME_HEIGHT));
        setMinimumSize(new Dimension(Static.FRAME_WIDTH, Static.FRAME_HEIGHT));
        setSize(new Dimension(Static.FRAME_WIDTH, Static.FRAME_HEIGHT));
        setLocationRelativeTo(null);

        TabbedPane tabbedPane = new TabbedPane(this);

        setJMenuBar(new MenuBar());

        add(new ToolBar(), BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(new StatusBar(new FlowLayout(FlowLayout.RIGHT)), BorderLayout.SOUTH);

        pack();

        setVisible(true);

        tabbedPane.setApplicationData(false);

        // Check every 15 seconds and set the data of the application
        Timer timer = new Timer(Static.DELAY_15000, actionEvent -> tabbedPane.setApplicationData(false));
        timer.start();

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                requestFocus();
            }
        });
    }

    /**
     * The method creates a directory and empty files. Files are needed to save and retrieve the history and the favorites.
     */
    private void createDirectoryAndFiles() {
    }
}
