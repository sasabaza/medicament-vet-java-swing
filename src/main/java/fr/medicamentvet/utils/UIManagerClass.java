package fr.medicamentvet.utils;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * This class helps us to change the look and feel of the program, in particular the FileChooser component.
 */
public class UIManagerClass extends NimbusLookAndFeel {

    private static final String OPTION_PANE_CANCEL_BUTTON = "OptionPane.cancelButtonText";
    private static final String CANCEL_BUTTON_TEXT = "Annuler";

    private static final String FILECHOOSER_RENAME_ERROR_TITLE_TEXT = "FileChooser.renameErrorTitleText";
    private static final String FILECHOOSER_RENAME_ERROR_TEXT = "FileChooser.renameErrorText";
    private static final String FILECHOOSER_RENAME_ERROR_FILE_EXISTS_TEXT = "FileChooser.renameErrorText";
    private static final String RENAME_ERROR_TEXT = "Erreur de nommage";
    private static final String RENAME_ERROR_FILE_EXISTS_TEXT = "Dossier ou fichier déjà existant";

    private static final String FILECHOOSER_LOOK_IN_LABEL_TEXT = "FileChooser.lookInLabelText";
    private static final String FILECHOOSER_FILE_NAME_LABEL_TEXT = "FileChooser.fileNameLabelText";
    private static final String FILECHOOSER_FILES_OF_TYPE_LABEL_TEXT = "FileChooser.filesOfTypeLabelText";
    private static final String LOOK_IN_LABEL_TEXT = "Voir dans";
    private static final String FILE_NAME_LABEL_TEXT = "Nom de fichier";
    private static final String FILES_OF_TYPE_LABEL_TEXT = "Type de fichiers";

    private static final String FILECHOOSER_UP_FOLDER_TOOLTIP_TEXT = "FileChooser.upFolderToolTipText";
    private static final String FILECHOOSER_NEW_FOLDER_TOOLTIP_TEXT = "FileChooser.newFolderToolTipText";
    private static final String FILECHOOSER_HOME_FOLDER_TOOLTIP_TEXT = "FileChooser.homeFolderToolTipText";
    private static final String FILECHOOSER_LIST_VIEW_BUTTON_TOOLTIP_TEXT = "FileChooser.listViewButtonToolTipText";
    private static final String FILECHOOSER_DETAILS_VIEW_BUTTON_TOOLTIP_TEXT = "FileChooser.detailsViewButtonToolTipText";
    private static final String UP_FOLDER_TOOLTIP_TEXT = "Monter d'un niveau";
    private static final String NEW_FOLDER_TEXT = "Nouveau dossier";
    private static final String HOME_FOLDER_TOOLTIP_TEXT = "Accueil";
    private static final String LIST_VIEW_BUTTON_TOOLTIP_TEXT = "Liste";
    private static final String DETAILS_VIEW_BUTTON_TOOLTIP_TEXT = "Détails";

    private static final String FILECHOOSER_VIEW_MENU_BUTTON_TOOLTIP_TEXT = "FileChooser.viewMenuButtonToolTipText";
    private static final String VIEW_MENU_BUTTON_TOOLTIP_TEXT = "Voir menu";

    private static final String FILECHOOSER_SAVE_BUTTON_TEXT = "FileChooser.saveButtonText";
    private static final String FILECHOOSER_OPEN_BUTTON_TEXT = "FileChooser.openButtonText";
    private static final String FILECHOOSER_CANCEL_BUTTON_TEXT = "FileChooser.cancelButtonText";
    private static final String FILECHOOSER_UPDATE_BUTTON_TEXT = "FileChooser.updateButtonText";
    private static final String FILECHOOSER_HELP_BUTTON_TEXT = "FileChooser.helpButtonText";
    private static final String SAVE_BUTTON_TEXT = "Enregistrer";
    private static final String OPEN_BUTTON_TEXT = "Ouvrir";
    private static final String UPDATE_BUTTON_TEXT = "Mettre à jour";
    private static final String HELP_BUTTON_TEXT = "Aide";

    private static final String FILECHOOSER_WIN32_NEW_FOLDER_TEXT = "FileChooser.win32.newFolder";

    private static final String FILECHOOSER_FILE_NAME_HEADER_TEXT = "FileChooser.fileNameHeaderText";
    private static final String FILECHOOSER_FILE_SIZE_HEADER_TEXT = "FileChooser.fileSizeHeaderText";
    private static final String FILECHOOSER_FILE_DATE_HEADER_TEXT = "FileChooser.fileDateHeaderText";
    private static final String FILE_NAME_HEADER_TEXT = "Nom";
    private static final String FILE_SIZE_HEADER_TEXT = "Taille";
    private static final String FILE_DATE_HEADER_TEXT = "Date de modification";

    private static final String FILECHOOSER_SAVE_BUTTON_TOOLTIP_TEXT = "FileChooser.saveButtonToolTipText";
    private static final String FILECHOOSER_OPEN_BUTTON_TOOLTIP_TEXT = "FileChooser.openButtonToolTipText";
    private static final String FILECHOOSER_CANCEL_BUTTON_TOOLTIP_TEXT = "FileChooser.cancelButtonToolTipText";
    private static final String FILECHOOSER_UPDATE_BUTTON_TOOLTIP_TEXT = "FileChooser.updateButtonToolTipText";
    private static final String SAVE_BUTTON_TOOLTIP_TEXT = "Enregistrer le fichier sélectionné";
    private static final String OPEN_BUTTON_TOOLTIP_TEXT = "Ouvrir le fichier sélectionné";

    private static final String FILECHOOSER_HELP_BUTTON_TOOLTIP_TEXT = "FileChooser.helpButtonToolTipText";
    private static final String FILECHOOSER_DIRECTORY_OPEN_BUTTON_TOOLTIP_TEXT = "FileChooser.directoryOpenButtonToolTipText";
    private static final String DIRECTORY_OPEN_BUTTON_TOOLTIP_TEXT = "Ouvrir dossier";

    public UIManagerClass() {

        javax.swing.UIManager.put(OPTION_PANE_CANCEL_BUTTON, CANCEL_BUTTON_TEXT);

        javax.swing.UIManager.put(FILECHOOSER_RENAME_ERROR_TITLE_TEXT, RENAME_ERROR_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_RENAME_ERROR_TEXT, RENAME_ERROR_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_RENAME_ERROR_FILE_EXISTS_TEXT, RENAME_ERROR_FILE_EXISTS_TEXT);

        javax.swing.UIManager.put(FILECHOOSER_LOOK_IN_LABEL_TEXT, LOOK_IN_LABEL_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_FILE_NAME_LABEL_TEXT, FILE_NAME_LABEL_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_FILES_OF_TYPE_LABEL_TEXT, FILES_OF_TYPE_LABEL_TEXT);

        javax.swing.UIManager.put(FILECHOOSER_UP_FOLDER_TOOLTIP_TEXT, UP_FOLDER_TOOLTIP_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_NEW_FOLDER_TOOLTIP_TEXT, NEW_FOLDER_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_HOME_FOLDER_TOOLTIP_TEXT, HOME_FOLDER_TOOLTIP_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_LIST_VIEW_BUTTON_TOOLTIP_TEXT, LIST_VIEW_BUTTON_TOOLTIP_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_DETAILS_VIEW_BUTTON_TOOLTIP_TEXT, DETAILS_VIEW_BUTTON_TOOLTIP_TEXT);

        javax.swing.UIManager.put(FILECHOOSER_VIEW_MENU_BUTTON_TOOLTIP_TEXT, VIEW_MENU_BUTTON_TOOLTIP_TEXT);

        javax.swing.UIManager.put(FILECHOOSER_SAVE_BUTTON_TEXT, SAVE_BUTTON_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_OPEN_BUTTON_TEXT, OPEN_BUTTON_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_CANCEL_BUTTON_TEXT, CANCEL_BUTTON_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_UPDATE_BUTTON_TEXT, UPDATE_BUTTON_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_HELP_BUTTON_TEXT, HELP_BUTTON_TEXT);

        javax.swing.UIManager.put(FILECHOOSER_WIN32_NEW_FOLDER_TEXT, NEW_FOLDER_TEXT);

        javax.swing.UIManager.put(FILECHOOSER_FILE_NAME_HEADER_TEXT, FILE_NAME_HEADER_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_FILE_SIZE_HEADER_TEXT, FILE_SIZE_HEADER_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_FILE_DATE_HEADER_TEXT, FILE_DATE_HEADER_TEXT);

        javax.swing.UIManager.put(FILECHOOSER_SAVE_BUTTON_TOOLTIP_TEXT, SAVE_BUTTON_TOOLTIP_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_OPEN_BUTTON_TOOLTIP_TEXT, OPEN_BUTTON_TOOLTIP_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_CANCEL_BUTTON_TOOLTIP_TEXT, CANCEL_BUTTON_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_UPDATE_BUTTON_TOOLTIP_TEXT, UPDATE_BUTTON_TEXT);

        javax.swing.UIManager.put(FILECHOOSER_HELP_BUTTON_TOOLTIP_TEXT, HELP_BUTTON_TEXT);
        javax.swing.UIManager.put(FILECHOOSER_DIRECTORY_OPEN_BUTTON_TOOLTIP_TEXT, DIRECTORY_OPEN_BUTTON_TOOLTIP_TEXT);
    }
}
