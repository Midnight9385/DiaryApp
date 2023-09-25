package util.UI.Dialogs;

import App.DiaryApp;
import util.UI.Source.model.Form;
import util.UI.Source.model.FormElementChangeListener;
import util.UI.Source.UiBooster;

public class EntryDialog {
    private static String title;
    private static String entry;

    public static void showEntry(String cTitle){
        UiBooster booster = new UiBooster();
        title = cTitle;
        Form form = booster.createForm("Entry")
                    .addText("Title", DiaryApp.sendEntry(cTitle)[0], 0)
                    .setChangeListener(new FormElementChangeListener() {

                        @Override
                        public void onChange(util.UI.Source.model.FormElement element, Object value, Form form) {
                            EntryDialog.saveEnteredData(element.getLabel(), value.toString());
                        }
                        
                    })
                    .addTextArea("Entry", DiaryApp.sendEntry(cTitle)[1])
                    .show();            
    }

    public static void saveEnteredData(String label, String data) {
        if(label.equals("Title")){
            // System.out.println("changed title: "+data);
            title = data;
        }else if(label.equals("Entry")){
            // System.out.println("changed entry: "+data);
            entry = data;
        }
    }

    public static void saveEntry(){
        // System.out.println("saved:"+title);
        DiaryApp.saveEntry(EntryListDialog.getChosenTitle(), title, entry);
    }

    public static void setTitle(String t){
        title = t;
    }

    public static void setEntry(String t){
        entry = t;
    }
}
