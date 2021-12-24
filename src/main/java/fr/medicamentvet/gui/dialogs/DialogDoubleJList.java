package fr.medicamentvet.gui.dialogs;

import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.Label;
import fr.medicamentvet.gui.tabbedpane.Renderer;
import fr.medicamentvet.utils.Static;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 * The goal of this class is to create two JLists, two buttons and the listeners attached to the JLists and the buttons.
 */
public class DialogDoubleJList extends Dialog {

    private final JList<String> jList;
    private final JList<String> jListSelection;
    private final Label labelChoice;
    private final Label labelSelection;
    private final Button buttonAdd;
    private final Button buttonRemove;

    /**
     * Maximum number of elements added to the JList
     */
    private final int maxCount;

    private List<String> stringList;
    private List<String> stringListSelection;
    private int count;

    public DialogDoubleJList(JFrame parent, String title, boolean modal, boolean resizable, int width, int height, int maxCount) {
        super(parent, title, modal, resizable, width, height);

        this.maxCount = maxCount;

        labelChoice = new Label(Static.LABEL_CHOIX_POSSIBLES_TEXT, Static.LABEL_CHOIX_POSSIBLES_TEXT, Static.FONT_SIZE_ELEVEN, Static.LABEL_WIDTH_CHOICE_SELECTION, Static.LABEL_HEIGHT);
        labelSelection = new Label(Static.LABEL_SELECTION_TEXT, Static.LABEL_SELECTION_TEXT, Static.FONT_SIZE_ELEVEN, Static.LABEL_WIDTH_CHOICE_SELECTION, Static.LABEL_HEIGHT);

        jList = new JList<>();
        stringList = new ArrayList<>();

        Renderer renderer = new Renderer();
        jList.setCellRenderer(renderer);

        jListSelection = new JList<>();
        jListSelection.setCellRenderer(renderer);

        stringListSelection = new ArrayList<>();

        buttonAdd = new Button(Static.AJOUTER_TEXT, Static.BUTTON_ARROW_DOWN_AJOUTER_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_WIDTH, Static.BUTTON_FIELD_HEIGHT);
        buttonRemove = new Button(Static.BUTTON_SUPPRIMER_TEXT, Static.BUTTON_ARROW_UP_SUPPRIMER_TEXT, Static.FONT_SIZE_ELEVEN, Static.BUTTON_DIALOG_SUPPRIMER_WIDTH, Static.BUTTON_FIELD_HEIGHT);

        pack();

        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                if (mouseEvent.getClickCount() >= Static.CLICK_NUMBER_TWO) {
                    int index = jList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        addAction();
                    }
                }
            }
        });

        jList.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                if (mouseEvent.getClickCount() >= Static.CLICK_NUMBER_TWO) {
                    int index = jList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        addAction();
                    }
                }
            }
        });

        jListSelection.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                if (mouseEvent.getClickCount() >= Static.CLICK_NUMBER_TWO) {
                    int index = jListSelection.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        removeAction();
                    }
                }
            }
        });

        jListSelection.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                if (mouseEvent.getClickCount() >= Static.CLICK_NUMBER_TWO) {
                    int index = jListSelection.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        removeAction();
                    }
                }
            }
        });

        buttonAdd.addActionListener(actionEvent -> addAction());
        buttonRemove.addActionListener(actionEvent -> removeAction());
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<String> getStringListSelection() {
        return stringListSelection;
    }

    public void setStringListSelection(List<String> stringListSelection) {
        this.stringListSelection = stringListSelection;
    }

    public JList<String> getjList() {
        return jList;
    }

    public JList<String> getjListSelection() {
        return jListSelection;
    }

    public Label getLabelChoice() {
        return labelChoice;
    }

    public Label getLabelSelection() {
        return labelSelection;
    }

    public Button getButtonAdd() {
        return buttonAdd;
    }

    public Button getButtonRemove() {
        return buttonRemove;
    }

    /**
     * The method sets the model of the JLists.
     *
     * @param list          List of Strings
     * @param listSelection List of selected Strings
     */
    public void setModel(List<String> list, List<String> listSelection) {
        jList.setModel(model(list));
        setStringList(list);

        jListSelection.setModel(model(listSelection));
        setStringListSelection(listSelection);
    }

    private DefaultListModel<String> model(List<String> list) {
        return new DefaultListModel<>();
    }

    private void addAction() {
    }

    private void removeAction() {
    }
}
