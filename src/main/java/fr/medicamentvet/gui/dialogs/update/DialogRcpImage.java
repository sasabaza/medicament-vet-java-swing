package fr.medicamentvet.gui.dialogs.update;

import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.Rcp;
import fr.medicamentvet.gui.FileChooser;
import fr.medicamentvet.gui.TabbedPane;
import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.ScrollPane;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

/**
 * The class is a dialog that allows user to delete or add images from/to Rcp object.
 */
public class DialogRcpImage extends Dialog {

    private final Button buttonUpdate;
    private final Button buttonReturn;
    private final Button buttonAddImage;
    private final TabbedPane tabbedPane;

    private final JPanel flowLayout;
    private int id;

    public DialogRcpImage(JFrame parent, DialogRcp dialogRcp, String title, boolean modal, boolean resizable, int width, int height, TabbedPane tabbedPane) {
        super(parent, title, modal, resizable, width, height);

        this.tabbedPane = tabbedPane;

        buttonAddImage = new Button(Static.BUTTON_AJOUTER_TEXT, Static.BUTTON_PLUS_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        flowLayout = new JPanel(new FlowLayout(FlowLayout.LEFT));

        ScrollPane scrollPane = new ScrollPane(flowLayout, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED, Static.SCROLLPANE_WIDTH_DIALOG_RCP_IMAGE, Static.SCROLLPANE_HEIGHT_DIALOG_RCP_IMAGE);

        buttonUpdate = getButtonValidate();
        buttonUpdate.setText(Static.BUTTON_METTRE_A_JOUR_TEXT);
        buttonUpdate.setPreferredSize(new Dimension(Static.BUTTON_UPDATE_WIDTH, Static.BUTTON_FIELD_HEIGHT));
        buttonUpdate.setMinimumSize(new Dimension(Static.BUTTON_UPDATE_WIDTH, Static.BUTTON_FIELD_HEIGHT));
        buttonUpdate.setSize(new Dimension(Static.BUTTON_UPDATE_WIDTH, Static.BUTTON_FIELD_HEIGHT));

        buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT, Static.BUTTON_RETOUR_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, Static.LAYOUT_GAP_TEN, Static.LAYOUT_GAP_ZERO));
        panelBottom.add(buttonReturn);
        panelBottom.add(buttonUpdate);

        Utils.addComponent(this, buttonAddImage, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ZERO, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));
        Utils.addComponent(this, Static.LABEL_IMAGE_RCP_IMAGE, Static.GRID_ONE, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, scrollPane, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelBottom, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_THREE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ZERO, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_FIVE, Static.INSET_FIFTEEN, Static.INSET_FIFTEEN, Static.INSET_FIVE));

        pack();

        buttonUpdate.addActionListener(actionEvent -> buttonUpdateAction());

        buttonReturn.addActionListener(actionEvent -> {

            Medicament medicament = getMedicament();
            Rcp rcp = medicament.getRcp();

            medicament.setRcp(new Rcp(getImageURLList(), rcp.getDateValidation(), rcp.getLienRcp(), rcp.getTitreList(), rcp.getContenuList()));

            dialogRcp.init(medicament);
            dialogRcp.setLocationRelativeTo(parent);
            setVisible(false);
            dialogRcp.setVisible(true);
        });

        buttonAddImage.addActionListener(actionEvent -> {

            FileChooser fileChooser = new FileChooser();
            String selectedFileName = fileChooser.addImage(this);

            if (selectedFileName != null) {

                buttonReturn.setEnabled(true);
                buttonUpdate.setEnabled(true);

                LinkedHashSet<String> hashSetError = new LinkedHashSet<>();

                createImagesAndAddToFlowLayout(id, selectedFileName, hashSetError);
                flowLayout.updateUI();

                if (hashSetError.size() > 0) {
                    String errorMessage = String.join(Static.PATTERN_NEWLINE, hashSetError);
                    JOptionPane.showMessageDialog(this, errorMessage, Static.DIALOG_TITLE_ERREUR, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Given the Medicament object parameter, the method shows a list of images.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        flowLayout.removeAll();
        id = 0;

        Rcp rcp = medicament.getRcp();
        List<String> imageList = rcp.getImageURLList();

        buttonReturn.setEnabled(true);
        buttonUpdate.setEnabled(true);
        buttonAddImage.setEnabled(true);

        if (imageList != null) {

            LinkedHashSet<String> hashSetError = new LinkedHashSet<>();

            for (int i = 0; i < imageList.size(); i++) {
                createImagesAndAddToFlowLayout(i, imageList.get(i), hashSetError);
            }

            if (hashSetError.size() > 0) {
                String errorMessage = String.join(Static.PATTERN_NEWLINE, hashSetError);
                JOptionPane.showMessageDialog(this, errorMessage, Static.DIALOG_TITLE_ERREUR, JOptionPane.ERROR_MESSAGE);
            }
        }
        buttonUpdate.requestFocus();
    }

    /**
     * The method adds images to a JPanel flowLayout and each image is attached with a button to remove entire panel (gridBag).
     *
     * @param i            Number of the image
     * @param fileName     File name of the image
     * @param hashSetError Set of errors
     */
    private void createImagesAndAddToFlowLayout(int i, String fileName, LinkedHashSet<String> hashSetError) {
    }

    /**
     * The method looks for the images added to the flowLayout component and adds the path to a list of Strings.
     *
     * @return List of paths
     */
    private List<String> getImageURLList() {
        return new ArrayList<>();
    }

    /**
     * The method executes in a background thread the update of the Medicament object.
     */
    private void buttonUpdateAction() {
    }
}
