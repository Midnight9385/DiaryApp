package util.UI.List;

import de.milchreis.uibooster.model.DialogClosingState;
import de.milchreis.uibooster.model.FormCloseListenerWrapper;
import util.UI.Dialogs.EntryDialog;
import util.UI.Dialogs.EntryListDialog;

import javax.swing.*;

import App.DiaryApp;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;

public class ListBlockingDialog {

    private JDialog dialog;
    private final JComponent[] components;
    private final DialogClosingState closingState = new DialogClosingState();
    private Consumer<JDialog> onDialogCreated;
    private final ArrayList<String> options = new ArrayList<>(Arrays.asList(new String[]{"Create New Entry","Edit Entry", "Sign Out"}));

    public ListBlockingDialog(JComponent... component) {
        this.components = component;
    }

    public void setDialogCreatedListener(Consumer<JDialog> onDialogCreated) {
        this.onDialogCreated = onDialogCreated;
    }

    public DialogClosingState showDialog(String message, String title, String iconPath) {
        return showDialog(message, title, null, iconPath, null, false);
    }

    public DialogClosingState showDialog(String message, String title, WindowSetting setting, String iconPath, FormCloseListenerWrapper exitListenerWrapper, boolean resizable) {
        JOptionPane optionPane = new JOptionPane(null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options.toArray(new String[0]), null);
        optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);

        if (message != null && !message.isEmpty())
            optionPane.setMessage(new Object[]{message, components});
        else
            optionPane.setMessage(new Object[]{components});

        optionPane.setOptionType(JOptionPane.YES_NO_CANCEL_OPTION);   

        dialog = createDialog(title, optionPane);
        applyWindowIcon(iconPath, dialog);

        if (onDialogCreated != null)
            onDialogCreated.accept(dialog);

        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closingState.setClosedByUser(optionPane.getValue() == null);

                //TODO make sure all these methods do the right thing with the new UI
                switch(options.indexOf(optionPane.getValue().toString())){
                    case 0:  DiaryApp.createEntry(); break; //create
                    case 1:  EntryDialog.showEntry(EntryListDialog.getChosenTitle());break; //edit
                    case 2:  DiaryApp.signOut(); break; //sign out
                    default: break; //shouldn't get this case
                }

                if (exitListenerWrapper != null)
                    exitListenerWrapper.executeListener();
            }
        });

        if (setting != null) {
            // Works currently not with JDialog created by JOptionPane
//            dialog.setUndecorated(setting.isUndecorated());
            dialog.setPreferredSize(new Dimension(setting.getWidth(), setting.getHeight()));

            if (setting.getPositionX() != null && setting.getPositionY() != null)
                dialog.setLocation(setting.getPositionX(), setting.getPositionY());
            else
                dialog.setLocationRelativeTo(null);
        }

        dialog.setResizable(resizable);
        dialog.pack();
        dialog.setVisible(true);
        dialog.dispose();

        return closingState;
    }

    private JDialog createDialog(String title, JOptionPane optionPane) {
        return optionPane.createDialog(null, title);
    }

    public JDialog getDialog() {
        return dialog;
    }

}
