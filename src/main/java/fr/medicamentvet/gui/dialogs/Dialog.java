package fr.medicamentvet.gui.dialogs;

import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.utils.Static;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 * The purpose of this class is to set the base properties of the Dialog component.
 */
public class Dialog extends JDialog {

    private final Button buttonValidate;
    private final Button buttonCancel;
    private final JPanel panelBottom;

    private Medicament medicament;

    public Dialog(JFrame parent, String title, boolean modal, boolean resizable, int width, int height) {
        super(parent, title, modal);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        setResizable(resizable);

        buttonValidate = new Button(Static.BUTTON_VALIDER_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_WIDTH, Static.BUTTON_FIELD_HEIGHT, Static.BUTTON_VALIDER_COLOR_R, Static.BUTTON_VALIDER_COLOR_G, Static.BUTTON_VALIDER_COLOR_B);
        buttonCancel = new Button(Static.BUTTON_ANNULER_TEXT, Static.BUTTON_ANNULER_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, Static.LAYOUT_GAP_TEN, Static.LAYOUT_GAP_ZERO));

        panelBottom.add(buttonValidate);
        panelBottom.add(buttonCancel);

        pack();

        buttonCancel.addActionListener(actionEvent -> setVisible(false));
    }

    public Button getButtonValidate() {
        return buttonValidate;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public JPanel getPanelBottom() {
        return panelBottom;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    /**
     * The method sets to false setVisible method of the JDialog component when user presses Escape key.
     *
     * @return JRootPane container
     */
    @Override
    protected JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();

        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke strokeEscape = KeyStroke.getKeyStroke(Static.KEYSTROKE_ESCAPE_TEXT);
        inputMap.put(strokeEscape, Static.KEYSTROKE_ESCAPE_TEXT);
        rootPane.getActionMap().put(Static.KEYSTROKE_ESCAPE_TEXT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        });
        return rootPane;
    }
}
