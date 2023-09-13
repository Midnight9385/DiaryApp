package UserSystem;

import java.io.Serializable;

/**
 * This class holds all user data like username and password as well as the serialized string of their journal entries
 * from the DataStorage class
 */ 
public class User implements Serializable{
    private String username;
    private String password;
    private String dataString;
    private String email;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.dataString = null;
    }

    public void resetPasswordEmail(){

    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSerial(){
        return dataString;
    }

    public void setSerial(String serial){
        this.dataString = serial;
    }

    private boolean checkPassword(String that){
        return password.equals(that);
    }

    private boolean checkUsername(String that){
        return username.equals(that);
    }

    public boolean checkCredentials(String username, String password){
        return checkUsername(username) && checkPassword(password);
    }
}
