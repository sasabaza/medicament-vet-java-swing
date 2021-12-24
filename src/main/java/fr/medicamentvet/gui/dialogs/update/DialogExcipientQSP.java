package fr.medicamentvet.gui.dialogs.update;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.gui.dialogs.DialogDoubleJListVertical;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The class lets user select one or multiple Excipient QSP items from the JList.
 */
public class DialogExcipientQSP extends DialogDoubleJListVertical {

    private static JFrame mainFrame;
    private final DialogComposition dialogComposition;
    private final Button buttonNext;

    public DialogExcipientQSP(JFrame parent, DialogConditionDelivrance dialogConditionDelivrance, String title, boolean modal, boolean resizable, int width, int height, int maxCount, TabbedPane tabbedPane) {
        super(parent, title, modal, resizable, width, height, maxCount);

        mainFrame = parent;

        dialogComposition = new DialogComposition(parent, this, Static.DIALOG_TITLE_COMPOSITION, false, false, Static.DIALOG_WIDTH_COMPOSITION, Static.DIALOG_HEIGHT_COMPOSITION, Static.COMPOSITION_MAXIMUM_NUMBER, tabbedPane);

        buttonNext = getButtonValidate();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT, Static.BUTTON_RETOUR_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, Static.LAYOUT_GAP_TEN, Static.LAYOUT_GAP_ZERO));
        panelBottom.add(buttonReturn);
        panelBottom.add(buttonNext);

        Utils.addComponent(this, panelBottom, Static.GRID_TWO, Static.GRID_EIGHT, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonNext.addActionListener(actionEvent -> buttonNextAction());

        buttonReturn.addActionListener(actionEvent -> {
            Medicament medicament = getMedicament();
            medicament.setExcipientQSPList(getStringListSelection());

            dialogConditionDelivrance.init(medicament);
            dialogConditionDelivrance.setLocationRelativeTo(parent);
            setVisible(false);
            dialogConditionDelivrance.setVisible(true);
        });
    }

    /**
     * The method sets the model of the JLists.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        List<String> list = medicament.getExcipientQSPList();
        if (list == null) {
            list = new ArrayList<>();
        }

        List<String> excipientQSPList = Controller.getUpdateForm().getExcipientQSPList();
        excipientQSPList.removeAll(list);

        setModel(excipientQSPList, list);
        buttonNext.requestFocus();
    }

    /**
     * The method makes an update of the Medicament object by setting the list of selected items and displays the next dialog.
     */
    private void buttonNextAction() {
    }
}
