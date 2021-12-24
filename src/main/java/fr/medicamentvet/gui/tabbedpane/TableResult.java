package fr.medicamentvet.gui.tabbedpane;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.dialogs.update.DialogUpdate;
import fr.medicamentvet.gui.simple.EditorPane;
import fr.medicamentvet.gui.simple.ScrollPane;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.SwingWorkerClass;
import fr.medicamentvet.utils.Utils;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

/**
 * The class displays the information about the medicament, and contains three buttons:<br>
 * A button to edit the information<br>
 * A toggle button to view the base or Rcp information<br>
 * A button to add the medicament name to the favorite list
 */
public class TableResult extends JPanel {

    private static Integer medicamentId;

    private final JFrame mainFrame;
    private final DialogUpdate dialogUpdate;
    private final EditorPane editorPane;

    private String[] arrayMedicamentData;

    public TableResult(JFrame parent, LayoutManager layout, TabbedPane tabbedPane) {
        super(layout);

        setBorder(BorderFactory.createEmptyBorder(0, Static.BORDER_WIDTH_TEN, 10, Static.BORDER_WIDTH_TEN));

        this.mainFrame = parent;

        PopupMenu popupMenu = new PopupMenu();

        JPanel gridBag = new JPanel(new GridBagLayout());

        Static.UPDATE_BUTTON.setEnabled(false);
        Static.TOGGLE_BUTTON.setEnabled(false);
        Static.FAVORITE_TOGGLE_BUTTON.setEnabled(false);

        dialogUpdate = new DialogUpdate(mainFrame, Static.MISE_A_JOUR_DES_INFORMATIONS_TEXT, false, false, Static.DIALOG_WIDTH_UPDATE, Static.DIALOG_HEIGHT_UPDATE, tabbedPane);

        Utils.addComponent(gridBag, Static.UPDATE_BUTTON, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_SEVEN, Static.INSET_ZERO, Static.INSET_SEVEN, Static.INSET_ZERO));
        Utils.addComponent(gridBag, Static.TOGGLE_BUTTON, Static.GRID_ONE, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(Static.INSET_SEVEN, Static.INSET_ZERO, Static.INSET_SEVEN, Static.INSET_FIFTEEN));
        Utils.addComponent(gridBag, Static.FAVORITE_TOGGLE_BUTTON, Static.GRID_TWO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ZERO, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(Static.INSET_SEVEN, Static.INSET_ZERO, Static.INSET_SEVEN, Static.INSET_ZERO));

        add(gridBag, BorderLayout.NORTH);

        editorPane = new EditorPane(Static.CONTENT_TYPE_EDITOR, Static.BORDER_WIDTH_ZERO, false, Static.CARET_POSITION_ZERO);

        ScrollPane scrollPane = new ScrollPane(editorPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        Static.UPDATE_BUTTON.addActionListener(actionEvent -> {

            if (arrayMedicamentData != null) {
                Medicament medicament = Controller.getMedicament();
                dialogUpdate.init(medicament);
                dialogUpdate.setLocationRelativeTo(mainFrame);
                dialogUpdate.setVisible(true);
            }
        });

        editorPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });

        Static.TOGGLE_BUTTON.addActionListener(actionEvent -> {
            if (arrayMedicamentData != null) {
                if (Static.TOGGLE_BUTTON.isSelected()) {
                    editorPane.setText(arrayMedicamentData[1]);
                } else {
                    editorPane.setText(arrayMedicamentData[0]);
                }
                editorPane.setCaretPosition(Static.CARET_POSITION_ZERO);
            }
        });

        Static.FAVORITE_TOGGLE_BUTTON.addActionListener(actionEvent -> {
            if (arrayMedicamentData != null) {

                if (Static.FAVORITE_TOGGLE_BUTTON.isSelected()) {
                    addFavorite(medicamentId);
                } else {
                    removeFavorite(medicamentId);
                }
            }
        });
    }

    public void setArrayMedicamentData(String[] arrayMedicamentData) {
        this.arrayMedicamentData = arrayMedicamentData;
    }

    public void generatePDFOrPrint(boolean print) {

        if (Static.TOGGLE_BUTTON.isSelected()) {
            generatePDFOrPrintRcp(print);
        } else {
            generatePDFOrPrintMedicament(print);
        }
    }

    /**
     * The method gets the information about the Medicament object given the id. The process is done in a background thread.
     *
     * @param id                id of the Medicament object
     * @param autocompleteField AutocompleteField
     */
    public void setEditorPaneContentById(int id, AutocompleteField autocompleteField) {
        medicamentId = id;

        if (id != -1) {
            setElements();

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);
            SwingWorkerClass swingWorkerClass = new SwingWorkerClass(Static.TABLE_RESULT_CLASS_NAME_TEXT, autocompleteField, id, editorPane, Static.UPDATE_BUTTON, Static.TOGGLE_BUTTON, Static.FAVORITE_TOGGLE_BUTTON, this);
            swingWorkerClass.execute();
        } else {
            clearEditorPane(Static.AUCUNES_DONNEES);

            autocompleteField.setEnabled(true);
        }
    }

    public void setEditorPaneContent(Medicament medicament) {

        int id = medicament.getId();
        medicamentId = id;

        setElements();

        arrayMedicamentData = Controller.createArray(medicament);
        setContent(arrayMedicamentData, id);
    }

    /**
     * The method clears the EditorPane with custom HTML content, disables the buttons, and changes the state of the rcpToggleButton and the favoriteToggleButton.
     *
     * @param text HTML content
     */
    public void clearEditorPane(String text) {
        editorPane.setText(text);
        editorPane.setCaretPosition(Static.CARET_POSITION_ZERO);

        Static.UPDATE_BUTTON.setEnabled(false);
        Static.TOGGLE_BUTTON.setEnabled(false);
        Static.FAVORITE_TOGGLE_BUTTON.setEnabled(false);

        Static.TOGGLE_BUTTON.setSelected(false);
        Static.FAVORITE_TOGGLE_BUTTON.setSelected(false);
    }

    public String getEditorPaneSelectedText() {
        return editorPane.getSelectedText();
    }

    public void setDialogUpdateVariables(boolean manual) {
        dialogUpdate.setVariables(manual);
    }

    /**
     * The method informs user that a process is on going by setting the EditorPane with a GIF and disables the buttons.
     */
    private void setElements() {
    }

    /**
     * The method sets the text of the EditorPane with the information about the medicament and checks whether true or false the medicament is in the favorite list.
     *
     * @param array Array of Strings: the first element contains the base information about the medicament
     * @param id    id of the Medicament object
     */
    private void setContent(String[] array, int id) {
    }

    /**
     * The method adds a new entry to the favorite file.
     *
     * @param id id of the Medicament object
     */
    private void addFavorite(Integer id) {
    }

    /**
     * The method removes a new entry from the favorite file.
     *
     * @param id id of the Medicament object
     */
    private void removeFavorite(Integer id) {
    }

    /**
     * The method either performs a printing or generates a PDF file of the base information about the medicament.
     *
     * @param print Boolean true to print a document, false to create a PDF file
     */
    private void generatePDFOrPrintMedicament(boolean print) {
    }

    /**
     * The method either performs a printing or generates a PDF file of the Rcp.
     *
     * @param print Boolean true to print a document, false to create a PDF file
     */
    private void generatePDFOrPrintRcp(boolean print) {
    }
}