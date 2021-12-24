package fr.medicamentvet.gui.dialogs;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.EditorPane;
import fr.medicamentvet.gui.tabbedpane.FieldPanel;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SingleSelectionModel;
import javax.swing.event.HyperlinkEvent;

/**
 * The main purpose of the class is to show a dialog containing a list of previous names searched by the user.
 */
public class DialogHistory extends Dialog {

    private static EditorPane editorPane;
    private static Button buttonOK;

    public DialogHistory(JFrame parent, String title, boolean modal, boolean resizable, int width, int height, FieldPanel fieldPanel, SingleSelectionModel selectionModel) {
        super(parent, title, modal, resizable, width, height);

        editorPane = new EditorPane(Static.CONTENT_TYPE_EDITOR, Static.BORDER_WIDTH_ZERO, false, Static.CARET_POSITION_ZERO);

        JScrollPane scrollPane = new JScrollPane(editorPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Button buttonDelete = getButtonValidate();
        buttonDelete.setText(Static.BUTTON_SUPPRIMER_HISTORIQUE_TEXT);
        buttonDelete.setPreferredSize(new Dimension(Static.BUTTON_DELETE_HISTORY_WIDTH, Static.BUTTON_FIELD_HEIGHT));
        buttonDelete.setMinimumSize(new Dimension(Static.BUTTON_DELETE_HISTORY_WIDTH, Static.BUTTON_FIELD_HEIGHT));
        buttonDelete.setSize(new Dimension(Static.BUTTON_DELETE_HISTORY_WIDTH, Static.BUTTON_FIELD_HEIGHT));

        buttonOK = getButtonCancel();
        buttonOK.setText(Static.BUTTON_OK_TEXT);

        JPanel panelBottom = getPanelBottom();

        Utils.addComponent(this, scrollPane, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonDelete.addActionListener(e -> {
            deleteHistoryData();
            editorPane.setText(Static.EMPTY_BODY_HTML);
        });

        editorPane.addHyperlinkListener(hyperlinkEvent -> {

            if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                int id = Integer.parseInt(hyperlinkEvent.getDescription());

                Map<Integer, String> idNomMap = Controller.getIdNomMap();
                String nom = idNomMap.get(id);

                selectionModel.setSelectedIndex(Static.DONNEES_MEDICAMENT_TAB_INDEX);
                fieldPanel.setFieldTextByNom(nom);
                setVisible(false);
            }
        });
    }

    public void init() {
        String data = historyData();

        editorPane.setText(data);
        editorPane.setCaretPosition(Static.CARET_POSITION_ZERO);
        buttonOK.requestFocus();
    }

    /**
     * The aim of the method is to get the history of queried names that will be added to the EditorPane.
     *
     * @return HTML content
     */
    private String historyData() {
        return "";
    }

    private void deleteHistoryData() {
    }
}
