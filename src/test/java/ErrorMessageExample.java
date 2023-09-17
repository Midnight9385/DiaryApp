import javax.swing.JOptionPane;

import util.Exceptions.ErrorHandler;

public class ErrorMessageExample {
    public static void main(String[] args) {
        generalUse();
    }

    private static void useInDiaryApp(){
        JOptionPane.showMessageDialog(null, "test error message", "test", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    private static void generalUse(){
        ErrorHandler.sendErrorMessage("test", "test error message");
    }
}
