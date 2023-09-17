package util.UI.Dialogs;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import App.DiaryApp;
import CRUD.DataInterface;
import CRUD.DataStorage;

import java.awt.Component;
import java.awt.Frame;

import util.UI.Source.model.Form;
import util.UI.Source.model.UiBoosterOptions;
import util.UI.Source.model.options.DarkUiBoosterOptions;
import util.UI.Source.utils.WindowIconHelper;
import util.UI.Source.UiBooster;

import static javax.swing.JOptionPane.*;

public class EntryDialog {
    private static String title;
    private static String entry;

    private static UiBoosterOptions options = new DarkUiBoosterOptions();
    public static void showEntry(String title){
        UiBooster booster = new UiBooster();
        Form form = booster.createForm("Entry")
                    .addText("Title", DiaryApp.sendEntry(title)[0], 0)
                    .addTextArea("Entry", DiaryApp.sendEntry(title)[1])
                    .show();            
    }

    private static void exit(){

    }

    public static void saveEntry(Object components) {
        String title;
        String entry;
    }

    public static void setTitle(String t){
        title = t;
    }

    public static void setEntry(String t){
        entry = t;
    }
}
