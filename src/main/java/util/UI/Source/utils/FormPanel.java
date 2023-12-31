package util.UI.Source.utils;

import util.UI.UISpacer;
import util.UI.Source.model.FormElement;
import util.UI.Source.model.FormElementChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class FormPanel {

    public static JPanel createPanel(List<FormElement> formElements, FormElementChangeListener changeListener, int panelBorder) {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(panelBorder, panelBorder, panelBorder, panelBorder));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (FormElement formElement : formElements) {

            JPanel elementPanel = new JPanel(new BorderLayout());
            JComponent component = formElement.createComponent(changeListener);

            if (formElement.getLabel() != null) {
                JLabel label = new JLabel(formElement.getLabel());
                label.setBorder(new EmptyBorder(0, 0, 5, 0));
                panel.add(label);
                elementPanel.add(label, BorderLayout.NORTH);
            }

            if (component != null) {
                elementPanel.add(component, BorderLayout.CENTER);
            }

            elementPanel.setBorder(new EmptyBorder(
                formElement.getMarginTop(), formElement.getMarginLeft(),
                formElement.getMarginBottom(), formElement.getMarginRight())
            );

            panel.setBounds(UISpacer.getMiddleX(800), UISpacer.getMiddleY(400), 800, 400);
            panel.add(elementPanel);
        }
        panel.setMinimumSize(new Dimension(500, 500));
        return panel;
    }
}
