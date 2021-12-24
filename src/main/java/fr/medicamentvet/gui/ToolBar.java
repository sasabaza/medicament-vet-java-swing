package fr.medicamentvet.gui;

import fr.medicamentvet.utils.Static;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JToolBar;

/**
 * The class represents a toolbar component with buttons that perform an action.
 */
public class ToolBar extends JToolBar {

    public ToolBar() {
        setFloatable(false);
        setFocusable(true);
        setBorder(BorderFactory.createMatteBorder(0, Static.MATTEBORDER_WIDTH_ZERO, 1, Static.MATTEBORDER_WIDTH_ZERO, Static.COLOR_BORDER));

        add(TabbedPane.actionPrint);
        add(TabbedPane.actionCreatePDF);
        add(TabbedPane.actionUpdateDataInput);
        add(TabbedPane.actionViewFavorite);
        add(TabbedPane.actionViewHistory);
        add(TabbedPane.actionDeleteMedicament);

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                requestFocus();
            }
        });
    }
}
