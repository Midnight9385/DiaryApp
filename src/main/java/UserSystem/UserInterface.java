package UserSystem;

//this class is used to interface with the UserStorage class
//because user info should be kept safe you cannot access the UserStorage object or class
public class UserInterface {
    private  UserStorage d = new UserStorage();


    public User createUser(String u, String p){
        return d.createUser(u, p);
    }

    public  User signIn(String username, String password){
        return d.checkSignIn(username, password);
    }


    public void exit(String serial, User user,boolean close) {
        user.setSerial(serial);
        d.close(close);
        // System.out.println("successful save");
    }
}
