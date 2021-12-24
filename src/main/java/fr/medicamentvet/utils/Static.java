package fr.medicamentvet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.medicamentvet.gui.simple.Button;
import fr.medicamentvet.gui.simple.Label;
import fr.medicamentvet.gui.simple.ToggleButton;
import java.awt.Color;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * The class contains most of the static variables.
 */
public final class Static {

    // Application
    /******************************************************************************************************************/

    public static final String TITLE_APPLICATION = "Médicament vétérinaire";

    // Controller
    /******************************************************************************************************************/

    public static final int FONT_SIZE_TEN = 10;
    public static final int FONT_SIZE_TWELVE = 12;

    public static final String ESPECE_DESTINATION_PLURIEL_TEXT = "Espèces de destination";
    public static final String SUBSTANCE_ACTIVE_PLURIEL_TEXT = "Substances actives";
    public static final String CONDITION_DELIVRANCE_SINGULIER_TEXT = "Condition de délivrance";
    public static final String CONDITIONNEMENT_PRIMAIRE_SINGULIER_TEXT = "Conditionnement primaire";
    public static final String CODE_ATCVET_SINGULIER_TEXT = "Code ATCVET";

    public static final String IMAGE_TEXT = "Image ";

    public static final String PATTERN_SPACE = " ";
    public static final String PATTERN_NEWLINE = "\n";

    // Medicament, Rcp
    /******************************************************************************************************************/

    public static final String PATTERN_AMP = "&";
    public static final String SPACE_AMP_AMP_SPACE_TEXT = PATTERN_SPACE + PATTERN_AMP + PATTERN_AMP + PATTERN_SPACE;

    // FileChooser
    /******************************************************************************************************************/

    public static final String DIALOG_TITLE_PDF_TEXT = "Créer un fichier PDF";
    public static final String DIALOG_TITLE_IMAGE_TEXT = "Sélectionner une image";
    public static final String BUTTON_SELECTIONNER_TEXT = "Sélectionner";
    public static final String JOPTIONPANE_DIALOG_TITLE_INCORRECT_FILE_TEXT = "Nom du fichier incorrect";
    public static final String JOPTIONPANE_DIALOG_TITLE_INCORRECT_EXTENSION_TEXT = "Extension incorrecte";
    public static final String ERROR_MESSAGE_INCORRECT_FILE_TEXT = "Le nom du fichier est incorrect. Merci de saisir un nouveau nom.";
    public static final String ERROR_MESSAGE_INCORRECT_EXTENSION_TEXT = "L'extension de fichier est incorrect.";

    public static final String USER_DIRECTORY = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String POINT_TEXT = ".";
    public static final String BACKSLASH_DOUBLE = "\\";
    public static final String FORWARD_SLASH = "/";
    public static final String OS_NAME_LINUX_TEXT = "Linux";
    public static final String OS_NAME_WINDOWS_10_TEXT = "Windows 10";
    public static final String PDF_EXTENSION_DESCRIPTION_TEXT = "Document PDF (*.pdf)";
    public static final String PDF_EXTENSION_TEXT = "pdf";
    public static final String JPG_EXTENSION_DESCRIPTION_TEXT = "JPG (*.jpg)";
    public static final String JPG_EXTENSION_TEXT = "jpg";
    public static final String JPEG_EXTENSION_DESCRIPTION_TEXT = "JPEG (*.jpeg)";
    public static final String JPEG_EXTENSION_TEXT = "jpeg";
    public static final String PNG_EXTENSION_DESCRIPTION_TEXT = "PNG (*.png)";
    public static final String PNG_EXTENSION_TEXT = "png";

    private static final String REGEX_PDF = "^[A-Za-z0-9._-]+\\.[A-Za-z]{3}$";
    public static final Pattern PATTERN_PDF = Pattern.compile(REGEX_PDF);
    private static final String REGEX_IMAGE = "^[A-Za-z0-9. _-]+\\.[A-Za-z]{3,4}$";
    public static final Pattern PATTERN_IMAGE = Pattern.compile(REGEX_IMAGE);

    // MainFrame
    /******************************************************************************************************************/

    public static final String PATH_MEDICAMENT_VET = System.getProperty("user.home") + "/Medicament-Vet/";
    private static final String FILE_NAME_FAVORITE = "favorite.txt";
    public static final Path PATH_FILE_FAVORITE = Paths.get(PATH_MEDICAMENT_VET + FILE_NAME_FAVORITE);

    public static final int FRAME_WIDTH = 730;
    public static final int FRAME_HEIGHT = 680;
    public static final int DELAY_15000 = 15000;

    // StatusBar, ToolBar
    /******************************************************************************************************************/

    public static final int STATUSBAR_HEIGHT = 25;
    public static final int MATTEBORDER_WIDTH_ZERO = 0;
    private static final int BORDER_COLOR = 150;
    public static final int LABEL_COLOR_COLOR1 = 220;
    public static final int LABEL_COLOR_COLOR2 = 40;
    public static final Color COLOR_BORDER = new Color(BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);

    // TabbedPane
    /******************************************************************************************************************/

    public static final String SUPPRIMER_MEDICAMENT_TEXT = "Supprimer un médicament";
    public static final String DONNEES_MEDICAMENT_TEXT = "Données du médicament";
    public static final String RECHERCHE_AVANCEE_TEXT = "Recherche avancée";
    public static final String FAVORIS_TEXT = "Favoris";
    public static final String COPIER_TEXT = "Copier";
    public static final String IMPRIMER_POPUP_MENU_TEXT = "Imprimer";
    public static final String CREER_PDF_POPUP_MENU_TEXT = "Créer PDF";
    public static final String IMPRIMER_TEXT = "Imprimer...";
    public static final String CREER_PDF_TEXT = "Créer PDF...";
    public static final String HISTORIQUE_TEXT = "Historique";
    public static final String QUITTER_TEXT = "Quitter";
    public static final String MISE_A_JOUR_TEXT = "Mise à jour";
    public static final String A_PROPOS_TEXT = "À propos";

    private static final String IMAGE_PATH_COPY = "/icons/copy.png";
    private static final String IMAGE_PATH_PRINT = "/icons/print.png";
    private static final String IMAGE_PATH_CREATE_PDF = "/icons/pdf.png";
    private static final String IMAGE_PATH_UPDATE = "/icons/update.png";
    private static final String IMAGE_PATH_FAVORITE = "/icons/favorite.png";
    private static final String IMAGE_PATH_HISTORY = "/icons/history.png";
    private static final String IMAGE_PATH_DELETE = "/icons/delete.png";
    private static final String IMAGE_PATH_ABOUT = "/icons/about.png";
    private static final String IMAGE_PATH_EXIT = "/icons/exit.png";

    public static final URL URL_COPY = Static.class.getResource(IMAGE_PATH_COPY);
    public static final URL URL_PRINT = Static.class.getResource(IMAGE_PATH_PRINT);
    public static final URL URL_CREATE_PDF = Static.class.getResource(IMAGE_PATH_CREATE_PDF);
    public static final URL URL_UPDATE = Static.class.getResource(IMAGE_PATH_UPDATE);
    public static final URL URL_FAVORITE = Static.class.getResource(IMAGE_PATH_FAVORITE);
    public static final URL URL_HISTORY = Static.class.getResource(IMAGE_PATH_HISTORY);
    public static final URL URL_DELETE = Static.class.getResource(IMAGE_PATH_DELETE);
    public static final URL URL_ABOUT = Static.class.getResource(IMAGE_PATH_ABOUT);
    public static final URL URL_EXIT = Static.class.getResource(IMAGE_PATH_EXIT);

    public static final int DONNEES_MEDICAMENT_TAB_INDEX = 0;
    public static final int RECHERCHE_AVANCEE_TAB_INDEX = 1;
    public static final int TAB_WIDTH = 300;
    public static final int TAB_HEIGHT = 18;

    public static final int DIALOG_FAVORITE_WIDTH = 550;
    public static final int DIALOG_FAVORITE_HEIGHT = 310;
    public static final int DIALOG_HISTORY_WIDTH = 650;
    public static final int DIALOG_HISTORY_HEIGHT = 400;
    public static final int DIALOG_DELETE_HEIGHT = 160;
    public static final int DIALOG_ABOUT_WIDTH = 270;
    public static final int DIALOG_ABOUT_HEIGHT = 140;

    public static final int BORDER_WIDTH_ZERO = 0;

    // AutocompleteField
    /******************************************************************************************************************/

    public static final String DOWN_KEYSTROKE_TEXT = "DOWN";
    public static final String UP_KEYSTROKE_TEXT = "UP";
    public static final String ENTER_KEYSTROKE_TEXT = "ENTER";
    public static final String ACTION_DOWN_KEY_TEXT = "actionDownKey";
    public static final String ACTION_UP_KEY_TEXT = "actionUpKey";
    public static final String ACTION_ENTER_KEY_TEXT = "actionEnterKey";

    // AutocompleteWindow
    /******************************************************************************************************************/

    public static final int FONT_SIZE_THIRTEEN = 13;
    public static final int THICKNESS = 1;

    // Maximum number of results to show
    public static final int ROW_MAXIMUM_NUMBER = 8;
    public static final int CELL_SIZE = 22;
    public static final String FIELD_PANEL_CLASS_NAME_TEXT = "FieldPanel";

    /******************************************************************************************************************/

    public static final int BORDER_WIDTH_FIVE = 5;
    public static final String HTML_BEGINNING = "<html>";
    private static final int GRAY_COLOR = 238;
    public static final Color COLOR_BACKGROUND_GRAY = new Color(GRAY_COLOR, GRAY_COLOR, GRAY_COLOR);

    // Dialog
    /******************************************************************************************************************/

    public static final int FONT_SIZE_ELEVEN = 11;

    public static final String BUTTON_VALIDER_TEXT = "Valider";
    public static final String BUTTON_ANNULER_TEXT = "Annuler";
    public static final String KEYSTROKE_ESCAPE_TEXT = "ESCAPE";
    public static final int BUTTON_DIALOG_WIDTH = 100;
    public static final int BUTTON_FIELD_HEIGHT = 30;

    public static final int BUTTON_VALIDER_COLOR_R = 150;
    public static final int BUTTON_VALIDER_COLOR_G = 170;
    public static final int BUTTON_VALIDER_COLOR_B = 200;

    public static final int LAYOUT_GAP_TEN = 10;
    public static final int LAYOUT_GAP_ZERO = 0;

    // DialogAbout
    /******************************************************************************************************************/

    private static final String TITLE_DIALOG_ABOUT_TEXT = "Application médicament vétérinaire";
    private static final String VERSION_TEXT = "1.0";
    public static final String DASH_TEXT = "-";
    public static final String DATES_TEXT = "2020-2021" + PATTERN_SPACE + DASH_TEXT + PATTERN_SPACE;
    public static final String URL = "https://medicament-vet.ddns.net";

    public static final int GRID_ZERO = 0;
    public static final int GRID_ONE = 1;
    public static final int GRID_TWO = 2;
    public static final int GRID_HEIGHT_ONE = 1;
    public static final int GRID_WIDTH_ONE = 1;
    public static final int GRID_WEIGHT_ZERO = 0;
    public static final int GRID_WEIGHT_ONE = 1;
    public static final int GRID_WIDTH_TWO = 2;

    public static final int INSET_ZERO = 0;
    public static final int INSET_TEN = 10;
    public static final int INSET_FIFTEEN = 15;

    private static final int COLOR_ZERO = 0;
    private static final int COLOR_LINK_R = 90;
    private static final int COLOR_LINK_G = 120;
    private static final int COLOR_LINK_B = 230;
    private static final int COLOR_DATES = 140;
    public static final int FONT_SIZE_NINE = 9;

    public static final Label LABEL_APPLICATION_NAME = new Label(TITLE_DIALOG_ABOUT_TEXT, FONT_SIZE_THIRTEEN, COLOR_ZERO, COLOR_ZERO);
    public static final Label LABEL_VERSION = new Label(VERSION_TEXT, FONT_SIZE_THIRTEEN, COLOR_ZERO, COLOR_ZERO);
    public static final Label LABEL_DATES = new Label(DATES_TEXT, FONT_SIZE_NINE, COLOR_DATES, COLOR_DATES);
    public static final Label LABEL_URI = new Label(FONT_SIZE_NINE, COLOR_LINK_R, COLOR_LINK_G, COLOR_LINK_B, TITLE_DIALOG_ABOUT_TEXT);

    // DialogDelete
    /******************************************************************************************************************/

    public static final String EMPTY_TEXT = "";
    public static final String LABEL_NOM_TEXT = "Nom";
    public static final String BUTTON_SUPPRIMER_TEXT = "Supprimer";
    public static final String BUTTON_FERMER_TEXT = "Fermer";
    public static final String DIALOG_TITLE_TEXT = "Suppression du médicament";
    public static final String DIALOG_MESSAGE_TEXT = "Le médicament n'est pas enregistré dans la base de données.";

    public static final int INSET_FIVE = 5;
    public static final int INSET_TWENTY = 20;

    public static final int LABEL_WIDTH_NOM = 30;
    public static final int LABEL_HEIGHT = 14;
    public static final int FIELD_WIDTH_DIALOG_DELETE = 504;

    public static final String DIALOG_DELETE_CLASS_NAME_TEXT = "DialogDelete";
    public static final Label LABEL_NOM_DELETE = new Label(LABEL_NOM_TEXT, LABEL_NOM_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_NOM, LABEL_HEIGHT);

    // DialogDoubleJList
    /******************************************************************************************************************/

    public static final String LABEL_CHOIX_POSSIBLES_TEXT = "Choix possibles";
    public static final String LABEL_SELECTION_TEXT = "Sélection";
    public static final String AJOUTER_TEXT = "Ajouter";
    private static final String ARROW_DOWN = "▼  ";
    private static final String ARROW_UP = "▲  ";
    public static final String DIALOG_TITLE_ERREUR = "Erreur";
    public static final String BUTTON_ARROW_DOWN_AJOUTER_TEXT = ARROW_DOWN + AJOUTER_TEXT;
    public static final String BUTTON_ARROW_UP_SUPPRIMER_TEXT = ARROW_UP + BUTTON_SUPPRIMER_TEXT;

    public static final int BUTTON_DIALOG_SUPPRIMER_WIDTH = 110;
    public static final int LABEL_WIDTH_CHOICE_SELECTION = 100;
    public static final int CLICK_NUMBER_TWO = 2;

    // France locale object comparator
    public static final Collator COLLATOR = Collator.getInstance(Locale.FRANCE);

    // DialogDoubleJListHorizontal
    /******************************************************************************************************************/

    public static final int SCROLLPANE_WIDTH_HORIZONTAL = 680;
    public static final int SCROLLPANE_HEIGHT_HORIZONTAL = 102;
    public static final int JLIST_ROW_COUNT_FOUR = 4;

    public static final int GRID_THREE = 3;
    public static final int GRID_FOUR = 4;

    // DialogDoubleJListVertical
    /******************************************************************************************************************/

    public static final int SCROLLPANE_WIDTH_VERTICAL = 250;
    public static final int SCROLLPANE_HEIGHT_VERTICAL = 252;
    public static final int JLIST_ROW_COUNT_TEN = 10;
    public static final String BUTTON_GT_TEXT = ">>";
    public static final String BUTTON_LS_TEXT = "<<";

    public static final int GRID_HEIGHT_SEVEN = 7;

    // DialogFavorite
    /******************************************************************************************************************/

    public static final String BUTTON_OK_TEXT = "OK";

    public static final int CARET_POSITION_ZERO = 0;
    public static final String CONTENT_TYPE_EDITOR = "text/html";

    // DialogHistory
    /******************************************************************************************************************/

    public static final String EMPTY_BODY_HTML = "<html><body></body></html>";
    public static final String BUTTON_SUPPRIMER_HISTORIQUE_TEXT = "Supprimer l'historique";

    public static final int BUTTON_DELETE_HISTORY_WIDTH = 160;

    // DialogDate
    /******************************************************************************************************************/

    public static final String LABEL_DEBUT_TEXT = "Date de début";
    public static final String LABEL_FIN_TEXT = "Date de fin";
    public static final String SPINNER_DATE_FORMAT_PATTERN = "dd/MM/yyyy";
    public static final int DATE_YEAR_QUANTITY_MAXIMUM = 200;
    public static final int DATE_BEGIN_YEAR = 1972;
    public static final int DATE_BEGIN_DAY_ONE = 1;
    public static final int DATE_BEGIN_DAY_ZERO = 0;

    private static final int LABEL_WIDTH_START_DATE = 100;

    public static final Label LABEL_START_DATE = new Label(LABEL_DEBUT_TEXT, LABEL_DEBUT_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_START_DATE, LABEL_HEIGHT);
    public static final Label LABEL_END_DATE = new Label(LABEL_FIN_TEXT, LABEL_FIN_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_START_DATE, LABEL_HEIGHT);

    // DialogEspece
    /******************************************************************************************************************/

    public static final int GRID_EIGHT = 8;

    // DialogSubstanceActive
    /******************************************************************************************************************/

    public static final String SUBSTANCE_ACTIVE_1_TEXT = "Substance active 1";
    public static final String SUBSTANCE_ACTIVE_2_TEXT = "Substance active 2";
    public static final int FIELD_WIDTH_SUBSTANCE_ACTIVE = 200;

    private static final int LABEL_WIDTH_SUBSTANCE_ACTIVE = 120;

    public static final Label LABEL_SUBSTANCE_ACTIVE_1 = new Label(SUBSTANCE_ACTIVE_1_TEXT, SUBSTANCE_ACTIVE_1_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_SUBSTANCE_ACTIVE, LABEL_HEIGHT);
    public static final Label LABEL_SUBSTANCE_ACTIVE_2 = new Label(SUBSTANCE_ACTIVE_2_TEXT, SUBSTANCE_ACTIVE_2_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_SUBSTANCE_ACTIVE, LABEL_HEIGHT);

    // DialogComposition
    /******************************************************************************************************************/

    public static final int INSET_THREE = 3;
    public static final int GRID_WIDTH_THREE = 3;

    public static final String BUTTON_SUIVANT_TEXT = "Suivant";
    public static final String BUTTON_RETOUR_TEXT = "Retour";
    public static final String BUTTON_AJOUTER_TEXT = "Ajouter";
    public static final String BUTTON_PLUS_TEXT = "+";
    public static final String BUTTON_MINUS_TEXT = "-";
    public static final int BUTTON_WIDTH = 38;

    public static final int SCROLLPANE_WIDTH_DIALOG_COMPOSITION = 690;
    public static final int SCROLLPANE_HEIGHT_DIALOG_COMPOSITION = 150;

    private static final int LABEL_WIDTH_COMPOSITION = 150;
    private static final String LABEL_NAME_COMPOSITION_TEXT = "Substance active - Quantité - Unité";

    public static final String DIALOG_TITLE_CONDITIONNEMENT_PRIMAIRE = "Conditionnements primaires - Codes ATCVET - Mise à jour";
    public static final int DIALOG_WIDTH_CONDITIONNEMENT_PRIMAIRE = 710;
    public static final int DIALOG_HEIGHT_CONDITIONNEMENT_PRIMAIRE = 430;
    public static final int CONDITIONNEMENT_PRIMAIRE_CODE_ATC_MAXIMUM_NUMBER = 12;

    public static final Label LABEL_COMPOSITION = new Label(LABEL_NAME_COMPOSITION_TEXT, LABEL_NAME_COMPOSITION_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_COMPOSITION, LABEL_HEIGHT);

    // DialogConditionDelivrance
    /******************************************************************************************************************/

    public static final int GRID_FIVE = 5;

    public static final String DIALOG_TITLE_EXCIPIENTS_QSP = "Excipients QSP - Mise à jour";
    public static final int DIALOG_WIDTH_EXCIPIENT_QSP = 710;
    public static final int DIALOG_HEIGHT_EXCIPIENT_QSP = 390;
    public static final int EXCIPIENT_QSP_MAXIMUM_NUMBER = 5;

    // DialogConditionnementPrimaireCodeATC
    /******************************************************************************************************************/

    public static final int FIELD_WIDTH_CONDITIONNEMENT_PRIMAIRE = 595;
    public static final int FIELD_WIDTH_CODE_ATCVET = 120;

    public static final int SCROLLPANE_WIDTH_DIALOG_CONDITIONNEMENT_PRIMAIRE_CODE_ATC = 690;
    public static final int SCROLLPANE_HEIGHT_DIALOG_CONDITIONNEMENT_PRIMAIRE_CODE_ATC = 114;

    public static final String DIALOG_TITLE_MODELE_DESTINE_VENTE = "Modèles destinés à la vente - Mise à jour";
    public static final int DIALOG_WIDTH_MODELE_DESTINE_VENTE = 710;
    public static final int DIALOG_HEIGHT_MODELE_DESTINE_VENTE = 300;
    public static final int MODELE_DESTINE_VENTE_MAXIMUM_NUMBER = 100;
    public static final int LABEL_WIDTH_CONDITIONNEMENT_PRIMAIRE_CODE_ATC = 100;

    public static final Label LABEL_CODE_ATCVET = new Label(CODE_ATCVET_SINGULIER_TEXT, CODE_ATCVET_SINGULIER_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_CONDITIONNEMENT_PRIMAIRE_CODE_ATC, LABEL_HEIGHT);
    public static final Label LABEL_CONDITIONNEMENT_PRIMAIRE = new Label(CONDITIONNEMENT_PRIMAIRE_SINGULIER_TEXT, CONDITIONNEMENT_PRIMAIRE_SINGULIER_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_CONDITIONNEMENT_PRIMAIRE_CODE_ATC, LABEL_HEIGHT);

    // DialogEspeceUpdate
    /******************************************************************************************************************/

    public static final String DIALOG_TITLE_VOIES_ADMINISTRATION = "Voies d'administration - Mise à jour";
    public static final int DIALOG_WIDTH_VOIE_ADMINISTRATION = 710;
    public static final int DIALOG_HEIGHT_VOIE_ADMINISTRATION = 390;
    public static final int VOIE_ADMINISTRATION_MAXIMUM_NUMBER = 10;

    // DialogExcipientQSP
    /******************************************************************************************************************/

    public static final String DIALOG_TITLE_COMPOSITION = "Composition - Mise à jour";
    public static final int DIALOG_WIDTH_COMPOSITION = 710;
    public static final int DIALOG_HEIGHT_COMPOSITION = 300;
    public static final int COMPOSITION_MAXIMUM_NUMBER = 20;

    // DialogModeleDestineVente
    /******************************************************************************************************************/

    public static final int FIELD_WIDTH_LIBELLE_FR = 470;
    public static final int FIELD_WIDTH_LIBELLE_EU = 320;
    public static final int FIELD_WIDTH_NUMERO_AMM = 140;

    public static final String BUTTON_METTRE_A_JOUR_TEXT = "Mettre à jour";
    public static final int BUTTON_UPDATE_WIDTH = 110;
    public static final String DIALOG_SAVE_UPDATE_TEXT = "DialogSaveUpdate";
    public static final String LABEL_TEXT = "Label";
    public static final String FR_TEXT = "FR";
    public static final String LIBELLE_CODE_GTIN_TEXT = "Libellé - Code GTIN";
    public static final String LIBELLE_CODE_GTIN_NUMERO_AMM_TEXT = "Libellé - Code GTIN - Numéro d'AMM";

    public static final String DIALOG_TITLE_RCP = "Résumé des caractéristiques du produit - Mise à jour";
    public static final int DIALOG_WIDTH_RCP = 710;
    public static final int DIALOG_HEIGHT_RCP = 430;
    public static final int LABEL_WIDTH_MODELE_DESTINE_VENTE = 230;

    // DialogRcp
    /******************************************************************************************************************/

    public static final int FIELD_WIDTH_LIEN_RCP = 600;

    public static final String HTML_CLOSING = "</html>";
    public static final String LABEL_DATE_VALIDATION_TEXT = "Date de validation";
    public static final String LABEL_LIEN_RCP = "Lien Rcp";
    public static final int LABEL_WIDTH_DATE_VALIDATION_LIEN_RCP = 120;

    public static final String DIALOG_TITLE_RCP_IMAGE = "Résumé des caractéristiques du produit - Annexe Images - Mise à jour";
    public static final int DIALOG_WIDTH_RCP_IMAGE = 710;
    public static final int DIALOG_HEIGHT_RCP_IMAGE = 330;

    // DialogRcpImage
    /******************************************************************************************************************/

    public static final String LABEL_IMAGE_TEXT = "Image";
    public static final String CONNECTION_FAILED_RETRY = "Veuillez vérifier votre connexion internet et réessayer.";
    public static final int LABEL_WIDTH_IMAGE = 40;

    public static final int SCROLLPANE_WIDTH_DIALOG_RCP_IMAGE = 690;
    public static final int SCROLLPANE_HEIGHT_DIALOG_RCP_IMAGE = 150;

    public static final Label LABEL_IMAGE_RCP_IMAGE = new Label(LABEL_IMAGE_TEXT, LABEL_IMAGE_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_IMAGE, LABEL_HEIGHT);

    // DialogUpdate
    /******************************************************************************************************************/

    public static final int FIELD_WIDTH_NOM = 650;
    public static final int IMAGE_MAX_CHARACTERS = 400;
    public static final int PANEL_IMAGE_WIDTH = 110;
    public static final int PANEL_IMAGE_HEIGHT = 110;
    private static final String BUTTON_ICON_TEXT = "Icon";
    public static final String DIALOG_UPDATE_CLASS_NAME_TEXT = "DialogUpdate";

    public static final String NOM_MEDICAMENT_TEXT = "Nom du médicament";
    public static final String NOM_TITULAIRE_TEXT = "Nom du titulaire";
    private static final String FORME_PHARMACEUTIQUE_TEXT = "Forme pharmaceutique";
    private static final String STATUT_AUTORISATION_TEXT = "Statut d'autorisation";
    private static final String TYPE_PROCEDURE_TEXT = "Type de procédure";
    public static final String DATE_AMM_TEXT = "Date d'AMM";
    public static final String ERROR_MESSAGE_PATH_TOO_LONG_TEXT = "Le chemin d'accès est trop long.";

    public static final String[] ARRAY_RECHERCHE_TEXT = {"RECHERCHE..."};

    public static final int GRID_SIX = 6;
    public static final int GRID_SEVEN = 7;
    public static final int GRID_TEN = 10;

    public static final int GRID_HEIGHT_FOUR = 4;
    public static final int GRID_WIDTH_FOUR = 4;

    public static final int LAYOUT_GAP_FIFTEEN = 15;

    public static final int LABEL_WIDTH_UPDATE = 150;

    public static final String DIALOG_TITLE_ESPECE_UPDATE = "Espèces de destination - Mise à jour";
    public static final int DIALOG_WIDTH_ESPECE_UPDATE = 710;
    public static final int DIALOG_HEIGHT_ESPECE_UPDATE = 410;
    public static final int ESPECE_MAXIMUM_NUMBER = 22;

    public static final Label IMAGE_ICON = new Label(BUTTON_ICON_TEXT, null, FONT_SIZE_ELEVEN);
    public static final Label LABEL_NOM_UPDATE = new Label(LABEL_NOM_TEXT, LABEL_NOM_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_NOM, LABEL_HEIGHT);
    public static final Label LABEL_IMAGE_UPDATE = new Label(IMAGE_TEXT, IMAGE_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_IMAGE, LABEL_HEIGHT);
    public static final Label LABEL_NOM_TITULAIRE_UPDATE = new Label(NOM_TITULAIRE_TEXT, NOM_TITULAIRE_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_UPDATE, LABEL_HEIGHT);
    public static final Label LABEL_FORME_PHARMACEUTIQUE_UPDATE = new Label(FORME_PHARMACEUTIQUE_TEXT, FORME_PHARMACEUTIQUE_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_UPDATE, LABEL_HEIGHT);
    public static final Label LABEL_STATUT_AUTORISATION = new Label(STATUT_AUTORISATION_TEXT, STATUT_AUTORISATION_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_UPDATE, LABEL_HEIGHT);
    public static final Label LABEL_TYPE_PROCEDURE_UPDATE = new Label(TYPE_PROCEDURE_TEXT, TYPE_PROCEDURE_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_UPDATE, LABEL_HEIGHT);
    public static final Label LABEL_DATE_AMM = new Label(DATE_AMM_TEXT, DATE_AMM_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_UPDATE, LABEL_HEIGHT);

    // DialogVoieAdministration
    /******************************************************************************************************************/

    public static final String DIALOG_TITLE_CONDITION_DELIVRANCE = "Conditions de délivrance - Mise à jour";
    public static final int DIALOG_WIDTH_CONDITION_DELIVRANCE = 710;
    public static final int DIALOG_HEIGHT_CONDITION_DELIVRANCE = 410;
    public static final int CONDITION_DELIVRANCE_MAXIMUM_NUMBER = 5;

    // MenuBar
    /******************************************************************************************************************/

    public static final String MENU_TITLE_FICHIER_TEXT = "Fichier";
    public static final String MENU_TITLE_AIDE_TEXT = "Aide";

    // FieldPanel
    /******************************************************************************************************************/

    public static final String NOM_TEXT = "Nom";

    public static final int BORDER_WIDTH_TEN = 10;
    public static final int FIELD_WIDTH_FIELD_PANEL = 690;
    public static final int FIELD_HEIGHT = 30;

    // FormPane
    /******************************************************************************************************************/

    public static final String NUMERO_AMM_TEXT = "Numéro d'AMM";
    public static final int LABEL_FORMPANE_COLOR_1 = 100;
    public static final int LABEL_FORMPANE_COLOR_2 = 110;
    private static final String COLON_SPACE = ": ";

    private static final String ESPECES_TEXT = "Espèces";
    public static final String DATE_AMM_AU_TEXT = " au ";
    public static final String DIV_BEGINNING = "<div>";
    public static final String DIV_CLOSING = "</div>";
    public static final String DATE_AMM_SPACE_TEXT = "Date d'AMM de" + PATTERN_SPACE;
    public static final String DIV_DATE_AMM_BEGINNING = DIV_BEGINNING + DATE_AMM_SPACE_TEXT;
    public static final int DATE_BEGIN_MONTH_ONE = 1;

    public static final int DIALOG_WIDTH_DATE = 256;
    public static final int DIALOG_HEIGHT_DATE_SUBSTANCE_ACTIVE = 220;

    public static final int DIALOG_WIDTH_SUBSTANCE_ACTIVE = 550;

    public static final int DIALOG_WIDTH_ESPECE = 710;
    public static final int DIALOG_HEIGHT_ESPECE = 390;

    private static final int BUTTON_WIDTH_FORM_PANE = 150;
    private static final String BUTTON_DATE_AMM_TEXT = "Dates";
    private static final String BUTTON_RECHERCHER_TEXT = "Rechercher";
    public static final String FORM_PANE_CLASS_NAME_TEXT = "FormPane";

    private static final int LABEL_WIDTH_FORM_PANE = 120;
    public static final int FIELD_WIDTH_NOM_TITULAIRE = 480;

    public static final Button BUTTON_DATE_AMM = new Button(BUTTON_DATE_AMM_TEXT, BUTTON_DATE_AMM_TEXT, FONT_SIZE_ELEVEN, BUTTON_WIDTH_FORM_PANE, BUTTON_FIELD_HEIGHT);
    public static final Button BUTTON_SUBSTANCE_ACTIVE = new Button(SUBSTANCE_ACTIVE_PLURIEL_TEXT, SUBSTANCE_ACTIVE_PLURIEL_TEXT, FONT_SIZE_ELEVEN, BUTTON_WIDTH_FORM_PANE, BUTTON_FIELD_HEIGHT);
    public static final Button BUTTON_ESPECE = new Button(ESPECES_TEXT, ESPECES_TEXT, FONT_SIZE_ELEVEN, BUTTON_WIDTH_FORM_PANE, BUTTON_FIELD_HEIGHT);
    public static final Button BUTTON_RECHERCHER = new Button(BUTTON_RECHERCHER_TEXT, FONT_SIZE_ELEVEN, BUTTON_WIDTH_FORM_PANE, BUTTON_FIELD_HEIGHT, BUTTON_VALIDER_COLOR_R, BUTTON_VALIDER_COLOR_G, BUTTON_VALIDER_COLOR_B);

    public static final Label LABEL_FORME_PHARMACEUTIQUE_FORM_PANE = new Label(FORME_PHARMACEUTIQUE_TEXT, FORME_PHARMACEUTIQUE_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_UPDATE, LABEL_HEIGHT);
    public static final Label LABEL_TYPE_PROCEDURE_FORM_PANE = new Label(TYPE_PROCEDURE_TEXT, TYPE_PROCEDURE_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_UPDATE, LABEL_HEIGHT);
    public static final Label LABEL_NOM_TITULAIRE_FORM_PANE = new Label(NOM_TITULAIRE_TEXT, NOM_TITULAIRE_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_UPDATE, LABEL_HEIGHT);
    public static final Label LABEL_NUMERO_AMM = new Label(NUMERO_AMM_TEXT, NUMERO_AMM_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_FORM_PANE, LABEL_HEIGHT);
    public static final Label LABEL_CONDITION_DELIVRANCE = new Label(CONDITION_DELIVRANCE_SINGULIER_TEXT, CONDITION_DELIVRANCE_SINGULIER_TEXT, FONT_SIZE_ELEVEN, LABEL_WIDTH_FORM_PANE, LABEL_HEIGHT);

    // SearchResult
    /******************************************************************************************************************/

    public static final int FONT_SIZE_FOURTEEN = 14;

    public static final String ICON_LOADER = String.valueOf(Static.class.getResource("/icons/loader.gif"));
    public static final String RECHERCHE_EN_COURS = "<html><body style=\"font-size:9px;padding:20px 7px 7px 7px;text-align:center;\"><img src=\"" + ICON_LOADER + "\" alt=\"Recherche...\" /></body></html>";

    public static final String PRINTER_TEXT = "-printer";

    public static final String NOMBRE_RESULTATS = "Nombre de résultats: ";

    public static final String FONT_PRINT = Objects.requireNonNull(Static.class.getResource("/ttf/Lato-Regular.ttf")).getFile();
    // max (int) page.getMediaBox().getWidth();
    public static final int PAGE_WIDTH = 612;
    // max (int) page.getMediaBox().getHeight();
    public static final int PAGE_HEIGHT = 792;
    public static final int X_MARGIN = 35;
    public static final int Y_MARGIN = 32;

    public static final int COLOR_ANNOTATION = 130;
    public static final String SEARCH_RESULT_CLASS_NAME_TEXT = "SearchResult";

    // TableResult
    /******************************************************************************************************************/

    public static final int INSET_SEVEN = 7;

    private static final int TOGGLE_BUTTON_WIDTH = 130;
    private static final String TOGGLE_BUTTON_RCP_TEXT = "RCP";
    private static final URL URL_RCP_BUTTON = Static.class.getResource("/icons/button/rcp.png");
    private static final String TOGGLE_BUTTON_FAV_TEXT = "FAV";
    private static final URL URL_FAVORITE_BUTTON = Static.class.getResource("/icons/button/favorite.png");
    private static final String BUTTON_UPDATE_TEXT = "MAJ";
    private static final URL URL_UPDATE_BUTTON = Static.class.getResource("/icons/button/edit.png");

    public static final String MISE_A_JOUR_DES_INFORMATIONS_TEXT = "Mise à jour des informations";
    public static final int DIALOG_WIDTH_UPDATE = 710;
    public static final int DIALOG_HEIGHT_UPDATE = 380;

    public static final String AUCUNES_DONNEES = "<html><body style=\"font-size:9px;padding:7px;\">Le médicament n'est pas enregistré dans la base de données</body></html>";

    public static final int PAGE_WIDTH_DIVIDED_BY_2 = PAGE_WIDTH / 2;

    public static final String TABLE_RESULT_CLASS_NAME_TEXT = "TableResult";

    public static final Button UPDATE_BUTTON = new Button(BUTTON_UPDATE_TEXT, BUTTON_UPDATE_TEXT, FONT_SIZE_TWELVE, TOGGLE_BUTTON_WIDTH, BUTTON_FIELD_HEIGHT, URL_UPDATE_BUTTON);
    public static final ToggleButton TOGGLE_BUTTON = new ToggleButton(TOGGLE_BUTTON_RCP_TEXT, TOGGLE_BUTTON_WIDTH, BUTTON_FIELD_HEIGHT, URL_RCP_BUTTON);
    public static final ToggleButton FAVORITE_TOGGLE_BUTTON = new ToggleButton(TOGGLE_BUTTON_FAV_TEXT, TOGGLE_BUTTON_WIDTH, BUTTON_FIELD_HEIGHT, URL_FAVORITE_BUTTON);

    // Services
    /******************************************************************************************************************/

    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCEPT = "Accept";
    public static final String MEDICAMENT_TEXT = "medicament";
    public static final String IMAGE_MEDICAMENT_TEXT = "imageMedicament";
    public static final String IMAGE_RCP_TEXT = "imageRcp";
    public static final String CONTENT_TYPE_TEXT = "Content-Type";
    public static final String STARTS_WITH_HTTP = "http";
    public static final String MULTIPART_FORM_DATA_BOUNDARY = "multipart/form-data;boundary=";

    public static final String API_URL = "https://medicament-vet.ddns.net/api";
    private static final String PATH_MEDICAMENTS_NOM_ID = "/medicaments/nom-id";
    public static final String PATH_MEDICAMENTS_SEARCH = "/medicaments/search";
    private static final String PATH_SEARCH_FORM = "/search-form";
    private static final String PATH_UPDATE_FORM = "/update-form";
    public static final String PATH_MEDICAMENT = "/medicament/";
    public static final String PATH_MEDICAMENT_UPDATE = "/medicament/update";

    public static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final HttpRequest REQUEST_NOM_ID = HttpRequest.newBuilder().header(ACCEPT, APPLICATION_JSON).uri(URI.create(API_URL + PATH_MEDICAMENTS_NOM_ID)).build();
    public static final HttpRequest REQUEST_SEARCH_FORM = HttpRequest.newBuilder().header(ACCEPT, APPLICATION_JSON).uri(URI.create(API_URL + PATH_SEARCH_FORM)).build();
    public static final HttpRequest REQUEST_UPDATE_FORM = HttpRequest.newBuilder().header(ACCEPT, APPLICATION_JSON).uri(URI.create(API_URL + PATH_UPDATE_FORM)).build();

    // MultipartBodyPublisher
    /******************************************************************************************************************/

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String DOUBLE_DASH = "--";
    public static final String CONTENT_DISPOSITION_TEXT = "Content-Disposition: form-data; name = ";
    public static final String CONTENT_TYPE_COLON_SPACE_TEXT = "Content-Type: " + COLON_SPACE;

    public static final String FILE_NAME_TEXT = "\"; filename = \"";
    public static final String BACKSLASH = "\"";

    // SwingWorkerClass
    /******************************************************************************************************************/

    public static final String SWINGWORKER_CLASS_TEXT = "SwingWorkerClass";
    public static final String SEARCH_RESULT_PRINTER_CLASS_TEXT = SEARCH_RESULT_CLASS_NAME_TEXT + PRINTER_TEXT;
    public static final String TABLE_RESULT_PRINTER_CLASS_TEXT = TABLE_RESULT_CLASS_NAME_TEXT + PRINTER_TEXT;

    public static final String ERROR_INTERNET_CONNEXION = "Veuillez vérifier votre connexion internet";
    public static final String ERROR_INTERNET_CONNEXION_FILE_NOT_FOUND = "Veuillez vérifier votre connexion internet, ou un ou plusieurs de vos fichiers est/sont introuvable(s)";
    public static final String DIALOG_TITLE_RECHERCHE_PRINT = "Résultats de recherche";
    public static final String DIALOG_TITLE_INFORMATION_PRINT = "Informations du médicament";
    public static final String INTERRUPTION_IMPRESSION_TEXT = "Interruption de l'impression";

    // Utils
    /******************************************************************************************************************/

    public static final ZoneId DEFAULT_TIME_ZONE = ZoneId.systemDefault();
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT_FR = new SimpleDateFormat(SPINNER_DATE_FORMAT_PATTERN);
    public static final DateTimeFormatter DATE_TIME_FORMATTER_FR = DateTimeFormatter.ofPattern(SPINNER_DATE_FORMAT_PATTERN);

    public static final String PATTERN_NEWLINE_DOUBLE = "\n\n";
    public static final Pattern SEPARATOR_2 = Pattern.compile(PATTERN_SPACE);
    public static final Pattern SEPARATOR_3 = Pattern.compile(FORWARD_SLASH);
    public static final String FOOTER_PAGE_TEXT = " - Page ";

    public static final String FILE_PATH = "file:";
    public static final String FILE_PATH_FORWARD_SLASH = FILE_PATH + FORWARD_SLASH;
    public static final String LEFT_ANGLE_BRACKET = "<";
    public static final String LEFT_ANGLE_BRACKET_HTML = "&lt;";

    public static final String BUTTON_CLASS_TEXT = "Button";
    public static final String ERROR_MESSAGE_PRINT = "L'impression n'est pas possible.";
    public static final String ERROR_MESSAGE_PDF = "La génération du fichier PDF n'est pas possible.";
    public static final String ERROR_MESSAGE_FILE_NOT_FOUND_1 = "Le fichier image ";
    public static final String ERROR_MESSAGE_FILE_NOT_FOUND_2 = " est introuvable.";
    public static final String JOPTIONPANE_DIALOG_TITLE_ERROR_PRINT_SERVICE_TEXT = "Service d'impression";
    public static final String ERROR_MESSAGE_PRINT_SERVICE_NOT_FOUND = "Le service d'impression est introuvable.";

    private Static() {
        super();
    }
}
