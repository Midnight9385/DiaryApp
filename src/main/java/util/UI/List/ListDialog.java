// Source code is decompiled from a .class file using FernFlower decompiler.
package util.UI.List;

import de.milchreis.uibooster.model.DialogClosingState;
import de.milchreis.uibooster.model.FormCloseListenerWrapper;
import de.milchreis.uibooster.model.ListElement;
import de.milchreis.uibooster.model.SelectElementListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ListDialog {
   public ListDialog() {
   }

   public static ListElement showList(String message, String title, String iconPath, ListElement... elements) {
      return showList(message, title, iconPath, (SelectElementListener)null, elements);
   }

   public static ListElement showList(String message, String title, String iconPath, SelectElementListener selectElementListener, ListElement... elements) {
      JList<ListElement> list = createList(selectElementListener, elements);
      JScrollPane jScrollPane = new JScrollPane(list, 20, 32);
      jScrollPane.setPreferredSize(new Dimension(400, 400));
      ListBlockingDialog dialog = new ListBlockingDialog(new JComponent[]{jScrollPane});
      DialogClosingState closingState = dialog.showDialog(message, title, (WindowSetting)null, null, (FormCloseListenerWrapper)null, true);
      return closingState.isClosedByUser() ? null : (ListElement)list.getSelectedValue();
   }

   public static JList<ListElement> createList(SelectElementListener selectElementListener, ListElement[] elements) {
      DefaultListModel<ListElement> listModel = createListModel(elements);
      JList<ListElement> list = new JList<ListElement>(listModel);
      if (selectElementListener != null) {
         list.addListSelectionListener((e) -> {
            if (e.getValueIsAdjusting()) {
               selectElementListener.onSelected(elements[e.getFirstIndex()]);
            }

         });
      }

      boolean hasAtLeasedOneIcon = Stream.of(elements).anyMatch((e) -> {
         return e.getImage() != null;
      });
      list.setSelectionMode(0);
      list.setCellRenderer((list1, listElement, index, isSelected, cellHasFocus) -> {
         JPanel row = new JPanel(new BorderLayout());
         Box vBox = Box.createVerticalBox();
         vBox.setAlignmentY(0.0F);
         vBox.add(new JMultilineLabel(listElement.getTitle(), true));
         vBox.add(new JMultilineLabel(listElement.getMessage(), false));
         row.add(vBox, "Center");
         Image preview = listElement.getImage() != null ? listElement.getImage().getScaledInstance(80, 80, 2) : new BufferedImage(80, 80, 6);
         JLabel image = new JLabel(new ImageIcon((Image)preview));
         image.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3));
         image.setVerticalAlignment(1);
         if (hasAtLeasedOneIcon) {
            row.add(image, "West");
         }

         if (isSelected) {
            row.setBackground(list.getSelectionBackground());
            row.setForeground(list.getSelectionForeground());
         } else {
            row.setBackground(list.getBackground());
            row.setForeground(list.getForeground());
         }

         row.setEnabled(list.isEnabled());
         row.setFont(list.getFont());
         row.setOpaque(true);
         row.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));
         return row;
      });
      return list;
   }

   public static DefaultListModel<ListElement> createListModel(ListElement[] elements) {
      DefaultListModel<ListElement> listModel = new DefaultListModel<ListElement>();
      Stream.of(elements).forEach(listModel::addElement);
      return listModel;
   }

   static class JMultilineLabel extends JLabel {
        private static final long serialVersionUID = 1L;

        public JMultilineLabel(String text, boolean bold) {
            super();

            if (text != null) {
                final String prepared = "<html>"
                        + text
                        .replace("\r", "")
                        .replace("\n", "<br>")
                        + "</html>";

                setText(prepared);
            }

            setCursor(null);
            setOpaque(false);
            setFocusable(false);
            setFont(UIManager.getFont("Label.font"));
            if (!bold)
                setFont(getFont().deriveFont(getFont().getStyle() & ~Font.BOLD));
            setBorder(new EmptyBorder(5, 5, 0, 5));
            setAlignmentY(JLabel.CENTER_ALIGNMENT);
        }
    }
}
