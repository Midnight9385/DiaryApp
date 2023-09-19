package util.UI.Dialogs;


import java.util.ArrayList;

import App.DiaryApp;
import UserSystem.User;
import de.milchreis.uibooster.model.ListElement;
import util.UI.UiBooster;

public class EntryListDialog {
    private static UiBooster b = new UiBooster();

    private static String chosenTitle = "";

    public static void showEntires(User u){
        LoginDialog.setUser(u);
        DiaryApp.getDataInterface().updateDataStorage(LoginDialog.getUser().getSerial());
        b.showList("", "Entries", 
                   element -> setChosenTitle(element.getTitle()), 
                   createElementList(u));
    }

    public static void setChosenTitle(String t){
        chosenTitle=t;
    }

    private static ListElement[] createElementList(User u){
        ArrayList<ListElement> l = new ArrayList<>();
        DiaryApp.getDataStorage().getTableList().forEach((e)->l.add(new ListElement(e[0], "last edited: "+e[1])));
        
        return l.toArray(new ListElement[0]);
    }

    public static String getChosenTitle(){
        return chosenTitle;
    }

    public static Object edit() {
        //TODO open chosen entry
        return null;
    }
}
