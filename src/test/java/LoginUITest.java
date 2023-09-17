import java.awt.Toolkit;

import App.DiaryApp;
import UserSystem.User;
import UserSystem.UserInterface;
import de.milchreis.uibooster.UiBooster;
import util.UI.Dialogs.LoginDialog;


public class LoginUITest {
    final static UiBooster booster = new UiBooster();
    final static LoginDialog login = new LoginDialog(true, "Login to App", "Enter Username And Password", 
                                                     "Username: ", "Password: ", 
                                                     "Login", "Exit", 
                                                     "C:\\Users\\Zachary\\Documents\\GitHub\\diaryapp\\src\\main\\resources\\DiaryAppIcon.png");
        
    private final int width = 800;
    private final int height = 400;     
    
    private static User user[] = new User[1];
    private UserInterface u = new UserInterface();
    public static void main(String[] args){
        LoginUITest l = new LoginUITest(new UserInterface());
    }

    public LoginUITest(UserInterface u){
        this.u = u;
        login.setBounds(getMiddleX(), getMiddleY(), width, height);

        // login.add("createUser", makeButton("create", a -> DiaryApp.createAccount(createAccountDialog().showDialog(), user)));
        // login.add("forgotPassword", makeButton("forgot password", b -> DiaryApp.forgotPassword()));

        new DiaryEntryUITest(this.login(false));
    }

    public User login(boolean failed){
        if(failed){
            booster.showInfoDialog("invalid username or password please retry");
        }
        u.signIn(login.showDialog(), this.user); //uses psuedo pass by reference since it is easier in this case
        return (this.user[0]!=null)?this.user[0]:login(true);
    }

    private int getMiddleX(){
        return (Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
    }

    private int getMiddleY(){
        return (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
    }

    private static LoginDialog createAccountDialog(){
        return new LoginDialog(false,"Create Account", "Enter A Username And Password", 
                                                     "Username: ", "Password: ", 
                                                     "Create", "Cancel", 
                                                     "C:\\Users\\Zachary\\Documents\\GitHub\\diaryapp\\src\\main\\resources\\DiaryAppIcon.png");
    }

    public static void createAccount(){
        DiaryApp.createAccount(createAccountDialog().showDialog(), user);
    }

    public static User getUser(){
        return user[0];
    }

}
