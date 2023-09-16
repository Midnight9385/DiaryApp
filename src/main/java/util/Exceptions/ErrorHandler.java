package util.Exceptions;
import javax.swing.JOptionPane;

public class ErrorHandler {
    public static void sendErrorMessage(String title, String message){
        if(JOptionPane.showOptionDialog(null,message,"ERROR: "+title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,null, new String[]{"Continue", "Exit"}, null)
                                        ==1 /* exit option */){
                                            System.exit(0);
                                        }
                                        else{
                                            return;
                                        }
    }
    public static void sendFatalErrorMessage(String title, String message){
        JOptionPane.showOptionDialog(null,message,"FATAL ERROR: "+title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,null, new String[]{"Exit"}, null);
        System.exit(1);                                
    }

    public static Object sendErrorMessageWithRetry(String title, String message){
        switch(JOptionPane.showOptionDialog(null,message,"ERROR: "+title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,null, new String[]{"Continue", "Retry", "Exit"}, null)){
            case 0: return false; //continue option
            case 1: return true; //retry option
            case 2: return null; //exit option
        }
        return false;
    }
}
