import App.DiaryApp;

public class Main {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DiaryApp();
            }
        });
    }
}