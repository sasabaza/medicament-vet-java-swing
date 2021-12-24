package fr.medicamentvet.gui.dialogs.update;

import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.ModeleDestineVente;
import fr.medicamentvet.entities.Rcp;
import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.Field;
import fr.medicamentvet.gui.simple.Label;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * The class allows user to edit the information of ModeleDestineVente object. User may either continue to the next dialog or update Medicament object.
 */
public class DialogModeleDestineVente extends Dialog {

    private static JFrame mainFrame;
    private final DialogRcp dialogRcp;
    private final Button buttonNext;
    private final TabbedPane tabbedPane;

    private final Button buttonAddModeleDestineVente;
    private final JPanel gridBag;
    private final Label labelModeleDestineVente;
    private int libelleWidth;
    private boolean numeroAMMFR;
    private int id;
    private int count;
    private final int maxCount;

    public DialogModeleDestineVente(JFrame parent, DialogConditionnementPrimaireCodeATC dialogConditionnementPrimaireCodeATC, String title, boolean modal, boolean resizable, int width, int height, int maxCount, TabbedPane tabbedPane) {
        super(parent, title, modal, resizable, width, height);

        mainFrame = parent;

        this.tabbedPane = tabbedPane;
        this.maxCount = maxCount;

        dialogRcp = new DialogRcp(parent, this, Static.DIALOG_TITLE_RCP, true, false, Static.DIALOG_WIDTH_RCP, Static.DIALOG_HEIGHT_RCP, tabbedPane);

        buttonAddModeleDestineVente = new Button(Static.BUTTON_AJOUTER_TEXT, Static.BUTTON_PLUS_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_WIDTH, Static.BUTTON_FIELD_HEIGHT);
        labelModeleDestineVente = new Label(Static.LABEL_TEXT, null, Static.FONT_SIZE_ELEVEN, Static.LABEL_WIDTH_MODELE_DESTINE_VENTE, Static.LABEL_HEIGHT);

        gridBag = new JPanel(new GridBagLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(gridBag, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        buttonNext = getButtonValidate();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT, Static.BUTTON_RETOUR_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, Static.LAYOUT_GAP_TEN, Static.LAYOUT_GAP_ZERO));
        panelBottom.add(buttonReturn);
        panelBottom.add(buttonNext);

        Utils.addComponent(this, buttonAddModeleDestineVente, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ZERO, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));
        Utils.addComponent(this, labelModeleDestineVente, Static.GRID_ONE, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, scrollPane, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_FOUR, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonNext.addActionListener(actionEvent -> buttonNextAction());

        buttonReturn.addActionListener(actionEvent -> {

            Component[] arrayComponent = gridBag.getComponents();
            List<ModeleDestineVente> modeleDestineVenteList = new ArrayList<>();

            Medicament medicament = getMedicament();
            String numeroAMM = medicament.getNumeroAMM();
            int variableNumber;

            if (numeroAMMFR) {
                variableNumber = 3;
            } else {
                variableNumber = 4;
            }

            for (int i = 0; i < arrayComponent.length; i = i + variableNumber) {

                int idMdv = i / variableNumber + 1;

                Field field = (Field) arrayComponent[i];
                String libelle = field.getText().trim();

                String codeGTIN = null;

                field = (Field) arrayComponent[i + 1];
                if (field.getText() != null) {
                    codeGTIN = field.getText().trim();
                }

                if (!numeroAMMFR) {
                    field = (Field) arrayComponent[i + 2];
                    numeroAMM = field.getText().trim();
                }

                modeleDestineVenteList.add(new ModeleDestineVente(idMdv, libelle, codeGTIN, numeroAMM));
            }

            medicament.setModeleDestineVenteList(modeleDestineVenteList);

            dialogConditionnementPrimaireCodeATC.init(medicament);
            dialogConditionnementPrimaireCodeATC.setLocationRelativeTo(parent);
            setVisible(false);
            dialogConditionnementPrimaireCodeATC.setVisible(true);
        });

        buttonAddModeleDestineVente.addActionListener(actionEvent -> {

            Utils.buttonEnable(gridBag, count, true);

            count++;
            id++;

            createFieldsAndAddToGridBag(id, null);
            gridBag.updateUI();

            if (count >= maxCount) {
                buttonAddModeleDestineVente.setEnabled(false);
            }
        });
    }

    /**
     * The method displays the main information of ModeleDestineVente object, user may edit the information.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        Rcp rcp = medicament.getRcp();

        if (rcp == null) {
            buttonNext.setText(Static.BUTTON_METTRE_A_JOUR_TEXT);
            buttonNext.setPreferredSize(new Dimension(Static.BUTTON_UPDATE_WIDTH, Static.BUTTON_FIELD_HEIGHT));
            buttonNext.setMinimumSize(new Dimension(Static.BUTTON_UPDATE_WIDTH, Static.BUTTON_FIELD_HEIGHT));
            buttonNext.setSize(new Dimension(Static.BUTTON_UPDATE_WIDTH, Static.BUTTON_FIELD_HEIGHT));
        } else {
            buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);
        }

        String numeroAMM = medicament.getNumeroAMM();

        List<ModeleDestineVente> modeleDestineVenteList = medicament.getModeleDestineVenteList();
        count = modeleDestineVenteList.size();
        id = count;

        gridBag.removeAll();

        if (numeroAMM != null && numeroAMM.startsWith(Static.FR_TEXT)) {
            libelleWidth = Static.FIELD_WIDTH_LIBELLE_FR;
            numeroAMMFR = true;
            labelModeleDestineVente.setText(Static.LIBELLE_CODE_GTIN_TEXT);
        } else {
            libelleWidth = Static.FIELD_WIDTH_LIBELLE_EU;
            numeroAMMFR = false;
            labelModeleDestineVente.setText(Static.LIBELLE_CODE_GTIN_NUMERO_AMM_TEXT);
        }

        for (int i = 0; i < count; i++) {
            createFieldsAndAddToGridBag(i, modeleDestineVenteList);
        }

        Utils.buttonEnable(gridBag, count, false);
        buttonNext.requestFocus();
    }

    /**
     * The method creates fields and a button, and adds them to the JPanel.
     *
     * @param i                      Index of the list
     * @param modeleDestineVenteList List of ModeleDestineVente object
     */
    private void createFieldsAndAddToGridBag(int i, List<ModeleDestineVente> modeleDestineVenteList) {
    }

    /**
     * The method verifies whether true or false there is an error, extracts input from the fields and sets the modeleDestineVenteList object. The end part of the method may update Medicament object or displays the next dialog.
     */
    private void buttonNextAction() {
    }
}
