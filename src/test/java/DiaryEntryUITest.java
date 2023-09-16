import java.awt.Toolkit;

import CRUD.DataInterface;
import UserSystem.User;
import UserSystem.UserInterface;
import de.milchreis.uibooster.UiBooster;
import de.milchreis.uibooster.components.LoginDialog;
import util.UI.EntryListDialog;


public class DiaryEntryUITest {
    private final int width = 800;
    private final int height = 400;        
    public static void main(String[] args){
        DiaryEntryUITest l = new DiaryEntryUITest(new User("null", "null"));
        // booster.showTable(d.getDataList(), new String[]{"Title", "Last Edited"}, "Entries");
    }

    public DiaryEntryUITest(User u){
        EntryListDialog.showEntires(u);
        // this.login();
    }

    // public void login(){
    //     u.signIn(login.showDialog());
    // }

    private int getMiddleX(){
        return (Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
    }

    private int getMiddleY(){
        return (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
    }
}
