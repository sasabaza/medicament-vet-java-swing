package fr.medicamentvet.gui.dialogs.searchform;

import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.tabbedpane.FormPane;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The objective of the class is to display two autocomplete text fields.
 */
public class DialogSubstanceActive extends Dialog {

    private static Button buttonValidate;

    private final AutocompleteField fieldSubstanceActive1;
    private final AutocompleteField fieldSubstanceActive2;

    private final FormPane formPane;

    public DialogSubstanceActive(JFrame parent, String title, boolean modal, boolean resizable, int width, int height, FormPane formPane) {
        super(parent, title, modal, resizable, width, height);

        this.formPane = formPane;

        fieldSubstanceActive1 = new AutocompleteField(this, Static.SUBSTANCE_ACTIVE_1_TEXT, Static.FIELD_WIDTH_SUBSTANCE_ACTIVE, Static.FIELD_HEIGHT);
        fieldSubstanceActive2 = new AutocompleteField(this, Static.SUBSTANCE_ACTIVE_2_TEXT, Static.FIELD_WIDTH_SUBSTANCE_ACTIVE, Static.FIELD_HEIGHT);

        buttonValidate = getButtonValidate();
        JPanel panelBottom = getPanelBottom();

        Utils.addComponent(this, Static.LABEL_SUBSTANCE_ACTIVE_1, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, fieldSubstanceActive1, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, Static.LABEL_SUBSTANCE_ACTIVE_2, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, fieldSubstanceActive2, Static.GRID_ZERO, Static.GRID_THREE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_FOUR, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonValidate.addActionListener(actionEvent -> validateAction());

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                requestFocus();
            }
        });
    }

    public void init() {
        buttonValidate.requestFocus();
    }

    public void setArrays(String[] arrayString) {
        fieldSubstanceActive1.setArrayString(arrayString);
        fieldSubstanceActive2.setArrayString(arrayString);
    }

    public void setFieldsEnable(boolean enable) {
        fieldSubstanceActive1.setEnabled(enable);
        fieldSubstanceActive2.setEnabled(enable);
    }

    /**
     * The method calls setContentLabel method of the FormPane class and hides the dialog.
     */
    private void validateAction() {
    }
}
