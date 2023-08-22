import javax.swing.JOptionPane;

public class ErrorMessage {
    public static void sendErrorMessage(String title){
        if(JOptionPane.showOptionDialog(null,"",title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,null, null,null)
                                        ==JOptionPane.CANCEL_OPTION){
                                            System.exit(0);}
                                        else{
                                            return;
                                        }
    }
}
