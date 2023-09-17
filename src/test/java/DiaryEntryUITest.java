import UserSystem.User;
import util.UI.Dialogs.EntryListDialog;


public class DiaryEntryUITest {
    public static void main(String[] args){
        DiaryEntryUITest l = new DiaryEntryUITest(new User("null", "null"));
    }

    public DiaryEntryUITest(User u){
        EntryListDialog.showEntires(u);
    }
}
