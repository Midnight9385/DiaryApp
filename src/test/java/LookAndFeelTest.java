import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import util.UI.Source.UiBooster;

public class LookAndFeelTest {
    public static void main(String[] args) {
        for(LookAndFeelInfo u: UIManager.getInstalledLookAndFeels()){
            System.out.println(u.getName());
        }

        // UiBooster u = new
    }
}
