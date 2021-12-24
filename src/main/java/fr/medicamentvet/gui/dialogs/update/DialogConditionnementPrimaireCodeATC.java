package fr.medicamentvet.gui.dialogs.update;

import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.ScrollPane;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

/**
 * The class contains the information regarding the Conditionnement primaire and the code ATCVET.
 */
public class DialogConditionnementPrimaireCodeATC extends Dialog {

    private static JFrame mainFrame;
    private final DialogModeleDestineVente dialogModeleDestineVente;
    private final Button buttonNext;

    private final Button buttonAddConditionnementPrimaire;
    private final JPanel gridBagConditionnementPrimaire;
    private int idConditionnementPrimaire;
    private int countConditionnementPrimaire;

    private final Button buttonAddCodeATCVET;
    private final JPanel gridBagCodeATCVET;
    private int idCodeATCVET;
    private int countCodeATCVET;

    private final int maxCount;

    public DialogConditionnementPrimaireCodeATC(JFrame parent, DialogComposition dialogComposition, String title, boolean modal, boolean resizable, int width, int height, int maxCount, TabbedPane tabbedPane) {
        super(parent, title, modal, resizable, width, height);

        mainFrame = parent;

        this.maxCount = maxCount;

        dialogModeleDestineVente = new DialogModeleDestineVente(parent, this, Static.DIALOG_TITLE_MODELE_DESTINE_VENTE, true, true, Static.DIALOG_WIDTH_MODELE_DESTINE_VENTE, Static.DIALOG_HEIGHT_MODELE_DESTINE_VENTE, Static.MODELE_DESTINE_VENTE_MAXIMUM_NUMBER, tabbedPane);

        buttonAddConditionnementPrimaire = new Button(Static.BUTTON_AJOUTER_TEXT, Static.BUTTON_PLUS_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        gridBagConditionnementPrimaire = new JPanel(new GridBagLayout());
        JPanel panelConditionnementPrimaire = new JPanel(new BorderLayout());
        panelConditionnementPrimaire.add(gridBagConditionnementPrimaire, BorderLayout.NORTH);
        ScrollPane scrollPaneConditionnementPrimaire = new ScrollPane(panelConditionnementPrimaire, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, Static.SCROLLPANE_WIDTH_DIALOG_CONDITIONNEMENT_PRIMAIRE_CODE_ATC, Static.SCROLLPANE_HEIGHT_DIALOG_CONDITIONNEMENT_PRIMAIRE_CODE_ATC);

        buttonAddCodeATCVET = new Button(Static.BUTTON_AJOUTER_TEXT, Static.BUTTON_PLUS_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        gridBagCodeATCVET = new JPanel(new GridBagLayout());
        JPanel panelCodeATCVET = new JPanel(new BorderLayout());
        panelCodeATCVET.add(gridBagCodeATCVET, BorderLayout.NORTH);
        ScrollPane scrollPaneCodeATCVET = new ScrollPane(panelCodeATCVET, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, Static.SCROLLPANE_WIDTH_DIALOG_CONDITIONNEMENT_PRIMAIRE_CODE_ATC, Static.SCROLLPANE_HEIGHT_DIALOG_CONDITIONNEMENT_PRIMAIRE_CODE_ATC);

        buttonNext = getButtonValidate();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT, Static.BUTTON_RETOUR_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, Static.LAYOUT_GAP_TEN, Static.LAYOUT_GAP_ZERO));
        panelBottom.add(buttonReturn);
        panelBottom.add(buttonNext);

        Utils.addComponent(this, buttonAddConditionnementPrimaire, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ZERO, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));
        Utils.addComponent(this, Static.LABEL_CONDITIONNEMENT_PRIMAIRE, Static.GRID_ONE, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, scrollPaneConditionnementPrimaire, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, buttonAddCodeATCVET, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ZERO, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));
        Utils.addComponent(this, Static.LABEL_CODE_ATCVET, Static.GRID_ONE, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, scrollPaneCodeATCVET, Static.GRID_ZERO, Static.GRID_THREE, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_FOUR, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonNext.addActionListener(actionEvent -> buttonNextAction());

        buttonReturn.addActionListener(actionEvent -> {

            Component[] arrayComponent = gridBagConditionnementPrimaire.getComponents();
            List<String> conditionnementPrimaireList = textFieldComponentToList(arrayComponent);

            arrayComponent = gridBagCodeATCVET.getComponents();
            List<String> codeATCVETList = textFieldComponentToList(arrayComponent);

            Medicament medicament = getMedicament();

            medicament.setConditionnementPrimaireList(conditionnementPrimaireList);

            medicament.setCodeATCVETList(codeATCVETList);

            dialogComposition.init(medicament);
            dialogComposition.setLocationRelativeTo(parent);
            setVisible(false);
            dialogComposition.setVisible(true);
        });

        buttonAddConditionnementPrimaire.addActionListener(actionEvent -> {
            countConditionnementPrimaire++;
            idConditionnementPrimaire++;

            createFieldsAndAddToGridBag(idConditionnementPrimaire, null, Static.FIELD_WIDTH_CONDITIONNEMENT_PRIMAIRE, gridBagConditionnementPrimaire);
            gridBagConditionnementPrimaire.updateUI();

            if (countConditionnementPrimaire >= maxCount) {
                buttonAddConditionnementPrimaire.setEnabled(false);
            }
        });

        buttonAddCodeATCVET.addActionListener(actionEvent -> {
            countCodeATCVET++;
            idCodeATCVET++;

            createFieldsAndAddToGridBag(idCodeATCVET, null, Static.FIELD_WIDTH_CODE_ATCVET, gridBagCodeATCVET);
            gridBagCodeATCVET.updateUI();

            if (countCodeATCVET >= maxCount) {
                buttonAddCodeATCVET.setEnabled(false);
            }
        });
    }

    /**
     * The method puts components to the JPanels, and initializes the text of the fields.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        gridBagConditionnementPrimaire.removeAll();
        List<String> conditionnementPrimaireList = medicament.getConditionnementPrimaireList();

        if (conditionnementPrimaireList != null) {
            countConditionnementPrimaire = conditionnementPrimaireList.size();

            for (int i = 0; i < countConditionnementPrimaire; i++) {
                createFieldsAndAddToGridBag(i, conditionnementPrimaireList, Static.FIELD_WIDTH_CONDITIONNEMENT_PRIMAIRE, gridBagConditionnementPrimaire);
            }
        } else {
            countConditionnementPrimaire = 0;
        }
        idConditionnementPrimaire = countConditionnementPrimaire;

        gridBagCodeATCVET.removeAll();
        List<String> codeATCVETList = medicament.getCodeATCVETList();

        if (codeATCVETList != null) {
            countCodeATCVET = codeATCVETList.size();

            for (int i = 0; i < countCodeATCVET; i++) {
                createFieldsAndAddToGridBag(i, codeATCVETList, Static.FIELD_WIDTH_CODE_ATCVET, gridBagCodeATCVET);
            }
        } else {
            countCodeATCVET = 0;
        }
        idCodeATCVET = countCodeATCVET;

        buttonNext.requestFocus();
    }

    /**
     * The method creates the components and adds them to the respective JPanel.
     *
     * @param i       Index of the list
     * @param list    List of Strings either a list of conditionnement primaire or list of code ATCVET
     * @param width   Width of the field
     * @param gridBag JPanel GridBagLayout
     */
    private void createFieldsAndAddToGridBag(int i, List<String> list, int width, JPanel gridBag) {
    }

    /**
     * The method extracts the text for each field and returns a list of Strings.
     *
     * @param arrayComponent Array of components
     * @return List of Strings
     */
    private List<String> textFieldComponentToList(Component[] arrayComponent) {
        return new ArrayList<>();
    }

    /**
     * The method makes sure that there is no error and moves to the next dialog.
     */
    private void buttonNextAction() {
    }
}
