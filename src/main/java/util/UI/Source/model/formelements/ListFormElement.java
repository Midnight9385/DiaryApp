package util.UI.Source.model.formelements;

import util.UI.Source.model.FormElement;
import util.UI.Source.model.FormElementChangeListener;
import util.UI.Source.model.ListElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.UI.Source.components.ListDialog.createList;
import static util.UI.Source.components.ListDialog.createListModel;

public class ListFormElement extends FormElement {

    private ListElement[] elements;
    private JList<ListElement> list;

    public ListFormElement(String label, ListElement[] elements) {
        super(label);
        this.elements = elements;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        list = createList(element -> {
            if (changeListener != null)
                changeListener.onChange(ListFormElement.this, getValue(), form);
        }, elements);

        return new JScrollPane(
                list,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void setEnabled(boolean enable) {
        list.setEnabled(enable);
    }

    @Override
    public ListElement getValue() {
        return list.getSelectedValue();
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof ListElement[]) {
            elements = (ListElement[]) value;
            list.setModel(createListModel(elements));

        } else if (value instanceof List) {
            elements = (ListElement[]) ((List) value).stream().toArray(ListElement[]::new);
            list.setModel(createListModel(elements));

        } else
            throw new IllegalArgumentException("The given value has to be of type 'ListElement[]' or 'List<ListElement>'");
    }

    public void addElement(ListElement element) {
        ArrayList<ListElement> arrayList = new ArrayList<>(Arrays.asList(elements));
        arrayList.add(element);
        elements = arrayList.toArray(elements);
        list.setModel(createListModel(elements));
    }

    public void clearAll() {
        elements = new ListElement[]{};
        list.setModel(createListModel(elements));
    }
}
