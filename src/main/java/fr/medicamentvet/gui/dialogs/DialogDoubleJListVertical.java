package fr.medicamentvet.gui.dialogs;

import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.Label;
import fr.medicamentvet.gui.simple.ScrollPane;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

/**
 * Similar to the class DialogDoubleJListHorizontal, this time the JLists are aligned vertically.
 */
public class DialogDoubleJListVertical extends DialogDoubleJList {

    public DialogDoubleJListVertical(JFrame parent, String title, boolean modal, boolean resizable, int width, int height, int maxCount) {
        super(parent, title, modal, resizable, width, height, maxCount);

        Label labelChoice = getLabelChoice();
        Label labelSelection = getLabelSelection();

        JList<String> jList = getjList();
        jList.setVisibleRowCount(Static.JLIST_ROW_COUNT_TEN);
        JList<String> jListSelection = getjListSelection();
        jList.setVisibleRowCount(Static.JLIST_ROW_COUNT_TEN);

        ScrollPane scrollPane = new ScrollPane(jList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, Static.SCROLLPANE_WIDTH_VERTICAL, Static.SCROLLPANE_HEIGHT_VERTICAL);
        ScrollPane scrollPaneSelection = new ScrollPane(jListSelection, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, Static.SCROLLPANE_WIDTH_VERTICAL, Static.SCROLLPANE_HEIGHT_VERTICAL);

        Button buttonAdd = getButtonAdd();
        buttonAdd.setText(Static.BUTTON_GT_TEXT);

        Button buttonRemove = getButtonRemove();
        buttonRemove.setText(Static.BUTTON_LS_TEXT);

        JPanel panelMiddle = new JPanel(new GridLayout(2, 1));
        panelMiddle.add(buttonAdd);
        panelMiddle.add(buttonRemove);

        Utils.addComponent(this, labelChoice, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, labelSelection, Static.GRID_TWO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, scrollPane, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_SEVEN, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelMiddle, Static.GRID_ONE, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, scrollPaneSelection, Static.GRID_TWO, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_SEVEN, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        pack();
    }
}
