package util.UI.Source.model.formelements;

import util.UI.Dialogs.EntryDialog;
import util.UI.Source.model.FormElement;
import util.UI.Source.model.FormElementChangeListener;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextFormElement extends FormElement {

    private final JTextField textfield;

    public TextFormElement(String label, String initialText, boolean readonly) {
        super(label);
        textfield = new JTextField(initialText);
        textfield.setEditable(!readonly);

        if (initialText != null) {
            textfield.setCaretPosition(initialText.length());
        }
        textfield.setPreferredSize(new Dimension(400, 40));
        textfield.setMaximumSize(new Dimension(400, 40));
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {

        if (changeListener != null) {
            textfield.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    changeListener.onChange(TextFormElement.this, getValue(), form);
                }
            });
        }

        return textfield;
    }

    public void addActionListener(){
        textfield.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntryDialog.setTitle(textfield.getText());
                // System.out.println("set text");
            }
        });    
    }

    @Override
    public void setEnabled(boolean enable) {
        textfield.setEnabled(enable);
    }

    @Override
    public String getValue() {
        return textfield.getText();
    }

    @Override
    public void setValue(Object value) {
        textfield.setText(value.toString());
    }
}
