import App.DiaryApp;
import util.UI.Dialogs.LoginDialog;

public class Main {
    
    public static void main(String[] args) {
      DiaryApp.createInterfaces();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new DiaryApp();
                LoginDialog.start();
            }
        });
    }
}