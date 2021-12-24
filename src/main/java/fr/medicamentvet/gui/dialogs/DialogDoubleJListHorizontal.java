package fr.medicamentvet.gui.dialogs;

import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.Label;
import fr.medicamentvet.gui.simple.ScrollPane;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

/**
 * The class puts the JLists in a separate row so that they are aligned horizontally.
 */
public class DialogDoubleJListHorizontal extends DialogDoubleJList {

    public DialogDoubleJListHorizontal(JFrame parent, String title, boolean modal, boolean resizable, int width, int height, int maxCount) {
        super(parent, title, modal, resizable, width, height, maxCount);

        Label labelChoice = getLabelChoice();
        Label labelSelection = getLabelSelection();

        JList<String> jList = getjList();
        jList.setVisibleRowCount(Static.JLIST_ROW_COUNT_FOUR);
        JList<String> jListSelection = getjListSelection();
        jListSelection.setVisibleRowCount(Static.JLIST_ROW_COUNT_FOUR);

        ScrollPane scrollPane = new ScrollPane(jList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, Static.SCROLLPANE_WIDTH_HORIZONTAL, Static.SCROLLPANE_HEIGHT_HORIZONTAL);
        ScrollPane scrollPaneSelection = new ScrollPane(jListSelection, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER, Static.SCROLLPANE_WIDTH_HORIZONTAL, Static.SCROLLPANE_HEIGHT_HORIZONTAL);

        Button buttonAdd = getButtonAdd();
        Button buttonRemove = getButtonRemove();

        JPanel panelMiddle = new JPanel(new FlowLayout(FlowLayout.CENTER, Static.LAYOUT_GAP_TEN, Static.LAYOUT_GAP_ZERO));
        panelMiddle.add(buttonAdd);
        panelMiddle.add(buttonRemove);

        Utils.addComponent(this, labelChoice, Static.GRID_ZERO, Static.GRID_ZERO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_TWENTY, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, scrollPane, Static.GRID_ZERO, Static.GRID_ONE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, panelMiddle, Static.GRID_ZERO, Static.GRID_TWO, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(Static.INSET_TEN, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        Utils.addComponent(this, labelSelection, Static.GRID_ZERO, Static.GRID_THREE, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_ZERO));

        Utils.addComponent(this, scrollPaneSelection, Static.GRID_ZERO, Static.GRID_FOUR, Static.GRID_WIDTH_ONE, Static.GRID_HEIGHT_ONE, Static.GRID_WEIGHT_ONE, Static.GRID_WEIGHT_ONE, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO, Static.INSET_FIFTEEN));

        pack();
    }
}
