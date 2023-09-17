package util.UI.Dialogs;

import java.awt.Toolkit;

import App.DiaryApp;
import UserSystem.User;


public class AccountCreatorDialog {
        
    private static final int width = 800;
    private static final int height = 400;     
    
    private static User user[] = new User[1];

    private static int getMiddleX(){
        return (Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
    }

    private static int getMiddleY(){
        return (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
    }

    private static LoginDialog createAccountDialog(){
        LoginDialog l = new LoginDialog(false,"Create Account", "Enter A Username And Password for Account", 
                                                     "Username: ", "Password: ", 
                                                     "Create", "Cancel", 
                                                     "C:\\Users\\Zachary\\Documents\\GitHub\\diaryapp\\src\\main\\resources\\DiaryAppIcon.png");
        l.setBounds(getMiddleX(), getMiddleY(), width, height);
                                          
        return l;
    }

    public static void createAccount(){
        DiaryApp.createAccount(createAccountDialog().showDialog(), user);
    }

    public static User getUser(){
        return user[0];
    }

}
