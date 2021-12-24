package fr.medicamentvet.gui.tabbedpane;

import fr.medicamentvet.entities.MedicamentSearch;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.dialogs.searchform.DialogDate;
import fr.medicamentvet.gui.dialogs.searchform.DialogEspece;
import fr.medicamentvet.gui.dialogs.searchform.DialogSubstanceActive;
import fr.medicamentvet.gui.simple.ComboBox;
import fr.medicamentvet.gui.simple.Label;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.SwingWorkerClass;
import fr.medicamentvet.utils.Utils;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents the advanced search form component.
 */
public class FormPane extends JPanel {

    private static Label labelFormPane;
    private static StringBuilder stringBuilderDate;

    private static LocalDate debutDate = LocalDate.of(Static.DATE_BEGIN_YEAR, Static.DATE_BEGIN_MONTH_ONE, Static.DATE_BEGIN_DAY_ONE);
    private static LocalDate finDate = LocalDate.now();
    private static String substanceActive1 = Static.EMPTY_TEXT;
    private static String substanceActive2 = Static.EMPTY_TEXT;
    private static List<String> especeSelectionList;

    private static ComboBox formePharmaceutiqueComboBox;
    private static ComboBox typeProcedureComboBox;
    private static ComboBox conditionDelivranceComboBox;
    private static DialogEspece dialogEspece;
    private static DialogSubstanceActive dialogSubstanceActive;

    private final AutocompleteField fieldNomTitulaire;
    private final AutocompleteField fieldNumeroAMM;

    public FormPane(JFrame parent, LayoutManager layout, SearchResult searchResult) {
        super(layout);

        setBorder(BorderFactory.createEmptyBorder(0, Static.BORDER_WIDTH_TEN, 10, Static.BORDER_WIDTH_TEN));

        String stringDebutDateFR = Utils.localDateToStringDateFR(debutDate);
        String StringFinDateFR = Utils.localDateToStringDateFR(finDate);

        stringBuilderDate = new StringBuilder();
        stringBuilderDate.append(Static.DIV_DATE_AMM_BEGINNING).append(stringDebutDateFR).append(Static.DATE_AMM_AU_TEXT).append(StringFinDateFR).append(Static.DIV_CLOSING);

        labelFormPane = new Label(Static.HTML_BEGINNING + stringBuilderDate.toString() + Static.HTML_CLOSING, Static.FONT_SIZE_ELEVEN, Static.LABEL_FORMPANE_COLOR_1, Static.LABEL_FORMPANE_COLOR_2);

        especeSelectionList = new ArrayList<>();

        fieldNomTitulaire = new AutocompleteField(this, Static.NOM_TITULAIRE_TEXT, Static.FIELD_WIDTH_NOM_TITULAIRE, Static.FIELD_HEIGHT);
        fieldNumeroAMM = new AutocompleteField(this, Static.NUMERO_AMM_TEXT, Static.FIELD_WIDTH_NUMERO_AMM, Static.FIELD_HEIGHT);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(Static.ARRAY_RECHERCHE_TEXT);
        Renderer renderer = new Renderer();

        formePharmaceutiqueComboBox = new ComboBox(model, renderer, Static.ROW_MAXIMUM_NUMBER);
        typeProcedureComboBox = new ComboBox(model, renderer, Static.ROW_MAXIMUM_NUMBER);
        conditionDelivranceComboBox = new ComboBox(model, renderer, Static.ROW_MAXIMUM_NUMBER);

        DialogDate dialogDate = new DialogDate(parent, Static.DATE_AMM_TEXT, true, false, Static.DIALOG_WIDTH_DATE, Static.DIALOG_HEIGHT_DATE_SUBSTANCE_ACTIVE, this);

        dialogSubstanceActive = new DialogSubstanceActive(parent, Static.SUBSTANCE_ACTIVE_PLURIEL_TEXT, false, false, Static.DIALOG_WIDTH_SUBSTANCE_ACTIVE, Static.DIALOG_HEIGHT_DATE_SUBSTANCE_ACTIVE, this);

        dialogEspece = new DialogEspece(parent, Static.ESPECE_DESTINATION_PLURIEL_TEXT, true, false, Static.DIALOG_WIDTH_ESPECE, Static.DIALOG_HEIGHT_ESPECE, Static.ESPECE_MAXIMUM_NUMBER, this);

        Utils.addComponent(this, Static.LABEL_NOM_TITULAIRE_FORM_PANE, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN));
        Utils.addComponent(this, Static.LABEL_NUMERO_AMM, Static.GRID_THREE, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, fieldNomTitulaire, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN));
        Utils.addComponent(this, fieldNumeroAMM, Static.GRID_THREE, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, Static.LABEL_FORME_PHARMACEUTIQUE_FORM_PANE, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN));
        Utils.addComponent(this, Static.LABEL_TYPE_PROCEDURE_FORM_PANE, Static.GRID_THREE, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, formePharmaceutiqueComboBox, Static.GRID_ZERO, Static.GRID_THREE, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN));
        Utils.addComponent(this, typeProcedureComboBox, Static.GRID_THREE, Static.GRID_THREE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, Static.LABEL_CONDITION_DELIVRANCE, Static.GRID_ZERO, Static.GRID_FOUR, Static.GRID_WIDTH_FOUR, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, conditionDelivranceComboBox, Static.GRID_ZERO, Static.GRID_FIVE, Static.GRID_WIDTH_FOUR, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, Static.BUTTON_DATE_AMM, Static.GRID_ZERO, Static.GRID_SIX, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN));
        Utils.addComponent(this, Static.BUTTON_SUBSTANCE_ACTIVE, Static.GRID_ONE, Static.GRID_SIX, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN));
        Utils.addComponent(this, Static.BUTTON_ESPECE, Static.GRID_TWO, Static.GRID_SIX, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN));
        Utils.addComponent(this, Static.BUTTON_RECHERCHER, Static.GRID_THREE, Static.GRID_SIX, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, labelFormPane, Static.GRID_ZERO, Static.GRID_SEVEN, Static.GRID_WIDTH_FOUR, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Static.BUTTON_DATE_AMM.addActionListener(actionEvent -> {
            dialogDate.setLocationRelativeTo(parent);
            dialogDate.init();
            dialogDate.setVisible(true);
        });

        Static.BUTTON_SUBSTANCE_ACTIVE.addActionListener(actionEvent -> {
            dialogSubstanceActive.setLocationRelativeTo(parent);
            dialogSubstanceActive.init();
            dialogSubstanceActive.setVisible(true);
        });

        Static.BUTTON_ESPECE.addActionListener(actionEvent -> {
            dialogEspece.setLocationRelativeTo(parent);
            dialogEspece.init();
            dialogEspece.setVisible(true);
        });

        Static.BUTTON_RECHERCHER.addActionListener(actionEvent -> {
            Static.BUTTON_RECHERCHER.setEnabled(false);

            String nomTitulaire = fieldNomTitulaire.getText().toUpperCase().trim();
            String numeroAMM = fieldNumeroAMM.getText().toUpperCase().trim();

            String formePharmaceutique = (String) formePharmaceutiqueComboBox.getSelectedItem();
            String typeProcedure = (String) typeProcedureComboBox.getSelectedItem();
            String conditionDelivrance = (String) conditionDelivranceComboBox.getSelectedItem();

            MedicamentSearch medicamentSearch = new MedicamentSearch(nomTitulaire, numeroAMM, formePharmaceutique, typeProcedure, conditionDelivrance, debutDate, finDate, substanceActive1, substanceActive2, especeSelectionList);

            searchResult.setEditorPaneContent(Static.BUTTON_RECHERCHER, medicamentSearch);
        });
    }

    /**
     * The aim of this method is to set the data needed to use the advanced form.
     *
     * @param manual Boolean true means that the background thread will always be executed
     */
    public void setFormPaneModel(boolean manual) {

        if (formePharmaceutiqueComboBox.getModel().getSize() == 1 || manual) {
            fieldNomTitulaire.setEnabled(false);
            fieldNumeroAMM.setEnabled(false);
            dialogSubstanceActive.setFieldsEnable(false);

            formePharmaceutiqueComboBox.setEnabled(false);
            typeProcedureComboBox.setEnabled(false);
            conditionDelivranceComboBox.setEnabled(false);

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(Static.ARRAY_RECHERCHE_TEXT);
            formePharmaceutiqueComboBox.setModel(model);
            typeProcedureComboBox.setModel(model);
            conditionDelivranceComboBox.setModel(model);

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);
            SwingWorkerClass swingWorkerClass = new SwingWorkerClass(Static.FORM_PANE_CLASS_NAME_TEXT, fieldNomTitulaire, fieldNumeroAMM, formePharmaceutiqueComboBox, typeProcedureComboBox, conditionDelivranceComboBox, dialogEspece, dialogSubstanceActive);
            swingWorkerClass.execute();
        }
    }
}
