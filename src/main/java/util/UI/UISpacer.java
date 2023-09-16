package util.UI;

import java.awt.Toolkit;

public class UISpacer {
    public static int getMiddleX(int width){
        return (Toolkit.getDefaultToolkit().getScreenSize().width-width)/2;
    }

    public static int getMiddleY(int height){
        return (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2;
    }
}
