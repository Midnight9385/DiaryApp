package util.API;

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
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class KeyList {
    public SerializedData data = new SerializedData();
    private static KeyList instance;
    public static boolean madeNew = false;

    private transient Cipher cipher;
    private transient KeySpec ks;
    private transient SecretKeyFactory skf;
    private SecretKey key;

    static String test;

    static Random r = new Random();

    public static KeyList getInstance(){
        if(instance == null){
            instance = new KeyList();
            // instance.data = getKeyList();
        }
        return instance;
    }

    private KeyList(){
        madeNew = true;
        setUpEncryption();
    }

    public static void main(String[] args){
        checkFilesExist();
        KeyList instance = KeyList.getInstance();
        System.out.println(madeNew);
    
        if (madeNew||true) {
            checkFilesExist();
            instance.generateKeys();
            saveData();
        } else {
            System.out.println(instance.data.password);
            System.out.println(instance.decrypt(instance.data.password));
        }
    }

    public String getKey(){
        return data.keys.get(data.keys.indexOf(decrypt(data.password)));
    }

    private static void saveData(){
        String rawP = instance.data.password;
        do{
            if(instance.key == null){
                instance.setUpEncryption();
            }
            instance.data.password = rawP;
            instance.data.password = instance.encrypt(rawP);
            System.out.println("encrypted: "+instance.data.password);
            System.out.println("decrypted: "+instance.decrypt(instance.data.password));
            System.out.println("actual: "+rawP);
        }while(!rawP.equals(instance.decrypt(instance.data.password)));

        // SerializedData.saveData(instance.data);
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

    private void setUpEncryption(){
        try{
            String myEncryptionKey = "ThisIsSpartaThisIsSparta";
            byte[] arrayBytes = myEncryptionKey.getBytes("UTF8");
            ks= new DESedeKeySpec(arrayBytes);
            skf = SecretKeyFactory.getInstance("DESede");
            // System.out.println(key.getAlgorithm());
            if(key == null || !key.getAlgorithm().equals("DESede")){
                // System.out.println("new key");
                key= skf.generateSecret(ks);
            }    
            cipher = Cipher.getInstance("DESede");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void generateKeys(){
        ArrayList<String> s = new ArrayList<>();
        s.add(getAccessKey());
        for(int i=0; i<49; i++){
            s.add(constructKey());
        }

        ArrayList<String> p = new ArrayList<>();
        for(int i=0; i<50; i++){
            int index = r.nextInt(s.size());
            if(s.get(index).equals(getAccessKey())){
                data.password = p.get(i-1);
            }
            p.add(s.get(index));
            s.remove(index);
            
        }

        data.keys = p;
    }

    private static String getRandomCharacter(boolean forceCapital){
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

    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            if(key==null){
                getInstance().setUpEncryption();
            }
            cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes("UTF8");
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.getEncoder().encode(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            if(key==null){
                getInstance().setUpEncryption();
            }
            cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}



// class TrippleDes {

//     private static final String UNICODE_FORMAT = "UTF8";
//     public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
//     private KeySpec ks;
//     private SecretKeyFactory skf;
//     private Cipher cipher;
//     byte[] arrayBytes;
//     private String myEncryptionKey;
//     private String myEncryptionScheme;
//     SecretKey key;

//     private static TrippleDes instance;

//     public static TrippleDes getInstance(){
//         if(instance == null){
//             try {
//                 instance = new TrippleDes();
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         }
//         return instance;
//     }

//     private TrippleDes() throws Exception {
//         myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
//         arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
//         ks = new DESedeKeySpec(arrayBytes);
//         skf = SecretKeyFactory.getInstance(myEncryptionScheme);
//         cipher = Cipher.getInstance(myEncryptionScheme);
//         key = skf.generateSecret(ks);
//     }

// }