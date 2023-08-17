package CRUD;

import java.util.ArrayList;
import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class stores all the journal entries as objects of DataClass
 */
public class DataStorage{
    private ArrayList<DataClass<?>> dataStorage;

    public static void main(String[] args) {
        System.out.println(new File("").getAbsolutePath()+"\\CRUD\\Data\\Data.txt");
    }

    /**
     * @param serial the serial to contruct the dataStorage object with or null if you want it empty
     */
    public DataStorage(String serial){
        dataStorage = (serial!=null?contructDataFromString(serial):new ArrayList<DataClass<?>>());
    }

    /**
     * used in {@link App.DiaryApp} to save the serial string to the user
     * @return the serial string of the data list
     */
    public String close(){
        return sendDataSerial();
    }

    /**
     * creates an entry in the data list
     * @param data the data inside the object (the entry itself)
     * @param name the name of the entry
     */
    public <type> void createData(type data, String name){
        // System.out.println("before size: "+dataStorage.size());
        dataStorage.add(new DataClass<type>(data, name));
        // System.out.println("after size: "+dataStorage.size());
    }

    /**
     * @param index the index in the data list to return
     * @return the DataClass object specified
     */
    public DataClass<?> readData(int index){
        return index<dataStorage.size()?dataStorage.get(index):new DataClass<>("error: index out of bounds", "");
    }

    /**
     * retrives the journal entry with the specified name
     * @param name the name of the entry to read
     * @return the DataClass object, if no name matches returns an empty object with data of "error"
     */
    public DataClass<?> readData(String name){
        for (DataClass<?> data : dataStorage) {
            if(data.getName().equals(name)){
                return data;
            }
        }

        return new DataClass<>("error", "");
    }

    /**
     * updates the data inside the specified entry
     * @param data2 the new data
     * @param name the entry to update
     */
    public void updateData(Object data2, String name){
        int index = -1;
        for (DataClass<?> data : dataStorage) {
            if(data.getName().equals(name)){
                index = dataStorage.indexOf(data);
            }
        }
        if(index!=-1){
            updateData(index, data2);
        }

        // return new DataClass<>("error: no file with name found", "");
    }

    public void updateData(int index, Object data){
        createData(data, dataStorage.get(index).getName());
        deleteData(index);
    }

    public void deleteData(int index){
        dataStorage.remove(index);
    }

    /**
     * deletes the entry with the specified name
     * @param name the name of the entry to delete
     */
    public void deleteData(String name){
        for (DataClass<?> data : dataStorage) {
            if(data.getName().equals(name)){
                // System.out.println("deleted :: "+data.getName());
                dataStorage.remove(data);
                return;
            }
        }
    }

    public ArrayList<DataClass<?>> getSavedData(){
        try{
            FileReader reader = new FileReader(new File("").getAbsolutePath()+"\\CRUD\\Data\\Data.txt");
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
                return (ArrayList<DataClass<?>>) o;
            }
            else{
                reader.close();
                return new ArrayList<DataClass<?>>();
            }
        }catch(Exception i){
            i.printStackTrace();
            return new ArrayList<DataClass<?>>();
        }
    }

    /**
     * constructs the data list from a serial string
     * @param serial the serial to contruct the list from
     * @return the contructed list or an empty list if the serial isn't valid
     */
    public ArrayList<DataClass<?>> contructDataFromString(String serial){
        try{
            if(!serial.equals("")){
                byte[] b = serial.getBytes();
                ByteArrayInputStream bo = new ByteArrayInputStream(Base64.getDecoder().decode(b));
                ObjectInputStream so = new ObjectInputStream(bo);
                Object o = so.readObject();
                return (ArrayList<DataClass<?>>) o;
            }
            else{
                return new ArrayList<DataClass<?>>();
            }
        }catch(Exception i){
            i.printStackTrace();
            return new ArrayList<DataClass<?>>();
        }
    }

    public void saveData(){
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(dataStorage);
            so.flush();
            FileWriter writer = new FileWriter(new File(new File("").getAbsolutePath()+"\\CRUD\\Data\\Data.txt"));
            String code = new String(Base64.getEncoder().encode(bo.toByteArray()));
            writer.write(code);
            writer.flush();
            // System.out.println("successful save");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * used to get the serial string of the entry list
     * @return the entry list serial string
     */
    public String sendDataSerial(){
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(dataStorage);
            so.flush();
            String code = new String(Base64.getEncoder().encode(bo.toByteArray()));
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * @return the list of strings from the title and date last changed of each entry
     */
    public String[] getList(){
        ArrayList<String> output = new ArrayList<>();
        // System.out.println(dataStorage.size());
        for (int i = 0; i < dataStorage.size(); i++) {
            output.add(dataStorage.get(i).getName()+" -\t last changed:"+dataStorage.get(i).getDate()+"\n");
        }
        return output.toArray(new String[0]); // Use toArray(new String[0])
    }    

}