package util.UI.Source.model.formelements;

import util.UI.Dialogs.EntryDialog;
import util.UI.Source.model.FormElement;
import util.UI.Source.model.FormElementChangeListener;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextAreaFormElement extends FormElement {

    private final JTextArea area;

    public TextAreaFormElement(String label, int rows, String initialText, boolean readOnly) {
        super(label);
        area = new JTextArea(initialText);
        area.setEditable(!readOnly);
        area.setRows(rows);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {

        if (changeListener != null) {
            area.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    changeListener.onChange(TextAreaFormElement.this, getValue(), form);
                }
            });
        }
        return new JScrollPane(area);
    }

    @Override
    public void setEnabled(boolean enable) {
        area.setEnabled(enable);
    }

    @Override
    public String getValue() {
        return area.getText();
    }

    @Override
    public void setValue(Object value) {
        area.setText(value.toString());
    }

    public void addActionListener() {
        area.addInputMethodListener(new InputMethodListener() {

            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                // System.out.println("changed");
                EntryDialog.setEntry(area.getText());
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {
                // System.out.println("changed");
                EntryDialog.setEntry(area.getText());
            }
            
        });
    }    
}