package fr.medicamentvet.gui.simple;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;

/**
 * The aim of the class is to create a custom constructor that extends JEditorPane component.
 */
public class EditorPane extends JEditorPane {

    /**
     * JEditorPane constructor
     *
     * @param contentType   Content type
     * @param borderWidth   Width of the border
     * @param editable      Boolean mention whether true or false JEditorPane is editable
     * @param caretPosition Position of the caret
     */
    public EditorPane(String contentType, int borderWidth, boolean editable, int caretPosition) {
        setContentType(contentType);
        setBorder(BorderFactory.createEmptyBorder(0, borderWidth, 0, borderWidth));
        setEditable(editable);
        setCaretPosition(caretPosition);
    }
}
