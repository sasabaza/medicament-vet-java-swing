package fr.medicamentvet.utils;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.simple.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.print.PrintService;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.printing.PDFPageable;

/**
 * The class contains static utility methods.
 */
public final class Utils {

    private Utils() {
        super();
    }

    /**
     * The method formats a LocalDate to a String using specific formatter.
     *
     * @param date LocalDate
     * @return String
     */
    public static String localDateToStringDateFR(LocalDate date) {
        return date.format(Static.DATE_TIME_FORMATTER_FR);
    }

    /**
     * The method parses the favorite file and returns array of the ids.
     *
     * @return Array of ids
     */
    public static Integer[] readFileFavorite() {

        Integer[] arrayInteger = null;

        try (Stream<String> stringStream = Files.lines(Static.PATH_FILE_FAVORITE, StandardCharsets.UTF_8)) {
            arrayInteger = stringStream.map(Integer::valueOf).toArray(Integer[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayInteger;
    }

    /**
     * The method checks whether true or false the medicament has been added to the favorite list.
     *
     * @param id id of the Medicament object
     * @return Boolean true or false the medicament is in the favorite list
     */
    public static boolean checkFavorite(Integer id) {

        Integer[] arrayInteger = readFileFavorite();

        // Sort array to use binary search
        Arrays.sort(arrayInteger);

        int[] arrayInt = customBinarySearchInteger(arrayInteger, id);

        return arrayInt[0] != -1;
    }

    /**
     * The method loads a custom font to the PDDocument document object.
     *
     * @param document PDDocument object
     * @param fileName File name of the font
     * @return PDFont font
     */
    public static PDFont setPDFont(PDDocument document, String fileName) {
        PDFont font = null;

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            font = PDType0Font.load(document, fileInputStream, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return font;
    }

    /**
     * The method displays a JOptionPane dialog when the printing or the creation of a PDF file is not possible.
     *
     * @param parent    JFrame
     * @param className Class name
     * @param print     Boolean true if the process is to print, false if the process is to create a PDF file
     */
    public static void showWarningMessage(JFrame parent, String className, boolean print) {
        String message = null;
        String titre = null;

        if (className.equals(Static.SEARCH_RESULT_CLASS_NAME_TEXT)) {
            titre = Static.DIALOG_TITLE_RECHERCHE_PRINT;
            if (print) {
                message = Static.ERROR_MESSAGE_PRINT;
            } else {
                message = Static.ERROR_MESSAGE_PDF;
            }
        }

        if (className.equals(Static.TABLE_RESULT_CLASS_NAME_TEXT)) {
            titre = Static.DIALOG_TITLE_INFORMATION_PRINT;
            if (print) {
                message = Static.ERROR_MESSAGE_PRINT;
            } else {
                message = Static.ERROR_MESSAGE_PDF;
            }
        }

        JOptionPane.showMessageDialog(parent, message, titre, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * The method shows a custom text at the bottom of each page of the PDDocument object.
     *
     * @param document PDDocument object
     * @param font     Font of the text
     */
    public static void showFooterText(PDDocument document, PDFont font) {
        int numberPages = document.getNumberOfPages();

        for (int i = 0; i < numberPages; i++) {

            Date date = new Date();

            String footerText = Static.SIMPLE_DATE_FORMAT_FR.format(date);

            int indexPage = i + 1;

            footerText = footerText + Static.FOOTER_PAGE_TEXT + indexPage + Static.FORWARD_SLASH + numberPages;

            int footerWidth = 0;
            try {
                footerWidth = (int) (Static.FONT_SIZE_NINE * font.getStringWidth(footerText) / 1000) / 2;
            } catch (IOException e) {
                e.printStackTrace();
            }

            PDPage page = document.getPage(i);

            int tx = Static.PAGE_WIDTH_DIVIDED_BY_2 - footerWidth;

            try (PDPageContentStream contents = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false, false)) {

                showSimpleText(footerText, contents, font, Static.FONT_SIZE_NINE, Static.FONT_SIZE_TWELVE, Static.COLOR_ANNOTATION, tx, Static.Y_MARGIN);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The method performs the printing process.
     *
     * @param document  PDDocument object
     * @param parent    JFrame
     * @param className Class name
     */
    public static void printWithDialog(PDDocument document, JFrame parent, String className) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PrintService printService = printerJob.getPrintService();

        if (printService != null) {
            printerJob.setPageable(new PDFPageable(document));

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);
            SwingWorkerClass swingWorkerClass = new SwingWorkerClass(className, printerJob, document);
            swingWorkerClass.execute();
        } else {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(parent, Static.ERROR_MESSAGE_PRINT_SERVICE_NOT_FOUND, Static.JOPTIONPANE_DIALOG_TITLE_ERROR_PRINT_SERVICE_TEXT, JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * The method puts a text at a specific horizontal and vertical position to the content stream.
     *
     * @param text            String text
     * @param contentStream   Content stream
     * @param font            Font of the text
     * @param fontSize        Font size of the text
     * @param fontSizeLeading Space between lines
     * @param color           Color of the text
     * @param tx              Horizontal position
     * @param ty              Vertical position
     */
    public static void showSimpleText(String text, PDPageContentStream contentStream, PDFont font, int fontSize, int fontSizeLeading, int color, int tx, int ty) {
        try {
            contentStream.beginText();
            contentStream.newLineAtOffset(tx, ty);
            contentStream.setLeading(fontSizeLeading);
            contentStream.setNonStrokingColor(new Color(color, color, color));
            contentStream.setFont(font, fontSize);
            contentStream.showText(text);
            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method resizes an image to a maximum length and returns an ImageIcon.
     *
     * @param fileName          Name of the image file
     * @param buttonNext        Button to continue to the next dialog
     * @param buttonReturn      Button to either cancel, or return to the previous dialog
     * @param buttonAddImage    Button to add an image
     * @param buttonRemoveImage Button to remove an image
     * @param hashSetError      Set of errors
     * @return ImageIcon
     */
    public static ImageIcon imageIconResize(String fileName, Button buttonNext, Button buttonReturn, Button buttonAddImage, Button buttonRemoveImage, LinkedHashSet<String> hashSetError) {

        ImageIcon imageIcon = null;

        URL url;
        if (fileName.startsWith(Static.STARTS_WITH_HTTP)) {
            try {
                url = new URL(fileName);

                try (Socket ignored = new Socket("1.1.1.1", 443)) {

                    imageIcon = newImageIcon(url);
                } catch (UnknownHostException | NoRouteToHostException | ConnectException e) {
                    buttonNext.setEnabled(false);
                    buttonReturn.setEnabled(false);
                    buttonAddImage.setEnabled(false);

                    if (buttonRemoveImage != null) {
                        buttonRemoveImage.setEnabled(false);
                    }

                    hashSetError.add(Static.CONNECTION_FAILED_RETRY);

                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                buttonAddImage.setEnabled(true);

                if (buttonRemoveImage != null) {
                    buttonRemoveImage.setEnabled(false);
                }
                e.printStackTrace();
            }
        } else {
            String filePath = null;

            if (Static.OS_NAME.equals(Static.OS_NAME_LINUX_TEXT)) {
                filePath = Static.FILE_PATH + fileName;
            }
            if (Static.OS_NAME.equals(Static.OS_NAME_WINDOWS_10_TEXT)) {
                fileName = fileName.replace(Static.BACKSLASH_DOUBLE, Static.FORWARD_SLASH);
                filePath = Static.FILE_PATH_FORWARD_SLASH + fileName;
            }

            if (filePath != null) {
                try {
                    url = new URL(filePath);

                    imageIcon = newImageIcon(url);
                } catch (MalformedURLException e) {
                    buttonAddImage.setEnabled(true);

                    if (buttonRemoveImage != null) {
                        buttonRemoveImage.setEnabled(false);
                    }
                    e.printStackTrace();
                } catch (IOException e) {
                    buttonAddImage.setEnabled(true);

                    if (buttonRemoveImage != null) {
                        buttonRemoveImage.setEnabled(false);
                    }

                    String[] strings = Static.SEPARATOR_3.split(fileName);
                    String file = strings[strings.length - 1];

                    hashSetError.add(Static.ERROR_MESSAGE_FILE_NOT_FOUND_1 + file + Static.ERROR_MESSAGE_FILE_NOT_FOUND_2);

                    e.printStackTrace();
                }
            }
        }

        return imageIcon;
    }

    /**
     * The method transforms a Date object to LocalDate object.
     *
     * @param date Date
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {

        if (date != null) {
            Instant instant = date.toInstant();
            return instant.atZone(Static.DEFAULT_TIME_ZONE).toLocalDate();
        } else {
            return null;
        }
    }

    /**
     * The method transforms a LocalDate object to Date object.
     *
     * @param localDate LocalDate
     * @return Date
     */
    public static Date localDateToDate(LocalDate localDate) {

        Instant instant = localDate.atStartOfDay(Static.DEFAULT_TIME_ZONE).toInstant();

        return Date.from(instant);
    }

    /**
     * The method takes nomIdMap object from Controller class and returns sorted array of names of the medicaments.
     *
     * @return Array of names of the medicaments
     */
    public static String[] nomIdMapToArrayNomSorted() {

        Map<String, Integer> nomIdMap = Controller.getNomIdMap();
        List<String> list = new ArrayList<>(nomIdMap.keySet());

        // Sort according to a specific comparator
        list.sort(Static.COLLATOR);

        return list.toArray(new String[0]);
    }

    /**
     * The method adds a component with specific GridBagConstraints to the GridBag JPanel.
     *
     * @param container  GridBag JPanel
     * @param component  Component to add to the JPanel
     * @param gridX      Component horizontal position on the grid
     * @param gridY      Component vertical position on the grid
     * @param gridWidth  Width of the component in a row
     * @param gridHeight Height of the component in a column
     * @param weightx    Weight of the component horizontally in comparison with other components
     * @param weighty    Weight of the component vertically in comparison with other components
     * @param anchor     Place of the component in its display area
     * @param fill       Space to take for the component
     * @param insets     Insets: border of the component
     */
    public static void addComponent(Container container, Component component, int gridX, int gridY, int gridWidth, int gridHeight, int weightx, int weighty, int anchor, int fill, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints(gridX, gridY, gridWidth, gridHeight, weightx, weighty, anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }

    /**
     * The method may enable or disable Button component on the JPanel making sure that there is at least one row of components.
     *
     * @param panel  JPanel component
     * @param count  Number of rows
     * @param enable Boolean enable or disable button component
     */
    public static void buttonEnable(JPanel panel, int count, boolean enable) {
        if (count == 1) {
            Component[] arrayComponent = panel.getComponents();
            for (Component component : arrayComponent) {

                if (component.getClass().getSimpleName().equals(Static.BUTTON_CLASS_TEXT)) {
                    component.setEnabled(enable);
                }
            }
        }
    }

    /**
     * The purpose of the method is to show a temporary message on the status bar.
     *
     * @param message Message to display on the status bar
     */
    public static void temporaryStatusMessage(String message) {
        StatusBar.setLabelMessage(message);

        Timer timer = new Timer(Static.DELAY_15000, actionEvent -> StatusBar.setLabelMessage(Static.EMPTY_TEXT));
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * The method generates an ImageIcon from the URL of the image file.
     *
     * @param url URL of the image file
     * @return ImageIcon
     */
    private static ImageIcon newImageIcon(URL url) throws IOException {
        return new ImageIcon();
    }

    /**
     * The method searches an Integer in an array, and returns whether true or false the Integer is found and the position in the array.
     *
     * @param arrayInteger Array of ids
     * @param integer      Integer id
     * @return Integer array. If id is found the array contains the position of the element. If id is not found, first element of the array is -1 and the second element is the position where the element should be added.
     */
    private static int[] customBinarySearchInteger(Integer[] arrayInteger, Integer integer) {
        return new int[2];
    }
}