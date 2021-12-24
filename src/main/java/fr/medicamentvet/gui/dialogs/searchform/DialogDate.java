package fr.medicamentvet.gui.dialogs.searchform;

import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.tabbedpane.FormPane;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

/**
 * The class exposes to the user beginning and end dates inputs that can be edited.
 */
public class DialogDate extends Dialog {

    private static Button buttonValidate;

    private final FormPane formPane;
    private final JSpinner spinnerStartDate;
    private final JSpinner spinnerEndDate;

    public DialogDate(JFrame parent, String title, boolean modal, boolean resizable, int width, int height, FormPane formPane) {
        super(parent, title, modal, resizable, width, height);

        this.formPane = formPane;

        Calendar calendar = Calendar.getInstance();
        Date nowDate = calendar.getTime();
        calendar.add(Calendar.YEAR, Static.DATE_YEAR_QUANTITY_MAXIMUM);
        Date endDate = calendar.getTime();
        calendar.set(Static.DATE_BEGIN_YEAR, Calendar.JANUARY, Static.DATE_BEGIN_DAY_ONE);
        Date currentDateInput = calendar.getTime();
        calendar.set(Static.DATE_BEGIN_YEAR, Calendar.JANUARY, Static.DATE_BEGIN_DAY_ZERO);
        Date startDate = calendar.getTime();
        int steps = Calendar.DAY_OF_MONTH;

        SpinnerModel modelStartDate = new SpinnerDateModel(currentDateInput, startDate, endDate, steps);
        SpinnerModel modelEndDate = new SpinnerDateModel(nowDate, startDate, endDate, steps);
        spinnerStartDate = new JSpinner(modelStartDate);
        spinnerEndDate = new JSpinner(modelEndDate);

        JComponent editorStartDate = new JSpinner.DateEditor(spinnerStartDate, Static.SPINNER_DATE_FORMAT_PATTERN);
        JComponent editorEndDate = new JSpinner.DateEditor(spinnerEndDate, Static.SPINNER_DATE_FORMAT_PATTERN);
        spinnerStartDate.setEditor(editorStartDate);
        spinnerEndDate.setEditor(editorEndDate);

        buttonValidate = getButtonValidate();
        JPanel panelBottom = getPanelBottom();

        Utils.addComponent(this, Static.LABEL_START_DATE, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, spinnerStartDate, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, Static.LABEL_END_DATE, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, spinnerEndDate, Static.GRID_ZERO, Static.GRID_THREE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_FOUR, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonValidate.addActionListener(actionEvent -> validateAction());
    }

    public void init() {
        buttonValidate.requestFocus();
    }

    /**
     * The method calls setContentLabel method of the FormPane class and hides the dialog.
     */
    private void validateAction() {
    }
}
