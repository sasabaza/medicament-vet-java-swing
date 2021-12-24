package fr.medicamentvet.gui.tabbedpane;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.dialogs.DialogDelete;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.SwingWorkerClass;
import java.awt.LayoutManager;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The class holds the AutocompleteField component, and completes tasks related to the AutocompleteField, the DialogDelete and the TableResult classes.
 */
public class FieldPanel extends JPanel {

    private final AutocompleteField fieldNom;
    private final TableResult tableResult;

    public FieldPanel(LayoutManager layout, TableResult tableResult) {
        super(layout);

        this.tableResult = tableResult;

        setBorder(BorderFactory.createEmptyBorder(0, Static.BORDER_WIDTH_TEN, 0, Static.BORDER_WIDTH_TEN));

        fieldNom = new AutocompleteField(this, Static.NOM_TEXT, Static.FIELD_WIDTH_FIELD_PANEL, Static.FIELD_HEIGHT);

        add(fieldNom);
    }

    /**
     * After the deletion of the medicament, this method may set to null the Medicament object from the Controller class and may set the text of the AutocompleteField to empty.
     *
     * @param id id of the Medicament object
     * @return Boolean true means that the Medicament object and the text of the AutocompleteField have been initialized to null and empty respectively
     */
    public boolean setClear(int id) {
        boolean clear = false;

        Medicament medicament = Controller.getMedicament();

        if (medicament != null) {
            String nom = medicament.getNom();

            Map<String, Integer> nomIdMap = Controller.getNomIdMap();
            int idNom = nomIdMap.get(nom);

            if (id == idNom) {
                Controller.setMedicament(null);
                fieldNom.setText(Static.EMPTY_TEXT);
                clear = true;
            }
        }

        return clear;
    }

    public void setFieldTextByNom(Object nom) {
        fieldNom.setAutocompleteFieldText(nom);
        getDataByNom(String.valueOf(nom));
    }

    public void setFieldTextAndRetrieveData(Medicament medicament) {
        String nom = medicament.getNom();
        fieldNom.setAutocompleteFieldText(nom);
        tableResult.setEditorPaneContent(medicament);
    }

    public void getDataByNom(String nom) {
        fieldNom.setEnabled(false);

        Map<String, Integer> nomIdMap = Controller.getNomIdMap();

        int id = -1;
        if (nomIdMap.containsKey(nom)) {
            id = nomIdMap.get(nom);
        }

        tableResult.setEditorPaneContentById(id, fieldNom);
    }

    /**
     * The purpose of this method is to execute a task in a background thread, specially to set String array variable of AutocompleteField class. See the AutocompleteField and the DialogDelete classes for more information
     *
     * @param manual       Boolean true means that the background thread will always be executed
     * @param dialogDelete DialogDelete component
     */
    public void setVariable(boolean manual, DialogDelete dialogDelete) {

        if (fieldNom.getArrayString() == null || manual) {

            fieldNom.setEnabled(false);

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);
            SwingWorkerClass swingWorkerClass = new SwingWorkerClass(Static.FIELD_PANEL_CLASS_NAME_TEXT, fieldNom, dialogDelete);
            swingWorkerClass.execute();
        }
    }
}