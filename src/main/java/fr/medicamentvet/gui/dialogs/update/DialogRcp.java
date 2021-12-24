package fr.medicamentvet.gui.dialogs.update;

import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.Rcp;
import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.Field;
import fr.medicamentvet.gui.simple.Label;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 * The goal of the class is to show a dialog and permits user to edit most of the data of the Rcp object.
 */
public class DialogRcp extends Dialog {

    private static JFrame mainFrame;
    private final DialogRcpImage dialogRcpImage;
    private final Button buttonNext;

    private final JPanel gridBag;
    private final Label labelDateValidation;
    private final JSpinner spinnerDateValidation;

    private final Label labelLienRcp;
    private final Field fieldLienRcp;

    public DialogRcp(JFrame parent, DialogModeleDestineVente dialogModeleDestineVente, String title, boolean modal, boolean resizable, int width, int height, TabbedPane tabbedPane) {
        super(parent, title, modal, resizable, width, height);

        mainFrame = parent;

        dialogRcpImage = new DialogRcpImage(parent, this, Static.DIALOG_TITLE_RCP_IMAGE, true, false, Static.DIALOG_WIDTH_RCP_IMAGE, Static.DIALOG_HEIGHT_RCP_IMAGE, tabbedPane);

        labelDateValidation = new Label(Static.LABEL_DATE_VALIDATION_TEXT, Static.LABEL_DATE_VALIDATION_TEXT, Static.FONT_SIZE_ELEVEN, Static.LABEL_WIDTH_DATE_VALIDATION_LIEN_RCP, Static.LABEL_HEIGHT);

        Calendar calendar = Calendar.getInstance();
        Date nowDate = calendar.getTime();
        calendar.add(Calendar.YEAR, Static.DATE_YEAR_QUANTITY_MAXIMUM);
        Date endDate = calendar.getTime();
        calendar.set(Static.DATE_BEGIN_YEAR, Calendar.JANUARY, Static.DATE_BEGIN_DAY_ZERO);
        Date startDate = calendar.getTime();
        int steps = Calendar.DAY_OF_MONTH;

        SpinnerModel modelDateValidation = new SpinnerDateModel(nowDate, startDate, endDate, steps);
        spinnerDateValidation = new JSpinner(modelDateValidation);
        JComponent editorDateValidation = new JSpinner.DateEditor(spinnerDateValidation, Static.SPINNER_DATE_FORMAT_PATTERN);
        spinnerDateValidation.setEditor(editorDateValidation);

        labelLienRcp = new Label(Static.LABEL_LIEN_RCP, Static.LABEL_LIEN_RCP, Static.FONT_SIZE_ELEVEN, Static.LABEL_WIDTH_DATE_VALIDATION_LIEN_RCP, Static.LABEL_HEIGHT);
        fieldLienRcp = new Field(Static.LABEL_LIEN_RCP, Static.FIELD_WIDTH_LIEN_RCP, Static.FIELD_HEIGHT);

        gridBag = new JPanel(new GridBagLayout());

        JScrollPane scrollPane = new JScrollPane(gridBag, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        buttonNext = getButtonValidate();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT, Static.BUTTON_RETOUR_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, Static.LAYOUT_GAP_TEN, Static.LAYOUT_GAP_ZERO));
        panelBottom.add(buttonReturn);
        panelBottom.add(buttonNext);

        Utils.addComponent(this, scrollPane, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonNext.addActionListener(actionEvent -> buttonNextAction());

        buttonReturn.addActionListener(actionEvent -> {

            Medicament medicament = getMedicament();
            Rcp rcp = medicament.getRcp();

            Date date = (Date) spinnerDateValidation.getValue();
            LocalDate dateValidation = Utils.dateToLocalDate(date);

            String lienRcp = fieldLienRcp.getText().trim();

            List<String> contenuList = getContenuList();

            medicament.setRcp(new Rcp(rcp.getImageURLList(), dateValidation, lienRcp, rcp.getTitreList(), contenuList));

            dialogModeleDestineVente.init(medicament);
            dialogModeleDestineVente.setLocationRelativeTo(parent);
            setVisible(false);
            dialogModeleDestineVente.setVisible(true);
        });

        scrollPane.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                scrollPane.getViewport().setViewPosition(new Point(0, 0));
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
    }

    /**
     * The method constructs and initializes JPanel considering the data of Rcp object.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        gridBag.removeAll();

        Rcp rcp = medicament.getRcp();

        Utils.addComponent(gridBag, labelDateValidation, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_THREE, Static.INSET_FIVE, Static.INSET_ZERO, Static.INSET_FIVE));

        Utils.addComponent(gridBag, spinnerDateValidation, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_ZERO, Static.INSET_FIVE, Static.INSET_ZERO, Static.INSET_FIVE));

        Utils.addComponent(gridBag, labelLienRcp, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TEN, Static.INSET_FIVE, Static.INSET_ZERO, Static.INSET_FIVE));

        Utils.addComponent(gridBag, fieldLienRcp, Static.GRID_ZERO, Static.GRID_THREE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_FIVE, Static.INSET_ZERO, Static.INSET_FIVE));

        LocalDate dateValidation = rcp.getDateValidation();
        Date date = Utils.localDateToDate(dateValidation);

        spinnerDateValidation.setValue(date);

        String lienRcp = rcp.getLienRcp();
        fieldLienRcp.setText(lienRcp);
        fieldLienRcp.setCaretPosition(Static.CARET_POSITION_ZERO);

        List<String> titreList = rcp.getTitreList();
        List<String> contenuList = rcp.getContenuList();

        int size = titreList.size();

        for (int i = 0; i < size; i++) {
            createFieldsAndAddToGridBag(i, titreList.get(i), contenuList.get(i));
        }
        buttonNext.requestFocus();
    }

    /**
     * The method creates and adds JLabels and JTextArea to the gridBag component.
     *
     * @param i       Index of titreList and contenuList variables of Rcp object
     * @param titre   Title
     * @param contenu Content
     */
    private void createFieldsAndAddToGridBag(int i, String titre, String contenu) {
    }

    /**
     * The method goes over the gridBag JPanel to obtain the text from each TextArea components.
     *
     * @return List of Strings each TextArea
     */
    private List<String> getContenuList() {
        return new ArrayList<>();
    }

    /**
     * The method checks if there is an error with a particular field, then sets the Rcp object with the user inputs and displays the DialogRcpImage dialog.
     */
    private void buttonNextAction() {
    }
}
