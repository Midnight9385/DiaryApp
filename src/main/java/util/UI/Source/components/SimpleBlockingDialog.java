package util.UI.Source.components;

import util.UI.UISpacer;
import util.UI.Dialogs.EntryDialog;
import util.UI.Dialogs.EntryListDialog;
import util.UI.Dialogs.LoginDialog;
import util.UI.Source.model.DialogClosingState;
import util.UI.Source.model.FormCloseListenerWrapper;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import static util.UI.Source.utils.WindowIconHelper.applyWindowIcon;

public class SimpleBlockingDialog {

    private JDialog dialog;
    private final JComponent[] components;
    private final DialogClosingState closingState = new DialogClosingState();
    private Consumer<JDialog> onDialogCreated;

    public SimpleBlockingDialog(JComponent... component) {
        this.components = component;
    }

    public void setDialogCreatedListener(Consumer<JDialog> onDialogCreated) {
        this.onDialogCreated = onDialogCreated;
    }

    public DialogClosingState showDialog(String message, String title, String iconPath) {
        return showDialog(message, title, null, iconPath, null, false);
    }

    public DialogClosingState showDialog(String message, String title, WindowSetting setting, String iconPath, FormCloseListenerWrapper exitListenerWrapper, boolean resizable) {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setOptions(new String[]{"Save and Exit"});

        if (message != null && !message.isEmpty())
            optionPane.setMessage(new Object[]{message, components});
        else
            optionPane.setMessage(new Object[]{components});

        optionPane.setBounds(UISpacer.getMiddleX(800), UISpacer.getMiddleY(400), 800, 400);
        dialog = createDialog(title, optionPane);
        dialog.setBounds(UISpacer.getMiddleX(800), UISpacer.getMiddleY(400), 800, 400);
        applyWindowIcon(iconPath, dialog);

        if (onDialogCreated != null)
            onDialogCreated.accept(dialog);

        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closingState.setClosedByUser(optionPane.getValue() == null);

                if(optionPane.getValue()!=null){
                    EntryDialog.saveEntry();
                    EntryListDialog.showEntires(LoginDialog.getUser());
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

        dialog.setBounds(UISpacer.getMiddleX(800), UISpacer.getMiddleY(400), 800, 400);

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
