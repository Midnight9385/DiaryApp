package util.API;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;

public class SerializedData implements Serializable{
    public ArrayList<String> keys = new ArrayList<>();
    public String password;

    private static SerializedData instance;

    public static void main(String[] args){
        SerializedData d = getInstance();

        System.out.println(d.keys.get(0));
        // d.keys.add("bitch istg");

        // SerializedData.saveData();
        // checkFilesExist();
        // KeyList instance = KeyList.getInstance();
        // instance.data = getInstance();
    
        // if (KeyList.madeNew||true) {
        //     checkFilesExist();
        //     instance.generateKeys();
        //     SerializedData.saveData();
        // } else {
        //     System.out.println(instance.data.password);
        //     System.out.println(instance.decrypt(instance.data.password));
        // }
    }

    private static void checkFilesExist(){
        
        String folderPath = new File("").getAbsolutePath();
        String filePath = folderPath + File.separator + "keys.txt";

        File file = new File(filePath);

        if (file.exists()) {
            //if the file already exist no need to do anything further
        } else {
            // Create the folder if it doesn't exist
            File folder = new File(folderPath);
            if (!folder.exists()) {
                if (folder.mkdirs()) {
                    //folder created successfully
                } else {
                    //if the folder wasn't successfully created then you cannot use this app
                    //so exit and display error message
                }
            }

            try {
                // Create the file
                if (file.createNewFile()) {
                    //file made successfully
                } else {
                    //while technically the app can still be used without the file it is used for critical functions
                    //and thus it not being created will result in the app closing and displaying and error message
                }
            } catch (IOException e) {
                //if there is some exception it is safe to assume the file cannot be used and thus the app cannot be used
            }
        }
    }

    public static SerializedData getInstance(){
        if(instance == null){
            instance = getKeyList();
        }
        return instance;
    }

    private static final long serialVersionUID = 1L;

    public static void saveData(){
        String filePath = new File("").getAbsolutePath()+File.separator+"src"+File.separator+"main"+File.separator+
                                        "java"+File.separator+"util"+File.separator+"API"+File.separator+"keys.txt";
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(SerializedData.getInstance());
            so.flush();
            FileWriter writer = new FileWriter(filePath);
            String code = new String(Base64.getEncoder().encode(bo.toByteArray()));
            System.out.println("code: "+code);
            writer.write(code);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SerializedData getKeyList(){
        String filePath = new File("").getAbsolutePath()+File.separator+"keys.txt";
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
                return (SerializedData) o;
            }
            else{
                reader.close();
                return new SerializedData();
            }
        }catch(Exception i){
            i.printStackTrace();
            return new SerializedData();
        }
    }
}
