package fr.medicamentvet.gui.dialogs;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.tabbedpane.FieldPanel;
import fr.medicamentvet.gui.tabbedpane.TableResult;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.SwingWorkerClass;
import fr.medicamentvet.utils.Utils;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class shows a dialog that allows user to delete a medicament.
 */
public class DialogDelete extends Dialog {

    private final AutocompleteField fieldDelete;
    private final Button buttonClose;

    public DialogDelete(JFrame parent, String title, boolean modal, boolean resizable, int width, int height, TableResult tableResult, FieldPanel fieldPanel) {
        super(parent, title, modal, resizable, width, height);

        fieldDelete = new AutocompleteField(this, Static.LABEL_NOM_TEXT, Static.FIELD_WIDTH_DIALOG_DELETE, Static.FIELD_HEIGHT);

        Button buttonDelete = getButtonValidate();
        buttonDelete.setText(Static.BUTTON_SUPPRIMER_TEXT);

        buttonClose = getButtonCancel();
        buttonClose.setText(Static.BUTTON_FERMER_TEXT);

        JPanel panelBottom = getPanelBottom();

        Utils.addComponent(this, Static.LABEL_NOM_DELETE, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, fieldDelete, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonDelete.addActionListener(actionEvent -> {

            String text = fieldDelete.getText();

            if (text != null && !text.isEmpty()) {

                String nom = text.toUpperCase().trim();
                Map<String, Integer> nomIdMap = Controller.getNomIdMap();

                if (nomIdMap != null) {
                    boolean contain = nomIdMap.containsKey(nom);

                    if (contain) {

                        buttonDelete.setEnabled(false);
                        int id = nomIdMap.get(nom);

                        StatusBar.setLabelMessage(Static.EMPTY_TEXT);

                        // Deletion action is done in a background thread with the help of SwingWorkerClass class.
                        SwingWorkerClass swingWorkerClass = new SwingWorkerClass(Static.DIALOG_DELETE_CLASS_NAME_TEXT, buttonDelete, id, tableResult, fieldPanel);
                        swingWorkerClass.execute();
                    } else {
                        JOptionPane.showMessageDialog(this, Static.DIALOG_MESSAGE_TEXT, Static.DIALOG_TITLE_TEXT, JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                requestFocus();
            }
        });
    }

    public void init() {
        fieldDelete.setText(Static.EMPTY_TEXT);
        buttonClose.requestFocus();
    }

    public void setVariable(String[] arrayString) {
        fieldDelete.setArrayString(arrayString);
    }
}
