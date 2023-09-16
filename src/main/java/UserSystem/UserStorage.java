package UserSystem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;

import de.milchreis.uibooster.components.Notification;
import de.milchreis.uibooster.components.TextAreaDialog;
import util.Exceptions.ErrorHandler;
import util.Exceptions.TestException;

/**
 * this class is used to store all the users
 * again because of the nature of user data this class is only accessable via the UserInterface class
 */ 
class UserStorage {
    ArrayList<User> users = getSavedData();
    private boolean testErrorHandler = false;

    public static void main(String[] args) {
        System.out.println(new File("").getAbsolutePath());
    }

    /**
     * sets the status of the boolean flag to test error handlers
     * @param status true to test or false to not test
     */
    public void setErrorTestStatus(boolean status){
        this.testErrorHandler = status;
    }

    /**
     * tests the {@link UserStorage#getSavedData()} method because it is used at instantiation of object instead of called after
     */
    public void testGetSavedData(){
        getSavedData();
    }

    /**
     * This method will save the list of users and then terminate the program depending on what you pass in
     * @param close whether or not to close the program
     */
    public void close(boolean close){
        saveData();
        if(close)
            System.exit(0);
    }

    /**
     * creates a new user
     * @param username the username of the user
     * @param password the password of the user
     * @return the newly created user
     */
    public User createUser(String username, String password){
        users.add(new User(username, password));
        return users.get(users.size()-1);
    }

    /**
     * changes the password of the user
     * @param index the index in the users list to delete
     * @param username the username of the user
     * @param password the updated password of the uer
     * @return the newly updated user
     */
    public User changePassword(int index, String username, String password){
        createUser(username, password);
        deleteUser(index);
        return users.get(users.size()-1);
    }

    /**
     * deletes the user in the user list at the index
     * @param index the index of the user to delete
     */
    public void deleteUser(int index){
        users.remove(index);
    }

    /**
     * sets the serial string of the user
     * @param u the user to set the serial string of
     * @param s the serial string
     */
    public void setSerialString(User u, String s){
        users.get(users.indexOf(u)-1).setSerial(s);
    }

    /**
     * trys signing in with given info
     * @param username the attempted username
     * @param password the attempted password
     * @return the user that matchs the username and password or null if there is no match
     */
    public User checkSignIn(String username, String password){
        for(User u : users){
            if(u.checkCredentials(username, password)){
                return u;
            }    
        }
        return null;
    }

    /**
     * loads the data from the designated data .txt file
     * @return the constructed user list from the serial string in the file
     */
    public ArrayList<User> getSavedData(){
        String filePath = new File("").getAbsolutePath()+File.separator+"Data"+File.separator+"UserData.txt";
        try{
            if(testErrorHandler){
                throw new TestException();
            }

            FileReader reader = new FileReader(filePath);
            int content;
            String n = "";
            while((content=reader.read()) !=-1){
                n+=(char)content;
            }
            if(!n.equals("")){
                byte[] b = n.getBytes();
                ByteArrayInputStream bo = new ByteArrayInputStream(Base64.getDecoder().decode(b));
                ObjectInputStream so = new ObjectInputStream(bo);
                Object o = so.readObject();
                reader.close();
                return (ArrayList<User>) o;
            }
            else{
                reader.close();
                return new ArrayList<User>();
            }
        }catch(Exception i){
            if(ErrorHandler.sendErrorMessageWithRetry("Read Error", "there was an error reading data, you can retry, exit, or continue without loading users").equals(true)){
                getSavedData();
            }
            // i.printStackTrace();
            return new ArrayList<User>();
        }
    }

    /**
     * saves the data of the user list and writes the serial string to the designated data .txt file
     */
    public void saveData(){;
        String filePath = new File("").getAbsolutePath()+File.separator+"Data"+File.separator+"UserData.txt";
        try {
            if(testErrorHandler){
                throw new TestException();
            }

            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(users);
            so.flush();
            FileWriter writer = new FileWriter(filePath);
            String code = new String(Base64.getEncoder().encode(bo.toByteArray()));
            writer.write(code);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            if(ErrorHandler.sendErrorMessageWithRetry("Save Error", "there was an error saving the data, you can retry or continue without saving").equals(true)){
                saveData();
            }
            // e.printStackTrace();
        }
    }

    public void checkEmails(String email){
        for (User user : users) {
            if(user.checkEmail(email)){
                // System.out.println("found user and sent email");
                user.sendResetPasswordEmail();
                return;
            }
        }
        Notification n = new Notification();
        n.display("no matches", null);
    }
}
