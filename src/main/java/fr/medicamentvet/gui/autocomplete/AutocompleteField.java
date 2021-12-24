package fr.medicamentvet.gui.autocomplete;

import fr.medicamentvet.utils.Static;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * The class represents the autocomplete text field component. There is three listener and binding actions attached to the field:<br>
 * - A document listener that check the text change of the input<br>
 * - A mouse listener that checks when the user clicks inside the field<br>
 * - A focus listener that checks the focus lost and gain events<br>
 * - A key pressed action listener that checks user typing for DOWN, UP, ENTER keys
 */
public class AutocompleteField extends JTextField {

    private final AutocompleteWindow autocompleteWindow;

    private String[] arrayString;

    public AutocompleteField(Container container, String name, int width, int height) {

        setName(name);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));

        autocompleteWindow = new AutocompleteWindow(container);

        getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(Static.DOWN_KEYSTROKE_TEXT), Static.ACTION_DOWN_KEY_TEXT);
        getActionMap().put(Static.ACTION_DOWN_KEY_TEXT, autocompleteWindow.actionDownKey);
        getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(Static.UP_KEYSTROKE_TEXT), Static.ACTION_UP_KEY_TEXT);
        getActionMap().put(Static.ACTION_UP_KEY_TEXT, autocompleteWindow.actionUpKey);
        getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(Static.ENTER_KEYSTROKE_TEXT), Static.ACTION_ENTER_KEY_TEXT);
        getActionMap().put(Static.ACTION_ENTER_KEY_TEXT, autocompleteWindow.actionEnterKey);

        Document document = this.getDocument();

        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                changedUpdate(documentEvent);
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                changedUpdate(documentEvent);
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                updatePopup(false);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                updatePopup(true);
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                setBackground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                autocompleteWindow.setVisible(false);
            }
        });
    }

    public void setAutocompleteFieldText(Object nom) {
        setBackground(Color.WHITE);

        // When user clicks on a name from Favorite dialog for example, we want to set the new text of the autocomplete field.
        // We initialize the boolean keyStrokeDownUp to true so that the popup does not show up.

        setText(String.valueOf(nom));

        // Then we initialize the boolean keyStrokeDownUp to the initial value (false).
    }

    public String[] getArrayString() {
        return arrayString;
    }

    public void setKeyStrokeDownUp(boolean keyStrokeDownUp) {
    }

    public void setArrayString(String[] arrayString) {
        this.arrayString = arrayString;

        // We need the autocomplete field component to set the text but also to get the width in AutocompleteWindow class.
        autocompleteWindow.setVariables(this, arrayString);
    }

    /**
     * The final purpose of the method is to show a popup holding a list of suggestions based on user text input.
     *
     * @param click Boolean user click inside the autocomplete field
     */
    private void updatePopup(boolean click) {
    }
}
