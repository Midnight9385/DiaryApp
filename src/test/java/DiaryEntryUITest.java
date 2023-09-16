import java.awt.Toolkit;

import CRUD.DataInterface;
import UserSystem.User;
import UserSystem.UserInterface;
import de.milchreis.uibooster.UiBooster;
import de.milchreis.uibooster.components.LoginDialog;


public class DiaryEntryUITest {
    final static UiBooster booster = new UiBooster();
    final static LoginDialog login = new LoginDialog("Login to App", "Enter Username And Password", 
                                                     "Username: ", "Password: ", 
                                                     "Login", "Exit", 
                                                     "C:\\Users\\Zachary\\Documents\\GitHub\\diaryapp\\src\\main\\resources\\DiaryAppIcon.png");
    static UserInterface u = null;   
    static DataInterface d = new DataInterface(); 
    private final int width = 800;
    private final int height = 400;        
    public static void main(String[] args){
        DiaryEntryUITest l = new DiaryEntryUITest(null);
        // booster.showTable(d.getDataList(), new String[]{"Title", "Last Edited"}, "Entries");
    }

    public DiaryEntryUITest(User u){
        d.updateDataStorage(u.getSerial());
        login.setBounds(getMiddleX(), getMiddleY(), width, height);
        booster.showTable(d.getDataList(), new String[]{"Title", "Last Edited"}, "Entries");
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
