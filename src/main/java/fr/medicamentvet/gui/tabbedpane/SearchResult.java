package fr.medicamentvet.gui.tabbedpane;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.MedicamentSearch;
import fr.medicamentvet.gui.FileChooser;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.EditorPane;
import fr.medicamentvet.gui.simple.ScrollPane;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.SwingWorkerClass;
import fr.medicamentvet.utils.Utils;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SingleSelectionModel;
import javax.swing.event.HyperlinkEvent;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * The class shows the names of the medicaments after user completes the advanced search form.
 */
public class SearchResult extends JPanel {

    private final JFrame mainFrame;

    private static EditorPane editorPane;
    private static String[] arrayFormInput;

    public SearchResult(JFrame parent, LayoutManager layout, FieldPanel fieldPanel, SingleSelectionModel selectionModel) {
        super(layout);

        setBorder(BorderFactory.createEmptyBorder(0, Static.BORDER_WIDTH_TEN, 10, Static.BORDER_WIDTH_TEN));

        this.mainFrame = parent;

        editorPane = new EditorPane(Static.CONTENT_TYPE_EDITOR, Static.BORDER_WIDTH_ZERO, false, Static.CARET_POSITION_ZERO);

        ScrollPane scrollPane = new ScrollPane(editorPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        PopupMenu popupMenu = new PopupMenu();

        editorPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });

        editorPane.addHyperlinkListener(hyperlinkEvent -> {

            if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                int id = Integer.parseInt(hyperlinkEvent.getDescription());

                Map<Integer, String> idNomMap = Controller.getIdNomMap();
                String nom = idNomMap.get(id);

                selectionModel.setSelectedIndex(Static.DONNEES_MEDICAMENT_TAB_INDEX);
                fieldPanel.setFieldTextByNom(nom);
            }
        });
    }

    /**
     * The method either performs a printing or generates a PDF file of the search results.
     *
     * @param print Boolean true to print a document, false to create a PDF file
     */
    public void generatePDFOrPrint(boolean print) {

        List<String> list = Controller.getSearchNomList();

        if (list != null) {

            List<String> medicamentNameList = changeList(list);
            String headerText = Static.NOMBRE_RESULTATS + list.size();

            PDDocument document = null;

            try {
                document = new PDDocument();

                PDFont font = Utils.setPDFont(document, Static.FONT_PRINT);

                int headerWidth = (int) (Static.FONT_SIZE_NINE * font.getStringWidth(headerText) / 1000) + Static.X_MARGIN;

                int lineIndexText = 0;

                while (medicamentNameList.size() > 0) {

                    PDPage page = new PDPage();
                    document.addPage(page);

                    try (PDPageContentStream contents = new PDPageContentStream(document, page)) {

                        int headerHeight = 0;
                        int lineNumberInAddition = 1;

                        int formInputLineNumber = 0;

                        if (document.getNumberOfPages() == 1) {

                            int tx = Static.PAGE_WIDTH - headerWidth;
                            int ty = Static.PAGE_HEIGHT - Static.Y_MARGIN;

                            Utils.showSimpleText(headerText, contents, font, Static.FONT_SIZE_NINE, Static.FONT_SIZE_TWELVE, Static.COLOR_ANNOTATION, tx, ty);

                            headerHeight = Static.FONT_SIZE_NINE * 2;

                            ty = Static.PAGE_HEIGHT - Static.Y_MARGIN - headerHeight;

                            int lineNumberMaximum = ((Static.PAGE_HEIGHT - Static.Y_MARGIN) / (Static.FONT_SIZE_TWELVE + 2));

                            List<String> formInputList = new ArrayList<>(Arrays.asList(arrayFormInput));

                            formInputLineNumber = showResultTopText(formInputList, lineNumberMaximum, contents, font, ty);

                            lineNumberInAddition = formInputLineNumber * (-1);
                        }

                        int ty = Static.PAGE_HEIGHT - Static.Y_MARGIN - headerHeight - (formInputLineNumber + 1) * Static.FONT_SIZE_TWELVE;

                        int lineNumberMaximumPage = ((Static.PAGE_HEIGHT - Static.Y_MARGIN) / (Static.FONT_SIZE_FOURTEEN + 1)) + lineNumberInAddition;

                        int[] index = showResultText(medicamentNameList, lineNumberMaximumPage, contents, font, lineIndexText, ty);

                        lineIndexText = index[0];
                        int arrayIndex = index[1];

                        medicamentNameList.subList(0, arrayIndex).clear();
                    }
                }

                Utils.showFooterText(document, font);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (print) {
                String aClassName = Static.SEARCH_RESULT_CLASS_NAME_TEXT + Static.PRINTER_TEXT;
                Utils.printWithDialog(document, mainFrame, aClassName);
            } else {
                FileChooser fileChooser = new FileChooser();
                fileChooser.savePDF(document, mainFrame);
            }

        } else {
            Utils.showWarningMessage(mainFrame, Static.SEARCH_RESULT_CLASS_NAME_TEXT, print);
        }
    }

    /**
     * Based on the MedicamentSearch object parameter, the method achieves a background task to search the list of names of the medicaments.
     *
     * @param buttonSearch     Button search component
     * @param medicamentSearch MedicamentSearch object
     */
    public void setEditorPaneContent(Button buttonSearch, MedicamentSearch medicamentSearch) {
        editorPane.setText(Static.RECHERCHE_EN_COURS);

        List<String> formInputs = formInput(medicamentSearch);
        arrayFormInput = formInputs.toArray(new String[0]);

        StatusBar.setLabelMessage(Static.EMPTY_TEXT);
        SwingWorkerClass swingWorkerClass = new SwingWorkerClass(Static.SEARCH_RESULT_CLASS_NAME_TEXT, buttonSearch, medicamentSearch, editorPane);
        swingWorkerClass.execute();
    }

    public String getEditorPaneSelectedText() {
        return editorPane.getSelectedText();
    }

    /**
     * The method generates a list of Strings based on MedicamentSearch parameter used in the generated PDF and the printed documents.
     *
     * @param medicamentSearch MedicamentSearch object
     * @return List of Strings
     */
    private List<String> formInput(MedicamentSearch medicamentSearch) {
        return new ArrayList<>();
    }

    /**
     * The method returns a rearranged list of Strings.
     *
     * @param list List of Strings
     * @return List of Strings
     */
    private List<String> changeList(List<String> list) {
        return new ArrayList<>();
    }

    /**
     * The method adds text to the content stream for the first page: assuming that the content fits the first the page. The text is the parameters of the search form.
     *
     * @param list                  List of Strings
     * @param lineNumberMaximumPage Maximum number of lines on a page
     * @param contentStream         Content stream
     * @param font                  Font used for the page
     * @param ty                    Vertical position on the page
     * @return Integer number of lines
     */
    private int showResultTopText(List<String> list, int lineNumberMaximumPage, PDPageContentStream contentStream, PDFont font, int ty) {
        return 0;
    }

    /**
     * The purpose of the method is to add the results to content stream.
     *
     * @param list                  List of Strings
     * @param lineNumberMaximumPage Maximum number of lines on a page
     * @param contentStream         Content stream
     * @param font                  Font used for the page
     * @param lineIndexText         The line number for the String element of the list
     * @param ty                    Vertical position on the page
     * @return Integer array: lineIndexText and current index of the list
     */
    private int[] showResultText(List<String> list, int lineNumberMaximumPage, PDPageContentStream contentStream, PDFont font, int lineIndexText, int ty) {
        return new int[2];
    }
}
