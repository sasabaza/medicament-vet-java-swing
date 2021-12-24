package fr.medicamentvet.gui;

import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.dialogs.DialogAbout;
import fr.medicamentvet.gui.dialogs.DialogDelete;
import fr.medicamentvet.gui.dialogs.DialogFavorite;
import fr.medicamentvet.gui.dialogs.DialogHistory;
import fr.medicamentvet.gui.simple.Label;
import fr.medicamentvet.gui.tabbedpane.FieldPanel;
import fr.medicamentvet.gui.tabbedpane.FormPane;
import fr.medicamentvet.gui.tabbedpane.SearchResult;
import fr.medicamentvet.gui.tabbedpane.TableResult;
import fr.medicamentvet.utils.Static;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SingleSelectionModel;
import static java.awt.Window.getWindows;

/**
 * The purpose of this class is to create components of the TabbedPane. The class contains Swing AbstractAction classes to perform actions.
 */
public class TabbedPane extends JTabbedPane {

    private static JFrame mainFrame;
    private static DialogFavorite dialogFavorite;
    private static DialogHistory dialogHistory;
    private static DialogDelete dialogDelete;
    private static DialogAbout dialogAbout;
    private static FieldPanel fieldPanel;
    private static TableResult tableResult;
    private static FormPane formPane;
    private static SearchResult searchResult;
    private static SingleSelectionModel selectionModel;

    public TabbedPane(JFrame parent) {
        setTabPlacement(JTabbedPane.BOTTOM);
        setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

        setBorder(BorderFactory.createEmptyBorder(10, Static.BORDER_WIDTH_ZERO, 10, Static.BORDER_WIDTH_ZERO));

        mainFrame = parent;

        selectionModel = getModel();

        tableResult = new TableResult(parent, new BorderLayout(), this);
        fieldPanel = new FieldPanel(new GridLayout(), tableResult);

        dialogFavorite = new DialogFavorite(parent, Static.FAVORIS_TEXT, true, true, Static.DIALOG_FAVORITE_WIDTH, Static.DIALOG_FAVORITE_HEIGHT, fieldPanel, selectionModel);
        dialogHistory = new DialogHistory(parent, Static.HISTORIQUE_TEXT, true, true, Static.DIALOG_HISTORY_WIDTH, Static.DIALOG_HISTORY_HEIGHT, fieldPanel, selectionModel);
        dialogDelete = new DialogDelete(parent, Static.SUPPRIMER_MEDICAMENT_TEXT, false, false, Static.DIALOG_FAVORITE_WIDTH, Static.DIALOG_DELETE_HEIGHT, tableResult, fieldPanel);
        dialogAbout = new DialogAbout(parent, Static.A_PROPOS_TEXT, true, false, Static.DIALOG_ABOUT_WIDTH, Static.DIALOG_ABOUT_HEIGHT);

        JPanel fieldPanelAndTableResult = new JPanel(new BorderLayout());
        fieldPanelAndTableResult.add(fieldPanel, BorderLayout.NORTH);
        fieldPanelAndTableResult.add(tableResult, BorderLayout.CENTER);

        searchResult = new SearchResult(parent, new GridLayout(), fieldPanel, selectionModel);
        formPane = new FormPane(parent, new GridBagLayout(), searchResult);

        JPanel formAndSearchResult = new JPanel(new BorderLayout());
        formAndSearchResult.add(formPane, BorderLayout.NORTH);
        formAndSearchResult.add(searchResult, BorderLayout.CENTER);

        Label donneesMedicamentLabel = new Label(Static.DONNEES_MEDICAMENT_TEXT, Static.FONT_SIZE_TWELVE, Static.TAB_WIDTH, Static.TAB_HEIGHT, 0);
        Label rechercheAvanceeLabel = new Label(Static.RECHERCHE_AVANCEE_TEXT, Static.FONT_SIZE_TWELVE, Static.TAB_WIDTH, Static.TAB_HEIGHT, 0);

        add(fieldPanelAndTableResult);
        setTabComponentAt(Static.DONNEES_MEDICAMENT_TAB_INDEX, donneesMedicamentLabel);
        add(formAndSearchResult);
        setTabComponentAt(Static.RECHERCHE_AVANCEE_TAB_INDEX, rechercheAvanceeLabel);

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                requestFocus();
            }
        });
    }

    /**
     * The action copies text from the EditorPane to the clipboard. The action is triggered when user clicks on "Copier" Popup Menu item.
     */
    public static final AbstractAction actionCopyTextPopupMenu = new AbstractAction() {
        {
            putValue(Action.NAME, Static.COPIER_TEXT);
            assert Static.URL_COPY != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_COPY));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            String copyText;
            if (selectionModel.getSelectedIndex() == 0) {
                copyText = tableResult.getEditorPaneSelectedText();
            } else {
                copyText = searchResult.getEditorPaneSelectedText();
            }

            StringSelection data = new StringSelection(copyText);
            clipboard.setContents(data, data);
        }
    };

    /**
     * The class executes printing action from either the Menu, or the Toolbar.
     */
    public static final AbstractAction actionPrint = new AbstractAction() {
        {
            putValue(Action.NAME, Static.IMPRIMER_TEXT);
            assert Static.URL_PRINT != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_PRINT));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_I);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (selectionModel.getSelectedIndex() == 0) {
                tableResult.generatePDFOrPrint(true);
            } else {
                searchResult.generatePDFOrPrint(true);
            }
        }
    };

    /**
     * The action creates a PDF file from either the Menu, or the Toolbar.
     */
    public static final AbstractAction actionCreatePDF = new AbstractAction() {
        {
            putValue(Action.NAME, Static.CREER_PDF_TEXT);
            assert Static.URL_CREATE_PDF != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_CREATE_PDF));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (selectionModel.getSelectedIndex() == 0) {
                tableResult.generatePDFOrPrint(false);
            } else {
                searchResult.generatePDFOrPrint(false);
            }
        }
    };

    /**
     * The class executes printing action from the Popup Menu item.
     */
    public static final AbstractAction actionPrintPopupMenu = new AbstractAction() {
        {
            putValue(Action.NAME, Static.IMPRIMER_POPUP_MENU_TEXT);
            assert Static.URL_PRINT != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_PRINT));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (selectionModel.getSelectedIndex() == 0) {
                tableResult.generatePDFOrPrint(true);
            } else {
                searchResult.generatePDFOrPrint(true);
            }
        }
    };

    /**
     * The action creates a PDF file by clicking on "Créer PDF" Popup Menu item.
     */
    public static final AbstractAction actionCreatePDFPopMenu = new AbstractAction() {
        {
            putValue(Action.NAME, Static.CREER_PDF_POPUP_MENU_TEXT);
            assert Static.URL_CREATE_PDF != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_CREATE_PDF));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (selectionModel.getSelectedIndex() == 0) {
                tableResult.generatePDFOrPrint(false);
            } else {
                searchResult.generatePDFOrPrint(false);
            }
        }
    };

    /**
     * The action completes an update of the data: list of names and associated ids of medicaments, and the data of SearchForm object. The action is triggered by clicking a menu item or a button from the Toolbar.
     */
    public static final AbstractAction actionUpdateDataInput = new AbstractAction() {
        {
            putValue(Action.NAME, Static.MISE_A_JOUR_TEXT);
            assert Static.URL_UPDATE != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_UPDATE));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            updateDataInput();
        }
    };

    /**
     * The action shows Favorite dialog with it content. The action can be initiated by clicking a menu item or a button from the Toolbar.
     */
    public static final AbstractAction actionViewFavorite = new AbstractAction() {
        {
            putValue(Action.NAME, Static.FAVORIS_TEXT);
            assert Static.URL_FAVORITE != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_FAVORITE));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_V);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            setVisibleFalseDialog();
            dialogFavorite.setLocationRelativeTo(mainFrame);
            dialogFavorite.init();
            dialogFavorite.setVisible(true);
        }
    };

    /**
     * The action displays History dialog with it content. The action can be initiated by clicking a menu item or a button from the Toolbar.
     */
    public static final AbstractAction actionViewHistory = new AbstractAction() {
        {
            putValue(Action.NAME, Static.HISTORIQUE_TEXT);
            assert Static.URL_HISTORY != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_HISTORY));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_H);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            setVisibleFalseDialog();
            dialogHistory.setLocationRelativeTo(mainFrame);
            dialogHistory.init();
            dialogHistory.setVisible(true);
        }
    };

    /**
     * The action shows Delete dialog and makes sure that others dialogs are not visible. The action can be initiated by clicking a menu item or a button from the Toolbar.
     */
    public static final AbstractAction actionDeleteMedicament = new AbstractAction() {
        {
            putValue(Action.NAME, Static.SUPPRIMER_MEDICAMENT_TEXT);
            assert Static.URL_DELETE != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_DELETE));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            setVisibleFalseDialog();
            dialogDelete.setLocationRelativeTo(mainFrame);
            dialogDelete.init();
            dialogDelete.setVisible(true);
        }
    };

    /**
     * The action displays the About dialog. The action is triggered when user clicks on "À propos" Popup Menu item.
     */
    public static final AbstractAction actionViewAbout = new AbstractAction() {
        {
            putValue(Action.NAME, Static.A_PROPOS_TEXT);
            assert Static.URL_ABOUT != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_ABOUT));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            setVisibleFalseDialog();
            dialogAbout.setLocationRelativeTo(mainFrame);
            dialogAbout.setVisible(true);
        }
    };

    /**
     * The action accomplishes exit program.
     */
    public static final AbstractAction actionExitProgram = new AbstractAction() {
        {
            putValue(Action.NAME, Static.QUITTER_TEXT);
            assert Static.URL_EXIT != null;
            putValue(Action.SMALL_ICON, new ImageIcon(Static.URL_EXIT));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Window[] windows = getWindows();
            for (Window window : windows) {
                window.dispose();
            }
        }
    };

    /**
     * The method sets the data necessary for the proper functioning of the program.
     *
     * @param manual Boolean parameter: true to force the update
     */
    public void setApplicationData(boolean manual) {
        fieldPanel.setVariable(manual, dialogDelete);
        formPane.setFormPaneModel(manual);
        tableResult.setDialogUpdateVariables(manual);
    }

    /**
     * The method sets all data of the application, updates the autocomplete text field, and the data of the EditorPane.
     *
     * @param medicament Medicament object
     */
    public void updateData(Medicament medicament) {
        setApplicationData(true);
        fieldPanel.setFieldTextAndRetrieveData(medicament);
    }

    /**
     * The method performs an update of the names and ids of the medicaments and the SearchForm object.
     */
    public static void updateDataInput() {
        fieldPanel.setVariable(true, dialogDelete);
        formPane.setFormPaneModel(true);
    }

    /**
     * The method hides all dialogs that extends Dialog class.
     */
    private static void setVisibleFalseDialog() {
    }
}
