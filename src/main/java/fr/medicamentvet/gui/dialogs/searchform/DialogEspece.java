package fr.medicamentvet.gui.dialogs.searchform;

import fr.medicamentvet.gui.dialogs.DialogDoubleJListVertical;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.tabbedpane.FormPane;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The class lets user select one or multiple Espèce items from the JList.
 */
public class DialogEspece extends DialogDoubleJListVertical {

    private static Button buttonValidate;

    private final FormPane formPane;

    public DialogEspece(JFrame parent, String title, boolean modal, boolean resizable, int width, int height, int maxCount, FormPane formPane) {
        super(parent, title, modal, resizable, width, height, maxCount);

        this.formPane = formPane;

        buttonValidate = getButtonValidate();
        JPanel panelBottom = getPanelBottom();

        Utils.addComponent(this, panelBottom, Static.GRID_TWO, Static.GRID_EIGHT, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonValidate.addActionListener(actionEvent -> validateAction());
    }

    public void init() {
        buttonValidate.requestFocus();
    }

    /**
     * The method calls setContentLabel method of FormPane class and hides the dialog.
     */
    private void validateAction() {
    }
}
