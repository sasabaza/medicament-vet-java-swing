package fr.medicamentvet.utils;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.MedicamentSearch;
import fr.medicamentvet.entities.SearchForm;
import fr.medicamentvet.entities.UpdateForm;
import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.gui.dialogs.DialogDelete;
import fr.medicamentvet.gui.dialogs.searchform.DialogEspece;
import fr.medicamentvet.gui.dialogs.searchform.DialogSubstanceActive;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.ComboBox;
import fr.medicamentvet.gui.simple.EditorPane;
import fr.medicamentvet.gui.simple.ToggleButton;
import fr.medicamentvet.gui.tabbedpane.FieldPanel;
import fr.medicamentvet.gui.tabbedpane.TableResult;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * The purpose of this class is to manage tasks in a background thread. Typically, when there is long-running process, we want the UI of the application to remain responsive.
 */
public class SwingWorkerClass extends SwingWorker<Object, Void> {

    private final String className;

    private AutocompleteField fieldNom;
    private DialogDelete dialogDelete;

    private int id;
    private EditorPane editorPane;
    private Button updateButton;
    private ToggleButton rcpToggleButton;
    private ToggleButton favoriteToggleButton;
    private TableResult tableResult;

    private Button buttonSearch;
    private MedicamentSearch medicamentSearch;
    private String data;

    private AutocompleteField fieldNomTitulaire;
    private AutocompleteField fieldNumeroAMM;
    private ComboBox formePharmaceutiqueComboBox;
    private ComboBox typeProcedureComboBox;
    private ComboBox conditionDelivranceComboBox;
    private DialogEspece dialogEspece;
    private DialogSubstanceActive dialogSubstanceActive;

    private ComboBox statutAutorisationComboBox;

    private Button buttonDelete;
    private FieldPanel fieldPanel;

    private Button buttonNext;
    private Medicament medicament;
    private Dialog dialog;
    private TabbedPane tabbedPane;

    private PrinterJob printerJob;
    private PDDocument document;

    public SwingWorkerClass(String className, AutocompleteField fieldNom, DialogDelete dialogDelete) {
        this.className = className;
        this.fieldNom = fieldNom;
        this.dialogDelete = dialogDelete;
    }

    public SwingWorkerClass(String className, Button buttonSearch, EditorPane editorPane, String data) {
        this.className = className;
        this.buttonSearch = buttonSearch;
        this.editorPane = editorPane;
        this.data = data;
    }

    public SwingWorkerClass(String className, AutocompleteField fieldNom, int id, EditorPane editorPane, Button updateButton, ToggleButton rcpToggleButton, ToggleButton favoriteToggleButton, TableResult tableResult) {
        this.className = className;
        this.fieldNom = fieldNom;
        this.id = id;
        this.editorPane = editorPane;
        this.updateButton = updateButton;
        this.rcpToggleButton = rcpToggleButton;
        this.favoriteToggleButton = favoriteToggleButton;
        this.tableResult = tableResult;
    }

    public SwingWorkerClass(String className, Button buttonSearch, MedicamentSearch medicamentSearch, EditorPane editorPane) {
        this.className = className;
        this.buttonSearch = buttonSearch;
        this.medicamentSearch = medicamentSearch;
        this.editorPane = editorPane;
    }

    public SwingWorkerClass(String className, AutocompleteField fieldNomTitulaire, ComboBox typeProcedureComboBox, ComboBox statutAutorisationComboBox, ComboBox formePharmaceutiqueComboBox) {
        this.className = className;
        this.fieldNomTitulaire = fieldNomTitulaire;
        this.typeProcedureComboBox = typeProcedureComboBox;
        this.statutAutorisationComboBox = statutAutorisationComboBox;
        this.formePharmaceutiqueComboBox = formePharmaceutiqueComboBox;
    }

    public SwingWorkerClass(String className, AutocompleteField fieldNomTitulaire, AutocompleteField fieldNumeroAMM, ComboBox formePharmaceutiqueComboBox, ComboBox typeProcedureComboBox, ComboBox conditionDelivranceComboBox, DialogEspece dialogEspece, DialogSubstanceActive dialogSubstanceActive) {
        this.className = className;
        this.fieldNomTitulaire = fieldNomTitulaire;
        this.fieldNumeroAMM = fieldNumeroAMM;
        this.formePharmaceutiqueComboBox = formePharmaceutiqueComboBox;
        this.typeProcedureComboBox = typeProcedureComboBox;
        this.conditionDelivranceComboBox = conditionDelivranceComboBox;
        this.dialogEspece = dialogEspece;
        this.dialogSubstanceActive = dialogSubstanceActive;
    }

    public SwingWorkerClass(String className, Button buttonDelete, int id, TableResult tableResult, FieldPanel fieldPanel) {
        this.className = className;
        this.buttonDelete = buttonDelete;
        this.id = id;
        this.tableResult = tableResult;
        this.fieldPanel = fieldPanel;
    }

    public SwingWorkerClass(String className, PrinterJob printerJob, PDDocument document) {
        this.className = className;
        this.printerJob = printerJob;
        this.document = document;
    }

    @Override
    protected Object doInBackground() throws IOException, InterruptedException, PrinterException {
        Object object = new Object();

        switch (className) {
            case Static.FIELD_PANEL_CLASS_NAME_TEXT:
                object = Controller.getAllMedicamentsNomId();
                break;
            case Static.FORM_PANE_CLASS_NAME_TEXT:
                object = Controller.getSearchFormInputs();
                break;
            case Static.DIALOG_UPDATE_CLASS_NAME_TEXT:
                object = Controller.getUpdateFormInputs();
                break;
            case Static.DIALOG_DELETE_CLASS_NAME_TEXT:
                Controller.deleteMedicamentById(id);
                break;
            case Static.DIALOG_SAVE_UPDATE_TEXT:
                object = Controller.updateMedicament(medicament);
                break;
            case Static.SEARCH_RESULT_CLASS_NAME_TEXT:
                object = Controller.searchMedicamentNames(medicamentSearch);
                break;
            case Static.SWINGWORKER_CLASS_TEXT:
                editorPane.setText(data);
                break;
            case Static.TABLE_RESULT_CLASS_NAME_TEXT:
                object = Controller.getMedicamentById(id);
                break;
            case Static.SEARCH_RESULT_PRINTER_CLASS_TEXT:
                printAction(Static.DIALOG_TITLE_RECHERCHE_PRINT, printerJob);
                break;
            case Static.TABLE_RESULT_PRINTER_CLASS_TEXT:
                printAction(Static.DIALOG_TITLE_INFORMATION_PRINT, printerJob);
                break;
        }

        return object;
    }

    @Override
    protected void done() {

        switch (className) {
            case Static.FORM_PANE_CLASS_NAME_TEXT:

                SearchForm searchForm = null;
                try {
                    searchForm = (SearchForm) get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);
                }

                if (searchForm != null) {
                    List<String> nomTitulaireList = searchForm.getNomTitulaireList();
                    List<String> typeProcedureList = searchForm.getTypeProcedureList();
                    List<String> formePharmaceutiqueList = searchForm.getFormePharmaceutiqueList();
                    List<String> numeroAMMList = searchForm.getNumeroAMMList();
                    List<String> especeDestinationList = searchForm.getEspeceDestinationList();
                    List<String> substanceActiveList = searchForm.getSubstanceActiveList();
                    List<String> conditionDelivranceList = searchForm.getConditionDelivranceList();

                    String[] arrayNomTitulaire = nomTitulaireList.toArray(new String[0]);
                    fieldNomTitulaire.setArrayString(arrayNomTitulaire);

                    String[] arrayNumeroAMM = numeroAMMList.toArray(new String[0]);
                    fieldNumeroAMM.setArrayString(arrayNumeroAMM);

                    setComboBoxModel(typeProcedureComboBox, typeProcedureList);
                    setComboBoxModel(formePharmaceutiqueComboBox, formePharmaceutiqueList);
                    setComboBoxModel(conditionDelivranceComboBox, conditionDelivranceList);

                    dialogEspece.setModel(especeDestinationList, new ArrayList<>());

                    String[] arrayString = substanceActiveList.toArray(new String[0]);
                    dialogSubstanceActive.setArrays(arrayString);
                }

                fieldNomTitulaire.setEnabled(true);
                fieldNumeroAMM.setEnabled(true);

                typeProcedureComboBox.setEnabled(true);
                formePharmaceutiqueComboBox.setEnabled(true);
                conditionDelivranceComboBox.setEnabled(true);

                dialogSubstanceActive.setFieldsEnable(true);

                break;
            case Static.DIALOG_UPDATE_CLASS_NAME_TEXT:

                UpdateForm updateForm = null;
                try {
                    updateForm = (UpdateForm) get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);
                }

                if (updateForm != null) {
                    List<String> nomTitulaireList = updateForm.getNomTitulaireList();
                    List<String> typeProcedureList = updateForm.getTypeProcedureList();
                    List<String> statutAutorisationList = updateForm.getStatutAutorisationList();
                    List<String> formePharmaceutiqueList = updateForm.getFormePharmaceutiqueList();

                    String[] arrayNomTitulaire = nomTitulaireList.toArray(new String[0]);
                    fieldNomTitulaire.setArrayString(arrayNomTitulaire);

                    setComboBoxModel(typeProcedureComboBox, typeProcedureList);
                    setComboBoxModel(statutAutorisationComboBox, statutAutorisationList);
                    setComboBoxModel(formePharmaceutiqueComboBox, formePharmaceutiqueList);
                }

                fieldNomTitulaire.setEnabled(true);
                typeProcedureComboBox.setEnabled(true);
                statutAutorisationComboBox.setEnabled(true);
                formePharmaceutiqueComboBox.setEnabled(true);

                break;
            case Static.FIELD_PANEL_CLASS_NAME_TEXT:

                Map<String, Integer> nomIdMap = null;
                try {
                    nomIdMap = (Map<String, Integer>) get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);
                }

                if (nomIdMap != null) {
                    String[] arrayNom = Utils.nomIdMapToArrayNomSorted();

                    fieldNom.setArrayString(arrayNom);
                    dialogDelete.setVariable(arrayNom);
                    clearEntriesInFiles();
                }
                fieldNom.setEnabled(true);

                break;
            case Static.DIALOG_DELETE_CLASS_NAME_TEXT:

                try {
                    get();

                    // Check if the autocompleteField name is identical to name of deleted Medicament
                    // if true autocompleteField text and TableResult content are cleared
                    boolean clear = fieldPanel.setClear(id);

                    if (clear) {
                        tableResult.clearEditorPane(Static.EMPTY_BODY_HTML);
                    }

                    TabbedPane.updateDataInput();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);
                }

                buttonDelete.setEnabled(true);

                break;
            case Static.DIALOG_SAVE_UPDATE_TEXT:

                Medicament medicament = null;
                try {
                    medicament = (Medicament) get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION_FILE_NOT_FOUND);
                }

                if (medicament != null) {
                    Controller.setMedicament(medicament);
                    tabbedPane.updateData(medicament);
                }

                buttonNext.setEnabled(true);
                dialog.setVisible(false);

                break;
            case Static.SEARCH_RESULT_CLASS_NAME_TEXT:

                List<String> list = null;
                try {
                    list = (List<String>) get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);
                }

                if (list != null) {
                    String result = searchResultsGUI(list);

                    // JEditorPane may become unresponsive therefore we create a new task that processes in background the setText method of the JEditorPane.
                    SwingWorkerClass swingWorkerClass = new SwingWorkerClass(Static.SWINGWORKER_CLASS_TEXT, buttonSearch, editorPane, result);
                    swingWorkerClass.execute();
                } else {
                    buttonSearch.setEnabled(true);
                    editorPane.setCaretPosition(Static.CARET_POSITION_ZERO);
                }

                break;
            case Static.SWINGWORKER_CLASS_TEXT:

                buttonSearch.setEnabled(true);
                editorPane.setCaretPosition(Static.CARET_POSITION_ZERO);

                break;
            case Static.TABLE_RESULT_CLASS_NAME_TEXT:

                String[] array;
                try {
                    array = (String[]) get();

                    if (array != null) {
                        editorPane.setText(array[0]);
                        tableResult.setArrayMedicamentData(array);

                        updateButton.setEnabled(true);
                        rcpToggleButton.setEnabled(true);
                        favoriteToggleButton.setEnabled(true);

                        addEntryToHistory(id, LocalDateTime.now());
                    } else {
                        editorPane.setText(Static.AUCUNES_DONNEES);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);
                }

                editorPane.setCaretPosition(Static.CARET_POSITION_ZERO);
                rcpToggleButton.setSelected(false);

                boolean b = Utils.checkFavorite(id);
                favoriteToggleButton.setSelected(b);

                fieldNom.setEnabled(true);

                break;
            case Static.SEARCH_RESULT_PRINTER_CLASS_TEXT:
            case Static.TABLE_RESULT_PRINTER_CLASS_TEXT:

                try {
                    get();
                } catch (InterruptedException | ExecutionException e) {
                    printerJob.cancel();
                    e.printStackTrace();
                    Utils.temporaryStatusMessage(Static.INTERRUPTION_IMPRESSION_TEXT);
                }

                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    /**
     * The method generates an HTML content containing the search results.
     *
     * @param list List of Strings
     * @return HTML content
     */
    private String searchResultsGUI(List<String> list) {
        return "";
    }

    /**
     * The objective of the method is to clear the id of the Medicament object that does no longer exist.
     */
    private void clearEntriesInFiles() {
    }

    private void addEntryToHistory(Integer id, LocalDateTime localDateTime) {
    }

    private void printAction(String title, PrinterJob printerJob) throws PrinterException {
    }

    private void setComboBoxModel(JComboBox<String> comboBox, List<String> list) {
    }
}