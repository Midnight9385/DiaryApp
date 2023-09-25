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
import java.util.Random;

public class KeyList implements Serializable{
    private static final long serialVersionUID = 2086384830709399376L;

    private String password;
    private ArrayList<String> keys = new ArrayList<>();

    private static KeyList instance;

    public static void main(String[] args){
        // KeyList d = KeyList.getInstance();
        // System.out.println(d.keys.get(d.keys.indexOf(d.password)+1));
        // d.generateKeys();
        // d.saveData();
    }

    //#region constructors
    public static KeyList getInstance(){
        if(instance == null){
            instance = load();
        }
        return instance;
    }

    private KeyList(){
    }
    //#endregion

    //#region serialization
    private static void checkFilesExist(){
        
        String folderPath = new File("").getAbsolutePath()+File.separator+"src"+File.separator+"main"+File.separator+
                                        "java"+File.separator+"util"+File.separator+"API";
        String filePath = folderPath+File.separator+"keys.txt";

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

    public static void saveData(){
        String filePath = new File("").getAbsolutePath()+File.separator+"src"+File.separator+"main"+File.separator+
                                        "java"+File.separator+"util"+File.separator+"API"+File.separator+"keys.txt";
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(KeyList.getInstance());
            so.flush();
            FileWriter writer = new FileWriter(filePath);
            String code = new String(Base64.getEncoder().encode(bo.toByteArray()));
            // System.out.println("code: "+code);
            writer.write(code);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyList load(){
        String filePath = new File("").getAbsolutePath()+File.separator+"src"+File.separator+"main"+File.separator+
                                        "java"+File.separator+"util"+File.separator+"API"+File.separator+"keys.txt";
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
                return (KeyList) o;
            }
            else{
                reader.close();
                return new KeyList();
            }
        }catch(Exception i){
            i.printStackTrace();
            return new KeyList();
        }
    }
    //#endregion

    //#region key generation
    private void generateKeys(){
        Random r = new Random();
        ArrayList<String> s = new ArrayList<>();
        s.add(getAccessKey());
        for(int i=0; i<49; i++){
            s.add(constructKey());
        }

        ArrayList<String> p = new ArrayList<>();
        for(int i=0; i<50; i++){
            int index = r.nextInt(s.size());
            if(s.get(index).equals(getAccessKey())){
               password = p.get(i-1);
            }
            p.add(s.get(index));
            s.remove(index);
            
        }

        keys = p;
    }

    private static String getRandomCharacter(boolean forceCapital){
        Random r = new Random();
        if(r.nextInt(100)>75 || forceCapital){
            return String.valueOf((char)(r.nextInt(65,91)));
        }else if(r.nextInt(100)>75){
            return String.valueOf((char)(r.nextInt(97,123)));
        }
        else if(r.nextInt(100)>95){
            return String.valueOf(r.nextInt(0,10));
        }
        else{
            return getRandomCharacter(false);
        }
    }

    private static String constructKey(){
        String s = "";
        for(int i=0; i<69; i++){
            switch(i){
                case 0:
                case 1: s+=getRandomCharacter(true); break;
                case 2:
                case 25: s+="."; break;
                case 9:
                case 48: s+="-"; break;
                case 29:
                case 55: s+="_"; break;
                default: s+=getRandomCharacter(false);
            }
        }
        return s;
    }
    //#endregion

    public String getAccessKey(){
        return keys.get(keys.indexOf(password)+1);
    }    
}