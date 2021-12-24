package fr.medicamentvet.gui.dialogs.update;

import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.FileChooser;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.ComboBox;
import fr.medicamentvet.gui.simple.Panel;
import fr.medicamentvet.gui.tabbedpane.Renderer;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.SwingWorkerClass;
import fr.medicamentvet.utils.Utils;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

/**
 * The class is a dialog allowing user to modify the base information of the medicament.
 */
public class DialogUpdate extends Dialog {

    private static JFrame mainFrame;
    private final DialogEspeceUpdate dialogEspeceUpdate;
    private final Button buttonAddImage;
    private final Button buttonRemoveImage;
    private final Button buttonNext;
    private final Button buttonCancel;

    private final AutocompleteField fieldNom;
    private final AutocompleteField fieldNomTitulaire;
    private final ComboBox formePharmaceutiqueComboBox;
    private final ComboBox statutAutorisationComboBox;
    private final ComboBox typeProcedureComboBox;
    private final JSpinner spinnerDateAMM;

    public DialogUpdate(JFrame parent, String title, boolean modal, boolean resizable, int width, int height, TabbedPane tabbedPane) {
        super(parent, title, modal, resizable, width, height);

        mainFrame = parent;

        dialogEspeceUpdate = new DialogEspeceUpdate(parent, this, Static.DIALOG_TITLE_ESPECE_UPDATE, true, false, Static.DIALOG_WIDTH_ESPECE_UPDATE, Static.DIALOG_HEIGHT_ESPECE_UPDATE, Static.ESPECE_MAXIMUM_NUMBER, tabbedPane);

        buttonAddImage = new Button(Static.BUTTON_AJOUTER_TEXT, Static.BUTTON_PLUS_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_WIDTH, Static.BUTTON_FIELD_HEIGHT);
        buttonRemoveImage = new Button(Static.BUTTON_SUPPRIMER_TEXT, Static.BUTTON_MINUS_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        JPanel panelButtonsImage = new JPanel(new FlowLayout(FlowLayout.LEFT, Static.LAYOUT_GAP_FIFTEEN, Static.LAYOUT_GAP_ZERO));
        panelButtonsImage.add(Static.LABEL_IMAGE_UPDATE);
        panelButtonsImage.add(buttonAddImage);
        panelButtonsImage.add(buttonRemoveImage);

        Panel panelImage = new Panel(new FlowLayout(FlowLayout.LEFT, Static.LAYOUT_GAP_ZERO, Static.LAYOUT_GAP_ZERO), Static.PANEL_IMAGE_WIDTH, Static.PANEL_IMAGE_HEIGHT);
        panelImage.add(Static.IMAGE_ICON);

        fieldNom = new AutocompleteField(this, Static.NOM_MEDICAMENT_TEXT, Static.FIELD_WIDTH_NOM, Static.FIELD_HEIGHT);
        fieldNomTitulaire = new AutocompleteField(this, Static.NOM_TITULAIRE_TEXT, Static.FIELD_WIDTH_NOM, Static.FIELD_HEIGHT);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(Static.ARRAY_RECHERCHE_TEXT);
        Renderer renderer = new Renderer();

        formePharmaceutiqueComboBox = new ComboBox(model, renderer, Static.ROW_MAXIMUM_NUMBER);
        statutAutorisationComboBox = new ComboBox(model, renderer, Static.ROW_MAXIMUM_NUMBER);
        typeProcedureComboBox = new ComboBox(model, renderer, Static.ROW_MAXIMUM_NUMBER);

        Calendar calendar = Calendar.getInstance();
        Date nowDate = calendar.getTime();
        calendar.add(Calendar.YEAR, Static.DATE_YEAR_QUANTITY_MAXIMUM);
        Date endDate = calendar.getTime();
        calendar.set(Static.DATE_BEGIN_YEAR, Calendar.JANUARY, Static.DATE_BEGIN_DAY_ZERO);
        Date startDate = calendar.getTime();
        int steps = Calendar.DAY_OF_MONTH;

        SpinnerModel modelDateAMM = new SpinnerDateModel(nowDate, startDate, endDate, steps);
        spinnerDateAMM = new JSpinner(modelDateAMM);
        JComponent editorDateAMM = new JSpinner.DateEditor(spinnerDateAMM, Static.SPINNER_DATE_FORMAT_PATTERN);
        spinnerDateAMM.setEditor(editorDateAMM);

        buttonNext = getButtonValidate();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        buttonCancel = getButtonCancel();

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, Static.LAYOUT_GAP_TEN, Static.LAYOUT_GAP_ZERO));
        panelBottom.add(buttonCancel);
        panelBottom.add(buttonNext);

        Utils.addComponent(this, Static.LABEL_NOM_UPDATE, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_FOUR, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, fieldNom, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_FOUR, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, Static.LABEL_NOM_TITULAIRE_UPDATE, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_FOUR, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, fieldNomTitulaire, Static.GRID_ZERO, Static.GRID_THREE, Static.GRID_WIDTH_FOUR, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, Static.LABEL_FORME_PHARMACEUTIQUE_UPDATE, Static.GRID_ZERO, Static.GRID_FOUR, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));
        Utils.addComponent(this, Static.LABEL_DATE_AMM, Static.GRID_THREE, Static.GRID_FOUR, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, formePharmaceutiqueComboBox, Static.GRID_ZERO, Static.GRID_FIVE, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));
        Utils.addComponent(this, spinnerDateAMM, Static.GRID_THREE, Static.GRID_FIVE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelButtonsImage, Static.GRID_ZERO, Static.GRID_SIX, Static.GRID_WIDTH_FOUR, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, panelImage, Static.GRID_ZERO, Static.GRID_SEVEN, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_FOUR, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_TEN, Static.INSET_ZERO));
        Utils.addComponent(this, Static.LABEL_STATUT_AUTORISATION, Static.GRID_ONE, Static.GRID_SEVEN, Static.GRID_WIDTH_TWO, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_FIFTEEN));
        Utils.addComponent(this, Static.LABEL_TYPE_PROCEDURE_UPDATE, Static.GRID_THREE, Static.GRID_SEVEN, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, statutAutorisationComboBox, Static.GRID_ONE, Static.GRID_EIGHT, Static.GRID_WIDTH_TWO, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_FIFTEEN));
        Utils.addComponent(this, typeProcedureComboBox, Static.GRID_THREE, Static.GRID_EIGHT, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_TEN, Static.GRID_WIDTH_FOUR, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIVE));

        pack();

        buttonAddImage.addActionListener(actionEvent -> {

            FileChooser fileChooser = new FileChooser();

            String selectedFileName = fileChooser.addImage(this);

            if (selectedFileName != null) {

                if (selectedFileName.length() > Static.IMAGE_MAX_CHARACTERS) {
                    JOptionPane.showMessageDialog(this, Static.ERROR_MESSAGE_PATH_TOO_LONG_TEXT, Static.DIALOG_TITLE_ERREUR, JOptionPane.ERROR_MESSAGE);
                } else {

                    buttonNext.setEnabled(true);
                    buttonCancel.setEnabled(true);
                    buttonAddImage.setEnabled(false);
                    buttonRemoveImage.setEnabled(true);

                    LinkedHashSet<String> hashSetError = new LinkedHashSet<>();

                    ImageIcon imageIcon = Utils.imageIconResize(selectedFileName, buttonNext, buttonCancel, buttonAddImage, buttonRemoveImage, hashSetError);

                    if (imageIcon != null) {
                        Medicament medicament = getMedicament();
                        medicament.setImageURL(selectedFileName);
                    }

                    if (hashSetError.size() > 0) {
                        String errorMessage = String.join(Static.PATTERN_NEWLINE, hashSetError);

                        JOptionPane.showMessageDialog(this, errorMessage, Static.DIALOG_TITLE_ERREUR, JOptionPane.ERROR_MESSAGE);
                    }

                    Static.IMAGE_ICON.setIcon(imageIcon);
                }
            } else {
                buttonAddImage.setEnabled(true);
                buttonRemoveImage.setEnabled(false);

                Static.IMAGE_ICON.setIcon(null);
            }
        });

        buttonRemoveImage.addActionListener(actionEvent -> {
            Medicament medicament = getMedicament();

            buttonAddImage.setEnabled(true);
            buttonRemoveImage.setEnabled(false);

            Static.IMAGE_ICON.setIcon(null);

            medicament.setImageURL(null);
        });

        buttonNext.addActionListener(actionEvent -> buttonNextAction());

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                requestFocus();
            }
        });
    }

    /**
     * The method initializes the dialog with base information of the Medicament object.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        buttonCancel.setEnabled(true);

        if (medicament != null) {

            buttonAddImage.setEnabled(true);
            buttonRemoveImage.setEnabled(false);
            buttonNext.setEnabled(true);
            buttonCancel.setEnabled(true);

            Static.IMAGE_ICON.setIcon(null);

            String imageURL = medicament.getImageURL();

            if (imageURL != null) {

                buttonAddImage.setEnabled(false);
                buttonRemoveImage.setEnabled(true);

                LinkedHashSet<String> hashSetError = new LinkedHashSet<>();

                ImageIcon imageIcon = Utils.imageIconResize(imageURL, buttonNext, buttonCancel, buttonAddImage, buttonRemoveImage, hashSetError);

                if (hashSetError.size() > 0) {
                    String errorMessage = String.join(Static.PATTERN_NEWLINE, hashSetError);

                    JOptionPane.showMessageDialog(this, errorMessage, Static.DIALOG_TITLE_ERREUR, JOptionPane.ERROR_MESSAGE);

                    medicament.setImageURL(null);
                }

                Static.IMAGE_ICON.setIcon(imageIcon);
            }

            String nom = medicament.getNom();

            String[] arrayNom = Utils.nomIdMapToArrayNomSorted();
            fieldNom.setArrayString(arrayNom);
            fieldNom.setAutocompleteFieldText(nom);

            String nomTitulaire = medicament.getNomTitulaire();
            fieldNomTitulaire.setAutocompleteFieldText(nomTitulaire);

            String formePharmaceutique = medicament.getFormePharmaceutique();
            formePharmaceutiqueComboBox.setSelectedItem(formePharmaceutique);

            LocalDate dateAMM = medicament.getDateAMM();
            Date date = Utils.localDateToDate(dateAMM);
            spinnerDateAMM.setValue(date);

            String statutAutorisation = medicament.getStatutAutorisation();
            statutAutorisationComboBox.setSelectedItem(statutAutorisation);

            String typeProcedure = medicament.getTypeProcedure();
            typeProcedureComboBox.setSelectedItem(typeProcedure);

            buttonNext.requestFocus();
        } else {
            buttonAddImage.setEnabled(false);
            buttonRemoveImage.setEnabled(false);
            buttonNext.setEnabled(false);
            buttonCancel.setEnabled(false);
        }
    }

    /**
     * The objective of the method is to perform a task in a background thread to set the data needed to edit the base information of a medicament.
     *
     * @param manual Boolean true means that the background thread will always be executed
     */
    public void setVariables(boolean manual) {

        if (fieldNomTitulaire.getArrayString() == null || manual) {
            fieldNomTitulaire.setEnabled(false);
            typeProcedureComboBox.setEnabled(false);
            statutAutorisationComboBox.setEnabled(false);
            formePharmaceutiqueComboBox.setEnabled(false);

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);
            SwingWorkerClass swingWorkerClass = new SwingWorkerClass(Static.DIALOG_UPDATE_CLASS_NAME_TEXT, fieldNomTitulaire, typeProcedureComboBox, statutAutorisationComboBox, formePharmaceutiqueComboBox);
            swingWorkerClass.execute();
        }
    }

    /**
     * The method checks that the information entered is valid and then displays the next dialog.
     */
    private void buttonNextAction() {
    }
}
