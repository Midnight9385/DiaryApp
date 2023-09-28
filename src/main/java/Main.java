import com.formdev.flatlaf.FlatLaf;

import App.DiaryApp;
import util.UI.Dialogs.LoginDialog;
import util.UI.Source.model.options.themes.OceanDarkLaf;
import util.UI.Source.model.options.themes.PurpleDarkLaf;

public class Main {
    
    public static void main(String[] args) {
      FlatLaf.registerCustomDefaultsSource( "src\\main\\java\\util\\UI\\Source\\model\\options\\themes");

      DiaryApp.createInterfaces();
      PurpleDarkLaf.setup();
      OceanDarkLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new DiaryApp();
                LoginDialog.start();
            }
        });
    }
}