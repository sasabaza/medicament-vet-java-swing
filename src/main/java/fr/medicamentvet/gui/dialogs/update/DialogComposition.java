package fr.medicamentvet.gui.dialogs.update;

import fr.medicamentvet.entities.Composition;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.Field;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * The class represents a dialog window where user can insert or delete text relevant to the composition of a medicament.
 */
public class DialogComposition extends Dialog {

    private static JFrame mainFrame;
    private final DialogConditionnementPrimaireCodeATC dialogConditionnementPrimaireCodeATC;
    private final Button buttonNext;

    private final Button buttonAddSubstanceActive;
    private final JPanel gridBag;
    private int id;
    private int count;
    private final int maxCount;

    public DialogComposition(JFrame parent, DialogExcipientQSP dialogExcipientQSP, String title, boolean modal, boolean resizable, int width, int height, int maxCount, TabbedPane tabbedPane) {
        super(parent, title, modal, resizable, width, height);

        mainFrame = parent;

        this.maxCount = maxCount;

        dialogConditionnementPrimaireCodeATC = new DialogConditionnementPrimaireCodeATC(parent, this, Static.DIALOG_TITLE_CONDITIONNEMENT_PRIMAIRE, true, true, Static.DIALOG_WIDTH_CONDITIONNEMENT_PRIMAIRE, Static.DIALOG_HEIGHT_CONDITIONNEMENT_PRIMAIRE, Static.CONDITIONNEMENT_PRIMAIRE_CODE_ATC_MAXIMUM_NUMBER, tabbedPane);

        buttonAddSubstanceActive = new Button(Static.BUTTON_AJOUTER_TEXT, Static.BUTTON_PLUS_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        gridBag = new JPanel(new GridBagLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(gridBag, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(Static.SCROLLPANE_WIDTH_DIALOG_COMPOSITION, Static.SCROLLPANE_HEIGHT_DIALOG_COMPOSITION));
        scrollPane.setMinimumSize(new Dimension(Static.SCROLLPANE_WIDTH_DIALOG_COMPOSITION, Static.SCROLLPANE_HEIGHT_DIALOG_COMPOSITION));
        scrollPane.setSize(new Dimension(Static.SCROLLPANE_WIDTH_DIALOG_COMPOSITION, Static.SCROLLPANE_HEIGHT_DIALOG_COMPOSITION));

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        BoundedRangeModel modelScrollBar = verticalScrollBar.getModel();

        buttonNext = getButtonValidate();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT, Static.BUTTON_RETOUR_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, Static.LAYOUT_GAP_TEN, Static.LAYOUT_GAP_ZERO));
        panelBottom.add(buttonReturn);
        panelBottom.add(buttonNext);

        Utils.addComponent(this, buttonAddSubstanceActive, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ZERO, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));
        Utils.addComponent(this, Static.LABEL_COMPOSITION, Static.GRID_ONE, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, scrollPane, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonNext.addActionListener(actionEvent -> buttonNextAction());

        buttonReturn.addActionListener(actionEvent -> {

            Component[] arrayComponent = gridBag.getComponents();
            List<Composition> compositionList = new ArrayList<>();

            for (int i = 0; i < arrayComponent.length; i = i + 4) {

                int idComposition = i / 4 + 1;

                AutocompleteField autocompleteField = (AutocompleteField) arrayComponent[i];
                String substanceActive = autocompleteField.getText().trim();

                String quantite = null;

                Field field = (Field) arrayComponent[i + 1];
                if (field.getText() != null) {
                    quantite = field.getText().trim();
                }

                String unite = null;

                field = (Field) arrayComponent[i + 2];
                if (field.getText() != null) {
                    unite = field.getText().trim();
                }

                compositionList.add(new Composition(idComposition, substanceActive, quantite, unite));
            }

            Medicament medicament = getMedicament();
            medicament.setCompositionList(compositionList);

            dialogExcipientQSP.init(medicament);
            dialogExcipientQSP.setLocationRelativeTo(parent);
            setVisible(false);
            dialogExcipientQSP.setVisible(true);
        });

        buttonAddSubstanceActive.addActionListener(actionEvent -> {

            Utils.buttonEnable(gridBag, count, true);

            count++;
            id++;

            createFieldsAndAddToGridBag(id, null);
            gridBag.updateUI();

            if (count >= maxCount) {
                buttonAddSubstanceActive.setEnabled(false);
            }
        });

        modelScrollBar.addChangeListener(actionEvent -> {

            Object source = actionEvent.getSource();
            DefaultBoundedRangeModel boundedRangeModel = (DefaultBoundedRangeModel) source;

            if (!boundedRangeModel.getValueIsAdjusting()) {

                requestFocus();
            }
        });

        // These listeners trigger requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                requestFocus();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                requestFocus();
            }
        });
    }

    /**
     * The purpose of the method is to create components and add them to the JPanel.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        List<Composition> compositionList = medicament.getCompositionList();
        count = compositionList.size();
        id = count;

        buttonAddSubstanceActive.setEnabled(true);
        gridBag.removeAll();

        for (int i = 0; i < count; i++) {
            createFieldsAndAddToGridBag(i, compositionList);
        }

        Utils.buttonEnable(gridBag, count, false);
        buttonNext.requestFocus();
    }

    /**
     * The method creates necessary fields to modify the text, and a button that is added to the row of components.
     *
     * @param i               Index of the list
     * @param compositionList List of Composition objects
     */
    private void createFieldsAndAddToGridBag(int i, List<Composition> compositionList) {
    }

    /**
     * The goal of the method is to validate the text and shows the next dialog.
     */
    private void buttonNextAction() {
    }
}
