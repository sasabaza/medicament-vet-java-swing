package fr.medicamentvet.gui.autocomplete;

import fr.medicamentvet.utils.Static;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JWindow;
import javax.swing.ListSelectionModel;
import javax.swing.event.MouseInputAdapter;

/**
 * This class is the autocomplete window component. It contains methods to show the popup and mouse listeners attached to the suggestions list.
 */
public class AutocompleteWindow extends JWindow {

    private static String[] results;

    private final JList<String> suggestions;
    private final Container container;

    private AutocompleteField autocompleteField;

    public AutocompleteWindow(Container container) {
        // setOpacity(1);
        setBackground(null);

        this.container = container;

        suggestions = new JList<>(results);
        suggestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suggestions.setFixedCellHeight(Static.CELL_SIZE);

        suggestions.setFont(new Font(null, Font.PLAIN, Static.FONT_SIZE_THIRTEEN));
        suggestions.setBorder(BorderFactory.createLineBorder(Static.COLOR_BORDER, Static.THICKNESS));

        add(suggestions);

        suggestions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                mousePressedAction(mouseEvent);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                mouseMoved(mouseEvent);
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                mouseEnteredOrMoved(mouseEvent);
            }
        });

        // This listener is identical to previous one. Some OS do not respond well to addMouseListener events.
        suggestions.addMouseMotionListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                mousePressedAction(mouseEvent);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                mouseMoved(mouseEvent);
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                mouseEnteredOrMoved(mouseEvent);
            }
        });
    }

    public void setVariables(AutocompleteField autocompleteField, String[] arrayString) {
        this.autocompleteField = autocompleteField;
    }

    /**
     * The action is triggered when user presses the Enter key and then sends the selected text.
     */
    public final Action actionEnterKey = new AbstractAction() {
        public void actionPerformed(ActionEvent actionEvent) {
            if (!suggestions.isSelectionEmpty()) {

                String nom = autocompleteField.getText();

                sendSelection(nom);
            }
        }
    };

    /**
     * The action is activated when there is a Down arrow keystroke. The method selects the specific item of the list and sets the text of the autocomplete field.
     */
    public final Action actionDownKey = new AbstractAction() {
        public void actionPerformed(ActionEvent actionEvent) {

            if (suggestions.getSelectedIndex() < results.length - 1) {
                suggestions.setSelectedIndex(suggestions.getSelectedIndex() + 1);
            } else {
                suggestions.setSelectedIndex(0);
            }
            // Set keyStrokeDownUp variable to true to skip the main part of updatePopup method of AutocompleteField class
            autocompleteField.setKeyStrokeDownUp(true);

            getSelectedText();

            // Set keyStrokeDownUp variable to initial value
            autocompleteField.setKeyStrokeDownUp(false);
        }
    };

    /**
     * The action is initiated when there is an Up arrow keystroke. The method selects the specific item of the list and sets the text of the autocomplete field.
     */
    public final Action actionUpKey = new AbstractAction() {
        public void actionPerformed(ActionEvent actionEvent) {

            if (suggestions.getSelectedIndex() > 0) {
                suggestions.setSelectedIndex(suggestions.getSelectedIndex() - 1);
            } else {
                suggestions.setSelectedIndex(results.length - 1);
            }
            // Set keyStrokeDownUp variable to true to skip the main part of updatePopup method of AutocompleteField class
            autocompleteField.setKeyStrokeDownUp(true);

            getSelectedText();

            // Set keyStrokeDownUp variable to initial value
            autocompleteField.setKeyStrokeDownUp(false);
        }
    };

    /**
     * The method calls a specific method of FieldPanel class if the container class name is FieldPanel and then hides the popup.
     *
     * @param text Selected text
     */
    private void sendSelection(String text) {
    }

    /**
     * The method gets the selected text from the suggestions list and processes the action.
     *
     * @param mouseEvent Event for mouse pressed
     */
    private void mousePressedAction(MouseEvent mouseEvent) {
    }

    private void mouseEnteredOrMoved(MouseEvent mouseEvent) {
    }

    private void getSelectedText() {
    }
}
