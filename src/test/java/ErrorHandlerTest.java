import App.DiaryApp;
import CRUD.DataStorage;
import UserSystem.User;
import UserSystem.UserInterface;

//this class is used to test the error handler system
public class ErrorHandlerTest {
    public static void main(String[] args) {
        //test DataStorage error handlers
        DataStorage d = new DataStorage(null);
        d.setErrorTestStatus(true);
        d.sendDataSerial();
        d.contructDataFromString(null);
        d.saveData();
        d.getSavedData();

        //test UserStorage error handlers
        UserInterface u = new UserInterface();
        u.setErrorTestStatus(true);
        u.exit("", new User("", ""), false);
        u.testGetSavedData();

        //test DiaryApp error handler
        DiaryApp.error();
    }
}
