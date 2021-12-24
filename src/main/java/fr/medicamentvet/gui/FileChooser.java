package fr.medicamentvet.gui;

import fr.medicamentvet.gui.dialogs.Dialog;
import fr.medicamentvet.utils.Static;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * The purpose of this class is to show a file chooser dialog to either save a PDF file or choose an image file.
 */
public class FileChooser extends JFileChooser {

    /**
     * The method saves a PDF file using PDFBox Apache plugin.
     *
     * @param document PDDocument object
     * @param parent   Parent JFrame
     */
    public void savePDF(PDDocument document, JFrame parent) {
        setCurrentDirectory(new File(Static.USER_DIRECTORY));
        setAcceptAllFileFilterUsed(false);
        setDialogTitle(Static.DIALOG_TITLE_PDF_TEXT);

        // Create empty JPopupMenu so that it displays nothing
        setComponentPopupMenu(new JPopupMenu());

        FileFilter pdfFilter = new FileNameExtensionFilter(Static.PDF_EXTENSION_DESCRIPTION_TEXT, Static.PDF_EXTENSION_TEXT);

        addChoosableFileFilter(pdfFilter);

        int state = showSaveDialog(parent);

        if (state == JFileChooser.APPROVE_OPTION) {

            String selectedFileName = getSelectedFile().getName();

            if (!Static.PATTERN_PDF.matcher(selectedFileName).matches()) {
                JOptionPane.showMessageDialog(parent, Static.ERROR_MESSAGE_INCORRECT_FILE_TEXT, Static.JOPTIONPANE_DIALOG_TITLE_INCORRECT_FILE_TEXT, JOptionPane.WARNING_MESSAGE);
            } else {

                if (!selectedFileName.toLowerCase().endsWith(Static.POINT_TEXT + Static.PDF_EXTENSION_TEXT)) {
                    selectedFileName = selectedFileName + Static.POINT_TEXT + Static.PDF_EXTENSION_TEXT;
                } else {
                    selectedFileName = selectedFileName.substring(0, selectedFileName.length() - 4) + Static.POINT_TEXT + Static.PDF_EXTENSION_TEXT;
                }

                String path = String.valueOf(getCurrentDirectory());

                if (Static.OS_NAME.equals(Static.OS_NAME_LINUX_TEXT)) {
                    path = path + Static.FORWARD_SLASH + selectedFileName;
                }
                if (Static.OS_NAME.equals(Static.OS_NAME_WINDOWS_10_TEXT)) {
                    path = path + Static.BACKSLASH_DOUBLE + selectedFileName;
                }

                try {
                    document.save(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The aim of this method is to allow user to choose an image from user's directory.
     *
     * @param dialog Parent dialog
     * @return String file name
     */
    public String addImage(Dialog dialog) {

        setCurrentDirectory(new File(Static.USER_DIRECTORY));
        setAcceptAllFileFilterUsed(false);

        // Create empty JPopupMenu so that it displays nothing
        setComponentPopupMenu(new JPopupMenu());
        setDialogTitle(Static.DIALOG_TITLE_IMAGE_TEXT);

        FileFilter jpgFilter = new FileNameExtensionFilter(Static.JPG_EXTENSION_DESCRIPTION_TEXT, Static.JPG_EXTENSION_TEXT);
        FileFilter jpegFilter = new FileNameExtensionFilter(Static.JPEG_EXTENSION_DESCRIPTION_TEXT, Static.JPEG_EXTENSION_TEXT);
        FileFilter pngFilter = new FileNameExtensionFilter(Static.PNG_EXTENSION_DESCRIPTION_TEXT, Static.PNG_EXTENSION_TEXT);

        addChoosableFileFilter(jpgFilter);
        addChoosableFileFilter(jpegFilter);
        addChoosableFileFilter(pngFilter);

        int state = showDialog(dialog, Static.BUTTON_SELECTIONNER_TEXT);

        if (state == JFileChooser.APPROVE_OPTION) {
            String name = getSelectedFile().getName();

            if (!Static.PATTERN_IMAGE.matcher(name).matches()) {
                JOptionPane.showMessageDialog(dialog, Static.ERROR_MESSAGE_INCORRECT_FILE_TEXT, Static.JOPTIONPANE_DIALOG_TITLE_INCORRECT_FILE_TEXT, JOptionPane.WARNING_MESSAGE);

                return null;
            } else {
                if (!name.toLowerCase().endsWith(Static.POINT_TEXT + Static.JPG_EXTENSION_TEXT) && !name.toLowerCase().endsWith(Static.POINT_TEXT + Static.JPEG_EXTENSION_TEXT) && !name.toLowerCase().endsWith(Static.POINT_TEXT + Static.PNG_EXTENSION_TEXT)) {
                    JOptionPane.showMessageDialog(dialog, Static.ERROR_MESSAGE_INCORRECT_EXTENSION_TEXT, Static.JOPTIONPANE_DIALOG_TITLE_INCORRECT_EXTENSION_TEXT, JOptionPane.WARNING_MESSAGE);

                    return null;
                } else {
                    return getSelectedFile().toString();
                }
            }
        } else {
            return null;
        }
    }
}
