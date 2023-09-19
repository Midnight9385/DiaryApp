import App.DiaryApp;
import UserSystem.User;
import util.UI.Dialogs.EntryListDialog;


public class DiaryEntryUITest {
    public static void main(String[] args){
        DiaryApp.createInterfaces();
        DiaryApp.getDataStorage().createData("dwa", "dwadwa");
        DiaryApp.getDataStorage().createData("abc", "213");
        DiaryEntryUITest l = new DiaryEntryUITest(new User("null", "null"));
    }

    public DiaryEntryUITest(User u){
        EntryListDialog.showEntires(u);
    }
}
