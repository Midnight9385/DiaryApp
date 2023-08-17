package UserSystem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;

//this class is used to store all the users
//again because of the nature of user data this class is only accessable via the UserInterface class
class UserStorage {
    ArrayList<User> users = getSavedData();

    public static void main(String[] args) {
        System.out.println(new File("").getAbsolutePath());
    }


    public void close(boolean close){
        saveData();
        if(close)
            System.exit(0);
    }

    public User createUser(String username, String name){
        users.add(new User(username, name));
        return users.get(users.size()-1);
    }

    public User changePassword(int index, String username, String password){
        createUser(username, password);
        deleteUser(index);
        return users.get(users.size()-1);
    }

    public void deleteUser(int index){
        users.remove(index);
    }

    public void setSerialString(User u, String s){
        users.get(users.indexOf(u)-1).setSerial(s);
    }

    public User checkSignIn(String username, String password){
        for(User u : users){
            if(u.checkCredentials(username, password)){
                return u;
            }    
        }
        System.out.println("no match");
        return null;
    }

    public ArrayList<User> getSavedData(){
        String filePath = new File("").getAbsolutePath()+File.separator+"Data"+File.separator+"UserData.txt";
        try{
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
                //  try {
                //     // Create a FileWriter to write to the file
                //     FileWriter fileWriter = new FileWriter(new File("").getAbsolutePath()+"\\DiaryApp\\UserSystem\\User\\Data\\UserData.txt");

                //     // Write the content to the file
                //     fileWriter.write(new File("").getAbsolutePath()+"\\DiaryApp\\UserSystem\\User\\Data\\UserData.txt");

                //     // Close the FileWriter
                //     fileWriter.close();

                //     System.out.println("File created and saved successfully.");
                // } catch (IOException e) {
                //     System.out.println("An error occurred: " + e.getMessage());
                // }
                reader.close();
                return new ArrayList<User>();
            }
        }catch(Exception i){
            i.printStackTrace();
            return new ArrayList<User>();
        }
    }

    public void saveData(){;
        String filePath = new File("").getAbsolutePath()+File.separator+"Data"+File.separator+"UserData.txt";
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(users);
            so.flush();
            FileWriter writer = new FileWriter(filePath);
            String code = new String(Base64.getEncoder().encode(bo.toByteArray()));
            writer.write(code);
            writer.flush();
            System.out.println("successful save");
        } catch (Exception e) {
            // try {
            //     // Create a FileWriter to write to the file
            //     FileWriter fileWriter = new FileWriter(new File("").getAbsolutePath()+"\\DiaryApp\\UserSystem\\User\\Data\\UserData.txt");

            //     // Write the content to the file
            //     fileWriter.write(new File("").getAbsolutePath()+"\\DiaryApp\\UserSystem\\User\\Data\\UserData.txt");

            //     // Close the FileWriter
            //     fileWriter.close();

            //     System.out.println("File created and saved successfully.");
            // } catch (IOException c) {
            //     System.out.println("An error occurred: " + c.getMessage());
            // }
            e.printStackTrace();
        }
    }
}
