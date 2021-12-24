package fr.medicamentvet.gui.dialogs;

import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;

/**
 * This class shows information about the application in a Dialog.
 */
public class DialogAbout extends Dialog {

    private Desktop desktop;

    public DialogAbout(JFrame parent, String title, boolean modal, boolean resizable, int width, int height) {
        super(parent, title, modal, resizable, width, height);

        desktop = Desktop.getDesktop();

        Utils.addComponent(this, Static.LABEL_APPLICATION_NAME, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_TWO, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, Static.LABEL_VERSION, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_TWO, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, Static.LABEL_DATES, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ZERO, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_ZERO));

        Utils.addComponent(this, Static.LABEL_URI, Static.GRID_ONE, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO));

        pack();

        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }

        Static.LABEL_URI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                URI url;
                try {
                    url = new URI(Static.URL);

                    // Launch the default browser and open the URL
                    desktop.browse(url);
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Static.LABEL_URI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                Static.LABEL_URI.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }
}
