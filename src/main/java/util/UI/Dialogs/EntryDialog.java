package util.UI.Dialogs;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import App.DiaryApp;
import CRUD.DataInterface;
import CRUD.DataStorage;

import java.awt.Frame;

import de.milchreis.uibooster.model.Form;
import de.milchreis.uibooster.model.UiBoosterOptions;
import de.milchreis.uibooster.model.options.DarkUiBoosterOptions;
import de.milchreis.uibooster.utils.WindowIconHelper;
import util.UI.UiBooster;

import static javax.swing.JOptionPane.*;

public class EntryDialog {
    private static UiBoosterOptions options = new DarkUiBoosterOptions();
    public static void showEntry(String title){
        UiBooster booster = new UiBooster();
        Form form = booster.createForm("Personal information")
                    .addText("Title")
                    .addTextArea("Entry")
                    .show();
        showInputDialog("title", DiaryApp.sendEntry(title), options);
    }

    private static void exit(){

    }

    private static String showInputDialog(String message, String[] entry, UiBoosterOptions options) {
        JOptionPane jp = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.CANCEL_OPTION, null, new String[]{"Save and Exit"}, null);

        jp.setWantsInput(true);
        jp.setSelectionValues(null);
        jp.setInitialSelectionValue(null);
        jp.setComponentOrientation(getRootFrame().getComponentOrientation());

        

        createDialog(jp, UIManager.getString("OptionPane.inputDialogTitle", null), options);

        Object value = jp.getInputValue();

        if (value == UNINITIALIZED_VALUE) {
            return null;
        }

        return value.toString();
    }

    private static void createDialog(JOptionPane jp, String String, UiBoosterOptions options) {
        JDialog dialog = jp.createDialog(null, String);
        ((Frame) dialog.getParent()).setIconImage(WindowIconHelper.getIcon(options.getIconPath()).getImage());
        dialog.setIconImage(WindowIconHelper.getIcon(options.getIconPath()).getImage());
        dialog.setVisible(true);
    }
}
